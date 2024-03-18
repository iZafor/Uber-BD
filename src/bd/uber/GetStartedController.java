package bd.uber;

import bd.uber.zafor.controller.driver.DriverViewController;
import bd.uber.zafor.controller.operationsmanager.OperationsManagerController;
import bd.uber.zafor.model.driver.Driver;
import bd.uber.zafor.model.operationsmanager.OperationsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GetStartedController implements Initializable {
    @FXML
    private TextField idTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<UserType> userTypeComboBox;

    private final Alert alert = new Alert(Alert.AlertType.WARNING);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeComboBox.getItems().addAll(UserType.values());
    }

    @FXML
    private void onForgotPassword() {
    }

    @FXML
    private void onCreateNewAccount(MouseEvent mouseEvent) {
        try {
            Scene scene = new Scene(Util.getInstance().getLoader(FXMLFilePath.CREATE_NEW_ACCOUNT_VIEW).load());
            Util.getInstance().showScene(scene, mouseEvent, "");
        } catch (IOException ignored) {
            // log the error
        }
    }


    @FXML
    private void onLogin(ActionEvent actionEvent) {
        UserType userType = userTypeComboBox.getValue();
        User user = null;
        LoginInfo loginInfo;

        if (userType == null) {
            showAlert("Invalid Input!");
            return;
        }

        if (userType == UserType.DRIVER || userType == UserType.OPERATIONS_MANAGER) {
            if ((loginInfo = validateInputs()) == null) {
                showAlert("Invalid Input!");
                return;
            }

            if ((user = loginInfo.verifyLoginInfo()) == null) {
                showAlert("Incorrect Credential!");
                return;
            }
        }

        try {
            switch (userType) {
                case DRIVER:
                    FXMLLoader dLoader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_VIEW);
                    Util.getInstance().showScene(dLoader.load(), actionEvent, "Driver", false);
                    ((DriverViewController) dLoader.getController()).setInitData((Driver) user);
                    break;
                case OPERATIONS_MANAGER:
                    FXMLLoader omLoader = Util.getInstance().getLoader(FXMLFilePath.OPERATIONS_MANAGER_VIEW);
                    Util.getInstance().showScene(omLoader.load(), actionEvent, "Operations Manger", false);
                    ((OperationsManagerController) omLoader.getController()).setInitData((OperationsManager) user);
                    break;
                case PASSENGER:
                    Util.getInstance().showScene(Util.getInstance().getLoader(FXMLFilePath.PASSENGER_PROFILE).load(),
                            actionEvent,
                            "Passenger",
                            false);
                    break;
                case HUMAN_RESOURCE_MANGER:
                    Util.getInstance().showScene(
                            Util.getInstance().getLoader(FXMLFilePath.HRM_Dashboard).load(),
                            actionEvent,
                            "Human Resource Manager",
                            false
                    );
                    break;
                case VEHICLE_MAINTENANCE_Manager:
                    Util.getInstance().showScene(
                            FXMLLoader.load(Objects.requireNonNull(getClass().getResource("redwan/MainScene.fxml"))),
                            actionEvent,
                            "Vehicle Maintenance Manger",
                            false
                    );
                    break;
                case BUSINESS_ACCOUNT_MANAGER:
                    Util.getInstance().showScene(
                            Util.getInstance().getLoader(FXMLFilePath.BUSINESS_ACCOUNT_MANAGER).load(),
                            actionEvent,
                            "Business Account Manager",
                            false
                    );
                    break;
            }
        } catch (Exception e) {
            // log the error
        }
    }

    private LoginInfo validateInputs() {
        try {
            int id = Integer.parseInt(idTextField.getText());
            String password = passwordField.getText();
            if (id > 0 && !password.isEmpty() && userTypeComboBox.getValue() != null) {
                return new LoginInfo(id, password, userTypeComboBox.getValue());
            }
        } catch (Exception ignored) {
            // log error
        }
        return null;
    }

    private void showAlert(String alertText) {
        alert.setContentText(alertText);
        alert.showAndWait();
    }
}