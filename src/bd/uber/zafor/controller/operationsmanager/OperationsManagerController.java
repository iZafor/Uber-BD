package bd.uber.zafor.controller.operationsmanager;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.operationsmanager.OperationsManagerMenuOption;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OperationsManagerController implements Initializable {
    @FXML
    private BorderPane operationsManagerBorderPane;
    @FXML
    private VBox mainMenuVBox;

    private final ObjectProperty<OperationsManagerMenuOption> menuOptionProperty = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureMenuOptionsProperty();
        menuOptionProperty.setValue(OperationsManagerMenuOption.MANAGE_LOCATIONS);
    }

    @FXML
    private void onShowDashboard() {
        menuOptionProperty.setValue(OperationsManagerMenuOption.DASHBOARD);
    }

    @FXML
    private void onShowProfileSettings() {
        menuOptionProperty.setValue(OperationsManagerMenuOption.PROFILE);
    }

    @FXML
    private void onManageLocations() {
        menuOptionProperty.setValue(OperationsManagerMenuOption.MANAGE_LOCATIONS);
    }

    @FXML
    private void onRequestForLeave() {
        menuOptionProperty.setValue(OperationsManagerMenuOption.REQUEST_FOR_LEAVE);
    }

    @FXML
    private void onLogout(ActionEvent event) {
        try {
            Util.getInstance().showScene(
                    Util.getInstance().getLoader(FXMLFilePath.GET_STARTED_VIEW).load(),
                    event,
                    "Get Started",
                    false
            );
        } catch (IOException ignored) {
            // log the error
        }
    }

    private void configureMenuOptionsProperty() {
        menuOptionProperty.addListener((observable, oldValue, newValue) -> {
            try {
                switch (newValue) {
                    case DASHBOARD:
                        break;
                    case PROFILE:
                        break;
                    case MANAGE_LOCATIONS:
                        operationsManagerBorderPane.setCenter(
                                Util.getInstance()
                                        .getLoader(FXMLFilePath.OPERATIONS_MANAGER_MANAGE_LOCATION_VIEW)
                                        .load()
                        );
                        break;
                    case REQUEST_FOR_LEAVE:
                        break;
                }
            } catch (IOException ignored) {
                // log the error
            }
        });
    }
}