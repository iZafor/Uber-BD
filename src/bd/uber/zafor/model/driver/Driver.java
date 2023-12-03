package bd.uber.zafor.model.driver;

import bd.uber.BinFilePath;
import bd.uber.User;
import bd.uber.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Driver extends User implements Serializable {
    private int currentLocationId;
    private DriverStatus driverStatus;
    private int vehicleInfoId;
    private transient List<Ride> rideList;
    private int experienceInYears;
    private float distanceDriven;
    private float totalEarnings;
    private List<Language> languagesSpeak;
    private int drivingLicenseId;
    private float rideRequestRange;

    public Driver(int id) {
        super(id);
        rideList = new ArrayList<>();
        languagesSpeak = new ArrayList<>();
    }

    public Ride acceptRideRequest(RideRequest rideRequest) {
        return new Ride(rideRequest.getPassengerId(), id, rideRequest.getPickupPointId(), rideRequest.getDropOffPointId(), rideRequest.getRideDistance(), rideRequest.getPaymentMethod(), rideRequest.getFare(), vehicleInfoId);
    }

    public void cancelRide(Ride ride) { // there should be a cancellation reason
        ride.setHasCancelled(true);
        rideList.add(ride);
        Util.getInstance().getWorkers().execute(() ->
                Util.getInstance().getDb().addObject(ride, BinFilePath.RIDE)
        );
    }

    public void completeRide(Ride ride) {
        ride.setHasCompleted(true);
        rideList.add(ride);
        totalEarnings += ride.getFare();
        distanceDriven += ride.getRideDistance();
        Util.getInstance().getWorkers().execute(() ->
                Util.getInstance().getDb().addObject(ride, BinFilePath.RIDE)
        );
    }

    public int getCurrentLocationId() {
        return currentLocationId;
    }

    public void setCurrentLocationId(int currentLocationId) {
        this.currentLocationId = currentLocationId;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public int getVehicleInfoId() {
        return vehicleInfoId;
    }

    public void setVehicleInfoId(int vehicleInfoId) {
        this.vehicleInfoId = vehicleInfoId;
    }

    public List<Ride> getRideList() {
        return rideList;
    }

    public void setRideList(List<Ride> rideList) {
        this.rideList = rideList;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(int experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public float getDistanceDriven() {
        return distanceDriven;
    }

    public void setDistanceDriven(float distanceDriven) {
        this.distanceDriven = distanceDriven;
    }

    public float getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(float totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public List<Language> getLanguagesSpeak() {
        return languagesSpeak;
    }

    public void setLanguagesSpeak(List<Language> languagesSpeak) {
        this.languagesSpeak = languagesSpeak;
    }

    public int getDrivingLicenseId() {
        return drivingLicenseId;
    }

    public void setDrivingLicenseId(int drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

    public float getRideRequestRange() {
        return rideRequestRange;
    }

    public void setRideRequestRange(float rideRequestRange) {
        this.rideRequestRange = rideRequestRange;
    }
}