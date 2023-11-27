package bd.uber.zafor.model.driver;

import java.io.Serializable;

public enum DriverStatus implements Serializable {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    ON_BREAK("On Break"),
    SHARING_RIDE("Sharing Ride");

    private final String status;

    DriverStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}