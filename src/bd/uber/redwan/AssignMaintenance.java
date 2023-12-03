package bd.uber.redwan;

import java.util.List;

/**
 *
 * @author Redwan
 */
public class AssignMaintenance {
    private List<MaintenanceTask> viewUpcomingMaintenanceTasks;

    public AssignMaintenance(List<MaintenanceTask> viewUpcomingMaintenanceTasks) {
        this.viewUpcomingMaintenanceTasks = viewUpcomingMaintenanceTasks;
    }

    public void performMaintenanceTaskAndUpdateLog(MaintenanceTask maintenanceTask, String logUpdate) {

    }

    public void confirmCompletionOfScheduledMaintenance(MaintenanceTask maintenanceTask) {

    }

    public List<MaintenanceTask> getViewUpcomingMaintenanceTasks() {
        return viewUpcomingMaintenanceTasks;
    }

    public void setViewUpcomingMaintenanceTasks(List<MaintenanceTask> viewUpcomingMaintenanceTasks) {
        this.viewUpcomingMaintenanceTasks = viewUpcomingMaintenanceTasks;
    }
}