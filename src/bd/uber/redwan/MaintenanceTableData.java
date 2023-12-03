package bd.uber.redwan;

/**
 *
 * @author Redwan
 */
import bd.uber.zafor.model.driver.EngineOilLevel;
import bd.uber.zafor.model.driver.MaintenanceRequest;
import bd.uber.zafor.model.driver.VehicleInfo;
import bd.uber.zafor.model.driver.VehicleStatus;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


public class MaintenanceTableData {

    private SimpleStringProperty model;
    private SimpleStringProperty preferredTimeSlot;
    private SimpleIntegerProperty odometerReading;
    private SimpleIntegerProperty fuelLevel;
    private SimpleObjectProperty<Float> tirePressure;
    private SimpleObjectProperty<EngineOilLevel> engineOilLevel;
    private SimpleIntegerProperty vehicleInfoId;

    public MaintenanceTableData() {
        this.model = new SimpleStringProperty();
        this.preferredTimeSlot = new SimpleStringProperty();
        this.odometerReading = new SimpleIntegerProperty();
        this.fuelLevel = new SimpleIntegerProperty();
        this.tirePressure = new SimpleObjectProperty<>();
        this.engineOilLevel = new SimpleObjectProperty<>();
        this.vehicleInfoId = new SimpleIntegerProperty();
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getPreferredTimeSlot() {
        return preferredTimeSlot.get();
    }

    public SimpleStringProperty preferredTimeSlotProperty() {
        return preferredTimeSlot;
    }

    public void setPreferredTimeSlot(String preferredTimeSlot) {
        this.preferredTimeSlot.set(preferredTimeSlot);
    }

    public int getOdometerReading() {
        return odometerReading.get();
    }

    public SimpleIntegerProperty odometerReadingProperty() {
        return odometerReading;
    }

    public void setOdometerReading(int odometerReading) {
        this.odometerReading.set(odometerReading);
    }

    public int getFuelLevel() {
        return fuelLevel.get();
    }

    public SimpleIntegerProperty fuelLevelProperty() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel.set(fuelLevel);
    }

    public Float getTirePressure() {
        return tirePressure.get();
    }

    public SimpleObjectProperty<Float> tirePressureProperty() {
        return tirePressure;
    }

    public void setTirePressure(Float tirePressure) {
        this.tirePressure.set(tirePressure);
    }

    public EngineOilLevel getEngineOilLevel() {
        return engineOilLevel.get();
    }

    public SimpleObjectProperty<EngineOilLevel> engineOilLevelProperty() {
        return engineOilLevel;
    }

    public void setEngineOilLevel(EngineOilLevel engineOilLevel) {
        this.engineOilLevel.set(engineOilLevel);
    }

    public int getVehicleInfoId() {
        return vehicleInfoId.get();
    }

    public SimpleIntegerProperty vehicleInfoIdProperty() {
        return vehicleInfoId;
    }

    public void setVehicleInfoId(int vehicleInfoId) {
        this.vehicleInfoId.set(vehicleInfoId);
    }
    

    public static MaintenanceTableData fromModels(VehicleInfo vehicleInfo, VehicleStatus vehicleStatus, MaintenanceRequest maintenanceRequest) {
        MaintenanceTableData tableData = new MaintenanceTableData();

        if (vehicleInfo != null) {
            tableData.setModel(vehicleInfo.getModel());
            tableData.setVehicleInfoId(vehicleInfo.getVehicleInfoId());
        }

        if (vehicleStatus != null) {
            tableData.setOdometerReading(vehicleStatus.getOdometerReading());

            String fuelLevelStr = vehicleStatus.getFuelLevel().toString();

            int fuelLevelInt;
            switch (fuelLevelStr) {
                case "Full":
                    fuelLevelInt = 100; // Full corresponds to 100% fuel level
                    break;
                case "3/4":
                    fuelLevelInt = 75; // Three-quarters corresponds to 75% fuel level
                    break;
                case "1/2":
                    fuelLevelInt = 50; // Half corresponds to 50% fuel level
                    break;
                case "1/4":
                    fuelLevelInt = 25; // Quarter corresponds to 25% fuel level
                    break;
                case "Empty":
                    fuelLevelInt = 0; // Empty corresponds to 0% fuel level
                    break;
                default:
                    fuelLevelInt = -1; // Default value or error handling
                    break;
            }

            tableData.setFuelLevel(fuelLevelInt);
            tableData.setTirePressure(vehicleStatus.getTirePressure());
            tableData.setEngineOilLevel(vehicleStatus.getEngineOilLevel());
        }

        if (maintenanceRequest != null) {
            tableData.setPreferredTimeSlot(maintenanceRequest.getTimeSlot().toString()); // Assuming getTimeSlot() returns a string representation
        }

        return tableData;
    }
    /////test
@Override
public String toString() {
    return "Id: " + this.vehicleInfoId.getValue() +
            ", Model: " + this.model.getValue() +
            ", Preferred Time: " + this.preferredTimeSlot +
            ", Odometer Reading: " + this.odometerReading +
            ", Fuel Level: " +this.fuelLevel+
            ", Tire Pressure: " +this.tirePressure+
            ", Engine Oil Level" +this.engineOilLevel;
}    
    

}
