package bd.uber;

import bd.uber.zafor.model.driver.PaymentMethod;
import bd.uber.zafor.model.driver.RideRequest;
import bd.uber.zafor.model.operationsmanager.OperationsManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    private static final DB db = new DB();
    private static final Random random = new Random(System.nanoTime());

    public static void main(String[] args) {
//        OperationsManager operationsManager = new OperationsManager(1);
//        operationsManager.setName("Zafor Iqbal");
//        operationsManager.setPassword("abc");
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
}