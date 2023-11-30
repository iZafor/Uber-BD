package bd.uber;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee extends User implements Serializable {
    private Salary salary;
    private Gender gender;
    private Department department;
    private Designation designation;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;

    public Employee(int id) {
        super(id);
    }

    public boolean submitLeaveRequest(int reasonId, LocalDate startDate, LocalDate endDate) {
        return Util.getInstance().getDb().addObject(
                new LeaveRequest(id, reasonId, startDate, endDate),
                BinFilePath.LEAVE_REQUEST
        );
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
}