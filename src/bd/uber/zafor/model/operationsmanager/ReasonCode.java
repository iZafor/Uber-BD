package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public enum ReasonCode implements Serializable {
    DRIVER_LATE("Driver is being late"),
    CHANGE_OF_PLAN("Change of plan"),
    VEHICLE_ISSUES("Vehicle related issues"),
    OTHER("Other");

    private final String reasonCode;

    ReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    @Override
    public String toString() {
        return reasonCode;
    }
}