package bd.uber.zafor.model.operationsmanager;

public enum FareModifierType {
    NIGHT_TIME("Night time modifier"),
    DAY_TIME("Day time modifier");

    private final String modifier;

    FareModifierType(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return modifier;
    }
}