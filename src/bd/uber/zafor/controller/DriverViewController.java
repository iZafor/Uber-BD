package bd.uber.zafor.controller;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.Driver;
import bd.uber.zafor.model.DriverStatus;
import bd.uber.zafor.model.DriverViewMenuOption;
import bd.uber.zafor.model.Ride;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DriverViewController implements Initializable {
    @FXML
    public VBox mainMenuVBox;

    @FXML
    private BorderPane driverBorderPane;

    @FXML
    public VBox accountSettingsMenuVBox;

    private Driver driver;

    private final ObjectProperty<DriverViewMenuOption> menuOptionObjectProperty = new SimpleObjectProperty<>();

    private final ObjectProperty<Location> driverLocationProperty = new SimpleObjectProperty<>();

    private final FloatProperty totalEarningsProperty = new SimpleFloatProperty(0);

    private final FloatProperty earnedTodayProperty = new SimpleFloatProperty(0);

    private final FloatProperty ratingsProperty = new SimpleFloatProperty(0);

    private final IntegerProperty tripCountProperty = new SimpleIntegerProperty(0);

    private final ObjectProperty<DriverStatus> driverStatusProperty = new SimpleObjectProperty<>(DriverStatus.INACTIVE);

    private final FloatProperty rideRequestAreaProperty = new SimpleFloatProperty(0);

    private boolean showAccountSettingsMenuVBox = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureDriverViewMenuOptionProperty();
        mainMenuVBox.getChildren().remove(2);
    }

    public void setInitData(Driver driver) {
        this.driver = driver;
        Util.getInstance().getWorkers().execute(() -> {
            this.driver.setRideList(Util.getInstance().getDb().getObjectList(BinFilePath.RIDE, ride -> ride.getDriverId() == driver.getId()));

            List<Ride> completedRides = driver.getRideList().stream().filter(Ride::isCompleted).collect(Collectors.toList());
            ratingsProperty.setValue(completedRides.isEmpty() ? 0 :
                    (completedRides.stream().mapToInt(ride -> ride.getPassengerFeedback().getRating())
                            .sum() / (float) completedRides.size())
            );
            tripCountProperty.setValue(completedRides.size());
            totalEarningsProperty.setValue(completedRides.stream().mapToDouble(Ride::getFare).sum());
            driver.setTotalEarnings(totalEarningsProperty.get());
            LocalDateTime todayMidnight = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
            earnedTodayProperty.setValue(completedRides.stream().filter(ride -> ride.getDropOffTime().isAfter(todayMidnight)).mapToDouble(Ride::getFare).sum());
        });
        driverLocationProperty.setValue(driver.getContactDetails().getAddress());
        menuOptionObjectProperty.setValue(DriverViewMenuOption.DASHBOARD);
    }

    @FXML
    private void onShowDashboard() {
        menuOptionObjectProperty.setValue(DriverViewMenuOption.DASHBOARD);
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
        menuOptionObjectProperty.setValue(DriverViewMenuOption.PROFILE_BASIC_INFO);
    }

    @FXML
    private void onShowDrivingLicense() {
        menuOptionObjectProperty.setValue(DriverViewMenuOption.DRIVING_LICENSE);
    }

    @FXML
    private void onShowVehicleInfo() {
        menuOptionObjectProperty.setValue(DriverViewMenuOption.VEHICLE_INFO);
    }

    @FXML
    private void onShowVehicleStatus() {
        menuOptionObjectProperty.setValue(DriverViewMenuOption.VEHICLE_STATUS);
    }

    @FXML
    private void onViewRides() {
        menuOptionObjectProperty.setValue(DriverViewMenuOption.RIDES);
    }

    @FXML
    private void onRequestForRepair() {
        menuOptionObjectProperty.set(DriverViewMenuOption.REPAIR_REQUEST);
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
        menuOptionObjectProperty.addListener((observable, oldValue, newValue) -> {
            try {
                FXMLLoader loader;
                switch (newValue) {
                    case DASHBOARD:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_DASHBOARD_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverDashboardController) loader.getController()).setInitData(driver,
                                driverLocationProperty,
                                totalEarningsProperty,
                                earnedTodayProperty,
                                ratingsProperty,
                                tripCountProperty,
                                driverStatusProperty,
                                rideRequestAreaProperty);
                        break;
                    case PROFILE_BASIC_INFO:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_PROFILE_BASIC_INFO_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverProfileBasicInfoController) loader.getController()).setInitData(driver);
                        break;
                    case DRIVING_LICENSE:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_PROFILE_DRIVING_LICENSE_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverProfileDrivingLicenseController) loader.getController()).setInitData(driver);
                        break;
                    case VEHICLE_INFO:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_PROFILE_VEHICLE_INFO_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverProfileVehicleInfoController) loader.getController()).setInitData(driver);
                        break;
                    case VEHICLE_STATUS:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_PROFILE_VEHICLE_STATUS_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverProfileVehicleStatusController) loader.getController()).setInitData(driver);
                        break;
                    case RIDES:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_RIDES_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverViewRidesController) loader.getController()).setInitData(driver);
                        break;
                    case REPAIR_REQUEST:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_REPAIR_REQUEST_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverRepairRequestController) loader.getController()).setInitData(driver);
                        break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void updateDriver(Driver driver) {
        Util.getInstance().getWorkers().submit(() -> {
            if (Util.getInstance().getDb().updateObject(driver, BinFilePath.DRIVER, d -> d.getId() == driver.getId())) {
                Platform.runLater(() -> Util.getInstance().showSuccessMessage("Info updated successfully."));
            } else {
                Platform.runLater(() -> Util.getInstance().showError("Failed to update data!"));
            }
        });
    }
}