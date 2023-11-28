package bd.uber.zafor.controller.driver;

import bd.uber.Util;
import bd.uber.zafor.model.driver.*;
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
    private DatePicker drivingLicenseExpirationDatePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restoreData();
        licenseClassComboBox.getItems().addAll(LicenseClass.values());
        insurancePolicyExpirationDatePicker.setConverter(Util.STRING_CONVERTER_FOR_LOCAL_DATE);
        drivingLicenseExpirationDatePicker.setConverter(Util.STRING_CONVERTER_FOR_LOCAL_DATE);
        insurancePolicyExpirationDatePicker.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
        drivingLicenseExpirationDatePicker.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
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

        LocalDate drivingLicenseExpirationDate = drivingLicenseExpirationDatePicker.getValue();
        if (drivingLicenseExpirationDate == null) {
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
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Provide a valid coverage amount!");
            return false;
        }

        LocalDate insurancePolicyExpirationDate = insurancePolicyExpirationDatePicker.getValue();
        if (insurancePolicyExpirationDate == null) {
            Util.getInstance().showWarningMessage("Insurance policy expiration date cannot be avoided!");
            return false;
        }

        DrivingLicense drivingLicense = Util.getInstance().getSignUpDriverDrivingLicense();
        drivingLicense.setLicenseNumber(licenseNumber);
        drivingLicense.setExpirationDate(drivingLicenseExpirationDate);
        drivingLicense.setLicenseClass(licenseClass);

        InsurancePolicy insurancePolicy = Util.getInstance().getSignUpDriverInsurancePolicy();
        insurancePolicy.setPolicyNumber(policyNumber);
        insurancePolicy.setProvider(insuranceProvider);
        insurancePolicy.setCoverageAmount(insuranceCoverageAmount);
        insurancePolicy.setExpirationDate(insurancePolicyExpirationDate);
        return true;
    }

    private void restoreData() {
        try {
            DrivingLicense drivingLicense = Util.getInstance().getSignUpDriverDrivingLicense();
            // DrivingLicense
            licenseNumberTextField.setText(drivingLicense.getLicenseNumber());
            licenseClassComboBox.setValue(drivingLicense.getLicenseClass());
            drivingLicenseExpirationDatePicker.setValue(drivingLicense.getExpirationDate());

            // InsurancePolicy
            InsurancePolicy insurancePolicy = Util.getInstance().getSignUpDriverInsurancePolicy();
            policyNumberTextField.setText(insurancePolicy.getPolicyNumber());
            policyProviderTextField.setText(insurancePolicy.getProvider());
            insuranceCoverageAmountTextField.setText(String.valueOf(insurancePolicy.getCoverageAmount()));
            insurancePolicyExpirationDatePicker.setValue(insurancePolicy.getExpirationDate());
        } catch (NullPointerException ignored) {

        }
    }
}