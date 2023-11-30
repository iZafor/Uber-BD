package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public class RideDistanceCondition implements Serializable, DiscountCondition {
    private float minDistance;

    @Override
    public boolean isSatisfied(RideRequest rideRequest) {
        return rideRequest.getRideDistance() >= minDistance;
    }
}