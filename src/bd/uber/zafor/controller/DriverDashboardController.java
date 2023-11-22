package bd.uber.zafor.controller;

import bd.uber.zafor.model.Driver;
import bd.uber.zafor.model.DriverStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DriverDashboardController implements Initializable {
    @FXML
    private Text driverNameText;

    @FXML
    private Text currentDateText;

    @FXML
    private ComboBox<DriverStatus> driverStatusComboBox;

    @FXML
    private Text driverRatingText;

    @FXML
    private Text tripsCountText;

    @FXML
    private TextField currentLocationTextField;

    @FXML
    private TextField rideRequestAreaTextField;

    @FXML
    private Text totalEarningsText;

    @FXML
    private Text earnedTodayText;

    @FXML
    private Text vehicleModelText;

    @FXML
    private VBox rideRequestsVBox;

    private Driver driver;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureRideRequestArea();
        configureAvailabilityStatus();
        currentDateText.setText(LocalDate.now().toString());
        driverStatusComboBox.getItems().addAll(DriverStatus.values());
    }

    public void setInitData(Driver driver) {
        this.driver = driver;
        driverNameText.setText(driver.getName());
        vehicleModelText.setText(driver.getVehicleInfo().getModel());
        currentLocationTextField.setText(driver.getContactDetails().getAddress().getName());
    }

    private void configureAvailabilityStatus() {

    }

    private void configureRideRequestArea() {
        rideRequestAreaTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                try {
                    float area = Float.parseFloat(rideRequestAreaTextField.getText());
                    System.out.println("Ride request area: " + area);
                } catch (NumberFormatException | NullPointerException ignored) {

                }
            }
        });
    }
}