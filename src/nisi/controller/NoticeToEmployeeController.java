/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nisi.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import java.io.BufferedWriter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class NoticeToEmployeeController implements Initializable {

    @FXML
    private TextField tropicNameTextField;
    @FXML
    private TextArea noticeDetailsTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submiteButtonOnClick(ActionEvent event) {
        String topic = tropicNameTextField.getText();
        String details = noticeDetailsTextArea.getText();
        String data = topic + "\t" + details + "\n".toString();
        try {
            FileWriter fw = new FileWriter("HR_officer_notice.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.newLine();
            bw.close();
            fw.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBackButtonOnClick(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("HRM_Dashboard.fxml"));
//        Scene auditScene = new Scene(root);
//        Stage nstage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        nstage.setScene(auditScene);
//        nstage.show();
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
