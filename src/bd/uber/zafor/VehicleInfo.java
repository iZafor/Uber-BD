package bd.uber.zafor;

import java.io.Serializable;
import java.util.List;

public class VehicleInfo implements Serializable {
    private String model;
    private String registrationNumber;
    private int passengerCapacity;
    private int numberOfEngines;
    private String color;
    private VehicleType vehicleType;
    private VehicleStatus vehicleStatus;
    private MaintenanceStatus maintenanceStatus;
    private List<DamageReport> damageReports;
    private InsurancePolicy insurancePolicy;

    public VehicleInfo(String model, String registrationNumber, int passengerCapacity, int numberOfEngines, String color, VehicleType vehicleType, VehicleStatus vehicleStatus, MaintenanceStatus maintenanceStatus, List<DamageReport> damageReports, InsurancePolicy insurancePolicy) {
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.passengerCapacity = passengerCapacity;
        this.numberOfEngines = numberOfEngines;
        this.color = color;
        this.vehicleType = vehicleType;
        this.vehicleStatus = vehicleStatus;
        this.maintenanceStatus = maintenanceStatus;
        this.damageReports = damageReports;
        this.insurancePolicy = insurancePolicy;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getNumberOfEngines() {
        return numberOfEngines;
    }

    public void setNumberOfEngines(int numberOfEngines) {
        this.numberOfEngines = numberOfEngines;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public MaintenanceStatus getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(MaintenanceStatus maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public List<DamageReport> getDamageReports() {
        return damageReports;
    }

    public void setDamageReports(List<DamageReport> damageReports) {
        this.damageReports = damageReports;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}