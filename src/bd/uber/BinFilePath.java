package bd.uber;

public enum BinFilePath {
    // Users
    DRIVER("bin/driver.bin"),
    PASSENGER("bin/passenger.bin"),
    PAYMENT_PROCESSOR("bin/payment_processor.bin"),
    OPERATIONS_MANAGER("bin/operations_manager.bin"),
    HUMAN_RESOURCE_MANGER("bin/human_resource_manager.bin"),
    SUPPORT_REPRESENTATIVE("bin/support_representative.bin"),
    VEHICLE_MAINTENANCE_TEAM("bin/vehicle_maintenance_team.bin"),
    BUSINESS_ACCOUNT_MANAGER("bin/business_account_manager.bin"),

    // Unknown
    LOCATION("bin/location.bin"),
    RIDE("bin/ride.bin"),
    RIDE_REQUEST("bin/ride_request.bin"),

    // Driver Related Files,
    VEHICLE_INFO("bin/vehicle_info.bin"),

    INVENTORY("bin/inventory.bin"),
    PLACED_ORDER("bin/placed_order.bin");

    private final String path;

    BinFilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        synchronized (this) {
            return path;
        }
    }
}