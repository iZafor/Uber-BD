package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.Ride;

import java.io.Serializable;

public class CancellationRule implements Serializable {
    private String name;
    private float passengerPenalty;
    private float driverCompensation;

    public boolean appliesTo(Ride ride, CancellationReason cancellationReason) {
        return false;
    }

    public CancellationResponse applyRule(Ride ride, CancellationReason cancellationReason) {
        return new CancellationResponse().getDefaultResponse();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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