package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FareStructureManagement implements Serializable {
    private Map<RideType, PricingTear> pricingTearMap;
    private List<PriceModificationRule> priceModificationRuleList;

    public boolean addPricingTear(RideType rideType, PricingTear pricingTear) {
        return false;
    }

    public boolean removePricingTear(RideType rideType) {
        return pricingTearMap.remove(rideType) != null;
    }

    public boolean addPriceModificationRule(PriceModificationRule priceModificationRule) {
        return priceModificationRuleList.add(priceModificationRule);
    }

    public boolean removePriceModificationRule(PriceModificationRule priceModificationRule) {
        return priceModificationRuleList.remove(priceModificationRule);
    }

    public float calculateFare(RideType rideType, RideRequest rideRequest) {
        PricingTear pricingTear = pricingTearMap.get(rideType);
        for (PriceModificationRule modificationRule : priceModificationRuleList) {
            if (modificationRule.appliesTo(rideRequest)) {
                float baserFare = modificationRule.getFareModifier().modifyBaseFare(pricingTear.getBaseFare());
                float distanceRate = modificationRule.getFareModifier().modifyDistanceRate(pricingTear.getDistanceRate());
                float timeRate = modificationRule.getFareModifier().modifyTimeRate(pricingTear.getTimeRate());

                return baserFare + baserFare * distanceRate + baserFare * timeRate;
            }
        }
        return 0f;
    }
}