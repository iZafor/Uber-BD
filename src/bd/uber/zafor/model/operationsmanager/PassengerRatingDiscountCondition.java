package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public class PassengerRatingDiscountCondition implements Serializable, DiscountCondition {
    private final float minPassengerRating;

    public PassengerRatingDiscountCondition(float minPassengerRating) {
        this.minPassengerRating = minPassengerRating;
    }

    @Override
    public boolean isSatisfied(RideRequest rideRequest) {
        return rideRequest.getPassengerRating() >= minPassengerRating;
    }

    public float getMinPassengerRating() {
        return minPassengerRating;
    }
}