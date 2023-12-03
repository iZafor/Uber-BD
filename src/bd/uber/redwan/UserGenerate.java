
package bd.uber.redwan;

import bd.uber.BinFilePath;
import bd.uber.DB;

/**
 *
 * @author Redwan
 */
public class UserGenerate {
    public static void main(String [] list){ 
        VehicleMaintenanceManager maintainance_manager = new VehicleMaintenanceManager();
        maintainance_manager.setPassword("123");
        DB db = new DB();
        db.addObject(maintainance_manager, BinFilePath.VEHICLE_MAINTENANCE_Manager);
}
    
}
