/*
 *
 * @author Redwan
 */
package bd.uber.redwan;

import java.util.Random;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VehicleRetirement {

    private final SimpleIntegerProperty vehicleRegNo;
    private final SimpleStringProperty year;
    private final SimpleStringProperty model;
    private final SimpleStringProperty status;

    public VehicleRetirement(int vehicleRegNo, String year, String model, String status) {
        this.vehicleRegNo = new SimpleIntegerProperty(vehicleRegNo);
        this.year = new SimpleStringProperty(year);
        this.model = new SimpleStringProperty(model);
        this.status = new SimpleStringProperty(status);
    }

    public static ObservableList<VehicleRetirement> getVehiclesNeedingRetirement() {
        ObservableList<VehicleRetirement> vehicles = FXCollections.observableArrayList();

        // Simulating a larger dataset with random values
        String[] models = {"Honda", "Toyota", "Tesla", "Lexus", "BMW"};
        String[] statuses = {"Needs Retirement", "In Poor Condition", "High Maintenance", "Outdated"};

        Random random = new Random();
        for (int i = 1; i <= 50; i++) {
            int year = 1990 + random.nextInt(20); // Random year between 1990 and 2009
            String model = models[random.nextInt(models.length)];
            String status = statuses[random.nextInt(statuses.length)];
            vehicles.add(new VehicleRetirement(i, String.valueOf(year), model, status));
        }

        return vehicles;
    }

    // Getters for properties
    public SimpleIntegerProperty vehicleRegNoProperty() {
        return vehicleRegNo;
    }

    public SimpleStringProperty yearProperty() {
        return year;
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public SimpleIntegerProperty getVehicleRegNo() {
        return vehicleRegNo;
    }

    public SimpleStringProperty getYear() {
        return year;
    }

    public SimpleStringProperty getModel() {
        return model;
    }

    public SimpleStringProperty getStatus() {
        return status;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }
}
