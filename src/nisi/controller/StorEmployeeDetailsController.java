/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nisi.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class StorEmployeeDetailsController implements Initializable {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> idTableColumn;
    @FXML
    private TableColumn<Employee, String> nameTableColumn;
    @FXML
    private TableColumn<Employee, Float> salaryTableColumn;
    @FXML
    private TableColumn<Employee, String> genderTableColumn;
    @FXML
    private TableColumn<Employee, String> designationTableColumn;
    @FXML
    private TableColumn<Employee, String> departmentTableColumn;
    @FXML
    private TableColumn<Employee, String> dobTableColumn;
    @FXML
    private TableColumn<Employee, String> dojTableColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
