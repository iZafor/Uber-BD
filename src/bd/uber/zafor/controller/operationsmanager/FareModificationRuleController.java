package bd.uber.zafor.controller.operationsmanager;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.operationsmanager.DayTimeFareModifier;
import bd.uber.zafor.model.operationsmanager.FareModificationRule;
import bd.uber.zafor.model.operationsmanager.NightTimeFareModifier;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class FareModificationRuleController {
    @FXML
    private Label ruleDescriptionLabel;
    @FXML
    private Text ruleStatusText;
    @FXML
    private Text fareModifierTitleText;
    @FXML
    private Text factorText;
    @FXML
    private Button statusUpdateButton;

    private FareModificationRule modificationRule;

    public void setInitData(FareModificationRule modificationRule) {
        this.modificationRule = modificationRule;
        ruleDescriptionLabel.setText(modificationRule.getDescription());
        ruleStatusText.setText(modificationRule.isActive() ? "Active" : "In active");
        fareModifierTitleText.setText(modificationRule.getFareModifier() instanceof NightTimeFareModifier ? "Night Time" : "Day Time");
        factorText.setText(String.valueOf(modificationRule.getFareModifier() instanceof NightTimeFareModifier ?
                ((NightTimeFareModifier) modificationRule.getFareModifier()).getNightTimeMultiplier() :
                ((DayTimeFareModifier) modificationRule.getFareModifier()).getDayTimeMultiplier()));
        statusUpdateButton.setText(modificationRule.isActive() ? "Deactivate" : "Activate");
    }

    @FXML
    private void onUpdateStatus() {
        modificationRule.setActive(!modificationRule.isActive());
        ruleStatusText.setText(modificationRule.isActive() ? "Active" : "In active");
        statusUpdateButton.setText(modificationRule.isActive() ? "Deactivate" : "Activate");
        updateModificationRuleOnFile();
    }

    private void updateModificationRuleOnFile() {
        Util.getInstance().getWorkers().execute(() ->
                Util.getInstance().getDb().updateObjectFile(
                modificationRule,
                BinFilePath.FARE_MODIFICATION_RULE,
                f -> f.getModificationRuleId() == modificationRule.getModificationRuleId(),
                false
        ));
    }
}