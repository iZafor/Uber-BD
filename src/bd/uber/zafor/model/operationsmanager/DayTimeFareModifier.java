package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public class DayTimeFareModifier implements Serializable, FareModifier {
    private static final int DAY_START_HOUR = 6;
    private static final int DAY_END_HOUR = 18;

    private final float dayTimeMultiplier;

    public DayTimeFareModifier(float dayTimeMultiplier) {
        this.dayTimeMultiplier = dayTimeMultiplier;
    }

    public float getDayTimeMultiplier() {
        return dayTimeMultiplier;
    }

    @Override
    public boolean appliesTo(RideRequest rideRequest) {
        return rideRequest.getRequestTime().getHour() >= DAY_START_HOUR && rideRequest.getRequestTime().getHour() <= DAY_END_HOUR;
    }

    @Override
    public float modifyBaseFare(float baseFare) {
        return baseFare + baseFare * (dayTimeMultiplier / 100f);
    }

    @Override
    public float modifyDistanceRate(float distanceRate) {
        return distanceRate * dayTimeMultiplier;
    }

    @Override
    public float modifyTimeRate(float timeRate) {
        return timeRate * dayTimeMultiplier;
    }
}