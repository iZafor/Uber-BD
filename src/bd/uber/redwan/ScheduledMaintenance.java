/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.uber.redwan;

import java.util.List;

/**
 *
 * @author Redwan
 */
public class ScheduledMaintenance {
    private List<MaintenanceTask> viewUpcomingMaintenanceTasks;

    // Constructor
    public ScheduledMaintenance(List<MaintenanceTask> viewUpcomingMaintenanceTasks) {
        this.viewUpcomingMaintenanceTasks = viewUpcomingMaintenanceTasks;
    }

    // Method to perform maintenance task and update log
    public void performMaintenanceTaskAndUpdateLog(MaintenanceTask maintenanceTask, String logUpdate) {
        // Logic to perform the given maintenance task and update the log
        // This method can handle executing the task and updating maintenance logs
    }

    // Method to confirm completion of scheduled maintenance
    public void confirmCompletionOfScheduledMaintenance(MaintenanceTask maintenanceTask) {
        // Logic to confirm the completion of the scheduled maintenance task
        // This method can mark the task as completed or update its status
    }

    // Other methods as needed
    public List<MaintenanceTask> getViewUpcomingMaintenanceTasks() {
        return viewUpcomingMaintenanceTasks;
    }

    public void setViewUpcomingMaintenanceTasks(List<MaintenanceTask> viewUpcomingMaintenanceTasks) {
        this.viewUpcomingMaintenanceTasks = viewUpcomingMaintenanceTasks;
    }
}
