package bd.uber.zafor.controller.driver;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.driver.MaintenanceRequest;
import bd.uber.zafor.model.driver.MaintenanceTimeSlot;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DriverMaintenanceRequestController implements Initializable {
    @FXML
    private DatePicker preferredTimeDatePicker;
    @FXML
    private ComboBox<MaintenanceTimeSlot> timeSlotComboBox;

    private int vehicleInfoId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeSlotComboBox.getItems().addAll(MaintenanceTimeSlot.values());
        preferredTimeDatePicker.setDayCellFactory(datePicker -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
    }

    public void setInitData(int vehicleInfoId) {
        this.vehicleInfoId = vehicleInfoId;
    }

    @FXML
    private void onMakeRequest(ActionEvent event) {
        LocalDate date = preferredTimeDatePicker.getValue();
        if (date == null) {
            Util.getInstance().showWarningMessage("Preferred date cannot be empty!");
            return;
        }

        MaintenanceTimeSlot timeSlot = timeSlotComboBox.getValue();
        if (timeSlot == null) {
            Util.getInstance().showWarningMessage("Preferred time slot cannot be empty!");
            return;
        }

        Util.getInstance().getWorkers().execute(() -> {
            if (Util.getInstance().getDb().addObject(
                    new MaintenanceRequest(
                            vehicleInfoId,
                            date,
                            timeSlot
                    ),
                    BinFilePath.MAINTENANCE_REQUEST
            )) {
                Platform.runLater(() -> {
                    Util.getInstance().showSuccessMessage("Request submitted successfully.");
                    ((Node) event.getSource()).getScene().getWindow().hide();
                });
            } else {
                Platform.runLater(() -> {
                    Util.getInstance().showError("Failed to make request!");
                    ((Node) event.getSource()).getScene().getWindow().hide();
                });
            }
        });
    }
}