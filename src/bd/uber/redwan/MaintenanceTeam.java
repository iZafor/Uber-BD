
package bd.uber.redwan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Redwan
 */

public class MaintenanceTeam implements Serializable {
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
