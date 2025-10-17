package bd.uber.zafor.model.driver;

import bd.uber.BinFilePath;
import bd.uber.ContactDetails;
import bd.uber.Util;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SignupDriver {
    private final Driver driver;
    private final ContactDetails signupDriverContactDetails;
    private final VehicleInfo signupDriverVehicleInfo;
    private final VehicleStatus signUpDriverVehicleStatus;
    private final InsurancePolicy signupDriverInsurancePolicy;
    private final DrivingLicense signupDriverDrivingLicense;
    private final ObjectProperty<SignupForm> signupFormPageProperty;

    public SignupDriver() {
        this.driver = new Driver(Util.getInstance().getDb().getObjectCount(BinFilePath.DRIVER) + 1);
        this.signupDriverContactDetails = new ContactDetails(Util.getInstance().getDb().getObjectCount(BinFilePath.CONTACT_DETAILS) + 1);
        this.signupDriverVehicleInfo = new VehicleInfo(Util.getInstance().getDb().getObjectCount(BinFilePath.VEHICLE_INFO) + 1);
        this.signUpDriverVehicleStatus = new VehicleStatus(Util.getInstance().getDb().getObjectCount(BinFilePath.VEHICLE_STATUS) + 1);
        this.signupDriverInsurancePolicy = new InsurancePolicy(Util.getInstance().getDb().getObjectCount(BinFilePath.INSURANCE_POLICY) + 1);
        this.signupDriverDrivingLicense = new DrivingLicense(Util.getInstance().getDb().getObjectCount(BinFilePath.DRIVING_LICENSE) + 1);
        this.signupFormPageProperty = new SimpleObjectProperty<>(SignupForm.BASIC_INFO);
    }

    public Driver getDriver() {
        return driver;
    }

    public ContactDetails getSignupDriverContactDetails() {
        return signupDriverContactDetails;
    }

    public VehicleInfo getSignupDriverVehicleInfo() {
        return signupDriverVehicleInfo;
    }

    public VehicleStatus getSignUpDriverVehicleStatus() {
        return signUpDriverVehicleStatus;
    }

    public InsurancePolicy getSignupDriverInsurancePolicy() {
        return signupDriverInsurancePolicy;
    }

    public DrivingLicense getSignupDriverDrivingLicense() {
        return signupDriverDrivingLicense;
    }

    public ObjectProperty<SignupForm> getSignupFormPageProperty() {
        return signupFormPageProperty;
    }

    public void setSignupFormPage(SignupForm form) {
        signupFormPageProperty.setValue(form);
    }
}