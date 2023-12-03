
package bd.uber.redwan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PreventitiveMaintenancePlanningSceneController implements Initializable {

    @FXML
    private TextArea infoTextArea;

    @FXML
    private ComboBox<String> updateOrRestoreInfoComboBox;

    private final PreventiveMaintenancePlanning maintenancePlanning = new PreventiveMaintenancePlanning();
    private String originalData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate the combo box with data
        updateOrRestoreInfoComboBox.getItems().addAll("Maintenance Schedule", "Inspection Guidelines", "Driver Training", "Usage Guidelines");

        // Set the default selection
        updateOrRestoreInfoComboBox.getSelectionModel().selectFirst();

    }

    @FXML
    private void maintenanceScheduleButton(ActionEvent event) {
        String content = "Maintenance schedule content"; // Replace with your content
        maintenancePlanning.createTextFile("maintenance_schedule.txt", content);
        infoTextArea.setText("Routine check-ups every 5,000 miles. Oil change every 10,000 miles.");
    }

    @FXML
    private void inspectionGuidelinesButton(ActionEvent event) {
        String content = "Inspection guidelines content"; // Replace with your content
        maintenancePlanning.createTextFile("inspection_guidelines.txt", content);
        infoTextArea.setText("Inspect brakes, tires, and lights every month. Detailed inspection every quarter.");
    }

    @FXML
    private void driverTrainingButton(ActionEvent event) {
        String content = "Driver training content"; // Replace with your content
        maintenancePlanning.createTextFile("driver_training.txt", content);
        infoTextArea.setText("Driver training program covers defensive driving and vehicle maintenance basics.");
    }

    @FXML
    private void usageGuidelinesButton(ActionEvent event) {
        String content = "Usage guidelines content"; // Replace with your content
        maintenancePlanning.createTextFile("usage_guidelines.txt", content);
        infoTextArea.setText("Limit vehicle usage to designated routes. Avoid excessive idling for fuel efficiency.");
    }

    @FXML
    private void updateInfoButton(ActionEvent event) {

    }

    @FXML
    private void restoreOldInfo(ActionEvent event) {

    }

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        AnchorPane root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}
