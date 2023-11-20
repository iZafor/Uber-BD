package bd.uber.zafor.controller;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.*;
import javafx.event.ActionEvent;
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
        fuelLevelComboBox.getItems().addAll(FuelLevel.values());
        engineoilLevelComboBox.getItems().addAll(EngineOilLevel.values());
        exteriorDamageComboBox.getItems().addAll(ExteriorDamage.values());
        mechanicalIssuesComboBox.getItems().addAll(MechanicalIssue.values());
    }

    @FXML
    private void onSignup(ActionEvent event) {
        if (validateInputs()) {
            // Store to the bin file
            Driver driver = Util.getInstance().getSignUpDriver();
            Util.getInstance().getDb().addUser(
                    driver,
                    BinFilePath.DRIVER
            );

            // Reset the signup instance
            Util.getInstance().setSignUpDriver(null);

            // Switch to the get started view
            Util.getInstance().showSuccessMessage("Signup successful.\n Your login id is " + driver.getId());
            try {
                Util.getInstance().showScene(
                        Util.getInstance().getLoader(FXMLFilePath.GET_STARTED_VIEW).load(),
                        event,
                        "Get Started"
                );
            } catch (Exception ignored) {
                // log the error
            }
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