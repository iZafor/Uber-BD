package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FareModificationRule implements Serializable {
    private final int modificationRuleId;
    private String description;
    private boolean isActive;
    private FareModifier fareModifier;

    public FareModificationRule(int modificationRuleId, String description, FareModifier fareModifier) {
        this.modificationRuleId = modificationRuleId;
        this.description = description;
        this.fareModifier = fareModifier;
        this.isActive = true;
    }

    public boolean appliesTo(RideRequest rideRequest) {
        return isActive && fareModifier.appliesTo(rideRequest);
    }

    public int getModificationRuleId() {
        return modificationRuleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public FareModifier getFareModifier() {
        return fareModifier;
    }

    public void setFareModifier(FareModifier fareModifier) {
        this.fareModifier = fareModifier;
    }
}