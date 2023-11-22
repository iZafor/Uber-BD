package bd.uber.zafor.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.Driver;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setInitData(Driver driver) {
        this.driver = driver;
    }

    @FXML
    private void onShowDashboard(ActionEvent event) {
        FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_DASHBOARD_VIEW);
        try {
            driverBorderPane.setCenter(loader.load());
            DriverDashboardController controller = loader.getController();
            controller.setInitData(driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onShowAccountSettings(ActionEvent event) {
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
}