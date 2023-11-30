/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nisi.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import java.io.IOException;
import java.net.URL;
//import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.scene.control.TextField;
//import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class HRM_DashboardController implements Initializable {

    @FXML
    private TextField HRMdashboard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goBackButtonOnClck(ActionEvent event) {
        //        FXMLLoader loader = new FXMLLoader();
//        Parent nextSceneParent;
//        loader.setLocation(getClass().getResource("U0LoginScene.fxml"));
//        nextSceneParent = loader.load();
//        Scene nextScene = new Scene(nextSceneParent);
//        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        sameStage.setScene(nextScene);
//        sameStage.show();
//        sameStage.setTitle("Operations of Hamdard Laboratories");
    }

    @FXML
    private void addUpdateEmployeeDetailsButtonOnClck(ActionEvent event) {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.Add_Update_Employee_Details);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "HRM_Dashboard",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        FXMLLoader loader;
//        loader = new FXMLLoader(getClass().getResource("AddUpdateEmployeeDetailsController.fxml"));
//        Parent nextSceneParent = loader.load();
//        List<EmployeeInformation> EmployeeInformationList = null;
//        ((AddUpdateEmployeeDetailsController) loader.getController()).setInitData(EmployeeInformationList);
//        Scene nextScene = new Scene(nextSceneParent);
//        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        sameStage.setScene(nextScene);
//        sameStage.show();
    }

    @FXML
    private void monthlySuplingSalaryButtonOnClck(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.Monthly_Supling_Salary);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "HRM_Dashboard",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("MonthlySuplingSalary.fxml"));
//        Parent nextSceneParent = loader.load();
//        Scene nextScene = new Scene(nextSceneParent);
//        List<EmployeeInformation> EmployeeInformationList = null;
//        ((MonthlySuplingSalaryController)loader.getController()).setInitData(EmployeeInformationList);
//        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        sameStage.setScene(nextScene);
//        sameStage.show();
    }

    @FXML
    private void noticeToEmployeeButtonOnClck(ActionEvent event) {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.Notice_To_Employee);
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

    @FXML
    private void workingScheduleButtonOnClck(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.Working_Schedule);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "HRM_Dashboard",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("WorkingScheduleScene.fxml"));
//        Parent nextSceneParent = loader.load();
//        Scene nextScene = new Scene(nextSceneParent);
//        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        sameStage.setScene(nextScene);
//        sameStage.show();
    }

    @FXML
    private void storEmployeeDetailsButtonOnClck(ActionEvent event) {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.Stor_Employee_Details);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "HRM_Dashboard",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //        FXMLLoader loader = new FXMLLoader();
//        Parent nextSceneParent;
//        loader.setLocation(getClass().getResource("U0LoginScene.fxml"));
//        nextSceneParent = loader.load();
//        Scene nextScene = new Scene(nextSceneParent);
//        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        sameStage.setScene(nextScene);
//        sameStage.show();
//        sameStage.setTitle("Operations of Hamdard Laboratories");
    }

//    @FXML
//    private void employeeReportsButtonOnClck(ActionEvent event) {
//        try {
//            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.Employee_Reports);
//            Util.getInstance().showScene(
//                    loader.load(),
//                    event,
//                    "HRM_Dashboard",
//                    false
//            );
//            
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        //        FXMLLoader loader = new FXMLLoader();
//        Parent nextSceneParent;
//        loader.setLocation(getClass().getResource("U0LoginScene.fxml"));
//        nextSceneParent = loader.load();
//        Scene nextScene = new Scene(nextSceneParent);
//        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        sameStage.setScene(nextScene);
//        sameStage.show();
//        sameStage.setTitle("Operations of Hamdard Laboratories");
    //}

    @FXML
    private void employeePerformanceButtonOnClck(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.Employee_Performance);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "HRM_Dashboard",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        FXMLLoader loader = new FXMLLoader();
//        Parent nextSceneParent;
//        loader.setLocation(getClass().getResource("EmployeePerformanceScene.fxml"));
//        nextSceneParent = loader.load();
//
//        Scene nextScene = new Scene(nextSceneParent);
//        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        sameStage.setScene(nextScene);
//        sameStage.show();
    }

    @FXML
    private void manageMeetionButtonOnClck(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.Manage_Meeting);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "HRM_Dashboard",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        FXMLLoader loader = new FXMLLoader();
//        Parent nextSceneParent;
//        loader.setLocation(getClass().getResource("ManageMeeting.fxml"));
//        nextSceneParent = loader.load();
//        Scene nextScene = new Scene(nextSceneParent);
//        Stage sameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        sameStage.setScene(nextScene);
//        sameStage.show();
    }

    @FXML
    private void leaveRequestButtonOnClck(ActionEvent event) {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.LEAVE_REQUEST);
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
