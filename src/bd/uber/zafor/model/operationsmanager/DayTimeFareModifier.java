package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public class DayTimeFareModifier implements Serializable, FareModifier {
    private final float dayTimeMultiplier;

    public DayTimeFareModifier(float dayTimeMultiplier) {
        this.dayTimeMultiplier = dayTimeMultiplier;
    }

    public float getDayTimeMultiplier() {
        return dayTimeMultiplier;
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