package bd.uber.zafor.controller.driver;

import bd.uber.Util;
import bd.uber.zafor.model.driver.SignupForm;
import bd.uber.zafor.model.driver.VehicleInfo;
import bd.uber.zafor.model.driver.VehicleType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverSignUpVehicleInfoController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restoreData();
        vehicleTypeComboBox.getItems().addAll(VehicleType.values());
    }

    @FXML
    private void onShowNextView() {
        if (validateInput()) {
            Util.getInstance().setSignupForm(SignupForm.VEHICLE_STATUS);
        }
    }

    private boolean validateInput() {
        String model = modelTextField.getText();
        if (model == null || model.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Model cannot be empty!");
            return false;
        }

        String color = colorTextField.getText();
        if (color == null || color.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Color cannot be empty!");
            return false;
        }


        String registrationNumber = registrationNumberTextField.getText();
        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Registration number cannot be empty!");
            return false;
        }

        int passengerCapacity;
        try {
            passengerCapacity = Integer.parseInt(passengerCapacityTextField.getText());
            if (passengerCapacity <= 0) {
                Util.getInstance().showWarningMessage("Passenger capacity cannot be less or equal to 0!");
                return false;
            }
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Invalid passenger capacity!");
            return false;
        }

        int numberOfEngines;
        try {
            numberOfEngines = Integer.parseInt(passengerCapacityTextField.getText());
            if (numberOfEngines <= 0) {
                Util.getInstance().showWarningMessage("Number of engines cannot be less or equal to 0!");
                return false;
            }
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Invalid engines numbers!");
            return false;
        }

        VehicleType vehicleType = vehicleTypeComboBox.getValue();
        if (vehicleType == null) {
            Util.getInstance().showWarningMessage("Vehicle type cannot be empty!");
            return false;
        }

        VehicleInfo vehicleInfo = Util.getInstance().getSignupDriverVehicleInfo();
        vehicleInfo.setModel(model);
        vehicleInfo.setColor(color);
        vehicleInfo.setRegistrationNumber(registrationNumber);
        vehicleInfo.setPassengerCapacity(passengerCapacity);
        vehicleInfo.setNumberOfEngines(numberOfEngines);
        vehicleInfo.setVehicleType(vehicleType);

        return true;
    }

    private void restoreData() {
        try {
            VehicleInfo vehicleInfo = Util.getInstance().getSignupDriverVehicleInfo();
            int passengerCapacity = vehicleInfo.getPassengerCapacity();
            int numberOfEngines = vehicleInfo.getNumberOfEngines();
            modelTextField.setText(vehicleInfo.getModel());
            colorTextField.setText(vehicleInfo.getColor());
            registrationNumberTextField.setText(vehicleInfo.getRegistrationNumber());
            passengerCapacityTextField.setText(passengerCapacity == 0 ? "" : String.valueOf(passengerCapacity));
            numberOfEnginesTextField.setText(numberOfEngines == 0 ? "" : String.valueOf(numberOfEngines));
            vehicleTypeComboBox.setValue(vehicleInfo.getVehicleType());
        } catch (Exception ignored) {
            // log the error
        }
    }
}