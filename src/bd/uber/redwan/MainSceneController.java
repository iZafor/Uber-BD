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
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("MaintainenceScene.fxml"));
//            Parent root = loader.load();
//
//            Util.getInstance().showScene(root, event, "", false);
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the exception
//        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MaintainenceScene.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene inventoryScene = new Scene(root);
            stage.setScene(inventoryScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }

    }

    @FXML
    private void repairButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RepairTeamMemberScene.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene inventoryScene = new Scene(root);
            stage.setScene(inventoryScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }        
        
    }

    @FXML
    private void inventoryButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryScene.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene inventoryScene = new Scene(root);
            stage.setScene(inventoryScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }

    }

    @FXML
    private void retirementButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VehicleRetirementScene.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene retirementScene = new Scene(root);
            stage.setScene(retirementScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }       
        
    }

    @FXML
    private void preventativeMaintainenceButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PreventitiveMaintenancePlanningScene.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene preventativeScene = new Scene(root);
            stage.setScene(preventativeScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }         
        
    }

}
