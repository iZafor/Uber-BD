package bd.uber.redwan;

import bd.uber.zafor.model.driver.MaintenanceRequest;

/**
 *
 * @author Redwan
 */
public class MaintenanceTeamMember {

    private String memberId;
    private String memberName;

    public MaintenanceTeamMember(String memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void handleSupportRequest(MaintenanceRequest maintenanceRequest) {

    }

    //////test
    @Override
    public String toString() {
        return "Member ID: " + this.memberId
                + ", Member Name: " + this.memberName;
    }
}
