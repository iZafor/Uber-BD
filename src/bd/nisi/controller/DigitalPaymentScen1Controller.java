/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bd.nisi.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class DigitalPaymentScen1Controller implements Initializable {

    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void PayNowButtonOnAction(ActionEvent event) throws IOException {
        Stage stagem = (Stage)backButton.getScene().getWindow();
        stagem.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DigitalPaymentScen2.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void goBackScen1ButtonOnAction(ActionEvent event) throws IOException {
        Stage stagem = (Stage)backButton.getScene().getWindow();
        stagem.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaymentProcedure.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
}
