package bd.uber.zafor.controller;

import bd.uber.zafor.model.Ride;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;

public class RideViewController {
    @FXML
    private Label pickupLocationLabel;

    @FXML
    private Label dropOffLocationLabel;

    @FXML
    public Label pickupDateTimeLabel;

    @FXML
    public Label dropOffDateTimeLabel;

    @FXML
    private Label fareText;

    public void setInitData(Ride ride) {
        pickupLocationLabel.setText(ride.getPickupPoint().getName());
        dropOffLocationLabel.setText(ride.getDropOffPoint().getName());
        pickupDateTimeLabel.setText(ride.getPickupTime().format(DateTimeFormatter.ofPattern("hh:mm:ss, yyyy-MM-dd")));
        dropOffDateTimeLabel.setText(ride.getDropOffTime().format(DateTimeFormatter.ofPattern("hh:mm:ss, yyyy-MM-dd")));
        fareText.setText(String.valueOf(ride.getFare()));
    }
}