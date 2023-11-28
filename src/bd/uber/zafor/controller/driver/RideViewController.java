package bd.uber.zafor.controller.driver;

import bd.uber.FXMLFilePath;
import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.driver.Ride;
import bd.uber.zafor.model.driver.RideFeedback;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RideViewController {
    @FXML
    private Label pickupLocationLabel;
    @FXML
    private Label dropOffLocationLabel;
    @FXML
    private Label pickupDateTimeLabel;
    @FXML
    private Label dropOffDateTimeLabel;
    @FXML
    private Text rideDistanceText;
    @FXML
    private Text fareText;
    @FXML
    private Label statusLabel;

    private Ride ride;
    private RideFeedback rideFeedback;

    public void setInitData(Ride ride, RideFeedback rideFeedback) {
        this.ride = ride;
        this.rideFeedback = rideFeedback;
        List<Location> locationList = Util.getInstance().getLocationList();
        pickupLocationLabel.setText(locationList.stream().filter(l -> l.getLocationId() == ride.getPickupPointId()).findFirst().get().getName());
        dropOffLocationLabel.setText(locationList.stream().filter(l -> l.getLocationId() == ride.getDropOffPointId()).findFirst().get().getName());
        statusLabel.setText(ride.isCompleted() ? "Completed" : "Cancelled");
        statusLabel.getStyleClass().add(ride.isCompleted() ? "green-label" : "red-label");
        fareText.setText(String.valueOf(ride.getFare()));
        rideDistanceText.setText(String.format("%.2f", ride.getRideDistance()));

        LocalDateTime pickupTime = ride.getPickupTime();
        if (pickupTime != null) {
            pickupDateTimeLabel.setText(pickupTime.format(DateTimeFormatter.ofPattern("hh:mm:ss, yyyy-MM-dd")));
        }

        if (ride.isCompleted()) {
            dropOffDateTimeLabel.setText(ride.getDropOffTime().format(DateTimeFormatter.ofPattern("hh:mm:ss, yyyy-MM-dd")));
        }
    }

    @FXML
    private void onShowDetails(MouseEvent mouseEvent) {
        FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.DETAILED_RIDE_VIEW);
        try {
            Scene scene = new Scene(loader.load());
            ((DetailedRideViewController) loader.getController()).setInitData(ride, rideFeedback);
            Util.getInstance().showScene(scene, mouseEvent, "");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}