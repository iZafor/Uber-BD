package bd.uber.zafor.controller.driver;

import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.driver.Ride;
import bd.uber.zafor.model.driver.RideFeedback;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DetailedRideViewController {
    @FXML
    private Text pickupLocationText;
    @FXML
    private Text dropOffLocationText;
    @FXML
    private Text pickupTimeText;
    @FXML
    private Text dropOffTimeText;
    @FXML
    private Text rideDistanceText;
    @FXML
    private Text paymentMethodText;
    @FXML
    private Text passengerRatingText;
    @FXML
    private Text rideStatusText;
    @FXML
    private TextArea feedBackMessageTextArea;
    @FXML
    private Text fareAmountText;

    public void setInitData(Ride ride, RideFeedback rideFeedback) {
        List<Location> locationList = Util.getInstance().getLocationList();
        rideStatusText.setText(ride.hasCompleted() ? "Completed" : "Cancelled");
        rideStatusText.getStyleClass().add(ride.hasCancelled() ? "red-text" : "green-text");
        pickupLocationText.setText(locationList.get(ride.getPickupPointId()).getName());
        dropOffLocationText.setText(locationList.get(ride.getDropOffPointId()).getName());
        rideDistanceText.setText(String.valueOf(ride.getRideDistance()));
        paymentMethodText.setText(ride.getPaymentMethod().name());
        fareAmountText.setText(String.valueOf(ride.getFare()));

        if(ride.hasCompleted()) {
            pickupTimeText.setText(ride.getPickupTime().format(DateTimeFormatter.ofPattern("HH:mm:ss, yyyy-MM-dd")));
            dropOffTimeText.setText(ride.getDropOffTime().format(DateTimeFormatter.ofPattern("HH:mm:ss, yyyy-MM-dd")));
            passengerRatingText.setText(String.valueOf(rideFeedback.getRating()));
            feedBackMessageTextArea.setText(rideFeedback.getFeedbackMessage());
        }
    }
}