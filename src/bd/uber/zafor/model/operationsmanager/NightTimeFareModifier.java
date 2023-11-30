package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public class NightTimeFareModifier implements Serializable, FareModifier {
    private final float nightTimeMultiplier;

    public NightTimeFareModifier(float nightTimeMultiplier) {
        this.nightTimeMultiplier = nightTimeMultiplier;
    }

    public float getNightTimeMultiplier() {
        return nightTimeMultiplier;
    }

    @Override
    public float modifyBaseFare(float baseFare) {
        return 0;
    }

    @Override
    public float modifyDistanceRate(float distanceFare) {
        return 0;
    }

    @Override
    public float modifyTimeRate(float timeFare) {
        return 0;
    }
}