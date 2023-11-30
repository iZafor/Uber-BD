package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public class PriceModificationRule implements Serializable {
    private String description;
    private boolean isActive;
    private FareModifier fareModifier;

    public boolean appliesTo(RideRequest rideRequest) {
        return false;
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