package bd.uber;

import bd.uber.zafor.model.driver.PaymentMethod;
import bd.uber.zafor.model.driver.Ride;
import bd.uber.zafor.model.operationsmanager.OperationsManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    private static final DB db = new DB();
    private static final Random random = new Random(System.nanoTime());
    private static final OperationsManager manager = db.<OperationsManager>getObjectList(BinFilePath.OPERATIONS_MANAGER).get(0);
    private static final List<Location> locationList = db.getObjectList(BinFilePath.LOCATION);

    public static void main(String[] args) {
//        manager.setProfileImage("");
//        generateRides();
//        OperationsManager operationsManager = new OperationsManager(1);
//        operationsManager.setName("Zafor");
//        operationsManager.setPassword("abc");
//        operationsManager.setDateOfBirth(LocalDate.of(2002, 1, 7));
//        operationsManager.setDateOfJoining(LocalDate.now().minusDays(random.nextInt(365)));
//        operationsManager.setGender(Gender.MALE);
//        db.updateObjectFile(
//                manager,
//                BinFilePath.OPERATIONS_MANAGER,
//                m -> m.getId() == manager.getId(),
//                false
//        );
//        db.addObject(operationsManager, BinFilePath.OPERATIONS_MANAGER);
//        List<Location> locationList = db.getObjectList(BinFilePath.LOCATION);
//        List<RideRequest> requestList = new ArrayList<>();
//        for (int i = 0; i < 120; i++) {
//            int pIdx = random.nextInt(7) + 1;
//            int dIdx = random.nextInt(7) + 1;
//
//            dIdx = dIdx == pIdx ? dIdx == 7 ? dIdx - random.nextInt(3) + 1 : dIdx + 1 : dIdx;
//
//            int fDidx = dIdx;
//
//            System.out.println(pIdx + " " + fDidx);
//
//            RideRequest request = new RideRequest(
//                    random.nextInt(10) + 1,
//                    random.nextInt(5) + 1,
//                    pIdx,
//                    fDidx,
//                    (pIdx & 1) == 1 ? PaymentMethod.CASH : PaymentMethod.DIGITAL,
//                    random.nextInt(1000),
//                    locationList.stream().filter(l -> l.getLocationId() == pIdx).findFirst().get().getDistance(locationList.stream().filter(l -> l.getLocationId() == fDidx).findFirst().get()),
//                    LocalDateTime.now().minusSeconds(random.nextInt(1000000))
//            );
//
//            requestList.add(request);
//        }
//
//        db.addObjects(requestList, BinFilePath.RIDE_REQUEST, true);
    }

    private static void generateRides() {
        List<Ride> rideList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int pId = random.nextInt(locationList.size()) + 1;
            int dId = random.nextInt(locationList.size()) + 1;
            int mDId = pId == dId ? pId == locationList.size() ? dId - random.nextInt(3) + 1 : dId + 1 : dId;
            Location pLocation = locationList.stream().filter(l -> l.getLocationId() == pId).findFirst().get();
            Location dLocation = locationList.stream().filter(l -> l.getLocationId() == mDId).findFirst().get();
            int driverId = random.nextInt(5) + 1;
            Ride ride = new Ride(
                    random.nextInt(10) + 1,
                    driverId,
                    pId,
                    dId,
                    pLocation.getDistance(dLocation),
                    (mDId & 1) == 1 ? PaymentMethod.CASH : PaymentMethod.DIGITAL,
                    random.nextInt(1000),
                    driverId
            );

            if (mDId % 3 == 0) {
                ride.setHasCompleted(true);
                ride.setPassengerFeedbackId(i + 1);
                LocalDateTime pTime = LocalDateTime.now().minusDays(random.nextInt(365)).minusHours(random.nextInt(24)).minusSeconds(random.nextInt(60));
                LocalDateTime dTime = pTime.plusMinutes((long) ride.getRideDistance() * 5).plusSeconds(random.nextInt(60));
                ride.setPickupTime(pTime);
                ride.setDropOffTime(dTime);
            }

            rideList.add(ride);
        }

        db.addObjects(rideList, BinFilePath.RIDE, true);
    }
}