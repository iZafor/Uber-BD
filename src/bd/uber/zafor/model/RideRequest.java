package bd.uber.zafor.model;

import bd.uber.Location;

public class RideRequest {
    private final int passengerId;
    private final String passengerName;
    private final Location pickupPoint;
    private final Location dropOffPoint;
    private final float fare;

    public RideRequest(int passengerId, String passengerName, Location pickupPoint, Location dropOffPoint, float fare) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.pickupPoint = pickupPoint;
        this.dropOffPoint = dropOffPoint;
        this.fare = fare;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Location getPickupPoint() {
        return pickupPoint;
    }

    public Location getDropOffPoint() {
        return dropOffPoint;
    }

    public float getFare() {
        return fare;
    }
}