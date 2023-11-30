package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public class PassengerRatingDiscountCondition implements Serializable, DiscountCondition {
    private float minPassengerRating;

    @Override
    public boolean isSatisfied(RideRequest rideRequest) {
        return rideRequest.getPassengerRating() >= minPassengerRating;
    }
}