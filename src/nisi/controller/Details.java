/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nisi.controller;

import java.time.LocalDate;

/**
 *
 * @author Jannatul Ferdous
 */
public class Details {
    private  int id;
    private  String name;
    private  float salary;
    private  String gender;
    private  String department;
    private  String designation;
    private  LocalDate dob;
    private  LocalDate doj;

    public Details(int id, String name, float salary, String gender, String department, String designation, LocalDate dob, LocalDate doj) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.department = department;
        this.designation = designation;
        this.dob = dob;
        this.doj = doj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    @Override
    public String toString() {
        return "Details{" + "id=" + id + ", name=" + name + ", salary=" + salary + ", gender=" + gender + ", department=" + department + ", designation=" + designation + ", dob=" + dob + ", doj=" + doj + '}';
    }
    
}
