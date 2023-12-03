/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nisi.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class AddUpdateEmployeeDetailsController implements Initializable {

    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField salaryTextField;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private ComboBox<String> designationComboBox;
    @FXML
    private ComboBox<String> deptmentComboBox;
    @FXML
    private DatePicker dobDatePicker;
    @FXML
    private DatePicker dojDatePicker;
    @FXML
    private Text countLabel;
    @FXML
    private TableView<EmployeeDetails> employeeTable;
    @FXML
    private TableColumn<EmployeeDetails, Integer> idTableColumn;
    @FXML
    private TableColumn<EmployeeDetails, String> nameTableColumn;
    @FXML
    private TableColumn<EmployeeDetails, Float> salaryTableColumn;
    @FXML
    private TableColumn<EmployeeDetails, String> genderTableColumn;
    @FXML
    private TableColumn<EmployeeDetails, String> designationTableColumn;
    @FXML
    private TableColumn<EmployeeDetails, String> departmentTableColumn;
    @FXML
    private TableColumn<EmployeeDetails, String> dobTableColumn;
    @FXML
    private TableColumn<EmployeeDetails, String> dojTableColumn;

    /**
     * Initializes the controller class.
     */private final ToggleGroup toggleGroup = new ToggleGroup();

    private final List<String> designations = Arrays.asList("Executive",
                                                             "Junior Officer", 
                                                              "Senior Officer", 
                                                              "Accountant", 
                                                              "Director", 
                                                              "Engineer");
    private final List<String> departments = Arrays.asList("Operations Administrator", 
                                                           "Support Representative", 
                                                           "Vehicle Maintenance Team", 
                                                           "Business Account Manager",
                                                           "Payment Processor");
    private final List<EmployeeDetails> EmployeeDetailsList = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dobDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(LocalDate.now().minusYears(0)) || item.isAfter(LocalDate.now().plusDays(18)));
            }
        });
        maleRadioButton.setToggleGroup(toggleGroup);
        femaleRadioButton.setToggleGroup(toggleGroup);
        designationComboBox.getItems().addAll(designations);
        deptmentComboBox.getItems().addAll(departments);
        configureTableColumns();
        //loadFromBinFile();
    }    
    private void configureTableColumns() {
        idTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetails, Integer>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetails, String>("name"));
        salaryTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetails, Float>("salary"));
        genderTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetails, String>("gender"));
        departmentTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetails, String>("department"));
        designationTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetails, String>("designation"));
        dobTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetails, String>("dob"));
        dojTableColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetails, String>("doj"));
    }
    
    @FXML
    private void addNewEmpToListAndShowThemAllButtonOnClick(ActionEvent event) {
        int id = Integer.parseInt(idTextField.getText());
        if (EmployeeDetailsList.stream().anyMatch(employee -> employee.getId() == id)) {
            showAlert(id);
            return;
        }
        EmployeeDetailsList.add(new EmployeeDetails(
                id,
                nameTextField.getText(),
                Float.parseFloat(salaryTextField.getText()),
                maleRadioButton.isSelected() ? "Male" : "Female",
                deptmentComboBox.getValue(),
                designationComboBox.getValue(),
                dobDatePicker.getValue(),
                dojDatePicker.getValue()
        ));
        countLabel.setText("After addition, there are TOTAL " +
                EmployeeDetailsList.size() +
                " employees, as show below:");
        employeeTable.getItems().add(EmployeeDetailsList.get(EmployeeDetailsList.size() - 1));
    }
//    private void loadFromBinFile() {
//        employeeTable.getItems().addAll(
//                EmployeeFile.getEmployees()
//        );
//        countLabel.setText("After addition, there are TOTAL " +
//                employeeTable.getItems().size() +
//                " employees, as show below:");
//    }
    public void setInitData(List<EmployeeDetails> EmployeeDetailsList) {
        employeeTable.getItems().addAll(EmployeeDetailsList);
    }
    private void showAlert(int id) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(idTextField.getScene().getWindow());
        alert.initModality(Modality.WINDOW_MODAL);
        alert.setContentText("Employee with id " + id + " already exists!");
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
