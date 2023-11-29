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
public class Transport {
    private  String destination ;
    private  String weightComboBox ;
    private  String paymentComboBox ;
    private  LocalDate pickupDateAndTimeDatePicker ;
    private  LocalDate dropOffDateAndTimeDatePicker;

    public Transport(String destination, String weightComboBox, String paymentComboBox, LocalDate pickupDateAndTimeDatePicker, LocalDate dropOffDateAndTimeDatePicker) {
        this.destination = destination;
        this.weightComboBox = weightComboBox;
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

    public String getWeightComboBox() {
        return weightComboBox;
    }

    public void setWeightComboBox(String weightComboBox) {
        this.weightComboBox = weightComboBox;
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
        return "Transport{" + "destination=" + destination + ", weightComboBox=" + weightComboBox + ", paymentComboBox=" + paymentComboBox + ", pickupDateAndTimeDatePicker=" + pickupDateAndTimeDatePicker + ", dropOffDateAndTimeDatePicker=" + dropOffDateAndTimeDatePicker + '}';
    }
    
}
