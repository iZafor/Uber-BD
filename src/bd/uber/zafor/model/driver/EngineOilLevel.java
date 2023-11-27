package bd.uber.zafor.model.driver;

public enum EngineOilLevel {
    BELOW_MIN_LEVEL("Below min level"),
    BETWEEN_MIN_AND_MAX_LEVEL("Between min and max level"),
    ABOVE_MAX_LEVEL("Above max level");

    private final String oilLevel;

    EngineOilLevel(String oilLevel) {
        this.oilLevel = oilLevel;
    }

    @Override
    public String toString() {
        return oilLevel;
    }
}