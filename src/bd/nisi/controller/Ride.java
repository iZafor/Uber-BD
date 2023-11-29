/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.nisi.controller;

import java.time.LocalDate;

/**
 *
 * @author Jannatul Ferdous
 */
public class Ride {
    private  String pickUpLocation  ;
    private  String dropOffLocation  ;
    private  String search ;
    private  LocalDate pickUpNow  ;

    public Ride(String pickUpLocation, String dropOffLocation, String search, LocalDate pickUpNow) {
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.search = search;
        this.pickUpNow = pickUpNow;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public LocalDate getPickUpNow() {
        return pickUpNow;
    }

    public void setPickUpNow(LocalDate pickUpNow) {
        this.pickUpNow = pickUpNow;
    }

    @Override
    public String toString() {
        return "Ride{" + "pickUpLocation=" + pickUpLocation + ", dropOffLocation=" + dropOffLocation + ", search=" + search + ", pickUpNow=" + pickUpNow + '}';
    }
    
}
