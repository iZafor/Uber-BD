package bd.uber.zafor.controller.operationsmanager;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.operationsmanager.FareModificationRule;
import bd.uber.zafor.model.operationsmanager.FareStructureManagement;
import bd.uber.zafor.model.operationsmanager.PricingTier;
import bd.uber.zafor.model.driver.RideType;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OperationsManagerFareStructureManagementController {
    @FXML
    private VBox pricingTierVbox;
    @FXML
    private VBox fareModificationRuleVBox;

    private FareStructureManagement fareStructureManagement;
    private final ObservableList<Integer> modificationRuleObservableList = FXCollections.observableArrayList();
    private final ObservableMap<RideType, Integer> pricingTierObservableMap = FXCollections.observableHashMap();

    public void setInitData(FareStructureManagement fareStructureManagement) {
        this.fareStructureManagement = fareStructureManagement;
        modificationRuleObservableList.addAll(fareStructureManagement.getFareModificationRuleIdList());
        pricingTierObservableMap.putAll(fareStructureManagement.getPricingTierMap());
        addPricingTiers();
        addFareModificationRules();

        pricingTierObservableMap.addListener((MapChangeListener<? super RideType, ? super Integer>) c -> {
            if (c.wasAdded()) {
                fareStructureManagement.updatePricingTier(c.getKey(), c.getValueAdded());
                Platform.runLater(() -> {
                    pricingTierVbox.getChildren().clear();
                    addPricingTiers();
                });
            }
        });


        modificationRuleObservableList.addListener((ListChangeListener<? super Integer>) c -> {
            if (c.next() && c.wasAdded()) {
                Util.getInstance().getWorkers().execute(() -> {
                    FareModificationRule modificationRule = Util.getInstance().getDb().getObject(BinFilePath.FARE_MODIFICATION_RULE, f -> f.getModificationRuleId() == c.getAddedSubList().get(0));
                    fareStructureManagement.addFareModificationRule(modificationRule);
                    addModificationRuleView(modificationRule);
                });
            }
        });
    }

    @FXML
    private void onUpDatePricingTier(ActionEvent event) {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.UPDATE_PRICING_TIER_VIEW);
                AnchorPane pane = loader.load();
                ((PricingTierUpdateViewController) loader.getController()).setInitData(pricingTierObservableMap);
                Platform.runLater(() -> Util.getInstance().showScene(new Scene(pane), event, ""));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @FXML
    private void onAddModificationRule(ActionEvent event) {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.ADD_FARE_MODIFICATION_RULE_VIEW);
                AnchorPane pane = loader.load();
                ((AddFareModificationRuleController) loader.getController()).setInitData(modificationRuleObservableList);
                Platform.runLater(() -> Util.getInstance().showScene(new Scene(pane), event, ""));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void addPricingTiers() {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ignored) {

            }
            List<PricingTier> pricingTierList = Util.getInstance()
                    .getDb()
                    .getObjectList(BinFilePath.PRICING_TIER);
            for (RideType rideType : fareStructureManagement.getPricingTierMap().keySet()) {
                int value = fareStructureManagement.getPricingTierMap().get(rideType);
                PricingTier pricingTier = pricingTierList.stream().filter(p -> p.getPricingTierId() == value).findFirst().get();
                addPricingTierView(rideType, pricingTier);
            }
        });
    }

    private void addFareModificationRules() {
        Util.getInstance().getWorkers().execute(() -> {
            List<FareModificationRule> modificationRuleList = Util.getInstance().getDb().getObjectList(BinFilePath.FARE_MODIFICATION_RULE, f -> fareStructureManagement.getFareModificationRuleIdList().stream().anyMatch(id -> id == f.getModificationRuleId()));
            for (FareModificationRule modificationRule : modificationRuleList) {
                addModificationRuleView(modificationRule);
            }
        });
    }

    private void addPricingTierView(RideType rideType, PricingTier pricingTier) {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.PRICING_TIER_VIEW);
            AnchorPane pane = loader.load();
            ((PricingTierViewController) loader.getController()).setInitData(rideType, pricingTier);
            Platform.runLater(() -> pricingTierVbox.getChildren().add(pane));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addModificationRuleView(FareModificationRule modificationRule) {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.FARE_MODIFICATION_RULE_VIEW);
                AnchorPane pane = loader.load();
                ((FareModificationRuleController) loader.getController()).setInitData(modificationRule);
                Platform.runLater(() -> fareModificationRuleVBox.getChildren().add(pane));
            } catch (IOException ignored) {
                // log the error
            }
        });
    }
}