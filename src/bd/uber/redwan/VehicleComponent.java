package bd.uber.redwan;

import java.io.Serializable;

// Class representing a vehicle component
public class VehicleComponent implements Serializable {
    private final String componentName;
    private final ComponentCategory category;
    private final int quantity;

    public VehicleComponent(String componentName, ComponentCategory category, int quantity) {
        this.componentName = componentName;
        this.category = category;
        this.quantity = quantity;
    }

    public String getComponentName() {
        return componentName;
    }

    public ComponentCategory getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }
}