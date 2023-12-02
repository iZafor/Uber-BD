package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public class Discount implements Serializable {
    private final int discountId;
    private final String discountCode;
    private final float discountAmount;
    private final DiscountCondition discountCondition;

    public Discount(int discountId, String discountCode, float discountAmount, DiscountCondition discountCondition) {
        this.discountId = discountId;
        this.discountCode = discountCode;
        this.discountAmount = discountAmount;
        this.discountCondition = discountCondition;
    }

    public boolean isApplicable(RideRequest rideRequest) {
        return discountCondition.isSatisfied(rideRequest);
    }

    public int getDiscountId() {
        return discountId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public DiscountCondition getDiscountCondition() {
        return discountCondition;
    }
}