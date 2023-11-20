package bd.uber.zafor.controller;

import bd.uber.Util;
import bd.uber.zafor.model.Driver;
import bd.uber.zafor.model.SignupForm;
import bd.uber.zafor.model.VehicleType;
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
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Passenger capacity cannot be empty!");
            return false;
        }

        int numberOfEngines;
        try {
            numberOfEngines = Integer.parseInt(passengerCapacityTextField.getText());
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Provide a valid number for engines!");
            return false;
        }

        VehicleType vehicleType = vehicleTypeComboBox.getValue();
        if (vehicleType == null) {
            Util.getInstance().showWarningMessage("Vehicle type cannot be empty!");
            return false;
        }

        Driver driver = Util.getInstance().getSignUpDriver();
        driver.getVehicleInfo().setModel(model);
        driver.getVehicleInfo().setColor(color);
        driver.getVehicleInfo().setRegistrationNumber(registrationNumber);
        driver.getVehicleInfo().setPassengerCapacity(passengerCapacity);
        driver.getVehicleInfo().setNumberOfEngines(numberOfEngines);
        driver.getVehicleInfo().setVehicleType(vehicleType);

        return true;
    }

    private void restoreData() {
        try {
            Driver driver = Util.getInstance().getSignUpDriver();
            modelTextField.setText(driver.getVehicleInfo().getModel());
            colorTextField.setText(driver.getVehicleInfo().getColor());
            registrationNumberTextField.setText(driver.getVehicleInfo().getRegistrationNumber());
            passengerCapacityTextField.setText(String.valueOf(driver.getVehicleInfo().getPassengerCapacity()));
            numberOfEnginesTextField.setText(String.valueOf(driver.getVehicleInfo().getNumberOfEngines()));
            vehicleTypeComboBox.setValue(driver.getVehicleInfo().getVehicleType());
        } catch (Exception ignored) {
            // log the error
        }
    }
}