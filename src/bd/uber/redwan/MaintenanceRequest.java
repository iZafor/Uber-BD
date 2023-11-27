/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.uber.redwan;

import bd.uber.zafor.model.DamageReport;
import bd.uber.zafor.model.VehicleOwner;
import bd.uber.zafor.model.VehicleStatus;

/**
 *
 * @author Redwan
 */
public class MaintenanceRequest {
    private String model;
    private VehicleOwner owner;
    private VehicleStatus vehicleStatus;
    private MaintainenceStatus maintenanceStatus;
    private DamageReport damageReport;

    // Constructor
    public MaintenanceRequest(String model, VehicleOwner owner, VehicleStatus vehicleStatus,
                              MaintainenceStatus maintenanceStatus, DamageReport damageReport) {
        this.model = model;
        this.owner = owner;
        this.vehicleStatus = vehicleStatus;
        this.maintenanceStatus = maintenanceStatus;
        this.damageReport = damageReport;
    }

    // Getters and setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public VehicleOwner getOwner() {
        return owner;
    }

    public void setOwner(VehicleOwner owner) {
        this.owner = owner;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public MaintainenceStatus getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(MaintainenceStatus maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public DamageReport getDamageReport() {
        return damageReport;
    }

    public void setDamageReport(DamageReport damageReport) {
        this.damageReport = damageReport;
    }
    
    // Other methods as needed
}
