package bd.uber;

import bd.uber.redwan.ComponentCategory;
import bd.uber.redwan.Inventory;
import bd.uber.redwan.VehicleComponent;

import java.util.Random;

public class Generator {
    private static final DB db = new DB();
    private static final Random random = new Random(System.nanoTime());

    public static void main(String[] args) {
        ComponentCategory[] categoryList = ComponentCategory.values();
        Inventory inventory = new Inventory();

        for (int i = 0; i < 100; i++) {
            int idx = random.nextInt(categoryList.length);
            VehicleComponent component = new VehicleComponent(
                    categoryList[idx].getComponentList().get(random.nextInt(6)),
                    categoryList[idx],
                    random.nextInt(5) + 1
            );
            inventory.addComponent(component);
        }

        db.addObject(inventory, BinFilePath.INVENTORY);
    }
}