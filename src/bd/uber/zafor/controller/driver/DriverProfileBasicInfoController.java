package bd.uber.zafor.controller.driver;

import bd.uber.BinFilePath;
import bd.uber.ContactDetails;
import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.driver.Driver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
    @FXML
    public ScrollPane searchResultScrollPane;
    @FXML
    private VBox searchResultVBox;

    private Driver driver;
    private ContactDetails contactDetails;
    private Location tempLocation;
    private String imageFilePath;

    private final FileChooser fileChooser = new FileChooser();
    private final FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image", "*.png", "*.jpg", "*jpeg");

    {
        fileChooser.getExtensionFilters().add(extensionFilter);
    }

    public void setInitData(Driver driver, ContactDetails contactDetails) {
        this.driver = driver;
        this.contactDetails = contactDetails;
        tempLocation = Util.getInstance().getLocationList().stream().filter(l -> l.getLocationId() == contactDetails.getLocationId()).findFirst().get();

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

        emailTextField.setText(contactDetails.getEmail());
        addressTextField.setText(tempLocation.getName());
        primaryPhoneNumber.setText(contactDetails.getPrimaryPhoneNumber());
        secondaryPhoneNumber.setText(contactDetails.getSecondaryPhoneNumber());
        drivingExperienceText.setText(String.valueOf(driver.getExperienceInYears()));

        configureLocationSearch();
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

        contactDetails.setEmail(newEmail);
        contactDetails.setPrimaryPhoneNumber(newPPhoneNumber);
        contactDetails.setSecondaryPhoneNumber(newSPhoneNumber);
        contactDetails.setLocationId(tempLocation.getLocationId());

        Util.getInstance().getWorkers().execute(() -> {
            if (!Util.getInstance().getDb().updateObjectFile(
                    driver,
                    BinFilePath.DRIVER,
                    d -> d.getId() == driver.getId(),
                    false
            ) ||
                    !Util.getInstance().getDb().updateObjectFile(
                            contactDetails,
                            BinFilePath.CONTACT_DETAILS,
                            c -> c.getContactDetailsId() == driver.getContactDetailsId(),
                            false
                    )) {
                Platform.runLater(() ->
                        Util.getInstance().showError("Failed to update data!")
                );
            } else {
                Platform.runLater(() ->
                        Util.getInstance().showError("Data updated successfully.")
                );
            }
        });
    }

    private void configureLocationSearch() {
        addressTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                String currentLocation = addressTextField.getText();
                if (currentLocation == null || currentLocation.isEmpty() || Util.getInstance().getLocationList().stream().noneMatch(location -> location.getName().equals(currentLocation))) {
                    addressTextField.clear();
                }
                addressTextField.getParent().requestFocus();
            }
        });

        addressTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!searchResultScrollPane.isFocused()) {
                searchResultScrollPane.setVisible(newValue);
            }
        });

        addressTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchResultVBox.getChildren().clear();
            if (newValue.isEmpty()) {
                return;
            }
            String lower = newValue.toLowerCase();
            Util.getInstance().getLocationList().forEach(location -> {
                if (location.getName().toLowerCase().contains(lower)) {
                    Label label = new Label(location.getName());
                    label.getStyleClass().add("search-location");
                    label.setOnMouseClicked(event -> {
                        addressTextField.setText(location.getName());
                        tempLocation = location;
                        searchResultScrollPane.setVisible(false);
                    });
                    searchResultVBox.getChildren().add(label);
                }
            });
        });
    }
}