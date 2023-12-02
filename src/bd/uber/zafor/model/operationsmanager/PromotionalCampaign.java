package bd.uber.zafor.model.operationsmanager;

import javafx.collections.FXCollections;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PromotionalCampaign implements Serializable {
    private final int campaignId;
    private final String campaignName;
    private final String description;
    private final LocalDate startDate;
    private LocalDate endDate;
    private final List<Integer> discountIdList;

    public PromotionalCampaign(int campaignId, String campaignName, String description, LocalDate startDate, LocalDate endDate) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountIdList = new ArrayList<>();
    }

    public int getCampaignId() {
        return campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getDiscountIdList() {
        return discountIdList;
    }
}