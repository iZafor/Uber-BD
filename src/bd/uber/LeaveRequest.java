package bd.uber;

import java.io.Serializable;
import java.time.LocalDate;

public class LeaveRequest implements Serializable {
    private final int employeeId;
    private final String reason;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private LeaveRequestStatus status;

    public LeaveRequest(int employeeId, String reason, LocalDate startDate, LocalDate endDate) {
        this.employeeId = employeeId;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveRequestStatus.PENDING;
    }

    public void approve() {
        status = LeaveRequestStatus.APPROVED;
    }

    public void reject() {
        status = LeaveRequestStatus.REJECTED;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getReason() {
        return reason;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LeaveRequestStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveRequestStatus status) {
        this.status = status;
    }
}