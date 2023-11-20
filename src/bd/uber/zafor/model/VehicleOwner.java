package bd.uber.zafor.model;

public enum VehicleOwner {
    DRIVER("Driver"),
    UBER("Uber");

    private final String vehicleOwner;

    VehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    @Override
    public String toString() {
        return vehicleOwner;
    }
}