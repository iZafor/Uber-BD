package bd.uber.zafor.model.operationsmanager;

import bd.uber.zafor.model.driver.RideRequest;

import java.io.Serializable;

public interface DiscountCondition extends Serializable {
    boolean isSatisfied(RideRequest rideRequest);
}