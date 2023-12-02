package bd.uber.zafor.controller.driver;

import bd.uber.Util;
import bd.uber.zafor.model.driver.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
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
            VehicleStatus vehicleStatus = Util.getInstance().getSignupDriverVehicleStatus();
            odometerReadingTextField.setText(String.valueOf(vehicleStatus.getOdometerReading()));
            fuelLevelComboBox.setValue(vehicleStatus.getFuelLevel());
            engineoilLevelComboBox.setValue(vehicleStatus.getEngineOilLevel());
            interiorCleanlinessTextField.setText(vehicleStatus.getInteriorCleanliness());
            tirePressureTextField.setText(String.valueOf(vehicleStatus.getTirePressure()));
            exteriorDamageComboBox.setValue(vehicleStatus.getExteriorDamage());
            mechanicalIssuesComboBox.setValue(vehicleStatus.getMechanicalIssues());
        } catch (Exception ignored) {

        }
    }

    private boolean validateInputs() {
        int odometerReading;
        try {
            odometerReading = Integer.parseInt(odometerReadingTextField.getText());
            if (odometerReading < 0) {
                Util.getInstance().showWarningMessage("Odometer reading cannot be less than 0!");
                return false;
            }
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
            if (tirePressure <= 0) {
                Util.getInstance().showWarningMessage("Tire pressure cannot be less or eqaul to 0!");
                return false;
            }
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Invalid tire pressure!");
            return false;
        }

        VehicleStatus vehicleStatus = Util.getInstance().getSignupDriverVehicleStatus();
        vehicleStatus.setOdometerReading(odometerReading);
        vehicleStatus.setFuelLevel(fuelLevel);
        vehicleStatus.setTirePressure(tirePressure);
        vehicleStatus.setExteriorDamage(exteriorDamage);
        vehicleStatus.setInteriorCleanliness(interiorCleanlinessTextField.getText());
        vehicleStatus.setMechanicalIssues(mechanicalIssue);
        vehicleStatus.setEngineOilLevel(engineOilLevel);

        return true;
    }
}