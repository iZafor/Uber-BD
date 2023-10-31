package bd.uber;

import java.io.Serializable;

public class Location implements Serializable {
    private final String name;
    private final float distanceFromCentralPoint;

    public Location(String name, float distanceFromCentralPoint) {
        this.name = name;
        this.distanceFromCentralPoint = distanceFromCentralPoint;
    }

    public String getName() {
        return name;
    }

    public float getDistance(Location other) {
        return Math.abs(this.distanceFromCentralPoint - other.distanceFromCentralPoint);
    }
}