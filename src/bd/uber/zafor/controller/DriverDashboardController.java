package bd.uber.zafor.controller;

import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.Driver;
import bd.uber.zafor.model.DriverStatus;
import bd.uber.zafor.model.Ride;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    public ScrollPane searchResultScrollPane;

    @FXML
    public VBox searchResultVBox;

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

    private List<Location> locationList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureSearchFunctionality();
        configureRideRequestArea();
        configureAvailabilityStatus();
        searchResultScrollPane.setVisible(false);
        currentDateText.setText(LocalDate.now().toString());
        driverStatusComboBox.getItems().addAll(DriverStatus.values());
        Util.getInstance().getWorkers().execute(() -> locationList = Util.getInstance().getLocationList());
    }

    public void setInitData(Driver driver) {
        this.driver = driver;
        driverNameText.setText(driver.getName());
        vehicleModelText.setText(driver.getVehicleInfo().getModel());

        List<Ride> completedRides = driver.getRideList().stream().filter(Ride::isCompleted).collect(Collectors.toList());
        driverRatingText.setText(completedRides.isEmpty() ? "0" :
                String.format("%.2f",
                        (completedRides.stream().mapToInt(ride -> ride.getPassengerFeedback().getRating())
                                .sum() / (float) completedRides.size())
                ));
        tripsCountText.setText(String.valueOf(completedRides.size()));
        totalEarningsText.setText(String.valueOf(completedRides.stream().mapToDouble(Ride::getFare).sum()));
        LocalDateTime todayMidnight = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        earnedTodayText.setText(String.valueOf(completedRides.stream().filter(ride -> ride.getDropOffTime().isAfter(todayMidnight)).mapToDouble(Ride::getFare).sum()));
    }

    private void configureSearchFunctionality() {
        currentLocationTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                String currentLocation = currentLocationTextField.getText();
                if (currentLocation == null || currentLocation.isEmpty() || Util.getInstance().getLocationList().stream().noneMatch(location -> location.getName().equals(currentLocation))) {
                    currentLocationTextField.clear();
                }
                currentLocationTextField.getParent().requestFocus();
            }
        });
        currentLocationTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!searchResultScrollPane.isFocused()) {
                searchResultScrollPane.setVisible(newValue);
            }
        });
        currentLocationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchResultScrollPane.setVisible(true);
            searchResultVBox.getChildren().clear();
            String lower = newValue.toLowerCase();
            if (lower.isEmpty()) {
                return;
            }
            locationList.forEach(location -> {
                if (location.getName().toLowerCase().contains(lower)) {
                    Text text = new Text(location.getName());
                    text.setStyle("-fx-font-size: 20; -fx-cursor: hand");
                    text.setOnMouseClicked(event -> {
                        currentLocationTextField.setText(location.getName());
                        searchResultScrollPane.setVisible(false);
                    });
                    searchResultVBox.getChildren().add(text);
                }
            });
        });
    }

    private void configureAvailabilityStatus() {
        driverStatusComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            driver.setDriverStatus(newValue);
            rideRequestsVBox.getChildren().clear();
        });
    }

    private void configureRideRequestArea() {
        rideRequestAreaTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                rideRequestAreaTextField.getParent().requestFocus();
            }
        });
        rideRequestAreaTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                try {
                    float area = Float.parseFloat(rideRequestAreaTextField.getText());
                    System.out.println("Ride request area: " + area);
                } catch (NumberFormatException | NullPointerException ignored) {
                    rideRequestAreaTextField.clear();
                }
            }
        });
    }
}