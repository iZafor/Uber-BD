package bd.uber;

public enum UserType {
    DRIVER("Driver"),
    PASSENGER("Passenger"),
    PAYMENT_PROCESSOR("Payment Processor"),
    OPERATIONS_MANAGER("Operations Manager"),
    HUMAN_RESOURCE_MANGER("Human Resource Manager"),
    SUPPORT_REPRESENTATIVE("Support Representative"),
    VEHICLE_MAINTENANCE_Manager("Vehicle Maintenance Manager"),
    BUSINESS_ACCOUNT_MANAGER("Business Account Manager");

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return this.userType;
    }
}