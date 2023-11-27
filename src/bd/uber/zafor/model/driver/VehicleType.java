package bd.uber.zafor.model.driver;

import java.io.Serializable;

public enum VehicleType implements Serializable {
    SEDAN("Sedan"),
    SUV("Suv"),
    HACTHBACK("Hatchback"),
    LUXURY("Luxury"),
    VAN("Van"),
    CONVERTIBLE("Convertible"),
    TRUCK("Truck");

    private final String vehicleType;

    VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return vehicleType;
    }
}