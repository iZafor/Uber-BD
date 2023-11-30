package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public class LostItemClaim implements Serializable {
    private int passengerId;
    private int assignedDriverId;
    private String itemName;
    private String description;
    private ClaimStatus claimStatus;
    private String resolution;

    public void assignToDriver() {

    }

    public void resolveClaim() {

    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getAssignedDriverId() {
        return assignedDriverId;
    }

    public void setAssignedDriverId(int assignedDriverId) {
        this.assignedDriverId = assignedDriverId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}