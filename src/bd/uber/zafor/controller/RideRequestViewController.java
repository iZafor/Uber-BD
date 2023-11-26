package bd.uber.zafor.controller;

import bd.uber.zafor.model.RideRequest;
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
    public Text passengerRating;

    @FXML
    private Text fareAmountText;

    @FXML
    private ImageView ignoreButton;

    @FXML
    private ImageView acceptButton;

    public void setInitData(RideRequest rideRequest) {
        pickupLocationLabel.setText(rideRequest.getPickupPoint().getName());
        dropOffLocationLabel.setText(rideRequest.getDropOffPoint().getName());
        passengerRating.setText(String.format("%.2f", rideRequest.getPassengerRating()));
        fareAmountText.setText(String.format("%.2f", rideRequest.getFare()));
    }

    public ImageView getIgnoreButton() {
        return ignoreButton;
    }

    public ImageView getAcceptButton() {
        return acceptButton;
    }
}