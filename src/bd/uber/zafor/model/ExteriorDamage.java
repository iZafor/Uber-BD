package bd.uber.zafor.model;

public enum ExteriorDamage {
    SCRATCH("Scratch"),
    DENT("Dent"),
    CRACK("Crack"),
    BUMPER_DAMAGE("Bumper Damage"),
    RUST("Rust"),
    HEADLIGHT_OR_TAILLIGHT_DAMAGE("Headlight or taillight damage"),
    OTHER("Other"),
    NONE("None");

    private final String damage;

    ExteriorDamage(String damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return damage;
    }
}