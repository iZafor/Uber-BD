/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.uber.redwan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Redwan
 */

// InventoryTeamMember class
public class InventoryTeamMember {
    private Map<ComponentCategory, List<VehicleComponent>> inventory;

    public InventoryTeamMember() {
        inventory = new HashMap<>();
        // Initialize the inventory with empty lists for each category
        for (ComponentCategory category : ComponentCategory.values()) {
            inventory.put(category, new ArrayList<>());
        }
    }

    // Method to place an order for a specific component category
    public void placeOrderForInventory(ComponentCategory category, VehicleComponent component) {
        // Get the list of components for the specified category
        List<VehicleComponent> components = inventory.get(category);
        
        // Add the new component to the list
        components.add(component);
        
        // Update the inventory
        inventory.put(category, components);
        
        // Display a message confirming the order placement
        System.out.println("Ordered " + component.getComponentName() + " in category " + category);
    }

    // Getters and setters if needed for the inventory map

    public Map<ComponentCategory, List<VehicleComponent>> getInventory() {
        return inventory;
    }

    public void setInventory(Map<ComponentCategory, List<VehicleComponent>> inventory) {
        this.inventory = inventory;
    }
}
