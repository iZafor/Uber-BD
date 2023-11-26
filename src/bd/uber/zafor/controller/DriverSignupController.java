package bd.uber.zafor.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.SignupForm;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DriverSignupController implements Initializable {
    @FXML
    private BorderPane signupBorderPane;

    @FXML
    private Button basicInfoButton;

    @FXML
    private Button drivingLicenseButton;

    @FXML
    private Button vehicleInfoButton;

    @FXML
    private Button vehicleStatusButton;

    @FXML
    private Button identificationInfoButton;

    private ObjectProperty<SignupForm> signupFormProperty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drivingLicenseButton.setDisable(true);
        vehicleInfoButton.setDisable(true);
        vehicleStatusButton.setDisable(true);
        identificationInfoButton.setDisable(true);
    }

    public void setInitData(ObjectProperty<SignupForm> signupFormProperty) {
        this.signupFormProperty = signupFormProperty;
        configureSignupFormProperty();
    }

    private void setSignupForm(SignupForm form) {
        signupFormProperty.setValue(form);
    }

    @FXML
    private void onBasicInfo() {
        setSignupForm(SignupForm.BASIC_INFO);
    }

    @FXML
    private void onVehicleInfo() {
        setSignupForm(SignupForm.VEHICLE_INFO);
    }

    @FXML
    private void onDrivingLicense() {
        setSignupForm(SignupForm.DRIVING_LICENSE_AND_INSURANCE_POLICY);
    }

    @FXML
    private void onVehicleStatus() {
        setSignupForm(SignupForm.VEHICLE_STATUS);
    }

    @FXML
    public void onIdentificationInfo() {
        setSignupForm(SignupForm.IDENTIFICATION_INFO);
    }

    private void configureSignupFormProperty() {
        signupFormProperty.addListener((observable, oldValue, newValue) -> {
            basicInfoButton.getStyleClass().removeAll("selected");
            drivingLicenseButton.getStyleClass().removeAll("selected");
            vehicleInfoButton.getStyleClass().removeAll("selected");
            vehicleStatusButton.getStyleClass().removeAll("selected");
            identificationInfoButton.getStyleClass().removeAll("selected");

            try {
                switch (newValue) {
                    case BASIC_INFO:
                        basicInfoButton.getStyleClass().add("selected");
                        signupBorderPane.setCenter(
                                Util.getInstance()
                                        .getLoader(FXMLFilePath.DRIVER_SIGN_UP_BASIC_INFO_VIEW).load()
                        );
                        break;
                    case DRIVING_LICENSE_AND_INSURANCE_POLICY:
                        drivingLicenseButton.setDisable(false);
                        drivingLicenseButton.getStyleClass().add("selected");
                        signupBorderPane.setCenter(
                                Util.getInstance()
                                        .getLoader(FXMLFilePath.DRIVER_SIGN_UP_DRIVING_LICENSE_VIEW).load()
                        );
                        break;
                    case VEHICLE_INFO:
                        vehicleInfoButton.setDisable(false);
                        vehicleInfoButton.getStyleClass().add("selected");
                        signupBorderPane.setCenter(
                                Util.getInstance()
                                        .getLoader(FXMLFilePath.DRIVER_SIGN_UP_VEHICLE_INFO_VIEW).load()
                        );
                        break;
                    case VEHICLE_STATUS:
                        vehicleStatusButton.setDisable(false);
                        vehicleStatusButton.getStyleClass().add("selected");
                        signupBorderPane.setCenter(
                                Util.getInstance()
                                        .getLoader(FXMLFilePath.DRIVER_SIGN_UP_VEHICLE_STATUS_VIEW).load()
                        );
                        break;
                    case IDENTIFICATION_INFO:
                        identificationInfoButton.setDisable(false);
                        identificationInfoButton.getStyleClass().add("selected");
                        signupBorderPane.setCenter(
                                Util.getInstance()
                                        .getLoader(FXMLFilePath.DRIVER_SIGN_UP_IDENTIFICATION_INFO_VIEW).load()
                        );
                        break;
                }
            } catch (IOException e) {
                // log the error
            }
        });
    }
}