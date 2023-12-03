package bd.uber.hasibul;

import bd.uber.hasibul.model.BusinessAccountManager.BusinessAccountManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BusinessAcountManagerController {

    @FXML
    private Label resultLabel;

    private final BusinessAccountManager businessAccountManager = new BusinessAccountManager(1);

    @FXML
    private void onCalculateAverageRating(ActionEvent event) {
        resultLabel.setText(String.valueOf(businessAccountManager.getBusinessAnalytics().calculateAverageRating()));
    }

    @FXML
    private void onCalculateTotalRevenue(ActionEvent event) {
        resultLabel.setText(String.valueOf(businessAccountManager.getBusinessAnalytics().calculateTotalRevenue()));
    }

    @FXML
    private void onCounrCompletedRides(ActionEvent event) {
        resultLabel.setText(String.valueOf(businessAccountManager.getBusinessAnalytics().countCompletedRides()));
    }
}