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
import javafx.scene.control.Label;
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
public class MaintainenceTeamMemberSceneController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label idLabel;

    private MaintenanceTeamMember teamMember;
    @FXML
    private ComboBox<?> issueStatusComboBox;
    @FXML
    private TextArea reportTextArea;
    private TextArea inspectionTextArea;


    private Report generatedReport;
    private MaintenanceDetails maintenanceDetails;
    private MaintenanceTask maintenanceTask;
    @FXML
    private TableView<MaintenanceTask> inspectionTable;
    @FXML
    private TableColumn<MaintenanceTask, Integer> vehicleInfoIdColumn;
    @FXML
    private TableColumn<MaintenanceTask, ?> issueTrackerColumn;

    private int vehicleInfoId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void initializeTableColumns() {

    }

    public void receiveData(MaintenanceTableData maintenanceData, MaintenanceTeamMember teamMember) {
        this.teamMember = teamMember;

        nameLabel.setText("Name: " + teamMember.getMemberName());
        idLabel.setText("Id: " + teamMember.getMemberId());
    }

    public void receiveVehicleInfoId(int vehicleInfoId) {
        this.vehicleInfoId = vehicleInfoId;

    }

    @FXML
    private void InspectionReportButton(ActionEvent event) {
        MaintenanceTask selectedTask = inspectionTable.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            selectedTask.setVehicleInfoId(vehicleInfoId); 

            inspectionTable.refresh();
        } else {
        }
    }

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MaintainenceScene.fxml"));
        AnchorPane root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void generateReportButton(ActionEvent event) {
    }

}
