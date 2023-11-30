package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;
import java.util.List;

public class LostItemClaimProcess implements Serializable {
    private List<LostItemClaim> lostItemClaimList;

    public void submitLostItemClaim(int passengerId, String itemName, String description, int driverId) {

    }

    public void assignClaimToDriver() {

    }

    public void resolveClaim() {

    }

    public List<LostItemClaim> getLostItemClaimList() {
        return lostItemClaimList;
    }

    public void setLostItemClaimList(List<LostItemClaim> lostItemClaimList) {
        this.lostItemClaimList = lostItemClaimList;
    }
}