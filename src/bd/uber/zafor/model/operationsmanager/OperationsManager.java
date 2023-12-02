package bd.uber.zafor.model.operationsmanager;

import bd.uber.*;
import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperationsManager extends Employee implements Serializable {
    private final FareStructureManagement fareStructureManagement;
    private final List<Integer> promotionalCampaignIdList;

    public OperationsManager(int id) {
        super(id);
        setDepartment(Department.OPERATIONS);
        setDesignation(Designation.MANAGER);
        fareStructureManagement = new FareStructureManagement();
        promotionalCampaignIdList = new ArrayList<>();
    }

    public PromotionalCampaign createPromotionalCampaign(String name, String description, LocalDate startDate, LocalDate endDate) {
        PromotionalCampaign promotionalCampaign = new PromotionalCampaign(
                Util.getInstance().getDb().getObjectCount(BinFilePath.PROMOTIONAL_CAMPAIGN) + 1,
                name,
                description,
                startDate,
                endDate);
        promotionalCampaignIdList.add(promotionalCampaign.getCampaignId());
        return Util.getInstance().getDb().addObject(promotionalCampaign, BinFilePath.PROMOTIONAL_CAMPAIGN) ? promotionalCampaign : null;
    }

    public void deletePromotionalCampaign(int campaignId) {
        promotionalCampaignIdList.removeIf(id -> id == campaignId);
    }

    public boolean addDiscountToCampaign(int campaignId, Discount discount) {
        PromotionalCampaign campaign = Util.getInstance().getDb()
                .getObject(BinFilePath.PROMOTIONAL_CAMPAIGN, c -> c.getCampaignId() == campaignId);

        if (campaign == null) {
            return false;
        }

        campaign.getDiscountIdList().add(discount.getDiscountId());
        return Util.getInstance().getDb().updateObjectFile(
                campaign,
                BinFilePath.PROMOTIONAL_CAMPAIGN,
                c -> c.getCampaignId() == campaign.getCampaignId(),
                false
        ) && Util.getInstance().getDb().addObject(
                discount,
                BinFilePath.DISCOUNT
        );
    }

    public boolean removeDiscountFromCampaign(int campaignId, int discountId) {
        PromotionalCampaign campaign = Util.getInstance().getDb()
                .getObject(BinFilePath.PROMOTIONAL_CAMPAIGN, c -> c.getCampaignId() == campaignId);

        if (campaign == null) {
            return false;
        }

        campaign.getDiscountIdList().removeIf(id -> id == discountId);
        return Util.getInstance().getDb().updateObjectFile(
                campaign,
                BinFilePath.PROMOTIONAL_CAMPAIGN,
                c -> c.getCampaignId() == campaignId,
                false
        );
    }

    public float calculateFare(RideRequest rideRequest, String discountCode) {
        float fareWithoutOffers = fareStructureManagement.calculateFare(rideRequest);
        if (discountCode == null || discountCode.isEmpty()) {
            return fareWithoutOffers;
        }

        LocalDate requestDate = rideRequest.getRequestTime().toLocalDate();
        List<PromotionalCampaign> campaignList = Util.getInstance()
                .getDb()
                .getObjectList(
                        BinFilePath.PROMOTIONAL_CAMPAIGN,
                        c -> this.promotionalCampaignIdList.stream().anyMatch(id -> id == c.getCampaignId())
                );
        for (PromotionalCampaign campaign : campaignList) {
            if (requestDate.isAfter(campaign.getStartDate()) && requestDate.isBefore(campaign.getEndDate())) {
                Discount discount = Util.getInstance()
                        .getDb().<Discount>getObjectList(
                                BinFilePath.DISCOUNT,
                                d -> campaign.getDiscountIdList().stream().anyMatch(id -> id == d.getDiscountId())
                        ).stream()
                        .filter(d -> d.getDiscountCode().equals(discountCode))
                        .findFirst().orElseGet(() -> null);
                if (discount != null && discount.isApplicable(rideRequest)) {
                    return Math.max(0, fareWithoutOffers - discount.getDiscountAmount());
                }
                break;
            }
        }
        return fareWithoutOffers;
    }

    public FareStructureManagement getFareStructureManagement() {
        return fareStructureManagement;
    }

    public List<Integer> getPromotionalCampaignIdList() {
        return promotionalCampaignIdList;
    }
}