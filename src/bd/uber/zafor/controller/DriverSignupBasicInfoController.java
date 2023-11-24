package bd.uber.zafor.controller;

import bd.uber.ContactDetails;
import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.Driver;
import bd.uber.zafor.model.SignupForm;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverSignupBasicInfoController implements Initializable {
    @FXML
    private TextField nameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField primaryPhoneNumberTextField;

    @FXML
    private TextField secondaryPhoneNumberTextField;

    @FXML
    private TextField drivingExperienceTextField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox searchResultVBox;

    private Location tempLocation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restoreData();
        addSearchFunctionality();
        searchResultVBox.visibleProperty().setValue(true);
    }

    @FXML
    private void onShowNextView() {
        if (validateInputs()) {
            Util.getInstance().setSignupForm(SignupForm.DRIVING_LICENSE_AND_INSURANCE_POLICY);
        }
    }

    private boolean validateInputs() {
        String name = nameTextField.getText();
        if (name == null || name.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Name cannot be empty!");
            return false;
        }

        String password = passwordTextField.getText();
        if (password == null || password.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Password cannot be empty!");
            return false;
        }

        String email = emailTextField.getText();
        if (email == null || email.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Email cannot be empty!");
            return false;
        }
        if (!email.contains("@")) {
            Util.getInstance().showWarningMessage("Invalid email format!");
            return false;
        }

        String primaryPhoneNumber = primaryPhoneNumberTextField.getText();
        if (primaryPhoneNumber == null || primaryPhoneNumber.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Primary phone number cannot be empty!");
            return false;
        }

        String secondaryPhoneNumber = secondaryPhoneNumberTextField.getText();
        if (secondaryPhoneNumber == null || secondaryPhoneNumber.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Secondary phone number cannot be empty!");
            return false;
        }

        try {
            Driver driver = Util.getInstance().getSignUpDriver();
            driver.setName(name);
            driver.setPassword(password);
            driver.setContactDetails(new ContactDetails(
                    primaryPhoneNumber,
                    secondaryPhoneNumber,
                    email,
                    tempLocation
            ));
            driver.setExperienceInYears(Integer.parseInt(drivingExperienceTextField.getText()));
            return true;
        } catch (Exception ignored) {
            // log the error
        }
        return false;
    }

    private void addSearchFunctionality() {
        addressTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!scrollPane.focusedProperty().getValue()) {
                scrollPane.visibleProperty().setValue(newValue);
            }
        });
        addressTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchResultVBox.getChildren().clear();
            String lower = newValue.toLowerCase();
            if (lower.isEmpty()) {
                return;
            }
            for (Location location : Util.getInstance().getLocationList()) {
                if (location.getName().toLowerCase().contains(lower)) {
                    Text locationText = new Text(location.getName());
                    locationText.setStyle("-fx-cursor: hand");
                    locationText.setOnMouseClicked(event -> {
                        tempLocation = location;
                        addressTextField.setText(location.getName());
                        scrollPane.visibleProperty().setValue(false);
                    });
                    searchResultVBox.getChildren().add(locationText);
                }
            }
        });
    }

    private void restoreData() {
        try {
            Driver driver = Util.getInstance().getSignUpDriver();
            nameTextField.setText(driver.getName());
            passwordTextField.setText(driver.getPassword());
            confirmPasswordTextField.setText(driver.getPassword());
            emailTextField.setText(driver.getContactDetails().getEmail());
            addressTextField.setText(driver.getContactDetails().getAddress().getName());
            primaryPhoneNumberTextField.setText(driver.getContactDetails().getPrimaryPhoneNumber());
            secondaryPhoneNumberTextField.setText(driver.getContactDetails().getSecondaryPhoneNumber());
            drivingExperienceTextField.setText(String.valueOf(driver.getExperienceInYears()));
        } catch (Exception ignored) {
            // log the error
        }
    }
}