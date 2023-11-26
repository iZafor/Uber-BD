package bd.uber.zafor.controller;

import bd.uber.Location;
import bd.uber.zafor.model.Ride;
import bd.uber.zafor.model.RideRequest;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CurrentRideViewController implements Initializable {
    @FXML
    private Text driverLocationText;

    @FXML
    private Text pickupLocationText;

    @FXML
    private Text dropOffLocationText;

    @FXML
    private Text fareAmountText;

    @FXML
    private Text paymentMethodText;

    @FXML
    private Text rideDistanceText;

    @FXML
    private Text pickupLocationDistance;

    @FXML
    private Button startRideButton;

    @FXML
    private Button endRideButton;

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        endRideButton.setDisable(true);
    }

    public void setInitData(RideRequest rideRequest, Ride ride, ObjectProperty<Location> driverLocationObjectProperty) {
        driverLocationText.setText(driverLocationObjectProperty.getValue().getName());
        pickupLocationText.setText(rideRequest.getPickupPoint().getName());
        dropOffLocationText.setText(rideRequest.getDropOffPoint().getName());
        fareAmountText.setText(String.valueOf(rideRequest.getFare()));
        paymentMethodText.setText(ride.getPaymentMethod().name());
        rideDistanceText.setText(String.valueOf(rideRequest.getDropOffPoint().getDistance(rideRequest.getPickupPoint())));

        float distance = driverLocationObjectProperty.getValue().getDistance(rideRequest.getPickupPoint());
        pickupLocationDistance.setText(String.valueOf(distance));
        startRideButton.setDisable(!(distance == 0));

        ChangeListener<Location> pickupLocationChangeListenerFor = (observable, oldValue, newValue) -> {
            float pickupDistance = rideRequest.getPickupPoint().getDistance(newValue);
            pickupLocationDistance.setText(String.valueOf(pickupDistance));
            if (pickupDistance == 0) {
                ride.setPickupTime(LocalDateTime.now());
                startRideButton.setDisable(false);
            }
        };

        ChangeListener<Location> dropOffLocationChangeListenerFor = (observable, oldValue, newValue) -> {
            float dropOffDistance = rideRequest.getDropOffPoint().getDistance(newValue);
            rideDistanceText.setText(String.valueOf(dropOffDistance));
            if (dropOffDistance == 0) {
                cancelButton.setDisable(true);
            }
        };

        driverLocationObjectProperty.addListener(pickupLocationChangeListenerFor);
        startRideButton.setOnMouseClicked(event -> {
            startRideButton.setDisable(true);
            driverLocationObjectProperty.removeListener(pickupLocationChangeListenerFor);
            driverLocationObjectProperty.addListener(dropOffLocationChangeListenerFor);
            endRideButton.setDisable(false);
        });
    }

    public Button getEndRideButton() {
        return endRideButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}