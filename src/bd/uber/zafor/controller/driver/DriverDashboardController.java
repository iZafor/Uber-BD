package bd.uber.zafor.controller.driver;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.driver.*;
import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class DriverDashboardController implements Initializable {
    @FXML
    private AnchorPane dashBoardContainer;
    @FXML
    public Circle profileImageCircle;
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
    private ScrollPane searchResultScrollPane;
    @FXML
    private VBox searchResultVBox;
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

    private VBox currentRideContainer;

    private Driver driver;
    private ObservableList<RideFeedback> feedbackList;
    private List<Location> locationList;

    private ObjectProperty<Location> driverLocationProperty;
    private FloatProperty totalEarningsProperty;
    private FloatProperty earnedTodayProperty;
    private IntegerProperty tripCountProperty;
    private ObjectProperty<DriverStatus> driverStatusProperty;
    private FloatProperty rideRequestAreaProperty;
    private ObjectProperty<AnchorPane> currentRideViewProperty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchResultScrollPane.setVisible(false);
        currentDateText.setText(LocalDate.now().getDayOfWeek().name() + ", " + LocalDate.now().toString());
        driverStatusComboBox.getItems().addAll(DriverStatus.values());
        driverStatusComboBox.setValue(DriverStatus.INACTIVE);
        Util.getInstance().getWorkers().execute(() -> locationList = Util.getInstance().getLocationList());
    }

    public void setInitData(Driver driver,
                            VehicleInfo vehicleInfo,
                            ObservableList<RideFeedback> feedbackList,
                            ObjectProperty<Location> driverLocationProperty,
                            FloatProperty totalEarningsProperty,
                            FloatProperty earnedTodayProperty,
                            FloatProperty ratingsProperty,
                            IntegerProperty tripCountProperty,
                            ObjectProperty<DriverStatus> driverStatusProperty,
                            FloatProperty rideRequestAreaProperty,
                            ObjectProperty<AnchorPane> currentRideViewProperty,
                            VBox currentRideContainer
    ) {
        this.driver = driver;
        this.feedbackList = feedbackList;

        this.driverLocationProperty = driverLocationProperty;
        this.totalEarningsProperty = totalEarningsProperty;
        this.earnedTodayProperty = earnedTodayProperty;
        this.tripCountProperty = tripCountProperty;
        this.driverStatusProperty = driverStatusProperty;
        this.rideRequestAreaProperty = rideRequestAreaProperty;

        this.currentRideViewProperty = currentRideViewProperty;
        this.currentRideContainer = currentRideContainer;

        // Load profile image
        Util.getInstance().getWorkers().execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                profileImageCircle.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get(driver.getProfileImage())))));
                profileImageCircle.setStrokeWidth(0);
            } catch (IOException | InterruptedException ignored) {
                // log the error
            }
        });

        dashBoardContainer.getChildren().add(currentRideContainer);

        // Configure observable properties
        totalEarningsProperty.addListener((observable, oldValue, newValue) -> totalEarningsText.setText(String.format("%.2f", newValue.floatValue())));
        earnedTodayProperty.addListener((observable, oldValue, newValue) -> earnedTodayText.setText(String.format("%.2f", newValue.floatValue())));
        ratingsProperty.addListener((observable, oldValue, newValue) -> driverRatingText.setText(String.format("%.2f", newValue.floatValue())));
        tripCountProperty.addListener((observable, oldValue, newValue) -> tripsCountText.setText(String.valueOf(newValue.intValue())));

        driverStatusComboBox.setValue(driverStatusProperty.get());
        driverStatusProperty.bindBidirectional(driverStatusComboBox.valueProperty());

        driverNameText.setText(driver.getName());
        vehicleModelText.setText(vehicleInfo.getModel());
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
                    rideRequestsVBox.getChildren().clear();
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
                    Label label = new Label(location.getName());
                    label.getStyleClass().add("search-location");
                    label.setOnMouseClicked(event -> {
                        currentLocationTextField.setText(location.getName());
                        driverLocationProperty.setValue(location);
                        searchResultScrollPane.setVisible(false);
                        addRideRequests();
                    });
                    searchResultVBox.getChildren().add(label);
                }
            });
        });
    }

    private void configureAvailabilityStatus() {
        driverStatusComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == DriverStatus.ACTIVE) {
                addRideRequests();
            } else {
                rideRequestsVBox.getChildren().clear();
            }
            driverStatusComboBox.setDisable(newValue == DriverStatus.SHARING_RIDE);
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
                    if (area <= 0 || area > 10) {
                        Platform.runLater(() -> Util.getInstance().showWarningMessage("Ride request area must be in between 1 to 10 km!"));
                    } else {
                        rideRequestAreaProperty.setValue(area);
                        addRideRequests();
                        return;
                    }
                } catch (NumberFormatException | NullPointerException ignored) {
                }
                rideRequestAreaTextField.clear();
                rideRequestsVBox.getChildren().clear();
            }
        });
    }

    private void addRideRequests() {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                if (driverStatusComboBox.getValue() != DriverStatus.ACTIVE || driverStatusComboBox.getValue() == DriverStatus.SHARING_RIDE) {
                    return;
                }

                TimeUnit.MILLISECONDS.sleep(50);

                float rideArea = Float.parseFloat(rideRequestAreaTextField.getText());
                Platform.runLater(() -> rideRequestsVBox.getChildren().clear());
                Util.getInstance().getDb().<RideRequest>getObjectList(BinFilePath.RIDE_REQUEST)
                        .stream()
                        .filter(rideRequest -> locationList.stream().filter(l -> l.getLocationId() == rideRequest.getPickupPointId()).findFirst().get().getDistance(driverLocationProperty.getValue()) <= rideArea)
                        .forEach(rideRequest -> {
                            try {
                                FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.RIDE_REQUEST_VIEW);
                                HBox hBox = loader.load();
                                RideRequestViewController controller = loader.getController();
                                controller.setInitData(rideRequest);
                                controller.getIgnoreButton().setOnMouseClicked(event -> rideRequestsVBox.getChildren().remove(hBox));
                                controller.getAcceptButton().setOnMouseClicked(event -> {
                                    try {
                                        // Delete the instance from file
                                        Util.getInstance().getWorkers().execute(() ->
                                                Util.getInstance().getDb().updateObjectFile(
                                                        rideRequest,
                                                        BinFilePath.RIDE_REQUEST,
                                                        r -> r.getPickupPointId() == rideRequest.getPickupPointId() && r.getDropOffPointId() == rideRequest.getDropOffPointId() && r.getRequestTime().equals(rideRequest.getRequestTime()),
                                                        true
                                                )
                                        );

                                        driverStatusProperty.setValue(DriverStatus.SHARING_RIDE);
                                        // Configure current ride
                                        FXMLLoader currentRideLoader = Util.getInstance().getLoader(FXMLFilePath.CURRENT_RIDE_VIEW);
                                        AnchorPane pane = currentRideLoader.load();
                                        currentRideViewProperty.set(pane);

                                        // Configure the controller
                                        CurrentRideViewController currentRideViewController = currentRideLoader.getController();
                                        Ride ride = driver.acceptRideRequest(rideRequest);
                                        currentRideViewController.setInitData(rideRequest, ride, driverLocationProperty);

                                        // Add event listeners
                                        currentRideViewController.getCancelButton().setOnMouseClicked(mEvent -> {
                                            driver.cancelRide(ride);
                                            currentRideContainer.getChildren().clear();
                                            driverStatusProperty.setValue(DriverStatus.ACTIVE);
                                            addRideRequests();
                                        });
                                        currentRideViewController.getEndRideButton().setOnMouseClicked(eEvent -> {
                                            RideFeedback passengerFeedBack = new RideFeedback(
                                                    Util.getInstance().getDb().getObjectCount(BinFilePath.PASSENGER_FEEDBACK),
                                                    5,
                                                    5,
                                                    "");

                                            feedbackList.add(passengerFeedBack);
                                            Util.getInstance().getWorkers().submit(() -> {
                                                Util.getInstance().getDb().addObject(passengerFeedBack, BinFilePath.PASSENGER_FEEDBACK);
                                            });

                                            ride.setPassengerFeedbackId(passengerFeedBack.getRideFeedBackId());
                                            ride.setDropOffTime(LocalDateTime.now());
                                            driver.completeRide(ride);

                                            // Update observable properties
                                            totalEarningsProperty.setValue(driver.getTotalEarnings());
                                            earnedTodayProperty.setValue(earnedTodayProperty.get() + ride.getFare());
                                            tripCountProperty.setValue(tripCountProperty.get() + 1);

                                            currentRideContainer.getChildren().clear();
                                            driverStatusProperty.setValue(DriverStatus.ACTIVE);

                                            // Show ride requests
                                            addRideRequests();
                                        });

                                        // Show the current ride node
                                        Platform.runLater(() -> {
                                            rideRequestsVBox.getChildren().clear();
                                            currentRideContainer.getChildren().add(pane);
                                        });
                                    } catch (IOException ignored) {
                                        // log the error
                                    }
                                });
                                Platform.runLater(() -> rideRequestsVBox.getChildren().add(hBox));
                            } catch (IOException ignored) {
                                // log the error
                            }
                        });
            } catch (NumberFormatException | NullPointerException | InterruptedException | OutOfMemoryError ignored) {
                // log the error
            }
        });
    }
}