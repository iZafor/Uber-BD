package bd.uber.zafor.model.driver;

import java.io.Serializable;

public enum LicenseClass implements Serializable {
    LEARNER_LICENSE("Learner License"),
    PROFESSIONAL_LICENSE("Professional License"),
    NO_PROFESSIONAL_LICENSE("Non-Professional License"),
    PUBLIC_SERVICE_VEHICLE_LICENSE("Public Service Vehicle License"),
    INSTRUCTOR_LICENSE("Instructor License");

    private final String licenseClass;

    LicenseClass(String licenseClass) {
        this.licenseClass = licenseClass;
    }

    @Override
    public String toString() {
        return licenseClass;
    }
}