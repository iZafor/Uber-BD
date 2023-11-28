package bd.uber;

import java.io.Serializable;

public final class Location implements Serializable {
    private final int locationId;
    private final String name;
    private final Direction direction;
    private final float distanceFromCentralPoint;

    public Location(int locationId, String name, Direction direction, float distanceFromCentralPoint) {
        this.locationId = locationId;
        this.name = name;
        this.direction = direction;
        this.distanceFromCentralPoint = distanceFromCentralPoint;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public Direction getDirection() {
        return direction;
    }

    public float getDistance(Location other) {
        return other.direction.equals(direction) ? Math.abs(distanceFromCentralPoint - other.distanceFromCentralPoint) : distanceFromCentralPoint + other.distanceFromCentralPoint;
    }
}