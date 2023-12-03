package bd.uber;

import bd.uber.zafor.controller.driver.DriverViewController;
import bd.uber.zafor.model.driver.Driver;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

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

        if (loginInfo.verifyLoginInfo() == null) {
            showAlert("Incorrect Credential!");
            return;
        }
     try{       
        Parent parent = FXMLLoader.load(getClass().getResource("redwan/MainScene.fxml")) ;
        Util.getInstance().showScene(parent, actionEvent,"", false);
     }
     catch (Exception ignored){}
    
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