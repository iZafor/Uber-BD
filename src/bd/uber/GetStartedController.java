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
import java.util.ResourceBundle;

public class
GetStartedController implements Initializable {
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
    private void onForgotPassword(MouseEvent mouseEvent) {
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
        LoginInfo loginInfo;
        if ((loginInfo = validateInputs()) == null) {
            showAlert("Invalid Input!");
            return;
        }

        User user;
        if ((user = loginInfo.verifyLoginInfo()) == null) {
            showAlert("Incorrect Credential!");
            return;
        }

        FXMLLoader loader;
        try {
            switch (userTypeComboBox.getValue()) {
                case DRIVER:
                    loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_VIEW);
                    Util.getInstance().showScene(
                            loader.load(),
                            actionEvent,
                            "Driver",
                            false
                    );
                    ((DriverViewController) loader.getController()).setInitData((Driver) user);
                    break;
                case OPERATIONS_MANAGER:
                    loader = Util.getInstance().getLoader(FXMLFilePath.OPERATIONS_MANAGER_VIEW);
                    Util.getInstance().showScene(
                            loader.load(),
                            actionEvent,
                            "Operations Manager",
                            false
                    );
                    ((OperationsManagerController) loader.getController()).setInitData((OperationsManager) user);
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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