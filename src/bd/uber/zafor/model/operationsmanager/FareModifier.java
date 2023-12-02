package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public interface FareModifier extends Serializable {
    boolean appliesTo(RideRequest rideRequest);
    float modifyBaseFare(float baseFare);

    float modifyDistanceRate(float distanceRate);

    float modifyTimeRate(float timeRate);
}