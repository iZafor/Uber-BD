package bd.uber.zafor.controller.driver;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.driver.DrivingLicense;
import bd.uber.zafor.model.driver.InsurancePolicy;
import bd.uber.zafor.model.driver.LicenseClass;
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

    private DrivingLicense drivingLicense;
    private InsurancePolicy insurancePolicy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        licenseClassComboBox.getItems().addAll(LicenseClass.values());
        drivingLicenseExpirationDatePicker.setConverter(Util.STRING_CONVERTER_FOR_LOCAL_DATE);
        insurancePolicyExpirationDatePicker.setConverter(Util.STRING_CONVERTER_FOR_LOCAL_DATE);
        drivingLicenseExpirationDatePicker.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
        insurancePolicyExpirationDatePicker.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
    }

    public void setInitData(DrivingLicense drivingLicense, InsurancePolicy insurancePolicy) {
        this.drivingLicense = drivingLicense;
        licenseNumberTextField.setText(drivingLicense.getLicenseNumber());
        licenseClassComboBox.setValue(drivingLicense.getLicenseClass());
        drivingLicenseExpirationDatePicker.setValue(drivingLicense.getExpirationDate());

        this.insurancePolicy = insurancePolicy;
        policyNumberTextField.setText(insurancePolicy.getPolicyNumber());
        policyProviderTextField.setText(insurancePolicy.getProvider());
        insuranceCoverageAmountTextField.setText(String.valueOf(insurancePolicy.getCoverageAmount()));
        insurancePolicyExpirationDatePicker.setValue(insurancePolicy.getExpirationDate());
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
            Util.getInstance().showWarningMessage("Invalid input!\nPlease recheck values.");
            return;
        }

        if (licenseNumber.isEmpty() || licenseExpirationDate == null || policyNumber.isEmpty() || provider.isEmpty() || insuranceAmount <= 0 || insuranceExpirationDate == null) {
            Util.getInstance().showWarningMessage("Invalid input!\nPlease recheck values.");
            return;
        }

        drivingLicense.setLicenseNumber(licenseNumber);
        drivingLicense.setLicenseClass(licenseClass);
        drivingLicense.setExpirationDate(licenseExpirationDate);

        insurancePolicy.setProvider(provider);
        insurancePolicy.setCoverageAmount(insuranceAmount);
        insurancePolicy.setExpirationDate(insuranceExpirationDate);
        insurancePolicy.setPolicyNumber(policyNumber);

        Util.getInstance().getWorkers().execute(() -> {
            if (!Util.getInstance().getDb().updateObjectFile(
                    drivingLicense,
                    BinFilePath.DRIVING_LICENSE,
                    d -> d.getDrivingLicenseId() == drivingLicense.getDrivingLicenseId(),
                    false
            ) ||
                    !Util.getInstance().getDb().updateObjectFile(
                            insurancePolicy,
                            BinFilePath.INSURANCE_POLICY,
                            i -> i.getInsurancePolicyId() == insurancePolicy.getInsurancePolicyId(),
                            false
                    )) {
                Platform.runLater(() ->
                        Util.getInstance().showError("Failed to update data!")
                );
            } else {
                Platform.runLater(() ->
                        Util.getInstance().showError("Data updated successfully.")
                );
            }
        });
    }
}