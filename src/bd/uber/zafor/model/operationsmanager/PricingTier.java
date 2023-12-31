package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public class PricingTier implements Serializable {
    private final int pricingTierId;
    private float baseFare;
    private float distanceRate;
    private float timeRate;

    public PricingTier(int pricingTierId, float baseFare, float distanceRate, float timeRate) {
        this.pricingTierId = pricingTierId;
        this.baseFare = baseFare;
        this.distanceRate = distanceRate;
        this.timeRate = timeRate;
    }

    public int getPricingTierId() {
        return pricingTierId;
    }

    public float getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(float baseFare) {
        this.baseFare = baseFare;
    }

    public float getDistanceRate() {
        return distanceRate;
    }

    public void setDistanceRate(float distanceRate) {
        this.distanceRate = distanceRate;
    }

    public float getTimeRate() {
        return timeRate;
    }

    public void setTimeRate(float timeRate) {
        this.timeRate = timeRate;
    }
}