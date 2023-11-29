package bd.uber;

public enum FXMLFilePath {
    // SignIn/Signup
    GET_STARTED_VIEW("/fxml/get-started-view.fxml"),
    CREATE_NEW_ACCOUNT_VIEW("/fxml/create-new-account-view.fxml"),

    // Driver Profile
    DRIVER_VIEW("/fxml/zafor/driver-view.fxml"),
    DRIVER_DASHBOARD_VIEW("/fxml/zafor/driver-dashboard-view.fxml"),
    DRIVER_PROFILE_SETTINGS_VIEW("/fxml/zafor/driver-profile-settings-view.fxml"),
    DRIVER_RIDES_VIEW("/fxml/zafor/driver-rides-view.fxml"),
    DRIVER_REPAIR_REQUEST_VIEW("/fxml/zafor/driver-repair-request-view.fxml"),
    DETAILED_RIDE_VIEW("/fxml/zafor/detailed-ride-view.fxml"),

    // Driver Signup
    DRIVER_SIGN_UP_VIEW("/fxml/zafor/driver-sign-up-view.fxml"),
    DRIVER_SIGN_UP_BASIC_INFO_VIEW("/fxml/zafor/driver-sign-up-basic-info-view.fxml"),
    DRIVER_SIGN_UP_VEHICLE_INFO_VIEW("/fxml/zafor/driver-sign-up-vehicle-info-view.fxml"),
    DRIVER_SIGN_UP_VEHICLE_STATUS_VIEW("/fxml/zafor/driver-sign-up-vehicle-status-view.fxml"),
    DRIVER_SIGN_UP_DRIVING_LICENSE_VIEW("/fxml/zafor/driver-sign-up-driving-license-view.fxml"),
    DRIVER_SIGN_UP_IDENTIFICATION_INFO_VIEW("/fxml/zafor/driver-sign-up-identification-info-view.fxml"),
    
    // Others
    RIDE_VIEW("/fxml/zafor/ride-view.fxml"),
    //HRM Signup
    HRM_Dashboard("/fxml/nisi/HRM/HRM_Dashboard.fxml"),
    Employee_Performance("/fxml/nisi/HRM/EmployeePerformance.fxml"),
    Employee_Reports("/fxml/nisi/HRM/EmployeeReports.fxml"),
    Manage_Meeting("/fxml/nisi/HRM/ManageMeeting.fxml"),
    Monthly_Supling_Salary("/fxml/nisi/HRM/MonthlySuplingSalary.fxml"),
    Notice_To_Employee("/fxml/nisi/HRM/NoticeToEmployee.fxml"),
    Stor_Employee_Details("/fxml/nisi/HRM/StorEmployeeDetails.fxml"),
    Working_Schedule("/fxml/nisi/HRM/WorkingSchedule.fxml"),
    Add_Update_Employee_Details("/fxml/nisi/HRM/AddUpdateEmployeeDetails.fxml");
    //Passenger Signup
    

    private final String path;

    FXMLFilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}