package bd.uber.zafor.controller.driver;

import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.driver.Ride;
import bd.uber.zafor.model.driver.RideRequest;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
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

    public void setInitData(RideRequest rideRequest, BooleanProperty hasStartedProperty, Ride ride, ObjectProperty<Location> driverLocationObjectProperty) {
        List<Location> locationList = Util.getInstance().getLocationList();
        driverLocationText.setText(driverLocationObjectProperty.getValue().getName());
        pickupLocationText.setText(locationList.stream().filter(l -> l.getLocationId() == ride.getPickupPointId()).findFirst().get().getName());
        dropOffLocationText.setText(locationList.stream().filter(l -> l.getLocationId() == ride.getDropOffPointId()).findFirst().get().getName());
        fareAmountText.setText(String.format("%.2f", rideRequest.getFare()));
        paymentMethodText.setText(ride.getPaymentMethod().name());
        rideDistanceText.setText(String.format("%.2f", rideRequest.getRideDistance()));

        float distance = driverLocationObjectProperty.getValue().getDistance(locationList.stream().filter(l -> l.getLocationId() == rideRequest.getPickupPointId()).findFirst().get());

        pickupLocationDistance.setText(String.format("%.2f", distance));
        startRideButton.setDisable(!(distance == 0));

        hasStartedProperty.bindBidirectional(startRideButton.disableProperty());
        hasStartedProperty.addListener((observable, oldValue, newValue) -> endRideButton.setDisable(!newValue));

        ChangeListener<Location> pickupLocationChangeListener = (observable, oldValue, newValue) -> {
            float pickupDistance = locationList.stream().filter(l -> l.getLocationId() == rideRequest.getPickupPointId()).findFirst().get().getDistance(newValue);
            pickupLocationDistance.setText(String.format("%.2f", pickupDistance));
            if (pickupDistance == 0) {
                startRideButton.setDisable(false);
            }
        };

        ChangeListener<Location> dropOffLocationChangeListener = (observable, oldValue, newValue) -> {
            float dropOffDistance = locationList.stream().filter(l -> l.getLocationId() == rideRequest.getDropOffPointId()).findFirst().get().getDistance(newValue);
            rideDistanceText.setText(String.format("%.2f", dropOffDistance));
            if (dropOffDistance == 0) {
                cancelButton.setDisable(true);
            }
        };

        driverLocationObjectProperty.addListener(pickupLocationChangeListener);
        startRideButton.setOnMouseClicked(event -> {
            ride.setPickupTime(LocalDateTime.now());
            startRideButton.setDisable(true);
            driverLocationObjectProperty.removeListener(pickupLocationChangeListener);
            driverLocationObjectProperty.addListener(dropOffLocationChangeListener);
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