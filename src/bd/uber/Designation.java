package bd.uber;

import java.io.Serializable;

public enum Designation implements Serializable {
    EXECUTIVE("Executive"),
    JUNIOR_OFFICER("Junior Officer"),
    SENIOR_OFFICER("Senior Officer"),
    MANAGER("Manager"),
    ACCOUNTANT("Accountant"),
    DIRECTOR("Director"),
    ENGINEER("Engineer");

    private final String designation;

    Designation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return designation;
    }
}