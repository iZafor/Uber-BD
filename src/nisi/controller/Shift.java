/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nisi.controller;

/**
 *
 * @author Jannatul Ferdous
 */
public class Shift {
    private  int id;
    private String shift;
    private String shiftPeriod;

    public Shift(int id, String shift, String shiftPeriod) {
        this.id = id;
        this.shift = shift;
        this.shiftPeriod = shiftPeriod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getShiftPeriod() {
        return shiftPeriod;
    }

    public void setShiftPeriod(String shiftPeriod) {
        this.shiftPeriod = shiftPeriod;
    }

    @Override
    public String toString() {
        return "Shift{" + "id=" + id + ", shift=" + shift + ", shiftPeriod=" + shiftPeriod + '}';
    }
    
}
