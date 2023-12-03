/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private ComboBox<?> paymentComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void findRidesButtonOnClick(ActionEvent event) {
    }

    @FXML
    private void goBackButtonOnClick(ActionEvent event) {
    }

    @FXML
    private void oneWayButtonOnClick(ActionEvent event) {
    }

    @FXML
    private void roundTripButtonOnClick(ActionEvent event) {
    }
    
}
