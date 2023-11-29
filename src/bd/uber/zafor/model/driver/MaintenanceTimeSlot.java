package bd.uber.zafor.model.driver;

import java.io.Serializable;

public enum MaintenanceTimeSlot implements Serializable {
    SLOT_1("10AM-12PM"),
    SLOT_2("1:30PM-3:30PM"),
    SLOT_3("4:30PM-5:30PM");

    private final String timeSlot;

    MaintenanceTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return timeSlot;
    }
}