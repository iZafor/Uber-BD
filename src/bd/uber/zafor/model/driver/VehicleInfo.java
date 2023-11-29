package bd.uber.zafor.model.driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class VehicleInfo implements Serializable {
    private final int vehicleInfoId;
    private String model;
    private String registrationNumber;
    private int passengerCapacity;
    private int numberOfEngines;
    private String color;
    private VehicleType vehicleType;
    private int vehicleStatusId;
    private MaintenanceStatus maintenanceStatus;
    private List<Integer> damageReportIds;
    private int insurancePolicyId;

    public VehicleInfo(int vehicleInfoId) {
        this.vehicleInfoId = vehicleInfoId;
        damageReportIds = new ArrayList<>();
    }

    public int getVehicleInfoId() {
        return vehicleInfoId;
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

    public int getVehicleStatusId() {
        return vehicleStatusId;
    }

    public void setVehicleStatusId(int vehicleStatusId) {
        this.vehicleStatusId = vehicleStatusId;
    }

    public MaintenanceStatus getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(MaintenanceStatus maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public List<Integer> getDamageReportIds() {
        return damageReportIds;
    }

    public void setDamageReportIds(List<Integer> damageReportIds) {
        this.damageReportIds = damageReportIds;
    }

    public int getInsurancePolicyId() {
        return insurancePolicyId;
    }

    public void setInsurancePolicyId(int insurancePolicyId) {
        this.insurancePolicyId = insurancePolicyId;
    }
}