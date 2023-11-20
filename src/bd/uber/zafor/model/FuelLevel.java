package bd.uber.zafor.model;

public enum FuelLevel {
    FULL("Full"),
    THREE_QUARTERS("3/4"),
    HALF("1/2"),
    QUARTER("1/4"),
    EMPTY("Empty");

    private final String fuelLevel;

    FuelLevel(String fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    @Override
    public String toString() {
        return fuelLevel;
    }
}