/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nisi.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class WorkingScheduleController implements Initializable {

    @FXML
    private TableView<Shift> scheduleTable;
    @FXML
    private TableColumn<Shift, Integer> idTableColumn;
    @FXML
    private TableColumn<Shift, String> shiftTableColumn;
    @FXML
    private TableColumn<Shift, String> periodTableColumn;
    @FXML
    private ComboBox<String> shiftComboBox;
    @FXML
    private TextField idTextField;
    @FXML
    private ComboBox<String> periodComboBox;

    /**
     * Initializes the controller class.
     */
    private final List<Shift> shiftList = new ArrayList<>();
    private final List<String> shifts = Arrays.asList("Morning",
            "Night");
    private final List<String> times = Arrays.asList("six AM - Twelve PM",
            "Twelve PM - Six PM");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        shiftComboBox.getItems().addAll(shifts);
        periodComboBox.getItems().addAll(times);
        configureTableColumns();
        //Platform.runLater(() -> scheduleTable.getItems().addAll(EmployeeFile.getShifts()));
    }    
    private void configureTableColumns() {
        idTableColumn.setCellValueFactory(new PropertyValueFactory<Shift, Integer>("id"));
        shiftTableColumn.setCellValueFactory(new PropertyValueFactory<Shift, String>("shift"));
        periodTableColumn.setCellValueFactory(new PropertyValueFactory<Shift, String>("shiftPeriod"));
    }
    @FXML
    private void addShiftButtonOnClick(ActionEvent event) {
        int id = Integer.parseInt(idTextField.getText());
        if (shiftList.stream().anyMatch(employee -> employee.getId() == id)) {
            showAlert(id);
            return;
        }
        shiftList.add(new Shift(
                id,
                shiftComboBox.getValue(),
                periodComboBox.getValue()
        ));
        scheduleTable.getItems().add(shiftList.get(shiftList.size() - 1));
    }
    private void showAlert(int id) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(idTextField.getScene().getWindow());
        alert.initModality(Modality.WINDOW_MODAL);
        alert.setContentText("Employee with id " + id + " already exists!");
        alert.showAndWait();
    }
    @FXML
    private void onAddShitsToFile(ActionEvent event) {
        //EmployeeFile.addShits(shiftList);
    }
    public void setInitData(List<Shift> shiftList) {
        scheduleTable.getItems().addAll(shiftList);
    }

    @FXML
    private void goBackButtonOnclick(ActionEvent event) throws IOException {
//        Parent mainLCManagementLogInFormParent = FXMLLoader.load(getClass().getResource("HRM_Dashboard.fxml"));        
//        Scene mainLCManagementLogInFormScene = new Scene(mainLCManagementLogInFormParent);        
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();       
//        stage.setScene(mainLCManagementLogInFormScene);
//        stage.show();
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.HRM_Dashboard);
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

    
}
