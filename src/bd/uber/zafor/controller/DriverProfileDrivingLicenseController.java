package bd.uber.zafor.controller;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.Driver;
import bd.uber.zafor.model.DrivingLicense;
import bd.uber.zafor.model.InsurancePolicy;
import bd.uber.zafor.model.LicenseClass;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DriverProfileDrivingLicenseController implements Initializable {
    @FXML
    private TextField licenseNumberTextField;

    @FXML
    private ComboBox<LicenseClass> licenseClassComboBox;

    @FXML
    private DatePicker drivingLicenseExpirationDatePicker;

    @FXML
    private TextField policyNumberTextField;

    @FXML
    private TextField policyProviderTextField;

    @FXML
    private TextField insuranceCoverageAmountTextField;

    @FXML
    private DatePicker insurancePolicyExpirationDatePicker;

    private Driver driver;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        licenseClassComboBox.getItems().addAll(LicenseClass.values());
        drivingLicenseExpirationDatePicker.setConverter(Util.STRING_CONVERTER_FOR_LOCAL_DATE);
        insurancePolicyExpirationDatePicker.setConverter(Util.STRING_CONVERTER_FOR_LOCAL_DATE);
        drivingLicenseExpirationDatePicker.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
        insurancePolicyExpirationDatePicker.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
    }

    public void setInitData(Driver driver) {
        this.driver = driver;

        DrivingLicense drivingLicense = driver.getDrivingLicense();
        licenseNumberTextField.setText(drivingLicense.getLicenseNumber());
        licenseClassComboBox.setValue(drivingLicense.getLicenseClass());
        drivingLicenseExpirationDatePicker.setValue(drivingLicense.getExpirationDate());

        InsurancePolicy policy = driver.getVehicleInfo().getInsurancePolicy();
        policyNumberTextField.setText(policy.getPolicyNumber());
        policyProviderTextField.setText(policy.getProvider());
        insuranceCoverageAmountTextField.setText(String.valueOf(policy.getCoverageAmount()));
        insurancePolicyExpirationDatePicker.setValue(policy.getExpirationDate());
    }

    @FXML
    private void onSaveChanges() {
        String licenseNumber;
        LicenseClass licenseClass;
        LocalDate licenseExpirationDate;
        String policyNumber;
        String provider;
        float insuranceAmount;
        LocalDate insuranceExpirationDate;
        try {
            licenseNumber = licenseNumberTextField.getText();
            licenseClass = licenseClassComboBox.getValue();
            licenseExpirationDate = drivingLicenseExpirationDatePicker.getValue();

            policyNumber = policyNumberTextField.getText();
            provider = policyProviderTextField.getText();
            insuranceAmount = Float.parseFloat(insuranceCoverageAmountTextField.getText());
            insuranceExpirationDate = insurancePolicyExpirationDatePicker.getValue();
        } catch (NumberFormatException | NullPointerException ignored) {
            showWarning();
            return;
        }

        if (licenseNumber == null || licenseNumber.isEmpty() || licenseClass == null || licenseExpirationDate == null || policyNumber == null || policyNumber.isEmpty() || provider == null || provider.isEmpty() || insuranceAmount == 0 || insuranceExpirationDate == null) {
            showWarning();
            return;
        }

        DrivingLicense drivingLicense = driver.getDrivingLicense();
        drivingLicense.setLicenseNumber(licenseNumber);
        drivingLicense.setLicenseClass(licenseClass);
        drivingLicense.setExpirationDate(licenseExpirationDate);

        InsurancePolicy policy = driver.getVehicleInfo().getInsurancePolicy();
        policy.setPolicyNumber(policyNumber);
        policy.setProvider(provider);
        policy.setCoverageAmount(insuranceAmount);
        policy.setExpirationDate(insuranceExpirationDate);

        Util.getInstance().getWorkers().execute(() -> {
            if (Util.getInstance().getDb().updateObject(driver, BinFilePath.DRIVER, d -> d.getId() == driver.getId())) {
                Platform.runLater(() -> Util.getInstance().showSuccessMessage("Info updated successfully."));
            }else {
                Platform.runLater(() -> Util.getInstance().showError("Failed to update data!"));
            }
        });
    }

    private void showWarning() {
        Util.getInstance().showWarningMessage("Invalid input!\nPlease recheck values.");
    }
}