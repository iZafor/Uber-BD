package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public class PricingTear implements Serializable {
    private float baseFare;
    private float distanceRate;
    private float timeRate;

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