package bd.uber.zafor.controller;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class DriverSignUpIdentificationInfoController implements Initializable {
    @FXML
    private Circle profileImageCircle;

    @FXML
    private Rectangle nidFrontSideRectangle;

    @FXML
    private Rectangle nidBackSideRectangle;

    private final FileChooser fileChooser = new FileChooser();

    private final FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg");

    private final Driver signupDriver = Util.getInstance().getSignUpDriver();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Util.getInstance().getWorkers().execute(this::restoreData);
        configureImageSelection(profileImageCircle);
        configureImageSelection(nidFrontSideRectangle);
        configureImageSelection(nidBackSideRectangle);
        fileChooser.getExtensionFilters().add(extensionFilter);
    }

    private void configureImageSelection(Shape shape) {
        shape.setOnMouseClicked(event -> {
            File imageFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
            if (imageFile != null) {
                setFill(shape, imageFile.getPath());
                if (shape.equals(profileImageCircle)) {
                    signupDriver.setProfileImage(imageFile.getPath());
                } else if (shape.equals(nidFrontSideRectangle)) {
                    signupDriver.setNidFrontSide(imageFile.getPath());
                } else {
                    signupDriver.setNidBackSide(imageFile.getPath());
                }
            }
        });
    }

    @FXML
    private void onSignup(ActionEvent event) {
        if (validateInputs()) {
            // Store to the bin file
            if (!Util.getInstance().getDb().addObject(signupDriver, BinFilePath.DRIVER)) {
                Util.getInstance().showWarningMessage("Signup failed!");
                return;
            }

            // Reset the signup instance
            Util.getInstance().setSignUpDriver(null);

            // Switch to the get started view
            Util.getInstance().showSuccessMessage("Signup successful.\n Your login id is " + signupDriver.getId());
            try {
                Util.getInstance().showScene(
                        Util.getInstance().getLoader(FXMLFilePath.GET_STARTED_VIEW).load(),
                        event,
                        "Get Started",
                        false
                );
            } catch (Exception ignored) {
                // log the error
            }
        }
    }

    private void restoreData() {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            setFill(profileImageCircle, signupDriver.getProfileImage());
            setFill(nidFrontSideRectangle, signupDriver.getNidFrontSide());
            setFill(nidBackSideRectangle, signupDriver.getNidBackSide());
        } catch (Exception e) {
            System.out.println("Restoring error: " + e.getMessage());
        }
    }

    private void setFill(Shape shape, String imagePath) {
        try {
            shape.setFill(new ImagePattern(new Image(new FileInputStream(imagePath))));
            shape.setStrokeWidth(0);
        } catch (FileNotFoundException | NullPointerException ignored) {

        }
    }

    private boolean validateInputs() {
        Driver driver = Util.getInstance().getSignUpDriver();
        if (driver.getProfileImage() == null) {
            Util.getInstance().showWarningMessage("Profile image cannot be empty!");
            return false;
        }

        if (driver.getNidFrontSide() == null) {
            Util.getInstance().showWarningMessage("NID front side image cannot be empty!");
            return false;
        }

        if (driver.getNidBackSide() == null) {
            Util.getInstance().showWarningMessage("NID back side image cannot be empty!");
            return false;
        }

        return true;
    }
}