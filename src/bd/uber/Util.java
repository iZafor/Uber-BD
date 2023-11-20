package bd.uber;

import bd.uber.zafor.controller.DriverSignupController;
import bd.uber.zafor.model.Driver;
import bd.uber.zafor.model.SignupForm;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;
import java.util.List;

public class Util {
    private static Util util;

    private final DB db;

    private Parent driverView;
    private Driver signUpDriver;
    private BorderPane driverSignUpView;
    private final ObjectProperty<SignupForm> signupFormProperty;

    private List<Location> locationList;

    private final Alert alert = new Alert(Alert.AlertType.WARNING);

    private Util() {
        db = new DB();
        signupFormProperty = new SimpleObjectProperty<>(SignupForm.BASIC_INFO);
    }

    public static Util getInstance() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }

    public Driver getSignUpDriver() {
        if (signUpDriver == null) {
            signUpDriver = new Driver();
            signUpDriver.setId(db.getUserCount(BinFilePath.DRIVER) + 1);
        }
        return signUpDriver;
    }

    public void setSignUpDriver(Driver signUpDriver) {
        this.signUpDriver = signUpDriver;
    }

    public DB getDb() {
        return db;
    }

    public Parent getDriverView() {
        if (driverView == null) {
            try {
                driverView = getLoader(FXMLFilePath.DRIVER_VIEW).load();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return driverView;
    }

    public BorderPane getDriverSignUpView() {
        if (driverSignUpView == null) {
            try {
                FXMLLoader loader = getLoader(FXMLFilePath.DRIVER_SIGN_UP_VIEW);
                driverSignUpView = loader.load();
                ((DriverSignupController) loader.getController()).setInitData(signupFormProperty);
            } catch (IOException ignored) {
                //log the error
            }
        }
        return driverSignUpView;
    }

    public void setSignupForm(SignupForm form) {
        signupFormProperty.setValue(form);
    }

    /**
     * Use this method to show scene on the same window
     */
    public void showScene(Parent parent, EventObject event, String windowTitle) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(windowTitle);
        stage.show();
    }

    /**
     * Use this method to show scene on a new window
     */
    public void showScene(Scene scene, String windowTitle) {
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(windowTitle);
        stage.setResizable(false);
        stage.show();
    }

    public FXMLLoader getLoader(FXMLFilePath fxmlFilePath) {
        return new FXMLLoader(getClass().getResource(fxmlFilePath.getPath()));
    }

    public List<Location> getLocationList() {
        if (locationList == null) {
            locationList = db.getLocations();
        }
        return locationList;
    }

    public void showWarningMessage(String alertMessage) {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

    public void showSuccessMessage(String successMessage) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText(successMessage);
        alert.showAndWait();
    }
}