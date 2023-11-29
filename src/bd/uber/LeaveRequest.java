package bd.uber;

import java.io.Serializable;
import java.time.LocalDate;

public class LeaveRequest implements Serializable {
    private int employeeId;
    private Reason reason;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isPending;
    private boolean isApproved;

    public LeaveRequest() {
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

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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