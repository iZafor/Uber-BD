package bd.uber.zafor.model.driver;

import java.io.Serializable;
import java.time.LocalDate;

public class MaintenanceRequest implements Serializable {
    private final int vehicleInfoId;
    private final LocalDate preferredDate;
    private final MaintenanceTimeSlot timeSlot;

    public MaintenanceRequest(int vehicleInfoId, LocalDate preferredDate, MaintenanceTimeSlot timeSlot) {
        this.vehicleInfoId = vehicleInfoId;
        this.preferredDate = preferredDate;
        this.timeSlot = timeSlot;
    }

    public int getVehicleInfoId() {
        return vehicleInfoId;
    }

    public LocalDate getPreferredDate() {
        return preferredDate;
    }

    public MaintenanceTimeSlot getTimeSlot() {
        return timeSlot;
    }
}