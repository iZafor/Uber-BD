package bd.uber.zafor.controller.operationsmanager;

import bd.uber.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class OperationsManagerManageLocationsController implements Initializable {
    @FXML
    private VBox locationsVBox;

    @FXML
    private TextField newLocationNameTextField;

    @FXML
    private ComboBox<Direction> locationDirectionComboBox;

    @FXML
    private TextField locationDistanceTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        locationDirectionComboBox.getItems().addAll(Direction.values());
        addLocations();
    }

    @FXML
    private void onAddNewLocation() {
        String name = newLocationNameTextField.getText();
        if (name == null || name.isEmpty()) {
            Util.getInstance().showWarningMessage("Invalid name!");
            return;
        }

        Direction direction = locationDirectionComboBox.getValue();
        if (direction == null) {
            Util.getInstance().showWarningMessage("Invalid direction!");
            return;
        }

        float distance;
        try {
            distance = Float.parseFloat(locationDistanceTextField.getText());
            if (!direction.equals(Direction.CENTER) && distance < 0) {
                Util.getInstance().showWarningMessage("Distance cannot be less or equal to 0!");
                return;
            }
            if (direction.equals(Direction.CENTER) && distance != 0) {
                Util.getInstance().showWarningMessage("Distance for the center must be 0!");
                return;
            }
        } catch (NumberFormatException | NullPointerException ignored) {
            Util.getInstance().showWarningMessage("Invalid distance");
            return;
        }

        if (Util.getInstance().getLocationList().stream().anyMatch(location -> location.getName().equals(name) || (location.getDirection().equals(direction) && location.getDistanceFromCentralPoint() == distance))) {
            Util.getInstance().showWarningMessage("Location might already exits!\nPlease recheck the inputs.");
            return;
        }

        Util.getInstance().getWorkers().submit(() -> {
            Location newLocation = new Location(Util.getInstance().getLocationList().size() + 1, name, direction, distance);
            if (Util.getInstance().getDb().addObject(newLocation, BinFilePath.LOCATION)) {
                Util.getInstance().getLocationList().add(newLocation);
                Platform.runLater(() -> {
                    Util.getInstance().showSuccessMessage("New location added successfully");
                    newLocationNameTextField.setText("");
                    locationDirectionComboBox.setValue(null);
                    locationDistanceTextField.setText("");
                    try {
                        FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.LOCATION_VIEW);
                        AnchorPane pane = loader.load();
                        ((LocationViewController) loader.getController()).setInitData(newLocation);
                        locationsVBox.getChildren().add(pane);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
            } else {
                Platform.runLater(() -> Util.getInstance().showError("Failed to add new location"));
            }
        });
    }

    private void addLocations() {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ignored) {
                return;
            }
            Util.getInstance().getLocationList().forEach(location -> {
                try {
                    FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.LOCATION_VIEW);
                    AnchorPane pane = loader.load();
                    ((LocationViewController) loader.getController()).setInitData(location);
                    Platform.runLater(() -> locationsVBox.getChildren().add(pane));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        });
    }
}