package bd.uber.zafor.controller.operationsmanager;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.operationsmanager.Discount;
import bd.uber.zafor.model.operationsmanager.PassengerRatingDiscountCondition;
import bd.uber.zafor.model.operationsmanager.PromotionalCampaign;
import bd.uber.zafor.model.operationsmanager.RideDistanceCondition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class CampaignDetailedViewController implements Initializable {
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Text startDateText;
    @FXML
    private Text endDateText;
    @FXML
    private VBox discountsVBox;
    @FXML
    public TextField newDiscountCodeTextField;
    @FXML
    public TextField discountAmountTextField;
    @FXML
    public ComboBox<DiscountConditionType> discountConditionComboBox;
    @FXML
    public TextField conditionValueTextField;

    private volatile PromotionalCampaign promotionalCampaign;
    private final ObservableList<Integer> discountObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        discountConditionComboBox.getItems().addAll(DiscountConditionType.values());
    }

    public void setInitData(PromotionalCampaign promotionalCampaign) {
        this.promotionalCampaign = promotionalCampaign;
        discountObservableList.addAll(promotionalCampaign.getDiscountIdList());
        titleLabel.setText(promotionalCampaign.getCampaignName());
        descriptionLabel.setText(promotionalCampaign.getDescription());
        startDateText.setText(promotionalCampaign.getStartDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        endDateText.setText(promotionalCampaign.getEndDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));

        loadDiscounts();
        configureDiscountListUpdate();
        Platform.runLater(this::configureOnClose);
    }

    @FXML
    private void onAddDiscount() {
        String code = newDiscountCodeTextField.getText();
        if (code == null || code.isEmpty()) {
            Util.getInstance().showWarningMessage("Invalid discount code!");
            return;
        }

        float amount;
        try {
            amount = Float.parseFloat(discountAmountTextField.getText());
            if (amount <= 0) {
                Util.getInstance().showWarningMessage("Amount must be greater than 0!");
                return;
            }
        } catch (NullPointerException | NumberFormatException ignored) {
            Util.getInstance().showWarningMessage("Invalid amount!");
            return;
        }

        DiscountConditionType type = discountConditionComboBox.getValue();
        if (type == null) {
            Util.getInstance().showWarningMessage("Invalid condition type!");
            return;
        }

        float conditionValue;
        try {
            conditionValue = Float.parseFloat(conditionValueTextField.getText());
            if (conditionValue <= 0) {
                Util.getInstance().showWarningMessage("Condition value must be greater than 0!");
                return;
            }
        } catch (NullPointerException | NumberFormatException ignored) {
            Util.getInstance().showWarningMessage("Invalid condition value!");
            return;
        }

        Util.getInstance().getWorkers().execute(() -> {
            Discount discount = new Discount(
                    promotionalCampaign.getDiscountIdList().size() + 1,
                    code,
                    amount,
                    type.equals(DiscountConditionType.RATING) ? new PassengerRatingDiscountCondition(conditionValue) : new RideDistanceCondition(conditionValue)
            );

            if (Util.getInstance().getDb().addObject(discount, BinFilePath.DISCOUNT)) {
                Platform.runLater(() -> {
                    Util.getInstance().showSuccessMessage("Discount added successfully.");
                    discountObservableList.add(discount.getDiscountId());
                });
                addDiscountView(discount);
            } else {
                Platform.runLater(() ->
                        Util.getInstance().showSuccessMessage("Failed to add discount!")
                );
            }
        });
    }

    private void loadDiscounts() {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ignored) {

            }
            Util.getInstance()
                    .getDb()
                    .<Discount>getObjectList(
                            BinFilePath.DISCOUNT,
                            d -> promotionalCampaign.getDiscountIdList().stream().anyMatch(id -> id == d.getDiscountId())
                    )
                    .forEach(this::addDiscountView);
        });
    }

    private void addDiscountView(Discount discount) {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.DISCOUNT_VIEW);
                AnchorPane pane = loader.load();
                ((DiscountViewController) loader.getController()).setInitData(discount, discountObservableList);
                Platform.runLater(() ->
                        discountsVBox.getChildren().add(pane)
                );
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void configureDiscountListUpdate() {
        discountObservableList.addListener((ListChangeListener<? super Integer>) c -> {
            if (c.next()) {
                if (c.wasRemoved()) {
                    promotionalCampaign.getDiscountIdList().removeIf(id -> id == c.getRemoved().get(0));
                    discountsVBox.getChildren().clear();
                    loadDiscounts();
                    return;
                }

                if (c.wasAdded()) {
                    promotionalCampaign.getDiscountIdList().add(c.getAddedSubList().get(0));
                }
            }
        });
    }

    private void configureOnClose() {
        titleLabel.getScene().getWindow().setOnCloseRequest(this::updatePromotionalCampaignOnFile);
        titleLabel.getScene().getWindow().setOnHiding(this::updatePromotionalCampaignOnFile);
    }

    private void updatePromotionalCampaignOnFile(WindowEvent event) {
        Util.getInstance().getWorkers().execute(() ->
                Util.getInstance().getDb().updateObjectFile(
                        promotionalCampaign,
                        BinFilePath.PROMOTIONAL_CAMPAIGN,
                        p -> p.getCampaignId() == promotionalCampaign.getCampaignId(),
                        false
                )
        );
    }
}