package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public class Discount implements Serializable {
    private String discountCode;
    private float discountAmount;
    private DiscountCondition discountCondition;

    public boolean isApplicable(RideRequest rideRequest) {
        return discountCondition.isSatisfied(rideRequest);
    }
}