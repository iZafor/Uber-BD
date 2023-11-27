package bd.uber.zafor.controller.driver;

import bd.uber.Util;
import bd.uber.zafor.model.driver.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DriverSignUpVehicleStatusController implements Initializable {
    @FXML
    private TextField odometerReadingTextField;

    @FXML
    private ComboBox<FuelLevel> fuelLevelComboBox;

    @FXML
    private ComboBox<EngineOilLevel> engineoilLevelComboBox;

    @FXML
    private TextField interiorCleanlinessTextField;

    @FXML
    private TextField tirePressureTextField;

    @FXML
    private ComboBox<ExteriorDamage> exteriorDamageComboBox;

    @FXML
    private ComboBox<MechanicalIssue> mechanicalIssuesComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restoreData();
        fuelLevelComboBox.getItems().addAll(FuelLevel.values());
        engineoilLevelComboBox.getItems().addAll(EngineOilLevel.values());
        exteriorDamageComboBox.getItems().addAll(ExteriorDamage.values());
        mechanicalIssuesComboBox.getItems().addAll(MechanicalIssue.values());
    }

    @FXML
    private void onShowNextView() {
        if (validateInputs()) {
            Util.getInstance().setSignupForm(SignupForm.IDENTIFICATION_INFO);
        }
    }

    private void restoreData() {
        try {
            Driver driver = Util.getInstance().getSignUpDriver();
            odometerReadingTextField.setText(String.valueOf(driver.getVehicleInfo().getVehicleStatus().getOdometerReading()));
            fuelLevelComboBox.setValue(driver.getVehicleInfo().getVehicleStatus().getFuelLevel());
            engineoilLevelComboBox.setValue(driver.getVehicleInfo().getVehicleStatus().getEngineOilLevel());
            interiorCleanlinessTextField.setText(driver.getVehicleInfo().getVehicleStatus().getInteriorCleanliness());
            tirePressureTextField.setText(String.valueOf(driver.getVehicleInfo().getVehicleStatus().getTirePressure()));
            exteriorDamageComboBox.setValue(driver.getVehicleInfo().getVehicleStatus().getExteriorDamage());
            mechanicalIssuesComboBox.setValue(driver.getVehicleInfo().getVehicleStatus().getMechanicalIssues());
        } catch (Exception ignored) {

        }
    }

    private boolean validateInputs() {
        int odometerReading;
        try {
            odometerReading = Integer.parseInt(odometerReadingTextField.getText());
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Provide a valid number for odometer reading!");
            return false;
        }

        FuelLevel fuelLevel = fuelLevelComboBox.getValue();
        if (fuelLevel == null) {
            Util.getInstance().showWarningMessage("Fuel level cannot be empty!");
            return false;
        }

        EngineOilLevel engineOilLevel = engineoilLevelComboBox.getValue();
        if (engineOilLevel == null) {
            Util.getInstance().showWarningMessage("Engine oil level cannot be empty!");
            return false;
        }

        ExteriorDamage exteriorDamage = exteriorDamageComboBox.getValue();
        if (exteriorDamage == null) {
            Util.getInstance().showWarningMessage("Exterior damage cannot be empty!");
            return false;
        }

        MechanicalIssue mechanicalIssue = mechanicalIssuesComboBox.getValue();
        if (mechanicalIssue == null) {
            Util.getInstance().showWarningMessage("Mechanical issued cannot be empty!");
            return false;
        }

        float tirePressure;
        try {
            tirePressure = Float.parseFloat(tirePressureTextField.getText());
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Provide a valid number for tire pressure!");
            return false;
        }

        Driver driver = Util.getInstance().getSignUpDriver();
        driver.getVehicleInfo().setVehicleStatus(
                new VehicleStatus(
                        odometerReading,
                        fuelLevel,
                        tirePressure,
                        exteriorDamage,
                        interiorCleanlinessTextField.getText(),
                        mechanicalIssue,
                        engineOilLevel,
                        LocalDate.now()
                )
        );

        return true;
    }
}