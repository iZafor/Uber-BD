package bd.uber.zafor.controller.driver;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.driver.Ride;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

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

    public void setInitData(Ride ride) {
        this.ride = ride;
        pickupLocationLabel.setText(ride.getPickupPoint().getName());
        dropOffLocationLabel.setText(ride.getDropOffPoint().getName());
        statusLabel.setText(ride.isCompleted() ? "Completed" : "Cancelled");
        statusLabel.getStyleClass().add(ride.isCompleted() ? "green-label" : "red-label");
        fareText.setText(String.valueOf(ride.getFare()));

        if (ride.isCompleted()) {
            pickupDateTimeLabel.setText(ride.getPickupTime().format(DateTimeFormatter.ofPattern("hh:mm:ss, yyyy-MM-dd")));
            dropOffDateTimeLabel.setText(ride.getDropOffTime().format(DateTimeFormatter.ofPattern("hh:mm:ss, yyyy-MM-dd")));
            rideDistanceText.setText(String.valueOf(ride.getRideDistance()));
        }
    }

    @FXML
    private void onShowDetails(MouseEvent mouseEvent) {
        FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.DETAILED_RIDE_VIEW);
        try {
            Scene scene = new Scene(loader.load());
            ((DetailedRideViewController) loader.getController()).setInitData(ride);
            Util.getInstance().showScene(scene, mouseEvent, "");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}