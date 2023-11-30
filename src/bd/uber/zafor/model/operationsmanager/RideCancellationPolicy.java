package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.Ride;

import java.io.Serializable;
import java.util.Map;

public class RideCancellationPolicy implements Serializable {
    private Map<String, CancellationRule> cancellationRuleMap;

    private void addCancellationRule(String name, CancellationRule cancellationRule) {
        cancellationRuleMap.put(name, cancellationRule);
    }

    private boolean removeCancellationRule(String name) {
        return cancellationRuleMap.remove(name) != null;
    }

    private CancellationResponse processCancellation(Ride ride, CancellationReason cancellationReason) {
        return findApplicableRule(ride, cancellationReason).applyRule(ride, cancellationReason);
    }

    private CancellationRule findApplicableRule(Ride ride, CancellationReason cancellationReason) {
        return null;
    }

    public Map<String, CancellationRule> getCancellationRuleMap() {
        return cancellationRuleMap;
    }

    public void setCancellationRuleMap(Map<String, CancellationRule> cancellationRuleMap) {
        this.cancellationRuleMap = cancellationRuleMap;
    }
}