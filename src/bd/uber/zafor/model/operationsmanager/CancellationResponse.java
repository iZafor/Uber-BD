package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public class CancellationResponse implements Serializable {
    private float passengerPenalty;
    private float driverCompensation;

    public CancellationResponse getDefaultResponse() {
        return null;
    }

    public float getPassengerPenalty() {
        return passengerPenalty;
    }

    public void setPassengerPenalty(float passengerPenalty) {
        this.passengerPenalty = passengerPenalty;
    }

    public float getDriverCompensation() {
        return driverCompensation;
    }

    public void setDriverCompensation(float driverCompensation) {
        this.driverCompensation = driverCompensation;
    }
}