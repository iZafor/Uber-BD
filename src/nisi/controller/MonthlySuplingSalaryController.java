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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class MonthlySuplingSalaryController implements Initializable {

    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private TextField idTextField;
    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private ComboBox<String> salaryComboBox;

    /**
     * Initializes the controller class.
     */
    private final List<Salary> SalaryList = new ArrayList<>();
  
    private final List<String> months = Arrays.asList("January",
                                                       "February",
                                                       "March",
                                                       "April",
                                                       "May",
                                                       "June",
                                                       "July",
                                                       "August",
                                                       "Septembor",
                                                       "August",
                                                       "Octobor",
                                                       "Nobembor",
                                                       "Decembor");
    private final List<String> Salarys = Arrays.asList("10k",
                                                       "20k",
                                                       "30k",
                                                       "40k",
                                                       "50k");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        monthComboBox.getItems().addAll(months);
        salaryComboBox.getItems().addAll(Salarys);
    }    
//    public void setInitData(List<EmployeeDetails> EmployeeDetailsList) {
//        XYChart.Series<String, Number> salarySeries = new XYChart.Series<>();
//        for (EmployeeDetailsList emp : EmployeeDetailsList) {
//            salarySeries.getData().add(
//                    new XYChart.Data<>(emp.getName(), emp.getSalary())
//            );
//        }
//        lineChart.getData().add(salarySeries);
//    }
    @FXML
    private void addSalaryButtonOnClick(ActionEvent event) {
        int id = Integer.parseInt(idTextField.getText());
        if (SalaryList.stream().anyMatch(employee -> employee.getId() == id)) {
            showAlert(id);
            return;
        }
        SalaryList.add(new Salary(
                id,
                Float.parseFloat(salaryComboBox.getValue()),
                monthComboBox.getValue()
        ));

        // Optionally, you can clear the text fields after adding the salary
        idTextField.clear();
        salaryComboBox.getSelectionModel().clearSelection();
        monthComboBox.getSelectionModel().clearSelection();
    }
    private void showAlert(int id) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(idTextField.getScene().getWindow());
        alert.initModality(Modality.WINDOW_MODAL);
        alert.setContentText("Employee with id " + id + " already exists!");
        alert.showAndWait();
    }
    @FXML
    private void loadLineChartButtonOnClick(ActionEvent event) {
        lineChart.getData().clear();
        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        // Clear existing data from the chart
        lineChart.getData().clear();

        // Create a new series for the chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Salary");

        // Populate the series with data from the salaryList
        for (Salary s : SalaryList) {
            series.getData().add(new XYChart.Data<>(s.getMonth(), s.getSalary()));
        }

        // Add the series to the chart
        lineChart.getData().add(series);


        for (XYChart.Data<String, Number> data : series.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //lineChartLabel.setText("X: "+String.valueOf(data.getXValue())+"\nY: "+String.valueOf(data.getYValue()));
                            Tooltip.install(data.getNode(), new Tooltip("X: " + String.valueOf(data.getXValue()) + "\nY: " + String.valueOf(data.getYValue())));
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    }
            );
        }
    }

    
    @FXML
    private void goBackButtonOnClick(ActionEvent event) throws IOException {
//        Parent mainLCManagementLogInFormParent = FXMLLoader.load(getClass().getResource("HRM_Dashboard.fxml"));
//        Scene mainLCManagementLogInFormScene = new Scene(mainLCManagementLogInFormParent);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
