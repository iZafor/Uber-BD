package bd.uber;

public enum FXMLFilePath {
    GET_STARTED_VIEW("/fxml/get-started-view.fxml"),
    CREATE_NEW_ACCOUNT_VIEW("/fxml/create-new-account-view.fxml"),
    DRIVER_VIEW("/fxml/zafor/driver-view.fxml"),
    DRIVER_DASHBOARD_VIEW("/fxml/zafor/driver-dashboard-view.fxml"),
    DRIVER_SIGN_UP_VIEW("/fxml/zafor/driver-sign-up-view.fxml"),
    DRIVER_SIGN_UP_BASIC_INFO_VIEW("/fxml/zafor/driver-sign-up-basic-info-view.fxml"),
    DRIVER_SIGN_UP_VEHICLE_INFO_VIEW("/fxml/zafor/driver-sign-up-vehicle-info-view.fxml"),
    DRIVER_SIGN_UP_VEHICLE_STATUS_VIEW("/fxml/zafor/driver-sign-up-vehicle-status-view.fxml"),
    DRIVER_SIGN_UP_DRIVING_LICENSE_VIEW("/fxml/zafor/driver-sign-up-driving-license-view.fxml"),
    RIDE_VIEW("/fxml/zafor/ride-view.fxml");

    private final String path;

    FXMLFilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}