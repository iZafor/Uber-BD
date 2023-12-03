package bd.uber.zafor.controller.operationsmanager;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.operationsmanager.OperationsManager;
import bd.uber.zafor.model.operationsmanager.OperationsManagerMenuOption;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OperationsManagerController implements Initializable {
    @FXML
    private BorderPane operationsManagerBorderPane;

    private final ObjectProperty<OperationsManagerMenuOption> menuOptionProperty = new SimpleObjectProperty<>();
    private OperationsManager operationsManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureMenuOptionsProperty();
    }

    public void setInitData(OperationsManager operationsManager) {
        this.operationsManager = operationsManager;
        menuOptionProperty.setValue(OperationsManagerMenuOption.PROFILE);
        Platform.runLater(this::configureOnClose);
    }

    @FXML
    private void onShowProfile() {
        menuOptionProperty.setValue(OperationsManagerMenuOption.PROFILE);
    }

    @FXML
    private void onGetInsight() {
        menuOptionProperty.setValue(OperationsManagerMenuOption.GET_INSIGHT);
    }

    @FXML
    private void onManagePromotionalCampaigns() {
        menuOptionProperty.setValue(OperationsManagerMenuOption.MANAGE_PROMOTIONAL_CAMPAIGNS);
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
    public void onManageFareStructure() {
        menuOptionProperty.setValue(OperationsManagerMenuOption.MANAGE_FARE_STRUCTURE);
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
                FXMLLoader loader;
                switch (newValue) {
                    case PROFILE:
                        loader = Util.getInstance().getLoader(FXMLFilePath.OPERATIONS_MANAGER_PROFILE_VIEW);
                        operationsManagerBorderPane.setCenter(loader.load());
                        ((OperationsManagerProfileController) loader.getController()).setInitData(operationsManager);
                        break;
                    case GET_INSIGHT:
                        operationsManagerBorderPane.setCenter(Util.getInstance()
                                .getLoader(FXMLFilePath.OPERATIONS_MANAGER_GET_INSIGHT_VIEW)
                                .load()
                        );
                        break;
                    case MANAGE_PROMOTIONAL_CAMPAIGNS:
                        loader = Util.getInstance().getLoader(FXMLFilePath.OPERATIONS_MANAGER_MANAGE_PROMOTIONAL_CAMPAIGNS_VIEW);
                        operationsManagerBorderPane.setCenter(loader.load());
                        ((OperationsManagerManagePromotionalCampaignsController) loader.getController()).setInitData(operationsManager);
                        break;
                    case MANAGE_FARE_STRUCTURE:
                        loader = Util.getInstance().getLoader(FXMLFilePath.OPERATIONS_MANAGER_MANAGE_FARE_STRUCTURE_VIEW);
                        operationsManagerBorderPane.setCenter(loader.load());
                        ((OperationsManagerFareStructureManagementController) loader.getController()).setInitData(operationsManager.getFareStructureManagement());
                        break;
                    case MANAGE_LOCATIONS:
                        operationsManagerBorderPane.setCenter(
                                Util.getInstance()
                                        .getLoader(FXMLFilePath.OPERATIONS_MANAGER_MANAGE_LOCATION_VIEW)
                                        .load()
                        );
                        break;
                    case REQUEST_FOR_LEAVE:
                        loader = Util.getInstance().getLoader(FXMLFilePath.OPERATIONS_MANAGER_REQUEST_FOR_LEAVE_VIEW);
                        operationsManagerBorderPane.setCenter(loader.load());
                        ((OperationsManagerRequestForLeaveController) loader.getController()).setInitData(operationsManager.getId());
                        break;
                }
            } catch (IOException ignored) {
                // log the error
            }
        });
    }

    private void configureOnClose() {
        operationsManagerBorderPane.getScene().getWindow().setOnHiding(this::updateOperationsManager);
        operationsManagerBorderPane.getScene().getWindow().setOnCloseRequest(this::updateOperationsManager);
    }

    private void updateOperationsManager(WindowEvent event) {
        Util.getInstance().getWorkers().execute(() ->
                Util.getInstance().getDb().updateObjectFile(
                        operationsManager,
                        BinFilePath.OPERATIONS_MANAGER,
                        o -> o.getId() == operationsManager.getId(),
                        false
                )
        );
    }
}