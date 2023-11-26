package bd.uber.zafor.model;

import bd.uber.Location;

import java.io.Serializable;
import java.time.LocalDateTime;

public final class Ride implements Serializable {
    private boolean isCancelled;
    private boolean isCompleted;
    private int passengerId;
    private int driverId;
    private final Location pickupPoint;
    private final Location dropOffPoint;
    private LocalDateTime pickupTime;
    private LocalDateTime dropOffTime;
    private final float rideDistance;
    private float fare;
    private PaymentMethod paymentMethod;
    private RideFeedback passengerFeedback;
    private RideFeedback riderFeedback;
    private RideVehicle vehicle;

    public Ride(int passengerId, int driverId, Location pickupPoint, Location dropOffPoint, PaymentMethod paymentMethod, float fare) {
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.pickupPoint = pickupPoint;
        this.dropOffPoint = dropOffPoint;
        this.paymentMethod = paymentMethod;
        this.fare = fare;
        this.isCompleted = false;
        this.isCancelled = false;
        this.rideDistance = pickupPoint.getDistance(dropOffPoint);
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public Location getPickupPoint() {
        return pickupPoint;
    }

    public Location getDropOffPoint() {
        return dropOffPoint;
    }

    public float getRideDistance() {
        return rideDistance;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public LocalDateTime getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(LocalDateTime dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public RideFeedback getPassengerFeedback() {
        return passengerFeedback;
    }

    public void setPassengerFeedback(RideFeedback passengerFeedback) {
        this.passengerFeedback = passengerFeedback;
    }

    public RideFeedback getRiderFeedback() {
        return riderFeedback;
    }

    public void setRiderFeedback(RideFeedback riderFeedback) {
        this.riderFeedback = riderFeedback;
    }

    public RideVehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(RideVehicle vehicle) {
        this.vehicle = vehicle;
    }
}