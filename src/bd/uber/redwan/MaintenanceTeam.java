
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

    public MaintenanceTeam() {
        this.maintenanceTeamMembers = new ArrayList<>();
    }

    public void addMaintenanceTeamMember(MaintenanceTeamMember teamMember) {
        maintenanceTeamMembers.add(teamMember);
    }

    public List<MaintenanceTeamMember> getMaintenanceTeamMembers() {
        return maintenanceTeamMembers;
    }

}
