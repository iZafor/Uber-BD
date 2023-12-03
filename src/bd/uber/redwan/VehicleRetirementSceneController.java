
package bd.uber.redwan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Redwan
 */
public class VehicleRetirementSceneController implements Initializable {

    @FXML
    private TableView<VehicleRetirement> retireVehicleTable;
    @FXML
    private TableColumn<VehicleRetirement, Integer> vehicleRegNoColumn;
    @FXML
    private TableColumn<VehicleRetirement, String> yearColumn;
    @FXML
    private TableColumn<VehicleRetirement, String> modelColumn;
    @FXML
    private TableColumn<VehicleRetirement, String> statusColumn;
    @FXML
    private TextArea showRetiredListTextArea;

    private boolean vehicleRetired = false;
    private ObservableList<VehicleRetirement> retiredVehicles = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showVehiclesNeedingRetirementButton() {
        ObservableList<VehicleRetirement> vehiclesNeedingRetirement = VehicleRetirement.getVehiclesNeedingRetirement();

        // Populate TableView
        retireVehicleTable.setItems(vehiclesNeedingRetirement);
        vehicleRegNoColumn.setCellValueFactory(cellData -> cellData.getValue().vehicleRegNoProperty().asObject());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
    }

    @FXML
    private void retireSelectedVehicleButton(ActionEvent event) {
        VehicleRetirement selectedVehicle = retireVehicleTable.getSelectionModel().getSelectedItem();

        if (selectedVehicle != null) {
            retireVehicleTable.getItems().remove(selectedVehicle);
            retiredVehicles.add(selectedVehicle); // Add the retired vehicle to the list of retired vehicles
            vehicleRetired = true; // Set the flag to true when a vehicle is retired
        } else {
            showErrorAlert("Error", "No vehicle selected", "Please select a vehicle to retire.");
        }
    }

    @FXML
    private void generateRetirementReportButton(ActionEvent event) {
        if (vehicleRetired) {
            String currentDir = System.getProperty("user.dir");
            String filePath = currentDir + "/src/bd/uber/redwan/retirement_report.txt"; // Constructing the file path

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (VehicleRetirement vehicle : retiredVehicles) {
                    writer.write("Vehicle Registration No: " + vehicle.getVehicleRegNo().get()
                            + ", Year: " + vehicle.getYear().get()
                            + ", Model: " + vehicle.getModel().get()
                            + ", Status: " + vehicle.getStatus().get() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void showRetiredVehiclesDataButton(ActionEvent event) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "/src/bd/uber/redwan/retirement_report.txt"; // File path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;

            // Read lines from the file and append to content
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            // Set the content to the TextArea
            showRetiredListTextArea.setText(content.toString());
        } catch (IOException e) {
            System.err.println("Error reading retired vehicles data: " + e.getMessage());
            e.printStackTrace();
        }
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

    private void showErrorAlert(String title, String header, String content) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }

}
