package bd.uber.zafor.model.driver;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RideRequest implements Serializable {
    private final int passengerId;
    private final float passengerRating;
    private final int pickupPointId;
    private final int dropOffPointId;
    private final PaymentMethod paymentMethod;
    private final float fare;
    private final float rideDistance;
    private final LocalDateTime requestTime;
    private boolean isResolved;

    public RideRequest(int passengerId, float passengerRating, int pickupPointId, int dropOffPointId, PaymentMethod paymentMethod, float fare, float rideDistance, LocalDateTime requestTime) {
        this.passengerId = passengerId;
        this.passengerRating = passengerRating;
        this.pickupPointId = pickupPointId;
        this.dropOffPointId = dropOffPointId;
        this.paymentMethod = paymentMethod;
        this.fare = fare;
        this.rideDistance = rideDistance;
        this.requestTime = requestTime;
        this.isResolved = false;
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

    public float getRideDistance() {
        return rideDistance;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }
}