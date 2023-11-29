package bd.uber.zafor.controller.driver;

import bd.uber.ContactDetails;
import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.driver.Driver;
import bd.uber.zafor.model.driver.SignupForm;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

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
            ContactDetails contactDetails = Util.getInstance().getSignupDriverContactDetails();
            contactDetails.setEmail(email);
            contactDetails.setLocationId(tempLocation.getLocationId());
            contactDetails.setPrimaryPhoneNumber(primaryPhoneNumber);
            contactDetails.setSecondaryPhoneNumber(secondaryPhoneNumber);
            driver.setExperienceInYears(Integer.parseInt(drivingExperienceTextField.getText()));
            return true;
        } catch (Exception ignored) {
            // log the error
        }
        return false;
    }

    private void addSearchFunctionality() {
        addressTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                String currentLocation = addressTextField.getText();
                if (currentLocation == null || currentLocation.isEmpty() || Util.getInstance().getLocationList().stream().noneMatch(location -> location.getName().equals(currentLocation))) {
                    addressTextField.clear();
                }
                addressTextField.getParent().requestFocus();
            }
        });
        addressTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!scrollPane.isFocused()) {
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
                    Label label = new Label(location.getName());
                    label.getStyleClass().add("search-location");
                    label.setOnMouseClicked(event -> {
                        tempLocation = location;
                        addressTextField.setText(location.getName());
                        scrollPane.setVisible(false);
                    });
                    searchResultVBox.getChildren().add(label);
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

            ContactDetails contactDetails = Util.getInstance().getSignupDriverContactDetails();
            emailTextField.setText(contactDetails.getEmail());
            addressTextField.setText(Util.getInstance().getLocationList().stream().filter(l -> l.getLocationId() == contactDetails.getLocationId()).findFirst().get().getName());
            primaryPhoneNumberTextField.setText(contactDetails.getPrimaryPhoneNumber());
            secondaryPhoneNumberTextField.setText(contactDetails.getSecondaryPhoneNumber());
            drivingExperienceTextField.setText(String.valueOf(driver.getExperienceInYears()));
        } catch (Exception ignored) {
            // log the error
        }
    }
}