package bd.uber.zafor.controller.driver;

import bd.uber.zafor.model.driver.RideRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class RideRequestViewController {
    @FXML
    private Label pickupLocationLabel;

    @FXML
    private Label dropOffLocationLabel;

    @FXML
    private Text passengerRating;

    @FXML
    private Text fareAmountText;

    @FXML
    private Text rideDistanceText;

    @FXML
    private ImageView ignoreButton;

    @FXML
    private ImageView acceptButton;

    public void setInitData(RideRequest rideRequest) {
        pickupLocationLabel.setText(rideRequest.getPickupPoint().getName());
        dropOffLocationLabel.setText(rideRequest.getDropOffPoint().getName());
        passengerRating.setText(String.format("%.2f", rideRequest.getPassengerRating()));
        fareAmountText.setText(String.format("%.2f", rideRequest.getFare()));
        rideDistanceText.setText(String.valueOf(rideRequest.getDropOffPoint().getDistance(rideRequest.getPickupPoint())));
    }

    public ImageView getIgnoreButton() {
        return ignoreButton;
    }

    public ImageView getAcceptButton() {
        return acceptButton;
    }
}