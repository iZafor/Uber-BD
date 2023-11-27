/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.uber.redwan;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Redwan
 */

public class MaintenanceTeam {
    private List<MaintenanceTeamMember> maintenanceTeamMembers;

    // Constructor
    public MaintenanceTeam() {
        this.maintenanceTeamMembers = new ArrayList<>();
    }

    // Method to add a maintenance team member
    public void addMaintenanceTeamMember(MaintenanceTeamMember teamMember) {
        maintenanceTeamMembers.add(teamMember);
    }

    // Getter for maintenance team members
    public List<MaintenanceTeamMember> getMaintenanceTeamMembers() {
        return maintenanceTeamMembers;
    }

    // Other methods as needed
}
