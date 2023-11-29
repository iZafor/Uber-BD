/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.uber.redwan;

import bd.uber.zafor.model.driver.Driver;
import java.util.List;

/**
 *
 * @author Redwan
 */
public class RepairTeamMember {
    private List<RepairRequest> repairRequests;

    // Constructor
    public RepairTeamMember(List<RepairRequest> repairRequests) {
        this.repairRequests = repairRequests;
    }

    // Method to notify the driver about repair completion
    public void notifyDriverAboutRepairCompletion(Driver driver) {
        // Logic to notify the driver about repair completion
        // This method can involve sending a message or updating the driver's status
    }

    // Method to request components
    public List<VehicleComponent> requestComponent() {
        // Logic to request components needed for repair
        // This method can return a list of required VehicleComponent objects
        return null; // Placeholder return value
    }

    // Other getters and setters if needed
    public List<RepairRequest> getRepairRequests() {
        return repairRequests;
    }

    public void setRepairRequests(List<RepairRequest> repairRequests) {
        this.repairRequests = repairRequests;
    }
}
