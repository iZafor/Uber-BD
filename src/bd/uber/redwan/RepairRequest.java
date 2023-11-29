/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.uber.redwan;



import bd.uber.zafor.model.driver.DamageReport;

import java.util.List;

/**
 *
 * @author Redwan
 */
public class RepairRequest {
    private String requestId;
    private int driverId;
    private List<DamageReport> damageReports;

    // Constructor
    public RepairRequest(String requestId, int driverId, List<DamageReport> damageReports) {
        this.requestId = requestId;
        this.driverId = driverId;
        this.damageReports = damageReports;
    }

    // Getters and setters
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public List<DamageReport> getDamageReports() {
        return damageReports;
    }

    public void setDamageReports(List<DamageReport> damageReports) {
        this.damageReports = damageReports;
    }
    
    // Other methods as needed
}
