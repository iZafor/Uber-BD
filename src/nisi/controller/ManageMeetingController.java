/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nisi.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class ManageMeetingController implements Initializable {

    @FXML
    private CheckBox operationAdministatorCheckBox;
    @FXML
    private CheckBox supportRepresentativeCheckBox;
    @FXML
    private CheckBox vehicleMaintenanceTeamCheckBox;
    @FXML
    private CheckBox businessAccountManagerCheckBox;
    @FXML
    private CheckBox paymentProcessorCheckBox;
    @FXML
    private TextArea arrangeMeetingTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendButtonOnClick(ActionEvent event) throws IOException {
        if (operationAdministatorCheckBox.isSelected())
        {
            File dir = new File("Signup Info");
        if(!dir.exists()) {
            dir.mkdir();
        }               
        FileWriter fw = new FileWriter(new File(dir, "Notification for LC Officer.txt"), true);
        fw.write(arrangeMeetingTextArea.getText() + "\n");
        fw.close();
                
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("your message has been sent");
        alert.showAndWait();
        }
        
        
        if (businessAccountManagerCheckBox.isSelected()){
             
        FileWriter fw = new FileWriter(new File("Notifications for Accountant Officer.txt"), true);
        fw.write(arrangeMeetingTextArea.getText() + "\n");
        fw.close();
                
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("your message has been sent");
        alert.showAndWait();
        }
        if (supportRepresentativeCheckBox.isSelected()){
             
        FileWriter fw = new FileWriter(new File("Notifications for Accountant Officer.txt"), true);
        fw.write(arrangeMeetingTextArea.getText() + "\n");
        fw.close();
                
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("your message has been sent");
        alert.showAndWait();
        }
        if (vehicleMaintenanceTeamCheckBox.isSelected()){
             
        FileWriter fw = new FileWriter(new File("Notifications for Accountant Officer.txt"), true);
        fw.write(arrangeMeetingTextArea.getText() + "\n");
        fw.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("your message has been sent");
        alert.showAndWait();
        }
    }

    @FXML
    private void goBackButtonOnClick(ActionEvent event) throws IOException {
//        Parent mainLCManagementLogInFormParent = FXMLLoader.load(getClass().getResource("HRM_Dashboard.fxml"));        
//        Scene mainLCManagementLogInFormScene = new Scene(mainLCManagementLogInFormParent);        
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
//        stage.setScene(mainLCManagementLogInFormScene);
//        stage.show();
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.HRM_Dashboard);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "HRM_Dashboard",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
