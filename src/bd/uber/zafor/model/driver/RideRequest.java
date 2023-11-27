package bd.uber.zafor.model.driver;

import bd.uber.Location;

import java.io.Serializable;

public class RideRequest implements Serializable {
    private final int passengerId;
    private final float passengerRating;
    private final Location pickupPoint;
    private final Location dropOffPoint;
    private final PaymentMethod paymentMethod;
    private final float fare;

    public RideRequest(int passengerId, float passengerRating, Location pickupPoint, Location dropOffPoint, PaymentMethod paymentMethod, float fare) {
        this.passengerId = passengerId;
        this.passengerRating = passengerRating;
        this.pickupPoint = pickupPoint;
        this.dropOffPoint = dropOffPoint;
        this.paymentMethod = paymentMethod;
        this.fare = fare;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public float getPassengerRating() {
        return passengerRating;
    }

    public Location getPickupPoint() {
        return pickupPoint;
    }

    public Location getDropOffPoint() {
        return dropOffPoint;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public float getFare() {
        return fare;
    }
}