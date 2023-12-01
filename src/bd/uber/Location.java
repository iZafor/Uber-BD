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

    public float getDistanceFromCentralPoint() {
        return distanceFromCentralPoint;
    }

    public float getDistance(Location other) {
        if ((direction.equals(Direction.NORTH) && other.direction.equals(Direction.SOUTH))
                || (direction.equals(Direction.SOUTH) && other.direction.equals(Direction.NORTH))
                || (direction.equals(Direction.EAST) && other.direction.equals(Direction.WEST))
                || (direction.equals(Direction.WEST) && other.direction.equals(Direction.EAST))) {
            return distanceFromCentralPoint + other.distanceFromCentralPoint;
        }
        return Math.abs(distanceFromCentralPoint - other.distanceFromCentralPoint);
    }
}