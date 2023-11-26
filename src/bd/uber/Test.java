package bd.uber;

import bd.uber.zafor.model.PaymentMethod;
import bd.uber.zafor.model.Ride;
import bd.uber.zafor.model.RideFeedback;
import bd.uber.zafor.model.RideRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    private static final DB db = new DB();
    private static final List<RideRequest> requestList = new ArrayList<>();
    private static final List<Ride> rideList = new ArrayList<>();
    private static final Random random = new Random(System.nanoTime());

    public static void main(String[] args) {
//        generateRideRequests();
//        storeRideRequests();
        generateRides();
        storeRides();
    }

    private static void generateRideRequests() {
        List<Location> locationList = db.getObjectList(BinFilePath.LOCATION);
        int lSize = locationList.size();

        for (int i = 0; i < 20; i++) {
            int pIdx = random.nextInt(lSize);
            int dIdx = random.nextInt(lSize);
            dIdx = pIdx == dIdx ? dIdx == 0 ? dIdx + random.nextInt(3) : dIdx + 1 == lSize ? dIdx - random.nextInt(3) : dIdx - 1 : dIdx;
            requestList.add(
                    new RideRequest(
                            random.nextInt(20),
                            random.nextInt(5) + 1,
                            locationList.get(pIdx),
                            locationList.get(dIdx),
                            ((pIdx & 1) == 1) ? PaymentMethod.CASH : PaymentMethod.DIGITAL,
                            random.nextInt(1000) + 100
                    )
            );
        }
    }

    private static void storeRideRequests() {
        if (db.addObjects(requestList, BinFilePath.RIDE_REQUEST, true)) {
            System.out.println("Object added successfully...");
        }
    }

    private static void generateRides() {
        List<Location> locationList = db.getObjectList(BinFilePath.LOCATION);
        int lSize = locationList.size();
        for (int i = 0; i < 500; i++) {
            int pIdx = random.nextInt(lSize);
            int dIdx = random.nextInt(lSize);
            dIdx = pIdx == dIdx ? dIdx == 0 ? dIdx + random.nextInt(3) : dIdx + 1 == lSize ? dIdx - random.nextInt(3) : dIdx - 1 : dIdx;
            Ride ride = new Ride(
                    random.nextInt(5) + 1,
                    random.nextInt(5) + 1,
                    locationList.get(pIdx),
                    locationList.get(dIdx),
                    ((pIdx & 1) == 1) ? PaymentMethod.CASH : PaymentMethod.DIGITAL,
                    random.nextInt(1000)
            );
            ride.setPassengerFeedback(new RideFeedback(random.nextInt(5) + 1, ""));
            ride.setCancelled(random.nextInt(100) % 5 == 0);

            if (!ride.isCancelled()) {
                ride.setCompleted(true);
                ride.setPickupTime(LocalDateTime.now().minusDays(random.nextInt(365)).minusHours(random.nextInt(24)));
                ride.setDropOffTime(ride.getPickupTime().plusHours(random.nextInt(2)).plusSeconds(random.nextInt(60)));
            }

            rideList.add(ride);
        }
    }

    private static void storeRides() {
        db.addObjects(rideList, BinFilePath.RIDE, true);
    }
}