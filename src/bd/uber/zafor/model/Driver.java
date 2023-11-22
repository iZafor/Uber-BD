package bd.uber.zafor.model;

import bd.uber.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Driver extends User implements Serializable {
    private String profileImage;
    private String nidFrontSide;
    private String nidBackSide;
    private DriverStatus driverStatus;
    private Location currentLocation;
    private VehicleInfo vehicleInfo;
    private List<Ride> rideList;
    private int experienceInYears;
    private float distanceDriven;
    private float totalEarnings;
    private List<Language> languagesSpeak;
    private DrivingLicense drivingLicense;
    private float rideRequestRange;

    public Driver() {
        rideList = new ArrayList<>();
        vehicleInfo = new VehicleInfo();
        languagesSpeak = new ArrayList<>();
    }

    public Driver(int id, String name, String password, LocalDate accountCreationDate, ContactDetails contactDetails) {
        super(id, password, name, accountCreationDate, contactDetails);
    }

    public Ride confirmRideRequest(RideRequest rideRequest) {
        return new Ride(rideRequest.getPassengerId(), id, rideRequest.getPickupPoint(), rideRequest.getDropOffPoint(), rideRequest.getFare());
    }

    public Ride cancelRide(Ride ride) { // there should be a cancellation reason
        ride.setCancelled(true);
        rideList.add(ride);
        return ride;
    }

    public void completeRide(Ride ride) {
        ride.setCompleted(true);
        rideList.add(ride);
        totalEarnings += ride.getFare();
        distanceDriven += ride.getRideDistance();
        Util.getInstance().getDb().addObject(ride, BinFilePath.RIDE); // unsure
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

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
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

    public DrivingLicense getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(DrivingLicense drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public float getRideRequestRange() {
        return rideRequestRange;
    }

    public void setRideRequestRange(float rideRequestRange) {
        this.rideRequestRange = rideRequestRange;
    }
}