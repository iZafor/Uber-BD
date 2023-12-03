package bd.uber.zafor.model.driver;

import java.io.Serializable;
import java.time.LocalDateTime;

public final class Ride implements Serializable {
    private boolean hasCancelled;
    private boolean hasCompleted;
    private int passengerId;
    private int driverId;
    private final int pickupPointId;
    private final int dropOffPointId;
    private LocalDateTime pickupTime;
    private LocalDateTime dropOffTime;
    private final float rideDistance;
    private float fare;
    private PaymentMethod paymentMethod;
    private int passengerFeedbackId;
    private int riderFeedbackId;
    private final int rideVehicleInfoId;

    public Ride(int passengerId, int driverId, int pickupPointId, int dropOffPointId, float rideDistance, PaymentMethod paymentMethod, float fare, int rideVehicleInfoId) {
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.pickupPointId = pickupPointId;
        this.dropOffPointId = dropOffPointId;
        this.rideDistance = rideDistance;
        this.paymentMethod = paymentMethod;
        this.fare = fare;
        this.rideVehicleInfoId = rideVehicleInfoId;
        this.hasCompleted = false;
        this.hasCancelled = false;
    }

    public boolean hasCancelled() {
        return hasCancelled;
    }

    public void setHasCancelled(boolean hasCancelled) {
        this.hasCancelled = hasCancelled;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public boolean hasCompleted() {
        return hasCompleted;
    }

    public void setHasCompleted(boolean hasCompleted) {
        this.hasCompleted = hasCompleted;
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

    public Integer getPickupPointId() {
        return pickupPointId;
    }

    public Integer getDropOffPointId() {
        return dropOffPointId;
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

    public int getPassengerFeedbackId() {
        return passengerFeedbackId;
    }

    public void setPassengerFeedbackId(int passengerFeedbackId) {
        this.passengerFeedbackId = passengerFeedbackId;
    }

    public int getRiderFeedbackId() {
        return riderFeedbackId;
    }

    public void setRiderFeedbackId(int riderFeedbackId) {
        this.riderFeedbackId = riderFeedbackId;
    }

    public int getRideVehicleInfoId() {
        return rideVehicleInfoId;
    }
}