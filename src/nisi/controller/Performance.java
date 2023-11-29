/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nisi.controller;

/**
 *
 * @author Jannatul Ferdous
 */
public class Performance {
    
    private int id;
    private String performance;

    public Performance(int id, String performance) {
        this.id = id;
        this.performance = performance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    @Override
    public String toString() {
        return "Performance{" + "id=" + id + ", performance=" + performance + '}';
    }
    
}
