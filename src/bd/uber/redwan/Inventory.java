package bd.uber.redwan;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Redwan
 */

// Inventory class
public class Inventory implements Serializable {
    private Map<ComponentCategory, List<Pair<String, Integer>>> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    // Method to place an order for a specific component category
    public void addComponent(VehicleComponent component) {
        if (inventory.get(component.getCategory()) == null) {
            inventory.put(component.getCategory(), new ArrayList<Pair<String, Integer>>() {
                {
                    add(new Pair<>(component.getComponentName(), component.getQuantity()));
                }
            });
        } else {
            List<Pair<String, Integer>> components = inventory.get(component.getCategory());

            for (int i = 0; i < components.size(); i++) {
                if (components.get(i).getKey().equals(component.getComponentName())) {
                    components.set(i, new Pair<>(component.getComponentName(), component.getQuantity() + components.get(i).getValue()));
                    return;
                }
            }

            components.add(new Pair<>(component.getComponentName(), component.getQuantity()));
        }
    }

    public Map<ComponentCategory, List<Pair<String, Integer>>> getInventory() {
        return inventory;
    }

    public void setInventory(Map<ComponentCategory, List<Pair<String, Integer>>> inventory) {
        this.inventory = inventory;
    }
}