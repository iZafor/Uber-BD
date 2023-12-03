package bd.uber.zafor.controller.operationsmanager;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.operationsmanager.DayTimeFareModifier;
import bd.uber.zafor.model.operationsmanager.FareModificationRule;
import bd.uber.zafor.model.operationsmanager.FareModifierType;
import bd.uber.zafor.model.operationsmanager.NightTimeFareModifier;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddFareModificationRuleController implements Initializable {
    @FXML
    private TextArea descriptionTextarea;

    @FXML
    private ComboBox<FareModifierType> modifierComboBox;

    @FXML
    private TextField factorTextField;

    private List<Integer> modificationRuleList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modifierComboBox.getItems().addAll(FareModifierType.values());
    }

    public void setInitData(List<Integer> modificationRuleList) {
        this.modificationRuleList = modificationRuleList;
    }

    @FXML
    private void onAddRule(ActionEvent event) {
        String description = descriptionTextarea.getText();
        if (description == null || description.isEmpty()) {
            Util.getInstance().showWarningMessage("Description cannot be empty!");
            return;
        }

        FareModifierType modifierType = modifierComboBox.getValue();
        if (modifierType == null) {
            Util.getInstance().showWarningMessage("Modifier type cannot be empty!");
            return;
        }

        float factor;
        try {
            factor = Float.parseFloat(factorTextField.getText());
            if (factor <= 0) {
                Util.getInstance().showWarningMessage("Factor must be greater than 0!");
                return;
            }
        } catch (NullPointerException | NumberFormatException ignored) {
            Util.getInstance().showWarningMessage("Invalid value for the factor!");
            return;
        }

        Util.getInstance().getWorkers().execute(() -> {
            FareModificationRule modificationRule = new FareModificationRule(
                    Util.getInstance().getDb().getObjectCount(BinFilePath.FARE_MODIFICATION_RULE) + 1,
                    description,
                    modifierType.equals(FareModifierType.NIGHT_TIME) ? new NightTimeFareModifier(factor) : new DayTimeFareModifier(factor)
            );

            if (Util.getInstance().getDb().addObject(modificationRule, BinFilePath.FARE_MODIFICATION_RULE)) {
                Platform.runLater(() -> {
                    descriptionTextarea.getScene().getWindow().hide();
                });
                modificationRuleList.add(modificationRule.getModificationRuleId());
            } else {
                Platform.runLater(() ->
                        Util.getInstance().showSuccessMessage("Falied to add modification rule!")
                );
            }
        });
    }
}