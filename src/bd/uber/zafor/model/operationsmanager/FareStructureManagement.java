package bd.uber.zafor.model.operationsmanager;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FareStructureManagement implements Serializable {
    private final Map<RideType, Integer> pricingTierMap;
    private final List<Integer> fareModificationRuleIdList;

    public FareStructureManagement() {
        this.pricingTierMap = new HashMap<>();
        this.fareModificationRuleIdList = new ArrayList<>();
    }

    public void updatePricingTier(RideType rideType, Integer pricingTierId) {
        pricingTierMap.put(rideType, pricingTierId);
    }

    public boolean addFareModificationRule(FareModificationRule FareModificationRule) {
        return fareModificationRuleIdList.add(FareModificationRule.getModificationRuleId());
    }

    public boolean removeFareModificationRule(int modificationRuleId) {
        return fareModificationRuleIdList.remove(modificationRuleId) != -1;
    }

    public float calculateFare(RideRequest rideRequest) {
        List<FareModificationRule> FareModificationRuleList = Util.getInstance()
                .getDb()
                .getObjectList(
                        BinFilePath.FARE_MODIFICATION_RULE,
                        r -> fareModificationRuleIdList.stream().anyMatch(id -> id == r.getModificationRuleId())
                );

        Integer pricingTierId = pricingTierMap.get(rideRequest.getRideType());
        PricingTier pricingTier = Util.getInstance().getDb().getObject(BinFilePath.PRICING_TIER, p -> p.getPricingTierId() == pricingTierId);

        for (FareModificationRule modificationRule : FareModificationRuleList) {
            if (modificationRule.appliesTo(rideRequest)) {
                float baserFare = modificationRule.getFareModifier().modifyBaseFare(pricingTier.getBaseFare()) * rideRequest.getRideDistance();
                float distanceRate = modificationRule.getFareModifier().modifyDistanceRate(pricingTier.getDistanceRate());
                float timeRate = modificationRule.getFareModifier().modifyTimeRate(pricingTier.getTimeRate());
                return baserFare + baserFare * (distanceRate / 100f) + baserFare * (timeRate / 100f);
            }
        }
        float baseFare = pricingTier.getBaseFare();
        return baseFare + baseFare * (pricingTier.getDistanceRate() / 100f) + baseFare * (pricingTier.getTimeRate() / 100f);
    }

    public Map<RideType, Integer> getPricingTierMap() {
        return pricingTierMap;
    }

    public List<Integer> getFareModificationRuleIdList() {
        return fareModificationRuleIdList;
    }
}