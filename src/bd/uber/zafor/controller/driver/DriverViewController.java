package bd.uber.zafor.controller.driver;

import bd.uber.*;
import bd.uber.zafor.model.driver.*;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class DriverViewController implements Initializable {
    @FXML
    public VBox mainMenuVBox;
    @FXML
    private BorderPane driverBorderPane;
    @FXML
    private VBox accountSettingsMenuVBox;

    private Driver driver;
    private ContactDetails contactDetails;
    private VehicleInfo vehicleInfo;
    private VehicleStatus vehicleStatus;
    private InsurancePolicy insurancePolicy;
    private DrivingLicense drivingLicense;
    private final ObservableList<RideFeedback> feedbackList = FXCollections.observableArrayList();

    private final ObjectProperty<DriverViewMenuOption> menuOptionProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Location> driverLocationProperty = new SimpleObjectProperty<>();
    private final FloatProperty totalEarningsProperty = new SimpleFloatProperty(0);
    private final FloatProperty earnedTodayProperty = new SimpleFloatProperty(0);
    private final FloatProperty ratingsProperty = new SimpleFloatProperty(0);
    private final IntegerProperty tripCountProperty = new SimpleIntegerProperty(0);
    private final ObjectProperty<DriverStatus> driverStatusProperty = new SimpleObjectProperty<>(DriverStatus.INACTIVE);
    private final FloatProperty rideRequestAreaProperty = new SimpleFloatProperty(0);
    private final ObjectProperty<AnchorPane> currentRideViewProperty = new SimpleObjectProperty<>(null);
    private final ObjectProperty<RideRequest> acceptedRideRequestProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Ride> ongoingRideProperty = new SimpleObjectProperty<>();
    private final BooleanProperty rideStartedProperty = new SimpleBooleanProperty(false);

    private final VBox currentRideContainer = new VBox();

    {
        currentRideContainer.setLayoutX(18);
        currentRideContainer.setLayoutY(623);
        currentRideContainer.getStyleClass().add("current-ride-container");
    }

    private boolean showAccountSettingsMenuVBox = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureDriverViewMenuOptionProperty();
        mainMenuVBox.getChildren().remove(2);
    }

    public void setInitData(Driver driver) {
        this.driver = driver;

        driverStatusProperty.setValue(driver.getDriverStatus());
        rideRequestAreaProperty.setValue(driver.getRideRequestRange());

        // Retrieve DrivingLicense
        Util.getInstance().getWorkers().execute(() ->
                drivingLicense = Util.getInstance().getDb().getObject(BinFilePath.DRIVING_LICENSE, d -> d.getDrivingLicenseId() == driver.getId())
        );

        // Retrieve Rides
        Util.getInstance().getWorkers().execute(() -> {
            this.driver.setRideList(Util.getInstance().getDb().getObjectList(BinFilePath.RIDE, ride -> ride.getDriverId() == driver.getId()));
            List<Ride> completedRides = driver.getRideList().stream().filter(Ride::isCompleted).collect(Collectors.toList());

            // Retrieve Feedbacks
            Util.getInstance().getWorkers().execute(() -> {
                completedRides.stream()
                        .mapToInt(Ride::getPassengerFeedbackId)
                        .forEach(id ->
                                feedbackList.add(
                                        Util.getInstance()
                                                .getDb()
                                                .getObject(
                                                        BinFilePath.PASSENGER_FEEDBACK,
                                                        f -> f.getRideFeedBackId() == id
                                                )
                                )
                        );
                configureRatingsAutoUpdating();
                Platform.runLater(() ->
                        ratingsProperty.setValue(feedbackList.isEmpty() ? 0 : feedbackList.stream().mapToInt(RideFeedback::getRating)
                                .sum() / feedbackList.size()));
            });

            Platform.runLater(() -> {
                tripCountProperty.setValue(completedRides.size());
                totalEarningsProperty.setValue(completedRides.stream().mapToDouble(Ride::getFare).sum());
                driver.setTotalEarnings(totalEarningsProperty.get());
                LocalDateTime todayMidnight = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
                earnedTodayProperty.setValue(completedRides.stream().filter(ride -> ride.getDropOffTime().isAfter(todayMidnight)).mapToDouble(Ride::getFare).sum());
            });
        });

        // Retrieve ContactDetails
        Util.getInstance().getWorkers().execute(() -> {
            contactDetails = Util.getInstance().getDb().getObject(BinFilePath.CONTACT_DETAILS, c -> c.getContactDetailsId() == driver.getContactDetailsId());
            driverLocationProperty.setValue(Util.getInstance().getLocationList().stream().filter(l -> l.getLocationId() == driver.getCurrentLocationId()).findFirst().orElse(null));
            Platform.runLater(() -> menuOptionProperty.setValue(DriverViewMenuOption.DASHBOARD));
        });

        // Retrieve VehicleInfo
        Util.getInstance().getWorkers().execute(() -> {
            vehicleInfo = Util.getInstance().getDb().getObject(BinFilePath.VEHICLE_INFO, v -> v.getVehicleInfoId() == driver.getVehicleInfoId());

            Util.getInstance().getWorkers().execute(() ->
                    vehicleStatus = Util.getInstance().getDb().getObject(BinFilePath.VEHICLE_STATUS, v -> v.getVehicleStatusId() == vehicleInfo.getVehicleStatusId())
            );

            Util.getInstance().getWorkers().execute(() ->
                    insurancePolicy = Util.getInstance().getDb().getObject(BinFilePath.INSURANCE_POLICY, e -> e.getInsurancePolicyId() == vehicleInfo.getInsurancePolicyId())
            );
        });

        // Retrieve ongoing ride if there is any
        if (driverStatusProperty.get().equals(DriverStatus.SHARING_RIDE)) {
            Util.getInstance().getWorkers().submit(() -> {
                OngoingRide ongoingRide = Util.getInstance().getDb().<OngoingRide>getObjectList(BinFilePath.ONGOING_RIDE).get(0);

                try {
                    // Wait for dashboard
                    TimeUnit.MILLISECONDS.sleep(300);

                    Platform.runLater(() -> {
                        acceptedRideRequestProperty.setValue(ongoingRide.getRideRequest());
                        ongoingRideProperty.setValue(ongoingRide.getRide());
                        rideStartedProperty.setValue(ongoingRide.hasStarted());
                    });
                } catch (InterruptedException ignored) {

                }
            });
        }

        Platform.runLater(this::configureOnClose);
    }

    @FXML
    private void onShowDashboard() {
        menuOptionProperty.setValue(DriverViewMenuOption.DASHBOARD);
    }

    @FXML
    private void onShowProfileSettings() {
        if (showAccountSettingsMenuVBox) {
            mainMenuVBox.getChildren().remove(2);
            showAccountSettingsMenuVBox = false;
        } else {
            mainMenuVBox.getChildren().add(2, accountSettingsMenuVBox);
            showAccountSettingsMenuVBox = true;
        }
    }

    @FXML
    private void onShowBasicInfo() {
        menuOptionProperty.setValue(DriverViewMenuOption.PROFILE_BASIC_INFO);
    }

    @FXML
    private void onShowDrivingLicense() {
        menuOptionProperty.setValue(DriverViewMenuOption.DRIVING_LICENSE);
    }

    @FXML
    private void onShowVehicleInfo() {
        menuOptionProperty.setValue(DriverViewMenuOption.VEHICLE_INFO);
    }

    @FXML
    private void onShowVehicleStatus() {
        menuOptionProperty.setValue(DriverViewMenuOption.VEHICLE_STATUS);
    }

    @FXML
    private void onViewRides() {
        menuOptionProperty.setValue(DriverViewMenuOption.RIDES);
    }

    @FXML
    private void onReportDamage() {
        menuOptionProperty.set(DriverViewMenuOption.REPORT_DAMAGE);
    }

    @FXML
    private void onRequestForMaintenance(ActionEvent event) {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_MAINTENANCE_REQUEST_VIEW);
            AnchorPane anchorPane = loader.load();
            ((DriverMaintenanceRequestController) loader.getController()).setInitData(vehicleInfo.getVehicleInfoId());
            Util.getInstance().showScene(
                    new Scene(anchorPane),
                    event,
                    ""
            );
        } catch (IOException ignored) {
            // log the error
        }
    }

    @FXML
    private void onLogout(ActionEvent event) {
        try {
            Util.getInstance().showScene(
                    Util.getInstance().getLoader(
                            FXMLFilePath.GET_STARTED_VIEW
                    ).load(),
                    event,
                    "Get Started",
                    false
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void configureDriverViewMenuOptionProperty() {
        menuOptionProperty.addListener((observable, oldValue, newValue) -> {
            try {
                FXMLLoader loader;
                switch (newValue) {
                    case DASHBOARD:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_DASHBOARD_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverDashboardController) loader.getController()).setInitData(
                                driver,
                                vehicleInfo,
                                feedbackList,
                                driverLocationProperty,
                                totalEarningsProperty,
                                earnedTodayProperty,
                                ratingsProperty,
                                tripCountProperty,
                                driverStatusProperty,
                                rideRequestAreaProperty,
                                currentRideViewProperty,
                                currentRideContainer,
                                acceptedRideRequestProperty,
                                ongoingRideProperty,
                                rideStartedProperty
                        );
                        break;
                    case PROFILE_BASIC_INFO:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_PROFILE_BASIC_INFO_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverProfileBasicInfoController) loader.getController()).setInitData(driver, contactDetails);
                        break;
                    case DRIVING_LICENSE:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_PROFILE_DRIVING_LICENSE_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverProfileDrivingLicenseController) loader.getController()).setInitData(drivingLicense, insurancePolicy);
                        break;
                    case VEHICLE_INFO:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_PROFILE_VEHICLE_INFO_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverProfileVehicleInfoController) loader.getController()).setInitData(vehicleInfo);
                        break;
                    case VEHICLE_STATUS:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_PROFILE_VEHICLE_STATUS_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverProfileVehicleStatusController) loader.getController()).setInitData(vehicleStatus);
                        break;
                    case RIDES:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_RIDES_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverViewRidesController) loader.getController()).setInitData(driver, feedbackList);
                        break;
                    case REPORT_DAMAGE:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_REPORT_DAMAGE_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverReportDamageController) loader.getController()).setInitData(vehicleInfo);
                        break;
                }
            } catch (IOException ignored) {
                // log the error
            }
        });
    }

    private void configureRatingsAutoUpdating() {
        feedbackList.addListener((ListChangeListener<? super RideFeedback>) c ->
                ratingsProperty.setValue(feedbackList.stream().mapToInt(RideFeedback::getRating)
                        .sum() / (float) feedbackList.size())
        );
    }

    private void configureOnClose() {
        mainMenuVBox.getParent().getScene().getWindow().setOnHiding(this::commitChanges);
        mainMenuVBox.getParent().getScene().getWindow().setOnCloseRequest(this::commitChanges);
    }

    private void commitChanges(WindowEvent event) {
        if (driverStatusProperty.get().equals(DriverStatus.SHARING_RIDE)) {
            Util.getInstance().getWorkers().submit(() -> {
                OngoingRide ongoingRide = new OngoingRide(
                        acceptedRideRequestProperty.get(),
                        ongoingRideProperty.get(),
                        rideStartedProperty.get());
                Util.getInstance().getDb()
                        .addObjects(Collections.singletonList(
                                        ongoingRide
                                ),
                                BinFilePath.ONGOING_RIDE,
                                true);
            });
        }

        Util.getInstance().getWorkers().execute(() -> {
            driver.setCurrentLocationId(driverLocationProperty.getValue().getLocationId());
            driver.setDriverStatus(driverStatusProperty.get());
            driver.setRideRequestRange(rideRequestAreaProperty.get());
            Util.getInstance().getDb().updateObjectFile(
                    driver,
                    BinFilePath.DRIVER,
                    d -> d.getId() == driver.getId(),
                    false
            );
        });

        Util.getInstance().getWorkers().execute(() ->
                Util.getInstance().getDb().updateObjectFile(
                        vehicleInfo,
                        BinFilePath.VEHICLE_INFO,
                        v -> v.getVehicleInfoId() == vehicleInfo.getVehicleInfoId(),
                        false
                )
        );
    }
}