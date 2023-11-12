package bd.uber.zafor;

import java.io.Serializable;

public enum DriverStatus implements Serializable {
    ACTIVE,
    INACTIVE,
    ON_BREAK,
    SHARING_RIDE
}