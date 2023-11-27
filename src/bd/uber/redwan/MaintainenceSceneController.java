/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bd.uber.redwan;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Redwan
 */
public class MaintainenceSceneController implements Initializable {

    @FXML
    private TextArea viewMaintainenceRequests;
    @FXML
    private TextArea viewMaintainenceInspection;
    @FXML
    private TextArea showReport;
    @FXML
    private DatePicker datePickerId;
    @FXML
    private TextArea showScheduledMaintainence;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void scheduleMaintainence(ActionEvent event) {

        String request = viewMaintainenceRequests.getText();
        LocalDate selectedDate = datePickerId.getValue();
    
        showScheduledMaintainence.setText("Maintenance scheduled for: " + selectedDate.toString() + "\n Done! " + request);
   
        showAlert(AlertType.INFORMATION, "Success", "Maintenance Scheduled Successfully!");
}
    
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
}

    @FXML
    private void generateReportFromMaintenanceInspectionButton(ActionEvent event) {
    }

    @FXML
    private void maintainenceInspectionButton(ActionEvent event) {
    }

    @FXML
    private void viewReport(ActionEvent event) {
    }
    
}
