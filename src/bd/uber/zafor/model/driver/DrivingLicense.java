package bd.uber.zafor.model.driver;

import java.io.Serializable;
import java.time.LocalDate;

public class DrivingLicense implements Serializable {
    private final int drivingLicenseId;
    private String licenseNumber;
    private LocalDate expirationDate;
    private LicenseClass licenseClass;

    public DrivingLicense(int drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

    public int getDrivingLicenseId() {
        return drivingLicenseId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LicenseClass getLicenseClass() {
        return licenseClass;
    }

    public void setLicenseClass(LicenseClass licenseClass) {
        this.licenseClass = licenseClass;
    }
}