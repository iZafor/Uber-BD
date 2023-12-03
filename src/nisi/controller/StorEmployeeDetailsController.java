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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class StorEmployeeDetailsController implements Initializable {

    @FXML
    private TableView<Details> employeeTable;
    @FXML
    private TableColumn<Details, Integer> idTableColumn;
    @FXML
    private TableColumn<Details, String> nameTableColumn;
    @FXML
    private TableColumn<Details, Float> salaryTableColumn;
    @FXML
    private TableColumn<Details, String> genderTableColumn;
    @FXML
    private TableColumn<Details, String> designationTableColumn;
    @FXML
    private TableColumn<Details, String> departmentTableColumn;
    @FXML
    private TableColumn<Details, String> dobTableColumn;
    @FXML
    private TableColumn<Details, String> dojTableColumn;

    /**
     * Initializes the controller class.
     */
    private final List<Details> DetailsList = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configureTableColumns();
    }    
    private void configureTableColumns() {
        idTableColumn.setCellValueFactory(new PropertyValueFactory<Details, Integer>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("name"));
        salaryTableColumn.setCellValueFactory(new PropertyValueFactory<Details, Float>("salary"));
        genderTableColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("gender"));
        departmentTableColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("department"));
        designationTableColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("designation"));
        dobTableColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("dob"));
        dojTableColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("doj"));
    }
    @FXML
    private void goBackButtonOnClick(ActionEvent event) {
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
