package bd.uber.zafor.controller;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.Driver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class DriverProfileBasicInfoController {
    @FXML
    private Circle profileImageCircle;

    @FXML
    private Text driverNameText;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField primaryPhoneNumber;

    @FXML
    private TextField secondaryPhoneNumber;

    @FXML
    private Text drivingExperienceText;

    private Driver driver;

    private String imageFilePath;

    private final FileChooser fileChooser = new FileChooser();

    private final FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image", "*.png", "*.jpg", "*jpeg");

    {
        fileChooser.getExtensionFilters().add(extensionFilter);
    }

    public void setInitData(Driver driver) {
        this.driver = driver;
        Util.getInstance().getWorkers().execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
                profileImageCircle.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get(driver.getProfileImage())))));
                profileImageCircle.setStrokeWidth(0);
                imageFilePath = driver.getProfileImage();
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
                // log the error
            }
        });
        driverNameText.setText(driver.getName());
        passwordField.setText(driver.getPassword());
        emailTextField.setText(driver.getContactDetails().getEmail());
        addressTextField.setText(driver.getContactDetails().getAddress().getName());
        primaryPhoneNumber.setText(driver.getContactDetails().getPrimaryPhoneNumber());
        secondaryPhoneNumber.setText(driver.getContactDetails().getSecondaryPhoneNumber());
        drivingExperienceText.setText(String.valueOf(driver.getExperienceInYears()));
    }

    @FXML
    private void onSelectProfileImage(MouseEvent event) {
        File imageFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (imageFile != null) {
            try {
                profileImageCircle.setFill(new ImagePattern(new Image(new FileInputStream(imageFile))));
                imageFilePath = imageFile.getPath();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    private void onSaveChanges(ActionEvent event) {
        String newPassword = passwordField.getText();
        String newEmail = emailTextField.getText();
        String newPPhoneNumber = primaryPhoneNumber.getText();
        String newSPhoneNumber = secondaryPhoneNumber.getText();

        if (newPassword == null || newPassword.isEmpty() || newEmail == null || newEmail.isEmpty() || newPPhoneNumber == null || newPPhoneNumber.isEmpty() || newSPhoneNumber == null || newSPhoneNumber.isEmpty()) {
            Util.getInstance().showWarningMessage("Invalid input!\nPlease recheck values.");
            return;
        }

        driver.setProfileImage(imageFilePath);
        driver.setPassword(newPassword);
        driver.getContactDetails().setEmail(newEmail);
        driver.getContactDetails().setPrimaryPhoneNumber(newPPhoneNumber);
        driver.getContactDetails().setSecondaryPhoneNumber(newSPhoneNumber);
        Util.getInstance().getWorkers().submit(() -> {
            if (Util.getInstance().getDb().updateObject(driver, BinFilePath.DRIVER, d -> d.getId() == driver.getId())) {
                Platform.runLater(() -> Util.getInstance().showSuccessMessage("Info updated successfully."));
            } else {
                Platform.runLater(() -> Util.getInstance().showError("Failed to update data!"));
            }
        });
    }
}