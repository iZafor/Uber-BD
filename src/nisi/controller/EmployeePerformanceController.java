/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nisi.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class EmployeePerformanceController implements Initializable {

    @FXML
    private TableView<Performance> performanceTableView;
    @FXML
    private TableColumn<Performance, Integer> idTableColumn;
    @FXML
    private TableColumn<Performance, String> performanceTableColumn;
    @FXML
    private TextField idTextField;
    @FXML
    private ComboBox<String> SelectPerformanceComboBox;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Performance> performanceList = FXCollections.observableArrayList();
    private final List<String> performances = Arrays.asList("Excellent",
                                                             "Good", 
                                                             "Average", 
                                                             "Poor");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SelectPerformanceComboBox.getItems().addAll(performances);
        configureTableColumns();
    }
    private void configureTableColumns() {
        idTableColumn.setCellValueFactory(new PropertyValueFactory<Performance, Integer>("id"));
        performanceTableColumn.setCellValueFactory(new PropertyValueFactory<Performance, String>("performance"));

    }    

    @FXML
    private void loadPerformanceDataButtonOnClick(ActionEvent event) {
        int id = Integer.parseInt(idTextField.getText());
        if (performanceList.stream().anyMatch(employee -> employee.getId() == id)) {
            showAlert(id);
            return;
        }
        performanceList.add(new Performance(
                id,SelectPerformanceComboBox.getValue()));
        performanceTableView.getItems().add(performanceList.get(performanceList.size() - 1));
    }
    private void showAlert(int id) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Employee Not Found");
        alert.setContentText("The entered employee ID was not found.");
        alert.showAndWait();
    }

    @FXML
    private void goBackButtonOnClick(ActionEvent event) throws IOException {
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
