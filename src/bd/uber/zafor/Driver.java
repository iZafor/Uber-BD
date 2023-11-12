package bd.uber.zafor;

import bd.uber.Location;

import java.io.Serializable;

public class Driver implements Serializable {
    private DriverStatus driverStatus;
    private Location currentLocation;
    private VehicleInfo vehicleInfo;
}