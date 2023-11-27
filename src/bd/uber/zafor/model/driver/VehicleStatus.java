package bd.uber.zafor.model.driver;

import java.io.Serializable;
import java.time.LocalDate;

public final class VehicleStatus implements Serializable {
    private int odometerReading;
    private FuelLevel fuelLevel;
    private float tirePressure;
    private ExteriorDamage exteriorDamage;
    private String interiorCleanliness;
    private MechanicalIssue mechanicalIssues;
    private EngineOilLevel engineOilLevel;
    private LocalDate lastUpdated;

    public VehicleStatus(int odometerReading, FuelLevel fuelLevel, float tirePressure, ExteriorDamage exteriorDamage, String interiorCleanliness, MechanicalIssue mechanicalIssues, EngineOilLevel engineOilLevel, LocalDate lastUpdated) {
        this.odometerReading = odometerReading;
        this.fuelLevel = fuelLevel;
        this.tirePressure = tirePressure;
        this.exteriorDamage = exteriorDamage;
        this.interiorCleanliness = interiorCleanliness;
        this.mechanicalIssues = mechanicalIssues;
        this.engineOilLevel = engineOilLevel;
        this.lastUpdated = lastUpdated;
    }

    public int getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(int odometerReading) {
        this.odometerReading = odometerReading;
    }

    public FuelLevel getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(FuelLevel fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public float getTirePressure() {
        return tirePressure;
    }

    public void setTirePressure(float tirePressure) {
        this.tirePressure = tirePressure;
    }

    public ExteriorDamage getExteriorDamage() {
        return exteriorDamage;
    }

    public void setExteriorDamage(ExteriorDamage exteriorDamage) {
        this.exteriorDamage = exteriorDamage;
    }

    public String getInteriorCleanliness() {
        return interiorCleanliness;
    }

    public void setInteriorCleanliness(String interiorCleanliness) {
        this.interiorCleanliness = interiorCleanliness;
    }

    public MechanicalIssue getMechanicalIssues() {
        return mechanicalIssues;
    }

    public void setMechanicalIssues(MechanicalIssue mechanicalIssues) {
        this.mechanicalIssues = mechanicalIssues;
    }

    public EngineOilLevel getEngineOilLevel() {
        return engineOilLevel;
    }

    public void setEngineOilLevel(EngineOilLevel engineOilLevel) {
        this.engineOilLevel = engineOilLevel;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}