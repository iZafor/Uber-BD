package bd.uber.zafor.model.driver;

import java.io.Serializable;

public class OngoingRide implements Serializable {
    private final RideRequest rideRequest;
    private final Ride ride;
    private final boolean hasStarted;

    public OngoingRide(RideRequest rideRequest, Ride ride, boolean hasStarted) {
        this.rideRequest = rideRequest;
        this.ride = ride;
        this.hasStarted = hasStarted;
    }

    public RideRequest getRideRequest() {
        return rideRequest;
    }

    public Ride getRide() {
        return ride;
    }

    public boolean hasStarted() {
        return hasStarted;
    }
}