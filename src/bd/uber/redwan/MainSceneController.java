/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bd.uber.redwan;

import bd.uber.Util;
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
 * @author Redwan
 */
public class MainSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
@FXML
private void maintainenceButton(ActionEvent event) {   
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MaintainenceScene.fxml"));
        Parent root = loader.load();
        
        // Get the controller associated with the MaintainenceScene.fxml
       // MaintainenceSceneController maintainenceSceneController = loader.getController();
        
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
           
        Util.getInstance().showScene(root, event,"", false);
    } catch (IOException e) {
        e.printStackTrace();
        // Handle the exception
    }
}

    @FXML
    private void repairButton(ActionEvent event) {
    }

    @FXML
    private void inventoryButton(ActionEvent event) {
    }

    @FXML    private void retirementButton(ActionEvent event) {
    }

}
