/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.uber.redwan;

/**
 *
 * @author Redwan
 */
public class MaintenanceTask {
    private int taskId;
    private boolean isUpcoming;

    // Constructor
    public MaintenanceTask(int taskId, boolean isUpcoming) {
        this.taskId = taskId;
        this.isUpcoming = isUpcoming;
    }

    // Getters and setters
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public boolean isUpcoming() {
        return isUpcoming;
    }

    public void setUpcoming(boolean upcoming) {
        isUpcoming = upcoming;
    }

    // Other methods as needed
}

