package bd.uber.zafor.model.driver;

import java.io.Serializable;

public enum MaintenanceStatus implements Serializable {
    GOOD("Good"),
    NEEDS_MAINTENANCE("Needs Maintenance"),
    IN_SERVICE("In Service"),
    OUT_OF_SERVICE("Out of Service");

    private final String maintenanceStatus;

    MaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    @Override
    public String toString() {
        return maintenanceStatus;
    }
}