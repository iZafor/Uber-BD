package bd.uber.zafor.model.driver;

import bd.uber.BinFilePath;
import bd.uber.User;
import bd.uber.Util;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Driver extends User implements Serializable {
    private String profileImage;
    private String nidFrontSide;
    private String nidBackSide;
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
        ride.setCancelled(true);
        rideList.add(ride);
        Util.getInstance().getWorkers().execute(() ->
                Util.getInstance().getDb().addObject(ride, BinFilePath.RIDE)
        );
    }

    public void completeRide(Ride ride) {
        ride.setCompleted(true);
        rideList.add(ride);
        totalEarnings += ride.getFare();
        distanceDriven += ride.getRideDistance();
        Util.getInstance().getWorkers().execute(() ->
                Util.getInstance().getDb().addObject(ride, BinFilePath.RIDE)
        );
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getNidFrontSide() {
        return nidFrontSide;
    }

    public void setNidFrontSide(String nidFrontSide) {
        this.nidFrontSide = nidFrontSide;
    }

    public String getNidBackSide() {
        return nidBackSide;
    }

    public void setNidBackSide(String nidBackSide) {
        this.nidBackSide = nidBackSide;
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