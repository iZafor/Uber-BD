package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public class RideDistanceCondition implements Serializable, DiscountCondition {
    private final float minDistance;

    public RideDistanceCondition(float minDistance) {
        this.minDistance = minDistance;
    }

    @Override
    public boolean isSatisfied(RideRequest rideRequest) {
        return rideRequest.getRideDistance() >= minDistance;
    }

    public float getMinDistance() {
        return minDistance;
    }
}