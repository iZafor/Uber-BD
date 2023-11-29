/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.uber.redwan;

import bd.uber.zafor.model.driver.MaintenanceRequest;

/**
 *
 * @author Redwan
 */
public class MaintenanceTeamMember {
    private String memberId;

    // Constructor
    public MaintenanceTeamMember(String memberId) {
        this.memberId = memberId;
    }

    // Method to handle support request
    public void handleSupportRequest(MaintenanceRequest maintenanceRequest) {
        // Logic to handle the maintenance request
        // This method can process or handle the incoming maintenance request
    }

    // Getter and setter for memberId
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    // Other methods as needed
}
