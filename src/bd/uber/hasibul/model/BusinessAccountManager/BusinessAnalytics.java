package bd.uber.hasibul.model.BusinessAccountManager;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.driver.Ride;
import bd.uber.zafor.model.driver.RideFeedback;

import java.util.ArrayList;
import java.util.List;

public class BusinessAnalytics {
    private final List<Ride> rideList = Util.getInstance().getDb().getObjectList(BinFilePath.RIDE);

    public float calculateTotalRevenue() {
        float total = 0;
        for (Ride ride : rideList) {
            total += ride.getFare();
        }
        return total;
    }

    public float calculateAverageRating() {
        float result = 0;
        int count = 0;
        for (Ride ride : rideList) {
            if (ride.hasCompleted()) {
                RideFeedback rideFeedback = Util.getInstance().getDb().getObject(BinFilePath.PASSENGER_FEEDBACK, rideFeedback1 -> rideFeedback1.getRideFeedBackId() == rideFeedback1.getRideFeedBackId());
                result += rideFeedback.getRating();
                count++;
            }
        }
        return count == 0 ? 0 : result / count;
    }

    public int countCompletedRides() {
        int count = 0;
        for (Ride ride : rideList) {
            if (ride.hasCompleted()) {
                count++;
            }
        }
        return count;
    }

    public List<Ride> getRides(float distance) {
        List<Ride> rides = new ArrayList<>();
        for (Ride ride : rideList) {
            if (ride.getRideDistance() >= distance) {
                rides.add(ride);
            }
        }
        return rides;
    }
}