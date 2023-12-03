/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bd.nisi.controller;

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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class LeaveRequestController implements Initializable {

    @FXML
    private TableColumn<Leave, Integer> idTableColumn;
    @FXML
    private TableColumn<Leave, String> startingDateTableColumn;
    @FXML
    private TableColumn<Leave, String> endingDateTableColumn;
    @FXML
    private TableColumn<Leave, String> statusTableColumn;
    @FXML
    private TableColumn<Leave, String> resonTableColumn;

    /**
     * Initializes the controller class.
     */
    private final List<Leave> LeaveList = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configureTableColumns();
    }    
    private void configureTableColumns() {
        idTableColumn.setCellValueFactory(new PropertyValueFactory<Leave, Integer>("id"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<Leave, String>("status"));
        resonTableColumn.setCellValueFactory(new PropertyValueFactory<Leave, String>("reason"));
        startingDateTableColumn.setCellValueFactory(new PropertyValueFactory<Leave, String>("std"));
        endingDateTableColumn.setCellValueFactory(new PropertyValueFactory<Leave, String>("end"));
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
