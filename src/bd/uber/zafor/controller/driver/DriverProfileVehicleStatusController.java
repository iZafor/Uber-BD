package bd.uber.zafor.controller.driver;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.driver.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverProfileVehicleStatusController implements Initializable {
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

    private VehicleStatus vehicleStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fuelLevelComboBox.getItems().addAll(FuelLevel.values());
        engineoilLevelComboBox.getItems().addAll(EngineOilLevel.values());
        exteriorDamageComboBox.getItems().addAll(ExteriorDamage.values());
        mechanicalIssuesComboBox.getItems().addAll(MechanicalIssue.values());
    }


    public void setInitData(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;

        odometerReadingTextField.setText(String.valueOf(vehicleStatus.getOdometerReading()));
        fuelLevelComboBox.setValue(vehicleStatus.getFuelLevel());
        engineoilLevelComboBox.setValue(vehicleStatus.getEngineOilLevel());
        interiorCleanlinessTextField.setText(vehicleStatus.getInteriorCleanliness());
        exteriorDamageComboBox.setValue(vehicleStatus.getExteriorDamage());
        mechanicalIssuesComboBox.setValue(vehicleStatus.getMechanicalIssues());
        tirePressureTextField.setText(String.valueOf(vehicleStatus.getTirePressure()));
    }

    @FXML
    private void onSaveChanges(ActionEvent event) {
        int odometerReading;
        float tirePressure;
        try {
            odometerReading = Integer.parseInt(odometerReadingTextField.getText());
            tirePressure = Float.parseFloat(tirePressureTextField.getText());
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Invalid input!\nPlease recheck values.");
            return;
        }

        FuelLevel fuelLevel = fuelLevelComboBox.getValue();
        EngineOilLevel engineOilLevel = engineoilLevelComboBox.getValue();
        ExteriorDamage exteriorDamage = exteriorDamageComboBox.getValue();
        String interiorCleanliness = interiorCleanlinessTextField.getText();

        vehicleStatus.setOdometerReading(odometerReading);
        vehicleStatus.setFuelLevel(fuelLevel);
        vehicleStatus.setInteriorCleanliness(interiorCleanliness);
        vehicleStatus.setEngineOilLevel(engineOilLevel);
        vehicleStatus.setExteriorDamage(exteriorDamage);
        vehicleStatus.setTirePressure(tirePressure);

        Util.getInstance().updateObject(
                vehicleStatus,
                BinFilePath.VEHICLE_STATUS,
                v -> v.getVehicleStatusId() == vehicleStatus.getVehicleStatusId(),
                "Vehicle status updated successfully",
                "Failed to update vehicle status!"
        );
    }
}