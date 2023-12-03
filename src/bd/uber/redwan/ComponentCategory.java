package bd.uber.redwan;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Redwan
 */

// Enum for ComponentCategory
public enum ComponentCategory implements Serializable {
    POWERTRAIN_AND_TRANSMISSION(Arrays.asList("Engine",
            "Transmission",
            "Clutch",
            "Driveshaft",
            "Differential",
            "Axles",
            "Transfer case")
    ),
    FUEL_AND_IGNITION_SYSTEMS(Arrays.asList("Fuel tank",
            "Fuel pump",
            "Fuel injectors",
            "Ignition coil",
            "Spark plugs",
            "Ignition switch")),
    COOLING_AND_LUBRICATION(Arrays.asList("Radiator",
            "Water pump",
            "Thermostat",
            "Oil pump",
            "Oil filter",
            "Coolant reservoir",
            "Engine oil pan")),
    ELECTRICAL_SYSTEM(Arrays.asList("Battery",
            "Alternator",
            "Starter motor",
            "Wiring harness",
            "Fuses",
            "Relays",
            "Lights")),
    SUSPENSION_AND_STEERING(Arrays.asList("Shock absorbers",
            "Struts",
            "Springs",
            "Control arms",
            "Steering rack",
            "Tie rods",
            "Ball joints")),
    BRAKING_SYSTEM(Arrays.asList("Brake calipers",
            "Brake pads",
            "Brake rotors",
            "Brake lines",
            "Brake master cylinder",
            "Brake booster",
            "Parking brake assembly")),
    EXHAUST_SYSTEM(Arrays.asList("Exhaust manifold",
            "Catalytic converter",
            "Exhaust pipes",
            "Muffler",
            "Oxygen sensor",
            "Resonator",
            "Exhaust tips"));

    private final List<String> componentList;

    ComponentCategory(List<String> componentList) {
        this.componentList = componentList;
    }

    public List<String> getComponentList() {
        return componentList;
    }
}