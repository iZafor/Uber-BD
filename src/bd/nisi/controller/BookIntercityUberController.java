/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bd.nisi.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class BookIntercityUberController implements Initializable {

    @FXML
    private TextField destinationTextField;
    @FXML
    private DatePicker pickupDateAndTimeDatePicker;
    @FXML
    private DatePicker dropOffDateAndTimeDatePicker;
    @FXML
    private ComboBox<String> paymentComboBox;

    /**
     * Initializes the controller class.
     */
     private final List<String> payments = Arrays.asList("Book Intercity", 
                                                         "Book Transport Track", 
                                                         "Rental");
    private final List<Intercity> IntercityList = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        paymentComboBox.getItems().addAll(payments);
    }    

    @FXML
    private void findRidesButtonOnClick(ActionEvent event) {
    }

    

    @FXML
    private void oneWayButtonOnClick(ActionEvent event) {
    }

    @FXML
    private void roundTripButtonOnClick(ActionEvent event) {
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
