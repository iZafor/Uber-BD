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
public class Intercity {
    private  String destination ;
    private  String paymentComboBox ;
    private  LocalDate pickupDateAndTimeDatePicker ;
    private  LocalDate dropOffDateAndTimeDatePicker;

    public Intercity(String destination, String paymentComboBox, LocalDate pickupDateAndTimeDatePicker, LocalDate dropOffDateAndTimeDatePicker) {
        this.destination = destination;
        this.paymentComboBox = paymentComboBox;
        this.pickupDateAndTimeDatePicker = pickupDateAndTimeDatePicker;
        this.dropOffDateAndTimeDatePicker = dropOffDateAndTimeDatePicker;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPaymentComboBox() {
        return paymentComboBox;
    }

    public void setPaymentComboBox(String paymentComboBox) {
        this.paymentComboBox = paymentComboBox;
    }

    public LocalDate getPickupDateAndTimeDatePicker() {
        return pickupDateAndTimeDatePicker;
    }

    public void setPickupDateAndTimeDatePicker(LocalDate pickupDateAndTimeDatePicker) {
        this.pickupDateAndTimeDatePicker = pickupDateAndTimeDatePicker;
    }

    public LocalDate getDropOffDateAndTimeDatePicker() {
        return dropOffDateAndTimeDatePicker;
    }

    public void setDropOffDateAndTimeDatePicker(LocalDate dropOffDateAndTimeDatePicker) {
        this.dropOffDateAndTimeDatePicker = dropOffDateAndTimeDatePicker;
    }

    @Override
    public String toString() {
        return "Intercity{" + "destination=" + destination + ", paymentComboBox=" + paymentComboBox + ", pickupDateAndTimeDatePicker=" + pickupDateAndTimeDatePicker + ", dropOffDateAndTimeDatePicker=" + dropOffDateAndTimeDatePicker + '}';
    }
    
    
}
