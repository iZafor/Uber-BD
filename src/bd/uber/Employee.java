package bd.uber;

import java.io.Serializable;

public class Employee extends User implements Serializable {
    private String jobTitle;
    private Salary salary;

    public Employee(int id) {
        super(id);
    }

    public void submitLeaveRequest(LeaveRequest leaveRequest) {

    }
}