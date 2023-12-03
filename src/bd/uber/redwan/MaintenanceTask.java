package bd.uber.redwan;


public class MaintenanceTask {
    private int taskId;
    private boolean isUpcoming;
    private int vehicleInfoId; // Add this property

    public MaintenanceTask(int taskId, boolean isUpcoming, int vehicleInfoId) {
        this.taskId = taskId;
        this.isUpcoming = isUpcoming;
        this.vehicleInfoId = vehicleInfoId;
    }

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

    public int getVehicleInfoId() {
        return vehicleInfoId;
    }

    public void setVehicleInfoId(int vehicleInfoId) {
        this.vehicleInfoId = vehicleInfoId;
    }
}
