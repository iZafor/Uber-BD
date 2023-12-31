package bd.uber.zafor.controller.driver;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.driver.VehicleInfo;
import bd.uber.zafor.model.driver.VehicleType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverProfileVehicleInfoController implements Initializable {
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField colorTextField;
    @FXML
    private TextField registrationNumberTextField;
    @FXML
    private TextField passengerCapacityTextField;
    @FXML
    private TextField numberOfEnginesTextField;
    @FXML
    private ComboBox<VehicleType> vehicleTypeComboBox;

    private VehicleInfo vehicleInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleTypeComboBox.getItems().addAll(VehicleType.values());
    }

    public void setInitData(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
        modelTextField.setText(vehicleInfo.getModel());
        colorTextField.setText(vehicleInfo.getColor());
        registrationNumberTextField.setText(vehicleInfo.getRegistrationNumber());
        passengerCapacityTextField.setText(String.valueOf(vehicleInfo.getPassengerCapacity()));
        numberOfEnginesTextField.setText(String.valueOf(vehicleInfo.getNumberOfEngines()));
        vehicleTypeComboBox.setValue(vehicleInfo.getVehicleType());
    }

    @FXML
    private void onSaveChanges() {
        String model = modelTextField.getText();
        String color = colorTextField.getText();
        String registrationNumber = registrationNumberTextField.getText();

        int engines;
        int passengerCapacity;
        try {
            passengerCapacity = Integer.parseInt(passengerCapacityTextField.getText());
            engines = Integer.parseInt(numberOfEnginesTextField.getText());
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Invalid input!\nPlease recheck values.");
            return;
        }

        VehicleType vehicleType = vehicleTypeComboBox.getValue();

        if (model == null || model.isEmpty() || color == null || color.isEmpty() || registrationNumber == null || registrationNumber.isEmpty() || engines <= 0 || passengerCapacity <= 0 || vehicleType == null) {
            Util.getInstance().showWarningMessage("Invalid input!\nPlease recheck values.");
            return;
        }

        vehicleInfo.setModel(model);
        vehicleInfo.setColor(color);
        vehicleInfo.setRegistrationNumber(registrationNumber);
        vehicleInfo.setNumberOfEngines(engines);
        vehicleInfo.setPassengerCapacity(passengerCapacity);
        vehicleInfo.setVehicleType(vehicleType);

        Util.getInstance().updateObject(
                vehicleInfo,
                BinFilePath.VEHICLE_INFO,
                v -> v.getVehicleInfoId() == vehicleInfo.getVehicleInfoId(),
                "Vehicle info updated successfully.",
                "Failed to update vehicle info!"
        );
    }
}