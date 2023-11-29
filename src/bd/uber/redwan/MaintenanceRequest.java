/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.uber.redwan;


import bd.uber.zafor.model.driver.DamageReport;
import bd.uber.zafor.model.driver.VehicleOwner;
import bd.uber.zafor.model.driver.VehicleStatus;

/**
 *
 * @author Redwan
 */
public class MaintenanceRequest {
    private String model;
    
    private VehicleStatus vehicleStatus;
    private MaintainenceStatus maintenanceStatus;
    private DamageReport damageReport;

    // Constructor
    public MaintenanceRequest(String model, VehicleStatus vehicleStatus,
                              MaintainenceStatus maintenanceStatus, DamageReport damageReport) {
        this.model = model;
        
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
