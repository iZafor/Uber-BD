package bd.uber.zafor.controller.driver;

import bd.uber.BinFilePath;
import bd.uber.Location;
import bd.uber.Util;
import bd.uber.zafor.model.driver.DamageReport;
import bd.uber.zafor.model.driver.VehicleInfo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DriverReportDamageController implements Initializable {
    @FXML
    private TextField locationTextField;
    @FXML
    private TextArea damageDescription;
    @FXML
    private ScrollPane searchResultScrollPane;
    @FXML
    private VBox searchResultVBox;

    private VehicleInfo vehicleInfo;
    private Location tempLocation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureLocationSearch();
    }

    public void setInitData(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    @FXML
    private void onReportDamage() {
        if (tempLocation == null) {
            Util.getInstance().showWarningMessage("Location cannot be empty!");
            return;
        }

        String description = damageDescription.getText();
        if (description == null || description.isEmpty()) {
            Util.getInstance().showWarningMessage("Location cannot be empty!");
            return;
        }

        Util.getInstance().getWorkers().execute(() -> {
            DamageReport damageReport = new DamageReport(Util.getInstance().getDb().getObjectCount(BinFilePath.DAMAGE_REPORT) + 1, description, LocalDate.now(), tempLocation.getLocationId());

            vehicleInfo.getDamageReportIds().add(damageReport.getDamageReportId());

            if (Util.getInstance().getDb().addObject(damageReport, BinFilePath.DAMAGE_REPORT)) {
                Platform.runLater(() -> {
                    clearInputs();
                    Util.getInstance().showSuccessMessage("Damage reported successfully.");
                });
            } else {
                Platform.runLater(() -> {
                    clearInputs();
                    Util.getInstance().showError("Failed to report damage!");
                });
            }
        });
    }

    private void clearInputs() {
        tempLocation = null;
        locationTextField.setText("");
        damageDescription.setText(null);
    }

    private void configureLocationSearch() {
        locationTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                String currentLocation = locationTextField.getText();
                if (currentLocation == null || currentLocation.isEmpty() || Util.getInstance().getLocationList().stream().noneMatch(location -> location.getName().equals(currentLocation))) {
                    locationTextField.clear();
                    tempLocation = null;
                }
                locationTextField.getParent().requestFocus();
            }
        });

        locationTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!searchResultScrollPane.isFocused()) {
                searchResultScrollPane.setVisible(newValue);
            }
        });

        locationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
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
                        locationTextField.setText(location.getName());
                        tempLocation = location;
                        searchResultScrollPane.setVisible(false);
                    });
                    searchResultVBox.getChildren().add(label);
                }
            });
        });
    }
}