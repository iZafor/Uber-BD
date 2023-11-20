package bd.uber.zafor.model;

import java.io.Serializable;
import java.time.LocalDate;

public class DrivingLicense implements Serializable {
    private String licenseNumber;
    private LocalDate expirationDate;
    private LicenseClass licenseClass;

    public DrivingLicense(String licenseNumber, LocalDate expirationDate, LicenseClass licenseClass) {
        this.licenseNumber = licenseNumber;
        this.expirationDate = expirationDate;
        this.licenseClass = licenseClass;
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