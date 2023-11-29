/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bd.nisi.controller;

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
public class GivenFeedbackController implements Initializable {

    @FXML
    private TextArea messageTextArea;
    @FXML
    private TextField ratingTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void submitButtonOnClick(ActionEvent event) {
        String topic = ratingTextField.getText();
        String details = messageTextArea.getText();
        String data = topic + "\t" + details + "\n".toString();
        try {
            FileWriter fw = new FileWriter("complain_officer_notice.txt", true);
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
        Parent root = FXMLLoader.load(getClass().getResource("PassengerProfile.fxml"));
        Scene auditScene = new Scene(root);
        Stage nstage = (Stage)((Node)event.getSource()).getScene().getWindow();
        nstage.setScene(auditScene);
        nstage.show();
    }
}
