package bd.uber.zafor.controller.operationsmanager;

import bd.uber.BinFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.driver.Ride;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class OperationsManagerGetInsightController implements Initializable {
    @FXML
    private VBox visualizationGraphVBox;

    private final NumberAxis xAxisForEarningTrend = new NumberAxis();
    private final NumberAxis yAxisForEarningTrend = new NumberAxis();
    private final LineChart<Number, Number> earningTrendLineChart = new LineChart<>(xAxisForEarningTrend, yAxisForEarningTrend);

    {
        xAxisForEarningTrend.setLabel("Hour");
        yAxisForEarningTrend.setLabel("Money Earned");
        earningTrendLineChart.setAnimated(true);
    }

    private final CategoryAxis xAxisForLocation = new CategoryAxis();
    private final NumberAxis yAxisForLocation = new NumberAxis();
    private final BarChart<String, Number> locationBarChart = new BarChart<>(xAxisForLocation, yAxisForLocation);


    private final Text dataNotFoundText = new Text("Data not found!");
    private final List<Ride> rideList = new ArrayList<>();

    {
        dataNotFoundText.setStyle("-fx-font-size: 16");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rideList.addAll(Util.getInstance().getDb().getObjectList(BinFilePath.RIDE));
    }

    @FXML
    private void onShowEarningTrendsPerHour() {
        if (rideList.isEmpty()) {
            visualizationGraphVBox.getChildren().clear();
            visualizationGraphVBox.getChildren().add(dataNotFoundText);
            return;
        }

        // Extract Data
        Map<Integer, Float> perHourEarningMap = new HashMap<>();
        for (Ride ride : rideList) {
            if (ride.hasCompleted()) {
                int pickUpHour = ride.getDropOffTime().getHour();
                float fare = ride.getFare();
                if (perHourEarningMap.containsKey(pickUpHour)) {
                    perHourEarningMap.put(pickUpHour, perHourEarningMap.get(pickUpHour) + fare);
                } else {
                    perHourEarningMap.put(pickUpHour, fare);
                }
            }
        }

        // Prepare Data For Visualization
        XYChart.Series<Number, Number> earingSeries = new XYChart.Series<>();
        earingSeries.setName("Earnings Per Hour");
        for (Integer hour : perHourEarningMap.keySet()) {
            earingSeries.getData().add(new XYChart.Data<>(hour, perHourEarningMap.get(hour)));
        }

        // Show Data
        earningTrendLineChart.getData().clear();
        visualizationGraphVBox.getChildren().clear();
        visualizationGraphVBox.getChildren().add(earningTrendLineChart);
        earningTrendLineChart.getData().add(earingSeries);
    }

    @FXML
    private void onShowTop10DropOffLocations() {
        if (rideList.isEmpty()) {
            visualizationGraphVBox.getChildren().clear();
            visualizationGraphVBox.getChildren().add(dataNotFoundText);
            return;
        }

        locationBarChart.setAnimated(true);
        // Extract Data
        Map<Integer, Integer> dropOffPointMap = new HashMap<>();
        for (Ride ride : rideList) {
            if (ride.hasCompleted()) {
                int locationId = ride.getDropOffPointId();
                if (dropOffPointMap.containsKey(locationId)) {
                    dropOffPointMap.put(locationId, dropOffPointMap.get(locationId) + 1);
                } else {
                    dropOffPointMap.put(locationId, 1);
                }
            }
        }


        // Prepare Data For Visualization
        XYChart.Series<String, Number> dropOffSeries = new XYChart.Series<>();
        new ArrayList<>(dropOffPointMap.entrySet())
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .forEach(entry -> {
                    dropOffSeries.getData().add(
                            new XYChart.Data<>(
                                    Util.getInstance().getLocationList()
                                            .stream()
                                            .filter(l -> l.getLocationId() == entry.getKey())
                                            .findFirst().get().getName(),
                                    entry.getValue()
                            )
                    );
                });

        // Show Data
        xAxisForLocation.setLabel("Drop off Locations");
        yAxisForLocation.setLabel("Number of Drop Offs");
        locationBarChart.getData().clear();
        visualizationGraphVBox.getChildren().clear();
        visualizationGraphVBox.getChildren().add(locationBarChart);
        locationBarChart.getData().add(dropOffSeries);
        locationBarChart.setAnimated(false);
    }

    @FXML
    private void onShowTop10PickupLocations() {
        if (rideList.isEmpty()) {
            visualizationGraphVBox.getChildren().clear();
            visualizationGraphVBox.getChildren().add(dataNotFoundText);
            return;
        }

        locationBarChart.setAnimated(true);
        // Extract Data
        Map<Integer, Integer> pickupPointMap = new HashMap<>();
        for (Ride ride : rideList) {
            if (ride.hasCompleted()) {
                int locationId = ride.getPickupPointId();
                if (pickupPointMap.containsKey(locationId)) {
                    pickupPointMap.put(locationId, pickupPointMap.get(locationId) + 1);
                } else {
                    pickupPointMap.put(locationId, 1);
                }
            }
        }

        // Prepare Data For Visualization
        XYChart.Series<String, Number> pickupSeries = new XYChart.Series<>();
        new ArrayList<>(pickupPointMap.entrySet())
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .forEach(entry -> {
                    pickupSeries.getData().add(
                            new XYChart.Data<>(
                                    Util.getInstance().getLocationList()
                                            .stream()
                                            .filter(l -> l.getLocationId() == entry.getKey())
                                            .findFirst().get().getName(),
                                    entry.getValue()
                            )
                    );
                });

        // Show Data
        xAxisForLocation.setLabel("Pick up Locations");
        yAxisForLocation.setLabel("Number of Pickups");
        locationBarChart.getData().clear();
        visualizationGraphVBox.getChildren().clear();
        visualizationGraphVBox.getChildren().add(locationBarChart);
        locationBarChart.getData().add(pickupSeries);
        locationBarChart.setAnimated(false);
    }
}