package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public class NightTimeFareModifier implements Serializable, FareModifier {
    private static final int NIGHT_START_HOUR = 18;
    private static final int NIGHT_END_HOUR = 6;
    private final float nightTimeMultiplier;

    public NightTimeFareModifier(float nightTimeMultiplier) {
        this.nightTimeMultiplier = nightTimeMultiplier;
    }

    public float getNightTimeMultiplier() {
        return nightTimeMultiplier;
    }

    @Override
    public boolean appliesTo(RideRequest rideRequest) {
        return rideRequest.getRequestTime().getHour() > NIGHT_START_HOUR || rideRequest.getRequestTime().getHour() < NIGHT_END_HOUR;
    }

    @Override
    public float modifyBaseFare(float baseFare) {
        return baseFare + baseFare * (nightTimeMultiplier / 100f);
    }

    @Override
    public float modifyDistanceRate(float distanceRate) {
        return distanceRate * nightTimeMultiplier;
    }

    @Override
    public float modifyTimeRate(float timeRate) {
        return timeRate * nightTimeMultiplier;
    }
}