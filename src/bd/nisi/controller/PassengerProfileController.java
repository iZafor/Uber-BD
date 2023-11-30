/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bd.nisi.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class PassengerProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onBookIntercityButtonOnClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.BOOK_INTERCITY_UBER);
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

    @FXML
    private void feedbackButtonOnClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.GIVEN_FEEDBACK);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "PassengerProfile",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void paymentButtonOnClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.PAYMENT_PROCEDURE);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "PassengerProfile",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onBookTransportTrackButtonOnClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.BOOK_TRANSPORT_TRACK_UBER);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "PassengerProfile",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void rentalButtonOnClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.RENTAL);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "PassengerProfile",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void shownDriverLocationButtonOnClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.SHOWN_DRIVER_LOCATION);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "PassengerProfile",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void getRideButtonOnClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.GET_RIDE);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "PassengerProfile",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    private void goBackButtonOnClick(ActionEvent event) {
 
    }
}
