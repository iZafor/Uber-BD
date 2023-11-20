package bd.uber.zafor.model;

import java.io.Serializable;

public class RideVehicle implements Serializable {
    private final String model;
    private final String color;
    private final VehicleOwner owner;
    private final VehicleType vehicleType;

    public RideVehicle(String model, String color, VehicleOwner owner, VehicleType vehicleType) {
        this.model = model;
        this.color = color;
        this.owner = owner;
        this.vehicleType = vehicleType;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public VehicleOwner getOwner() {
        return owner;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}