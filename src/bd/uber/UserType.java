package bd.uber;

public enum UserType {
    DRIVER("Driver", ""),
    PASSENGER("Passenger", ""),
    PAYMENT_PROCESSOR("Payment Processor", ""),
    OPERATIONS_MANAGER("Operations Manager", ""),
    HUMAN_RESOURCE_MANGER("Human Resource Manager", ""),
    SUPPORT_REPRESENTATIVE("Support Representative", ""),
    VEHICLE_MAINTENANCE_TEAM("Vehicle Maintenance Team", ""),
    BUSINESS_ACCOUNT_MANAGER("Business Account Manager", "");

    private final String userType;
    private final String binFilePath;

    UserType(String userType, String binFilePath) {
        this.userType = userType;
        this.binFilePath = binFilePath;
    }

    public String getBinFilePath() {
        return this.binFilePath;
    }

    @Override
    public String toString() {
        return this.userType;
    }
}