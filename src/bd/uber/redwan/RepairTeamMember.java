package bd.uber.redwan;

import java.util.List;

/**
 *
 * @author Redwan
 */
public class RepairTeamMember {
    private List<RepairRequest> repairRequests;

    public RepairTeamMember(List<RepairRequest> repairRequests) {
        this.repairRequests = repairRequests;
    }


    public List<RepairRequest> getRepairRequests() {
        return repairRequests;
    }

    public void setRepairRequests(List<RepairRequest> repairRequests) {
        this.repairRequests = repairRequests;
    }
}
