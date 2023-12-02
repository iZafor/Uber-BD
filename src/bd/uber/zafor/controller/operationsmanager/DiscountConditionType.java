package bd.uber.zafor.controller.operationsmanager;

public enum DiscountConditionType {
    RATING("Based on rating"),
    RIDE_DISTANCE("Base on ride distance");

    private final String conditionType;

    DiscountConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    @Override
    public String toString() {
        return conditionType;
    }
}