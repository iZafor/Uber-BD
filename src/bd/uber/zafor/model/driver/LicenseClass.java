package bd.uber.zafor.model.driver;

import java.io.Serializable;

public enum LicenseClass implements Serializable {
    LICENSE_FOR_MOTORCYCLE("License for MotorCycle"),
    LICENSE_FOR_AUTO_TEMPO_MOTOR("License for Auto/Tempo"),
    LICENSE_FOR_MICROBUS_JEEP("License for MicroBus/Jeep"),
    LICENSE_FOR_MEDIUM_RANGE_VEHICLES("License for Medium Range Vehicles"),
    LICENSE_FOR_HEAVY_RANGE_VEHICLES("License for Heavy Range Vehicles"),
    LICENSE_FOR_TRACTOR_OR_SPECIAL_CLASSES_VEHICLE("License for Tractor or Special Classes Vehicles");

    private final String licenseClass;

    LicenseClass(String licenseClass) {
        this.licenseClass = licenseClass;
    }

    @Override
    public String toString() {
        return licenseClass;
    }
}