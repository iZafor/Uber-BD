package bd.uber.redwan;

import bd.uber.zafor.model.driver.EngineOilLevel;
import bd.uber.zafor.model.driver.FuelLevel;
import bd.uber.zafor.model.driver.MaintenanceRequest;
import bd.uber.zafor.model.driver.MaintenanceTimeSlot;
import bd.uber.zafor.model.driver.VehicleInfo;
import bd.uber.zafor.model.driver.VehicleStatus;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MaintainenceSceneController implements Initializable {

    @FXML
    private TableView<MaintenanceTableData> maintainenceRequestTable;
    @FXML
    private TableColumn<MaintenanceTableData, String> modelColumn;
    @FXML
    private TableColumn<MaintenanceTableData, String> preferredTimeSlotColumn;
    @FXML
    private TableColumn<MaintenanceTableData, Integer> odometerReadingColumn;
    @FXML
    private TableColumn<MaintenanceTableData, Integer> fuelLevelColumn;
    @FXML
    private TableColumn<MaintenanceTableData, Float> tirePressureColumn;
    @FXML
    private TableColumn<MaintenanceTableData, EngineOilLevel> engineOilLevelColumn;

    @FXML
    private TableColumn<MaintenanceTableData, Integer> vehicleInfoIdColumn;

    @FXML
    private TableView<MaintenanceTeamMember> teamMemberTable;
    @FXML
    private TableColumn<MaintenanceTeamMember, Integer> teamMemberIdColumn;
    @FXML
    private TableColumn<MaintenanceTeamMember, String> teamMemberNameColumn;

    private VehicleInfo vehicleInfo; // Declare VehicleInfo instance
    @FXML
    private ComboBox<MaintenanceTeamMember> teamMemberLoginComboBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateMaintainenceRequestTable();
        initializeTeamMembers();

    }

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
        populateMaintainenceRequestTable();
    }

    private void populateMaintainenceRequestTable() {
        addSampleDataToTable(2); 

        vehicleInfoIdColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleInfoId"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        preferredTimeSlotColumn.setCellValueFactory(new PropertyValueFactory<>("preferredTimeSlot"));
        odometerReadingColumn.setCellValueFactory(new PropertyValueFactory<>("odometerReading"));
        fuelLevelColumn.setCellValueFactory(new PropertyValueFactory<>("fuelLevel"));
        tirePressureColumn.setCellValueFactory(new PropertyValueFactory<>("tirePressure"));
        engineOilLevelColumn.setCellValueFactory(new PropertyValueFactory<>("engineOilLevel"));
    }

    private void addSampleDataToTable(int numberOfSamples) {
        for (int i = 0; i < numberOfSamples; i++) {
            VehicleInfo vehicleInfo = new VehicleInfo(i + 1);
            vehicleInfo.setModel("Sample Model " + (i + 1));

            VehicleStatus vehicleStatus = new VehicleStatus(i + 1);
            vehicleStatus.setOdometerReading(10000 + (i * 1000));
            vehicleStatus.setFuelLevel(FuelLevel.FULL);
            vehicleStatus.setTirePressure(32.0f - (i * 1.0f));
            vehicleStatus.setEngineOilLevel(EngineOilLevel.ABOVE_MAX_LEVEL);

            MaintenanceRequest maintenanceRequest = new MaintenanceRequest(i + 1, LocalDate.now(), MaintenanceTimeSlot.values()[i % 4]);

            MaintenanceTableData data = MaintenanceTableData.fromModels(vehicleInfo, vehicleStatus, maintenanceRequest);
            maintainenceRequestTable.getItems().add(data);
        }
    }

    private void initializeTeamMembers() {
        List<MaintenanceTeamMember> teamMembers = new ArrayList<>();
        teamMembers.add(new MaintenanceTeamMember("1", "John Doe"));
        teamMembers.add(new MaintenanceTeamMember("2", "Alice Smith"));
        teamMembers.add(new MaintenanceTeamMember("3", "Bob Johnson"));
        teamMembers.add(new MaintenanceTeamMember("4", "Eva Brown"));
        teamMembers.add(new MaintenanceTeamMember("5", "Michael Lee"));

        teamMemberTable.getItems().addAll(teamMembers);

        teamMemberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        teamMemberNameColumn.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        teamMemberLoginComboBox.getItems().addAll(teamMembers);
    }

    @FXML
    private void assignToMemberButton(ActionEvent event) throws IOException {
        MaintenanceTableData selectedMaintenanceData = maintainenceRequestTable.getSelectionModel().getSelectedItem();
        MaintenanceTeamMember selectedTeamMember = teamMemberTable.getSelectionModel().getSelectedItem();

        if (selectedMaintenanceData != null && selectedTeamMember != null) {
            List<Object> assignedDataList = new ArrayList<>();
            assignedDataList.add(selectedMaintenanceData);
            assignedDataList.add(selectedTeamMember);
        }
    }

    @FXML
    private void viewMaintainenceTasksButton(ActionEvent event) throws IOException {
        MaintenanceTableData selectedMaintenanceData = maintainenceRequestTable.getSelectionModel().getSelectedItem();
        MaintenanceTeamMember selectedTeamMember = teamMemberLoginComboBox.getValue();

        if (selectedMaintenanceData != null && selectedTeamMember != null) {
            boolean isValidTeamMember = validateTeamMember(selectedTeamMember, selectedMaintenanceData);

            if (isValidTeamMember) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("MaintainenceTeamMemberScene.fxml"));
                Parent root = loader.load();

                MaintainenceTeamMemberSceneController controller = loader.getController();
                controller.receiveData(selectedMaintenanceData, selectedTeamMember);

                controller.receiveVehicleInfoId(selectedMaintenanceData.getVehicleInfoId());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Selected maintenance data does not match the assigned team member or is not valid.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select maintenance data and team member.");
        }
    }

    private boolean validateTeamMember(MaintenanceTeamMember selectedTeamMember, MaintenanceTableData selectedMaintenanceData) {
        MaintenanceTeamMember maintenanceDataTeamMember = (MaintenanceTeamMember) teamMemberTable.getSelectionModel().getSelectedItem();

        return selectedTeamMember != null && maintenanceDataTeamMember != null
                && selectedTeamMember.getMemberId().equals(maintenanceDataTeamMember.getMemberId());
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.show();
    }

}
