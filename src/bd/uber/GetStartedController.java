package bd.uber;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

public class GetStartedController implements Initializable {
    @FXML
    private TextField idTextField;
    @FXML
    public PasswordField passwordField;
    @FXML
    ComboBox<UserType> userTypeComboBox;

    private final Alert alert = new Alert(Alert.AlertType.WARNING);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeComboBox.getItems().addAll(UserType.values());
        Platform.runLater(this::configureAlert);
    }

    @FXML
    private void onForgotPassword(MouseEvent mouseEvent) {
    }

    @FXML
    private void onCreateNewAccount(MouseEvent mouseEvent) {
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
    }

    private LoginInfo validateInputs() {
        try {
            int id = Integer.parseInt(idTextField.getText());
            String password = passwordField.getText();
            if (id > 0 && !password.trim().isEmpty() && password.length() >= 6 && userTypeComboBox.getValue() != null) {
                return new LoginInfo(id, password, userTypeComboBox.getValue());
            }
        } catch (Exception ignored) {
            // log error
        }
        return null;
    }

    private void configureAlert() {
        alert.initOwner(idTextField.getScene().getWindow());
        alert.initModality(Modality.WINDOW_MODAL);
    }

    private void showAlert(String alertText) {
        alert.setContentText(alertText);
        alert.showAndWait();
    }
}