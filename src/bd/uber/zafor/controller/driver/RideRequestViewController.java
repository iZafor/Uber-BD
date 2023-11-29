package bd.uber.zafor.controller.driver;

import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.driver.RideRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.List;

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
        List<Location> locationList = Util.getInstance().getLocationList();
        pickupLocationLabel.setText(locationList.stream().filter(l -> l.getLocationId() == rideRequest.getPickupPointId()).findFirst().get().getName());
        dropOffLocationLabel.setText(locationList.stream().filter(l -> l.getLocationId() == rideRequest.getDropOffPointId()).findFirst().get().getName());
        passengerRating.setText(String.format("%.2f", rideRequest.getPassengerRating()));
        fareAmountText.setText(String.format("%.2f", rideRequest.getFare()));
        rideDistanceText.setText(String.format("%.2f", rideRequest.getRideDistance()));
    }

    public ImageView getIgnoreButton() {
        return ignoreButton;
    }

    public ImageView getAcceptButton() {
        return acceptButton;
    }
}