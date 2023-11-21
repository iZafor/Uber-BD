package bd.uber.zafor.controller;

import bd.uber.Util;
import bd.uber.zafor.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DriverSignUpDrivingLicenseController implements Initializable {
    @FXML
    private TextField licenseNumberTextField;

    @FXML
    private ComboBox<LicenseClass> licenseClassComboBox;

    @FXML
    private TextField policyNumberTextField;

    @FXML
    private TextField policyProviderTextField;

    @FXML
    private TextField insuranceCoverageAmountTextField;

    @FXML
    private DatePicker insurancePolicyExpirationDatePicker;

    @FXML
    private DatePicker divingLicenseExpirationDatePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restoreData();
        licenseClassComboBox.getItems().addAll(LicenseClass.values());
    }

    @FXML
    private void onShowNextView() {
        if (validateInputs()) {
            Util.getInstance().setSignupForm(SignupForm.VEHICLE_INFO);
        }
    }

    private boolean validateInputs() {
        // DrivingLicense
        String licenseNumber = licenseNumberTextField.getText();
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("License number cannot be empty!");
            return false;
        }

        LicenseClass licenseClass = licenseClassComboBox.getValue();
        if (licenseClass == null) {
            Util.getInstance().showWarningMessage("License Class cannot be avoided!");
            return false;
        }

        LocalDate divingLicenseExpirationDate = divingLicenseExpirationDatePicker.getValue();
        if (divingLicenseExpirationDate == null) {
            Util.getInstance().showWarningMessage("Driving License expiration date cannot be avoided!");
            return false;
        }


        // InsurancePolicy
        String policyNumber = policyNumberTextField.getText();
        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Policy number cannot be empty!");
            return false;
        }

        String insuranceProvider = policyProviderTextField.getText();
        if (insuranceProvider == null || insuranceProvider.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Insurance provider cannot be empty!");
            return false;
        }

        float insuranceCoverageAmount;
        try {
            insuranceCoverageAmount = Float.parseFloat(insuranceCoverageAmountTextField.getText());
        }catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Provide a valid coverage amount!");
            return false;
        }

        LocalDate insurancePolicyExpirationDate = insurancePolicyExpirationDatePicker.getValue();
        if (insurancePolicyExpirationDate == null) {
            Util.getInstance().showWarningMessage("Insurance policy expiration date cannot be avoided!");
            return false;
        }

        Driver driver = Util.getInstance().getSignUpDriver();
        driver.setDrivingLicense(new DrivingLicense(
                licenseNumber,
                divingLicenseExpirationDate,
                licenseClass
        ));
        driver.getVehicleInfo().setInsurancePolicy(
                new InsurancePolicy(
                        policyNumber,
                        insuranceProvider,
                        insuranceCoverageAmount,
                        insurancePolicyExpirationDate
                )
        );
        return true;
    }

    private void restoreData() {
        try {
            Driver driver = Util.getInstance().getSignUpDriver();
            // DrivingLicense
            licenseNumberTextField.setText(driver.getDrivingLicense().getLicenseNumber());
            licenseClassComboBox.setValue(driver.getDrivingLicense().getLicenseClass());
            divingLicenseExpirationDatePicker.setValue(driver.getDrivingLicense().getExpirationDate());

            // InsurancePolicy
            policyNumberTextField.setText(driver.getVehicleInfo().getInsurancePolicy().getPolicyNumber());
            policyProviderTextField.setText(driver.getVehicleInfo().getInsurancePolicy().getProvider());
            insuranceCoverageAmountTextField.setText(String.valueOf(driver.getVehicleInfo().getInsurancePolicy().getCoverageAmount()));
            insurancePolicyExpirationDatePicker.setValue(driver.getVehicleInfo().getInsurancePolicy().getExpirationDate());
        }catch (NullPointerException ignored) {

        }
    }
}