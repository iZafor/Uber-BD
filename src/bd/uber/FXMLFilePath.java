package bd.uber;

public enum FXMLFilePath {
    // SignIn/Signup
    GET_STARTED_VIEW("/fxml/get-started-view.fxml"),
    CREATE_NEW_ACCOUNT_VIEW("/fxml/create-new-account-view.fxml"),

    // Driver Profile
    DRIVER_VIEW("/fxml/zafor/driver-view.fxml"),
    DRIVER_DASHBOARD_VIEW("/fxml/zafor/driver-dashboard-view.fxml"),
    DRIVER_PROFILE_BASIC_INFO_VIEW("/fxml/zafor/driver-profile-basic-info-view.fxml"),
    DRIVER_PROFILE_DRIVING_LICENSE_VIEW("/fxml/zafor/driver-profile-driving-license-view.fxml"),
    DRIVER_PROFILE_VEHICLE_INFO_VIEW("/fxml/zafor/driver-profile-vehicle-info-view.fxml"),
    DRIVER_PROFILE_VEHICLE_STATUS_VIEW("/fxml/zafor/driver-profile-vehicle-status-view.fxml"),
    DRIVER_RIDES_VIEW("/fxml/zafor/driver-rides-view.fxml"),
    DRIVER_REPAIR_REQUEST_VIEW("/fxml/zafor/driver-repair-request-view.fxml"),
    DETAILED_RIDE_VIEW("/fxml/zafor/detailed-ride-view.fxml"),
    RIDE_REQUEST_VIEW("/fxml/zafor/ride-request-view.fxml"),
    CURRENT_RIDE_VIEW("/fxml/zafor/current-ride-view.fxml"),

    // Driver Signup
    DRIVER_SIGN_UP_VIEW("/fxml/zafor/driver-sign-up-view.fxml"),
    DRIVER_SIGN_UP_BASIC_INFO_VIEW("/fxml/zafor/driver-sign-up-basic-info-view.fxml"),
    DRIVER_SIGN_UP_VEHICLE_INFO_VIEW("/fxml/zafor/driver-sign-up-vehicle-info-view.fxml"),
    DRIVER_SIGN_UP_VEHICLE_STATUS_VIEW("/fxml/zafor/driver-sign-up-vehicle-status-view.fxml"),
    DRIVER_SIGN_UP_DRIVING_LICENSE_VIEW("/fxml/zafor/driver-sign-up-driving-license-view.fxml"),
    DRIVER_SIGN_UP_IDENTIFICATION_INFO_VIEW("/fxml/zafor/driver-sign-up-identification-info-view.fxml"),

    // Others
    RIDE_VIEW("/fxml/zafor/ride-view.fxml");

    private final String path;

    FXMLFilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}