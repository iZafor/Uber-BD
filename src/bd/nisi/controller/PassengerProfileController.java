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
import javafx.stage.Stage;

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
        FXMLLoader loader = new FXMLLoader();
        Parent nextSceneParent;
        loader.setLocation(getClass().getResource("BookIntercityUber.fxml"));
        nextSceneParent = loader.load();

        Scene nextScene = new Scene(nextSceneParent);
        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sameStage.setScene(nextScene);
        sameStage.show();
    }

    @FXML
    private void feedbackButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent nextSceneParent;
        loader.setLocation(getClass().getResource("GivenFeedback.fxml"));
        nextSceneParent = loader.load();

        Scene nextScene = new Scene(nextSceneParent);
        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sameStage.setScene(nextScene);
        sameStage.show();
    }

    @FXML
    private void paymentButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent nextSceneParent;
        loader.setLocation(getClass().getResource("PaymentProcedure.fxml"));
        nextSceneParent = loader.load();

        Scene nextScene = new Scene(nextSceneParent);
        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sameStage.setScene(nextScene);
        sameStage.show();
    }

    @FXML
    private void onBookTransportTrackButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent nextSceneParent;
        loader.setLocation(getClass().getResource("BookTransportTrackUber.fxml"));
        nextSceneParent = loader.load();

        Scene nextScene = new Scene(nextSceneParent);
        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sameStage.setScene(nextScene);
        sameStage.show();
    }

    @FXML
    private void rentalButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent nextSceneParent;
        loader.setLocation(getClass().getResource("Rental.fxml"));
        nextSceneParent = loader.load();

        Scene nextScene = new Scene(nextSceneParent);
        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sameStage.setScene(nextScene);
        sameStage.show();
    }

    @FXML
    private void shownDriverLocationButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent nextSceneParent;
        loader.setLocation(getClass().getResource("ShownDriverLocation.fxml"));
        nextSceneParent = loader.load();

        Scene nextScene = new Scene(nextSceneParent);
        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sameStage.setScene(nextScene);
        sameStage.show();
    }

    @FXML
    private void getRideButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent nextSceneParent;
        loader.setLocation(getClass().getResource("GetRide.fxml"));
        nextSceneParent = loader.load();

        Scene nextScene = new Scene(nextSceneParent);
        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sameStage.setScene(nextScene);
        sameStage.show();
    }
    @FXML
    private void goBackButtonOnClick(ActionEvent event) {
        //        FXMLLoader loader = new FXMLLoader();
//        Parent nextSceneParent;
//        loader.setLocation(getClass().getResource(".fxml"));
//        nextSceneParent = loader.load();
//
//        Scene nextScene = new Scene(nextSceneParent);
//        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        sameStage.setScene(nextScene);
//        sameStage.show();
    }
}
