package bd.uber.zafor.controller;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.*;
import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

    @FXML
    public VBox currentRideContainerVBox;

    private Driver driver;

    private List<Location> locationList;

    private ObjectProperty<Location> driverLocationProperty;

    private FloatProperty totalEarningsProperty;

    private FloatProperty earnedTodayProperty;

    private FloatProperty ratingsProperty;

    private IntegerProperty tripCountProperty;

    private ObjectProperty<DriverStatus> driverStatusProperty;

    private FloatProperty rideRequestAreaProperty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchResultScrollPane.setVisible(false);
        currentDateText.setText(LocalDate.now().toString());
        driverStatusComboBox.getItems().addAll(DriverStatus.values());
        driverStatusComboBox.setValue(DriverStatus.INACTIVE);
        Util.getInstance().getWorkers().execute(() -> locationList = Util.getInstance().getLocationList());
    }

    public void setInitData(Driver driver,
                            ObjectProperty<Location> driverLocationProperty,
                            FloatProperty totalEarningsProperty,
                            FloatProperty earnedTodayProperty,
                            FloatProperty ratingsProperty,
                            IntegerProperty tripCountProperty,
                            ObjectProperty<DriverStatus> driverStatusProperty,
                            FloatProperty rideRequestAreaProperty) {
        this.driver = driver;

        this.driverLocationProperty = driverLocationProperty;
        this.totalEarningsProperty = totalEarningsProperty;
        this.earnedTodayProperty = earnedTodayProperty;
        this.ratingsProperty = ratingsProperty;
        this.tripCountProperty = tripCountProperty;
        this.driverStatusProperty = driverStatusProperty;
        this.rideRequestAreaProperty = rideRequestAreaProperty;

        // Configure observable properties
        totalEarningsProperty.addListener((observable, oldValue, newValue) -> totalEarningsText.setText(String.format("%.2f", newValue.floatValue())));
        earnedTodayProperty.addListener((observable, oldValue, newValue) -> earnedTodayText.setText(String.format("%.2f", newValue.floatValue())));
        ratingsProperty.addListener((observable, oldValue, newValue) -> driverRatingText.setText(String.format("%.2f", newValue.floatValue())));
        tripCountProperty.addListener((observable, oldValue, newValue) -> tripsCountText.setText(String.valueOf(newValue.intValue())));

        driverNameText.setText(driver.getName());
        vehicleModelText.setText(driver.getVehicleInfo().getModel());
        currentLocationTextField.setText(driverLocationProperty.getValue().getName());
        searchResultScrollPane.setVisible(false);

        driverRatingText.setText(String.format("%.2f", ratingsProperty.get()));
        totalEarningsText.setText(String.format("%.2f", totalEarningsProperty.get()));
        earnedTodayText.setText(String.format("%.2f", earnedTodayProperty.get()));
        driverRatingText.setText(String.format("%.2f", ratingsProperty.get()));
        tripsCountText.setText(String.valueOf(tripCountProperty.get()));
        driverStatusComboBox.setValue(driverStatusProperty.get());
        if (rideRequestAreaProperty.get() != 0) {
            rideRequestAreaTextField.setText(String.valueOf(rideRequestAreaProperty.get()));
        }

        configureSearchFunctionality();
        configureRideRequestArea();
        configureAvailabilityStatus();
        addRideRequests();
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
                        driverLocationProperty.setValue(location);
                        searchResultScrollPane.setVisible(false);
                    });
                    searchResultVBox.getChildren().add(text);
                }
            });
        });
    }

    private void configureAvailabilityStatus() {
        driverStatusComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
//            driver.setDriverStatus(newValue);
            if (newValue == DriverStatus.ACTIVE) {
                addRideRequests();
            } else {
                rideRequestsVBox.getChildren().clear();
            }
            driverStatusProperty.setValue(newValue);
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
                    if (area <= 0 || area > 5) {
                        Platform.runLater(() -> Util.getInstance().showWarningMessage("Ride request area must be in between of 1 and 5 km!"));
                    } else {
                        rideRequestAreaProperty.setValue(area);
                        addRideRequests();
                        return;
                    }
                } catch (NumberFormatException | NullPointerException ignored) {
                }
                rideRequestAreaTextField.clear();
            }
        });
    }

    private void addRideRequests() {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                if (driverStatusComboBox.getValue() != DriverStatus.ACTIVE) {
                    return;
                }

                float rideArea = Float.parseFloat(rideRequestAreaTextField.getText());
                Platform.runLater(() -> rideRequestsVBox.getChildren().clear());
                Util.getInstance().getDb().<RideRequest>getObjectList(BinFilePath.RIDE_REQUEST)
                        .stream()
                        .filter(rideRequest -> rideRequest.getPickupPoint().getDistance(driverLocationProperty.getValue()) <= rideArea)
                        .forEach(rideRequest -> {
                            try {
                                FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.RIDE_REQUEST_VIEW);
                                HBox hBox = loader.load();
                                RideRequestViewController controller = loader.getController();
                                controller.setInitData(rideRequest);
                                controller.getIgnoreButton().setOnMouseClicked(event -> rideRequestsVBox.getChildren().remove(hBox));
                                controller.getAcceptButton().setOnMouseClicked(event -> {
                                    try {
                                        // Configure current ride
                                        FXMLLoader currentRideLoader = Util.getInstance().getLoader(FXMLFilePath.CURRENT_RIDE_VIEW);
                                        AnchorPane pane = currentRideLoader.load();

                                        // Configure the controller
                                        CurrentRideViewController currentRideViewController = currentRideLoader.getController();
                                        Ride ride = driver.getRideRequest(rideRequest);
                                        currentRideViewController.setInitData(rideRequest, ride, driverLocationProperty);

                                        // Add event listeners
                                        currentRideViewController.getCancelButton().setOnMouseClicked(mEvent -> {
                                            ride.setPassengerFeedback(new RideFeedback(5, ""));
                                            driver.cancelRide(ride);
                                            currentRideContainerVBox.getChildren().clear();
                                            Util.getInstance().getWorkers().execute(this::addRideRequests);
                                        });
                                        currentRideViewController.getEndRideButton().setOnMouseClicked(eEvent -> {
                                            ride.setPassengerFeedback(new RideFeedback(5, ""));
                                            ride.setDropOffTime(LocalDateTime.now());
                                            driver.completeRide(ride);

                                            // Update observable properties
                                            totalEarningsProperty.setValue(driver.getTotalEarnings());
                                            earnedTodayProperty.setValue(earnedTodayProperty.floatValue() + ride.getFare());
                                            ratingsProperty.setValue((ratingsProperty.floatValue() + 5) / 2f); // need to be updated
                                            tripCountProperty.setValue(tripCountProperty.intValue() + 1);

                                            currentRideContainerVBox.getChildren().clear();

                                            // Show ride requests
                                            addRideRequests();
                                        });

                                        // Show the current ride node
                                        Platform.runLater(() -> {
                                            rideRequestsVBox.getChildren().clear();
                                            currentRideContainerVBox.getChildren().add(pane);
                                        });
                                    } catch (IOException e) {
                                        System.out.println(e.getMessage());
                                    }
                                });
                                Platform.runLater(() -> rideRequestsVBox.getChildren().add(hBox));
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        });
            } catch (NumberFormatException | NullPointerException ignored) {
                Platform.runLater(() -> {
                    Util.getInstance().showWarningMessage("Invalid ride request area!");
                });
            }
        });
    }
}