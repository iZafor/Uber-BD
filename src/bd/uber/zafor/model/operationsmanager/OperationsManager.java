package bd.uber.zafor.model.operationsmanager;

import bd.uber.Employee;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class OperationsManager extends Employee implements Serializable {
    private FareStructureManagement fareStructureManagement;
    private List<PromotionalCampaign> promotionalCampaignList;
    private RideCancellationPolicy rideCancellationPolicy;
    private LostItemClaimProcess lostItemClaimProcess;

    public OperationsManager(int id) {
        super(id);
    }

    public void createPromotionalCampaign(String name, String description, LocalDate startDate, LocalDate endDate) {

    }

    public void deletePromotionalCampaign(String name) {

    }

    public void addDiscountToCampaign(String campaignName, Discount discount) {

    }

    public void removeDiscountFromCampaign(String campaignName, String discountCode) {

    }

    public FareStructureManagement getFareStructureManagement() {
        return fareStructureManagement;
    }

    public void setFareStructureManagement(FareStructureManagement fareStructureManagement) {
        this.fareStructureManagement = fareStructureManagement;
    }

    public List<PromotionalCampaign> getPromotionalCampaignList() {
        return promotionalCampaignList;
    }

    public void setPromotionalCampaignList(List<PromotionalCampaign> promotionalCampaignList) {
        this.promotionalCampaignList = promotionalCampaignList;
    }

    public RideCancellationPolicy getRideCancellationPolicy() {
        return rideCancellationPolicy;
    }

    public void setRideCancellationPolicy(RideCancellationPolicy rideCancellationPolicy) {
        this.rideCancellationPolicy = rideCancellationPolicy;
    }

    public LostItemClaimProcess getLostItemClaimProcess() {
        return lostItemClaimProcess;
    }

    public void setLostItemClaimProcess(LostItemClaimProcess lostItemClaimProcess) {
        this.lostItemClaimProcess = lostItemClaimProcess;
    }
}