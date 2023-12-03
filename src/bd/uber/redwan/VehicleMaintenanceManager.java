
package bd.uber.redwan;

import bd.uber.Employee;
import bd.uber.zafor.model.driver.MaintenanceRequest;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Redwan
 */

public class VehicleMaintenanceManager extends Employee implements Serializable {
    private List<MaintenanceRequest> maintenanceRequests;
    private List<RepairRequest> repairRequests;
    private MaintenanceTeam maintenanceTeam;
    
    
    public VehicleMaintenanceManager() { 
        super (1); ///user id
        this.maintenanceRequests = new ArrayList<>();
        this.repairRequests = new ArrayList<>();
        this.maintenanceTeam = new MaintenanceTeam();
   }



}
