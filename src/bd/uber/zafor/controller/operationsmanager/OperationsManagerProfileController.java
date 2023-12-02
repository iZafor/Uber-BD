package bd.uber.zafor.controller.operationsmanager;

import bd.uber.Employee;
import bd.uber.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class OperationsManagerProfileController {
    @FXML
    private Circle profileImageCircle;
    @FXML
    private Text employeeNameText;
    @FXML
    private Text employeeGenderText;
    @FXML
    private Text employeeDepartmentText;
    @FXML
    private Text employeeDesignationText;
    @FXML
    private Text dateOfJoiningText;
    @FXML
    private Text dateOfBirthText;

    private Employee employee;
    private String imageFilePath;
    private final FileChooser fileChooser = new FileChooser();
    private final FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg");

    {
        fileChooser.getExtensionFilters().add(filter);
    }

    public void setInitData(Employee employee) {
        this.employee = employee;
        employeeNameText.setText(employee.getName());
        employeeGenderText.setText(employee.getGender().name());
        employeeDepartmentText.setText(employee.getDepartment().toString());
        employeeDesignationText.setText(employee.getDesignation().toString());
        dateOfJoiningText.setText(employee.getDateOfJoining().toString());
        dateOfBirthText.setText(employee.getDateOfBirth().toString());

        Util.getInstance().getWorkers().execute(() -> {
            if (employee.getProfileImage() == null || employee.getProfileImage().isEmpty()) {
                return;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(40);
                Image image = new Image(new FileInputStream(employee.getProfileImage()));
                Platform.runLater(() -> {
                    profileImageCircle.setFill(new ImagePattern(image));
                    profileImageCircle.setStrokeWidth(0);
                });
            } catch (InterruptedException | FileNotFoundException ignored) {

            }
        });

        profileImageCircle.setOnMouseClicked(event -> {
            File imageFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
            if (imageFile != null) {
                imageFilePath = imageFile.getPath();
                try {
                    profileImageCircle.setFill(new ImagePattern(new Image(new FileInputStream(imageFile))));
                    profileImageCircle.setStrokeWidth(0);
                } catch (FileNotFoundException ignored) {

                }
            }
        });
    }

    @FXML
    private void onUpdateImage() {
        if (imageFilePath != null) {
            employee.setProfileImage(imageFilePath);
            Util.getInstance().showSuccessMessage("Image updated successfully.");
        }
    }
}