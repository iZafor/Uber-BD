package bd.uber.zafor.controller.driver;

import bd.uber.zafor.model.driver.Ride;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

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

    public void setInitData(Ride ride) {
        rideStatusText.setText(ride.isCompleted() ? "Completed" : "Cancelled");
        rideStatusText.getStyleClass().add(ride.isCancelled() ? "red-text" : "green-text");
        pickupLocationText.setText(ride.getPickupPoint().getName());
        dropOffLocationText.setText(ride.getDropOffPoint().getName());
        rideDistanceText.setText(String.valueOf(ride.getRideDistance()));
        paymentMethodText.setText(ride.getPaymentMethod().name());
        fareAmountText.setText(String.valueOf(ride.getFare()));

        if(ride.isCompleted()) {
            pickupTimeText.setText(ride.getPickupTime().format(DateTimeFormatter.ofPattern("HH:mm:ss, yyyy-MM-dd")));
            dropOffTimeText.setText(ride.getDropOffTime().format(DateTimeFormatter.ofPattern("HH:mm:ss, yyyy-MM-dd")));
            passengerRatingText.setText(String.valueOf(ride.getPassengerFeedback().getRating()));
            feedBackMessageTextArea.setText(ride.getPassengerFeedback().getFeedbackMessage());
        }
    }
}