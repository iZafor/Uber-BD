package bd.uber.zafor.controller;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.Driver;
import bd.uber.zafor.model.DriverViewMenuOption;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DriverViewController implements Initializable {
    @FXML
    private BorderPane driverBorderPane;

    private Driver driver;

    private final ObjectProperty<DriverViewMenuOption> menuOptionObjectProperty = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureDriverViewMenuOptionProperty();
    }

    public void setInitData(Driver driver) {
        this.driver = driver;
        Util.getInstance().getWorkers().execute(() -> this.driver.setRideList(Util.getInstance().getDb().getObjectList(BinFilePath.RIDE, ride -> ride.getDriverId() == driver.getId())));
        menuOptionObjectProperty.setValue(DriverViewMenuOption.DASHBOARD);
    }

    @FXML
    private void onShowDashboard() {
        menuOptionObjectProperty.setValue(DriverViewMenuOption.DASHBOARD);
    }

    @FXML
    private void onShowProfileSettings() {
        menuOptionObjectProperty.setValue(DriverViewMenuOption.PROFILE_SETTINGS);
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
                        ((DriverDashboardController) loader.getController()).setInitData(driver);
                        break;
                    case PROFILE_SETTINGS:
                        loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_PROFILE_SETTINGS_VIEW);
                        driverBorderPane.setCenter(loader.load());
                        ((DriverProfileSettingsController) loader.getController()).setInitData(driver);
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
}