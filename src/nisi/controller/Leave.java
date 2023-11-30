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
public class Leave {
    private  int id;
    private  String status;
    private  String reason;
    private  LocalDate std;
    private  LocalDate end;

    public Leave(int id, String status, String reason, LocalDate std, LocalDate end) {
        this.id = id;
        this.status = status;
        this.reason = reason;
        this.std = std;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getStd() {
        return std;
    }

    public void setStd(LocalDate std) {
        this.std = std;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Leave{" + "id=" + id + ", status=" + status + ", reason=" + reason + ", std=" + std + ", end=" + end + '}';
    }
    
}
