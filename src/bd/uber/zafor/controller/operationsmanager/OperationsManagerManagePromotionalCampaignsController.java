package bd.uber.zafor.controller.operationsmanager;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.operationsmanager.OperationsManager;
import bd.uber.zafor.model.operationsmanager.PromotionalCampaign;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class OperationsManagerManagePromotionalCampaignsController implements Initializable {
    @FXML
    private VBox promotionalCampaignsVBox;
    @FXML
    private TextField newCampaignNameTextField;
    @FXML
    private TextArea newCampaignDescriptionTextArea;
    @FXML
    private DatePicker newCampaignStartDate;
    @FXML
    private DatePicker newCampaignEndDate;

    private OperationsManager operationsManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newCampaignStartDate.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
        newCampaignEndDate.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
        loadCampaigns();
    }

    public void setInitData(OperationsManager operationsManager) {
        this.operationsManager = operationsManager;
    }

    @FXML
    private void onCreateCampaign() {
        String name = newCampaignNameTextField.getText();
        if (name == null || name.isEmpty()) {
            Util.getInstance().showWarningMessage("Campaign name cannot be empty!");
            return;
        }

        String description = newCampaignDescriptionTextArea.getText();
        if (description == null || description.isEmpty()) {
            Util.getInstance().showWarningMessage("Campaign description cannot be empty!");
            return;
        }

        LocalDate startDate = newCampaignStartDate.getValue();
        LocalDate endDate = newCampaignEndDate.getValue();
        if (startDate == null || endDate == null) {
            Util.getInstance().showWarningMessage("You must include both the dates!");
            return;
        }

        Util.getInstance().getWorkers().execute(() -> {
            PromotionalCampaign campaign;
            if ((campaign = operationsManager.createPromotionalCampaign(name, description, startDate, endDate)) != null) {
                Platform.runLater(() -> {
                    Util.getInstance().showSuccessMessage("New campaign added successfully.");
                    resetFields();
                    addPromotionalCampaignView(campaign);
                });
            } else {
                Platform.runLater(() -> {
                    Util.getInstance().showError("Failed to add the new campaign!");
                });
            }
        });
    }

    private void loadCampaigns() {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ignored) {

            }
            Util.getInstance()
                    .getDb().<PromotionalCampaign>getObjectList(
                            BinFilePath.PROMOTIONAL_CAMPAIGN,
                            c -> c.getEndDate().isAfter(LocalDate.now()) && operationsManager.getPromotionalCampaignIdList().stream().anyMatch(id -> id == c.getCampaignId())
                    ).forEach(this::addPromotionalCampaignView);
        });
    }

    private void resetFields() {
        newCampaignNameTextField.setText("");
        newCampaignDescriptionTextArea.setText("");
        newCampaignStartDate.setValue(null);
        newCampaignEndDate.setValue(null);
    }

    private void addPromotionalCampaignView(PromotionalCampaign campaign) {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.CAMPAIGN_VIEW);
            AnchorPane pane = loader.load();
            ((CampaignViewController) loader.getController()).setInitData(campaign);
            Platform.runLater(() ->
                    promotionalCampaignsVBox.getChildren().add(pane)
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}