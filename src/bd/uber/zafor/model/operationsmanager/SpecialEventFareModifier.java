package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public class SpecialEventFareModifier implements Serializable, FareModifier{
    private final float eventMultiplier;

    public SpecialEventFareModifier(float eventMultiplier) {
        this.eventMultiplier = eventMultiplier;
    }

    public float getEventMultiplier() {
        return eventMultiplier;
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