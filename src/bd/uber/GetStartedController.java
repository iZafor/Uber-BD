package bd.uber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

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
    }

    @FXML
    private void onForgotPassword(MouseEvent mouseEvent) {
    }

    @FXML
    private void onCreateNewAccount(MouseEvent mouseEvent) {
        Util.getInstance().showScene(
                Util.getInstance().getDriverSignUpView(),
                mouseEvent,
                "Driver Signup"
        );
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
            if (id > 0 && password.length() >= 6 && userTypeComboBox.getValue() != null) {
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