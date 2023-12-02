package bd.uber.zafor.controller.operationsmanager;

import bd.uber.zafor.model.operationsmanager.PricingTier;
import bd.uber.zafor.model.operationsmanager.RideType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PricingTierViewController {
    @FXML
    private Text ridetTypeText;

    @FXML
    private Text baseFareText;

    @FXML
    private Text distanceRateText;

    @FXML
    private Text timeRateText;

    public void setInitData(RideType rideType, PricingTier pricingTier) {
        ridetTypeText.setText(rideType.name());
        baseFareText.setText(String.valueOf(pricingTier.getBaseFare()));
        distanceRateText.setText(String.valueOf(pricingTier.getDistanceRate()));
        timeRateText.setText(String.valueOf(pricingTier.getTimeRate()));
    }
}