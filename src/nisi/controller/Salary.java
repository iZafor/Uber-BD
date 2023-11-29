/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nisi.controller;

/**
 *
 * @author Jannatul Ferdous
 */
public class Salary {
    private  int id;
    private  float salary;
    private  String month ;

    public Salary(int id, float salary, String month) {
        this.id = id;
        this.salary = salary;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Salary{" + "id=" + id + ", salary=" + salary + ", month=" + month + '}';
    }
    
    
}
