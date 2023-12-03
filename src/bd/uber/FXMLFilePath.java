package bd.uber;

public enum FXMLFilePath {
    // SignIn/Signup
    GET_STARTED_VIEW("/fxml/get-started-view.fxml"),
    CREATE_NEW_ACCOUNT_VIEW("/fxml/create-new-account-view.fxml"),

    // Driver Profile
    DRIVER_VIEW("/fxml/zafor/driver/driver-view.fxml"),
    DRIVER_DASHBOARD_VIEW("/fxml/zafor/driver/driver-dashboard-view.fxml"),
    DRIVER_PROFILE_BASIC_INFO_VIEW("/fxml/zafor/driver/driver-profile-basic-info-view.fxml"),
    DRIVER_PROFILE_DRIVING_LICENSE_VIEW("/fxml/zafor/driver/driver-profile-driving-license-view.fxml"),
    DRIVER_PROFILE_VEHICLE_INFO_VIEW("/fxml/zafor/driver/driver-profile-vehicle-info-view.fxml"),
    DRIVER_PROFILE_VEHICLE_STATUS_VIEW("/fxml/zafor/driver/driver-profile-vehicle-status-view.fxml"),
    DRIVER_RIDES_VIEW("/fxml/zafor/driver/driver-rides-view.fxml"),
    DRIVER_MAINTENANCE_REQUEST_VIEW("/fxml/zafor/driver/driver-maintenance-request-view.fxml"),
    DETAILED_RIDE_VIEW("/fxml/zafor/driver/detailed-ride-view.fxml"),
    RIDE_REQUEST_VIEW("/fxml/zafor/driver/ride-request-view.fxml"),
    CURRENT_RIDE_VIEW("/fxml/zafor/driver/current-ride-view.fxml"),
    DRIVER_REPORT_DAMAGE_VIEW("/fxml/zafor/driver/driver-report-damage-view.fxml"),

    // Driver Signup
    DRIVER_SIGN_UP_VIEW("/fxml/zafor/driver/driver-sign-up-view.fxml"),
    DRIVER_SIGN_UP_BASIC_INFO_VIEW("/fxml/zafor/driver/driver-sign-up-basic-info-view.fxml"),
    DRIVER_SIGN_UP_VEHICLE_INFO_VIEW("/fxml/zafor/driver/driver-sign-up-vehicle-info-view.fxml"),
    DRIVER_SIGN_UP_VEHICLE_STATUS_VIEW("/fxml/zafor/driver/driver-sign-up-vehicle-status-view.fxml"),
    DRIVER_SIGN_UP_DRIVING_LICENSE_VIEW("/fxml/zafor/driver/driver-sign-up-driving-license-view.fxml"),
    DRIVER_SIGN_UP_IDENTIFICATION_INFO_VIEW("/fxml/zafor/driver/driver-sign-up-identification-info-view.fxml"),

    // Others
    RIDE_VIEW("/fxml/zafor/driver/ride-view.fxml"),

    // Operations Manager
    OPERATIONS_MANAGER_VIEW("/fxml/zafor/operationsmanager/operations-manager-view.fxml"),
    OPERATIONS_MANAGER_PROFILE_VIEW("/fxml/zafor/operationsmanager/operations-manager-profile-view.fxml"),
    OPERATIONS_MANAGER_GET_INSIGHT_VIEW("/fxml/zafor/operationsmanager/operations-manager-get-insight-view.fxml"),
    OPERATIONS_MANAGER_MANAGE_LOCATION_VIEW("/fxml/zafor/operationsmanager/operations-manager-manage-locations-view.fxml"),
    OPERATIONS_MANAGER_MANAGE_PROMOTIONAL_CAMPAIGNS_VIEW("/fxml/zafor/operationsmanager/operations-manager-manage-promotional-campaigns-view.fxml"),
    OPERATIONS_MANAGER_MANAGE_FARE_STRUCTURE_VIEW("/fxml/zafor/operationsmanager/operations-manager-fare-structure-management-view.fxml"),
    OPERATIONS_MANAGER_REQUEST_FOR_LEAVE_VIEW("/fxml/zafor/operationsmanager/operations-manager-request-for-leave-view.fxml"),
    CAMPAIGN_VIEW("/fxml/zafor/operationsmanager/campaign-view.fxml"),
    CAMPAIGN_DETAILED_VIEW("/fxml/zafor/operationsmanager/campaign-detailed-view.fxml"),
    DISCOUNT_VIEW("/fxml/zafor/operationsmanager/discount-view.fxml"),
    PRICING_TIER_VIEW("/fxml/zafor/operationsmanager/pricing-tier-view.fxml"),
    UPDATE_PRICING_TIER_VIEW("/fxml/zafor/operationsmanager/update-pricing-tier-view.fxml"),
    ADD_FARE_MODIFICATION_RULE_VIEW("/fxml/zafor/operationsmanager/add-fare-modification-rule-view.fxml"),
    FARE_MODIFICATION_RULE_VIEW("/fxml/zafor/operationsmanager/fare-modification-rule-view.fxml"),
    LEAVE_REQUEST_VIEW("/fxml/zafor/operationsmanager/leave-request-view.fxml"),
    LOCATION_VIEW("/fxml/zafor/operationsmanager/location-view.fxml");

    private final String path;

    FXMLFilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}