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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class DigitalPaymentScen2Controller implements Initializable {

    @FXML
    private TextField accountNumberTextField;
    @FXML
    private TextField programOTPTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button goBackButton;
    @FXML
    private Button backbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmButtonOnAction(ActionEvent event) {
        String accountNumber = accountNumberTextField.getText();
        String programOTP = programOTPTextField.getText();

        if (isValidAccountNumber(accountNumber) && isDevelopmentAccountConfirmed(accountNumber, programOTP)) {
            showConfirmationAlert("Development Account Confirmed");
        } 
        else {
            showConfirmationAlert("Invalid Account Number or Program OTP");
        }
    }
    private boolean isDevelopmentAccountConfirmed(String accountNumber, String programOTP) {
        return accountNumber.equals("your_development_account_number") && programOTP.equals("your_program_otp");
    }
    private boolean isValidAccountNumber(String accountNumber) {
        return !accountNumber.isEmpty();
    }

    private void showConfirmationAlert(String message) {
    // Implement the code to show an alert with the provided message.
    // You can use JavaFX Alert class for this purpose.
    }
    


    @FXML
    private void goBackScem1ButtonOnAction(ActionEvent event) throws IOException {
        Stage stagem = (Stage)backbutton.getScene().getWindow();
        stagem.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DigitalPaymentScen1.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
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
