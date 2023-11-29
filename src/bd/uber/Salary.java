package bd.uber;

import java.io.Serializable;

public class Salary implements Serializable {
    private float baseSalary;
    private float bonus;
    private int bankAccountID;

    public Salary(float baseSalary, int bankAccountID) {
        this.baseSalary = baseSalary;
        this.bankAccountID = bankAccountID;
    }

    public float calculateTotalSalary() {
        return baseSalary + (baseSalary * bonus);
    }

    public float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(float baseSalary) {
        this.baseSalary = baseSalary;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public int getBankAccount() {
        return bankAccountID;
    }

    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }
}