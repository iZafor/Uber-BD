/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bd.uber.redwan;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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
        
    @FXML
    private void backButton(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.show();        
    }        
    
    
}
