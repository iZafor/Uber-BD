package bd.uber;

import java.io.Serializable;

public enum Department implements Serializable {
    OPERATIONS("Operations"),
    SUPPORT("Support"),
    VEHICLE_MAINTENANCE("Vehicle Maintenance"),
    ACCOUNTS("Accounts"),
    HRM("Human Resource Management");

    private final String department;

    Department(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return department;
    }
}