package bd.uber.zafor.model.driver;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RideRequest implements Serializable {
    private final int passengerId;
    private final float passengerRating;
    private final int pickupPointId;
    private final int dropOffPointId;
    private final PaymentMethod paymentMethod;
    private float fare;
    private final float rideDistance;
    private final LocalDateTime requestTime;
    private final RideType rideType;
    private boolean hasResolved;

    public RideRequest(int passengerId, float passengerRating, int pickupPointId, int dropOffPointId, PaymentMethod paymentMethod, float rideDistance, LocalDateTime requestTime, RideType rideType) {
        this.passengerId = passengerId;
        this.passengerRating = passengerRating;
        this.pickupPointId = pickupPointId;
        this.dropOffPointId = dropOffPointId;
        this.paymentMethod = paymentMethod;
        this.rideDistance = rideDistance;
        this.requestTime = requestTime;
        this.rideType = rideType;
        this.hasResolved = false;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public float getPassengerRating() {
        return passengerRating;
    }

    public int getPickupPointId() {
        return pickupPointId;
    }

    public int getDropOffPointId() {
        return dropOffPointId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public float getRideDistance() {
        return rideDistance;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public RideType getRideType() {
        return rideType;
    }

    public boolean getHasResolved() {
        return hasResolved;
    }

    public void setHasResolved(boolean hasResolved) {
        this.hasResolved = hasResolved;
    }
}