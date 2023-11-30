package bd.uber;

import java.io.Serializable;
import java.time.LocalDate;

public class LeaveRequest implements Serializable {
    private final int employeeId;
    private final int reasonId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private boolean isPending;
    private boolean isApproved;

    public LeaveRequest(int employeeId, int reasonId, LocalDate startDate, LocalDate endDate) {
        this.employeeId = employeeId;
        this.reasonId = reasonId;
        this.startDate = startDate;
        this.endDate = endDate;
        isPending = true;
    }

    public void approve() {
        isApproved = true;
        isPending = false;
    }

    public void reject() {
        isApproved = false;
        isPending = false;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getReasonId() {
        return reasonId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}