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
public class Time {
    private  Integer countTime ;
    private  String payment ;
    private  LocalDate chooseRide ;

    public Time(Integer countTime, String payment, LocalDate chooseRide) {
        this.countTime = countTime;
        this.payment = payment;
        this.chooseRide = chooseRide;
    }

    public Integer getCountTime() {
        return countTime;
    }

    public void setCountTime(Integer countTime) {
        this.countTime = countTime;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public LocalDate getChooseRide() {
        return chooseRide;
    }

    public void setChooseRide(LocalDate chooseRide) {
        this.chooseRide = chooseRide;
    }

    @Override
    public String toString() {
        return "Time{" + "countTime=" + countTime + ", payment=" + payment + ", chooseRide=" + chooseRide + '}';
    }
    
}
