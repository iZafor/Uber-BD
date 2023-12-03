package bd.uber.zafor.controller.operationsmanager;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.operationsmanager.PricingTier;
import bd.uber.zafor.model.driver.RideType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class PricingTierUpdateViewController implements Initializable {
    @FXML
    private TextField baseFareTextField;
    @FXML
    private TextField distanceRateTextField;
    @FXML
    private TextField timeRateTextField;
    @FXML
    private ComboBox<RideType> rideTypeComboBox;

    private Map<RideType, Integer> pricingTierMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rideTypeComboBox.getItems().addAll(RideType.values());
    }

    public void setInitData(Map<RideType, Integer> pricingTierMap) {
        this.pricingTierMap = pricingTierMap;
    }

    @FXML
    private void onUpdate(ActionEvent event) {
        float baseFare;
        try {
            baseFare = Float.parseFloat(baseFareTextField.getText());
            if (baseFare <= 0) {
                Util.getInstance().showWarningMessage("Base fare must be greater than 0!");
                return;
            }
        } catch (NullPointerException | NumberFormatException ignored) {
            Util.getInstance().showWarningMessage("Invalid input for the base fare!");
            return;
        }

        float distanceRate;
        try {
            distanceRate = Float.parseFloat(distanceRateTextField.getText());
            if (distanceRate <= 0) {
                Util.getInstance().showWarningMessage("Distance rate must be greater than 0!");
                return;
            }
        } catch (NullPointerException | NumberFormatException ignored) {
            Util.getInstance().showWarningMessage("Invalid input for the distance rate!");
            return;
        }

        float timeRate;
        try {
            timeRate = Float.parseFloat(timeRateTextField.getText());
            if (timeRate <= 0) {
                Util.getInstance().showWarningMessage("Time rate must be greater than 0!");
                return;
            }
        } catch (NullPointerException | NumberFormatException ignored) {
            Util.getInstance().showWarningMessage("Invalid input for the time rate!");
            return;
        }

        RideType rideType = rideTypeComboBox.getValue();
        if (rideType == null) {
            Util.getInstance().showWarningMessage("Ride type cannot be empty!");
            return;
        }

        Util.getInstance().getWorkers().execute(() -> {
            PricingTier pricingTier = new PricingTier(
                    Util.getInstance().getDb().getObjectCount(BinFilePath.PRICING_TIER) + 1,
                    baseFare,
                    distanceRate,
                    timeRate
            );
            if (Util.getInstance().getDb().addObject(pricingTier, BinFilePath.PRICING_TIER)) {
                Platform.runLater(() -> {
                    baseFareTextField.getScene().getWindow().hide();
                });
                pricingTierMap.put(rideType, pricingTier.getPricingTierId());
            } else {
                Platform.runLater(() -> {
                    Util.getInstance().showSuccessMessage("Failed to update pricing tier!");
                });
            }
        });
    }
}