package bd.uber.zafor;

import java.io.Serializable;

public enum MaintenanceStatus implements Serializable {
    GOOD,
    NEEDS_MAINTENANCE,
    IN_SERVICE,
    OUT_OF_SERVICE,
}