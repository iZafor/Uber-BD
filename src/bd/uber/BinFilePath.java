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
    CONTACT_DETAILS("bin/contact_details"),

    // Driver Related Files,

    
    EMPLOYEE_DETAILS("bin/Employee_Details"),
    VEHICLE_INFO("bin/vehicle_info.bin"),
    VEHICLE_STATUS("bin/vehicle_status.bin"),
    INSURANCE_POLICY("bin/insurance_policy.bin"),
    DRIVING_LICENSE("bin/driving_license.bin"),
    RIDE_VEHICLE("bin/ride_vehicle.bin"),
    PASSENGER_FEEDBACK("bin/passenger_feedback.bin"),
    DRIVER_FEEDBACK("bin/driver_feedback.bin"),
    DAMAGE_REPORT("bin/damage_report.bin"),
    MAINTENANCE_REQUEST("bin/maintenance_request.bin"),

    // Operations Manager Related Files
    PROMOTIONAL_CAMPAIGN("bin/promotional_campaign.bin"),
    DISCOUNT("bin/discount.bin"),
    FARE_MODIFICATION_RULE("bin/fare_modification_rule.bin"),
    PRICING_TIER("bin/pricing_tier.bin"),

    // Others
    LEAVE_REQUEST("bin/leave_request.bin"),
    REASON("bin/reason.bin"),

    // Temp files
    ONGOING_RIDE("bin/ongoing_ride.bin");

    private final String path;

    BinFilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}