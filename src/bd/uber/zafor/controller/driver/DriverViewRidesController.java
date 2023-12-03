package bd.uber.zafor.controller.driver;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.driver.Driver;
import bd.uber.zafor.model.driver.Ride;
import bd.uber.zafor.model.driver.RideFeedback;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DriverViewRidesController implements Initializable {
    @FXML
    private VBox rideListView;
    @FXML
    private CheckBox fromDateCheckBox;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private CheckBox toDateCheckBox;
    @FXML
    private DatePicker toDatePicker;
    @FXML
    private CheckBox fareLowerCheckBox;
    @FXML
    private CheckBox fareUpperCheckBox;
    @FXML
    private TextField fareLowerTextField;
    @FXML
    private TextField fareUpperTextField;

    private Driver driver;
    private ObservableList<RideFeedback> feedbackList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromDatePicker.setDisable(true);
        toDatePicker.setDisable(true);
        fareLowerTextField.setDisable(true);
        fareUpperTextField.setDisable(true);

        fromDatePicker.setConverter(Util.STRING_CONVERTER_FOR_LOCAL_DATE);
        toDatePicker.setConverter(Util.STRING_CONVERTER_FOR_LOCAL_DATE);

        fromDatePicker.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(null, LocalDate.now()));
        toDatePicker.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(null, LocalDate.now()));

        fromDateCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> fromDatePicker.setDisable(!newValue));
        toDateCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> toDatePicker.setDisable(!newValue));
        fareLowerCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> fareLowerTextField.setDisable(!newValue));
        fareUpperCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> fareUpperTextField.setDisable(!newValue));
    }

    public void setInitData(Driver driver, ObservableList<RideFeedback> feedbackList) {
        this.driver = driver;
        this.feedbackList = feedbackList;
        updateRideListView(driver.getRideList().stream());
    }

    @FXML
    void onApplyFilter() {
        boolean isFromDate = fromDateCheckBox.isSelected();
        boolean isToDate = toDateCheckBox.isSelected();
        boolean isFareLower = fareLowerCheckBox.isSelected();
        boolean isFareUpper = fareUpperCheckBox.isSelected();

        if (!isFromDate && !isToDate && !isFareLower && !isFareUpper) {
            Util.getInstance().showWarningMessage("Invalid filtering!");
            return;
        }

        Predicate<Ride> ridePredicate = getRideFilterPredicate(isFromDate, isToDate, isFareLower, isFareUpper);
        if (ridePredicate == null) {
            Util.getInstance().showWarningMessage("Invalid filtering!");
            return;
        }

        updateRideListView(
                driver.getRideList()
                        .stream().filter(ridePredicate)
        );
    }

    @FXML
    void onResetFilter() {
        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);
        fareLowerTextField.setText(null);
        fareUpperTextField.setText(null);

        fromDateCheckBox.setSelected(false);
        toDateCheckBox.setSelected(false);
        fareLowerCheckBox.setSelected(false);
        fareUpperCheckBox.setSelected(false);

        updateRideListView(driver.getRideList().stream());
    }

    private void updateRideListView(Stream<Ride> rideStream) {
        rideListView.getChildren().clear();
        Util.getInstance().getWorkers().execute(() -> rideStream.forEach(ride -> {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.RIDE_VIEW);
            try {
                HBox rideView = loader.load();
                ((RideViewController) loader.getController()).setInitData(ride, feedbackList.stream().filter(f -> f.getRideFeedBackId() == ride.getPassengerFeedbackId()).findFirst().orElse(null));
                Platform.runLater(() -> rideListView.getChildren().add(rideView));
            } catch (IOException | OutOfMemoryError e) {
                System.out.println(e.getMessage());
            }
        }));
    }

    private Predicate<Ride> getRideFilterPredicate(boolean isFromDate, boolean isToDate, boolean isFareLower, boolean isFareUpper) {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        if ((isFromDate || isToDate) && fromDate == null) {
            return null;
        }

        if (fromDate != null && toDate != null && (!fromDate.isEqual(toDate) && fromDate.isAfter(toDate))) {
            return null;
        }

        float fareLower = 0;
        float fareUpper = 0;

        if (isFareLower) {
            try {
                fareLower = Float.parseFloat(fareLowerTextField.getText());
            } catch (NumberFormatException | NullPointerException ignored) {
                return null;
            }
        }

        if (isFareUpper) {
            try {
                fareUpper = Float.parseFloat(fareUpperTextField.getText());
            } catch (NumberFormatException | NullPointerException ignored) {
                return null;
            }
        }

        if (isFareLower && isFareUpper && fareLower > fareUpper) {
            return null;
        }

        float finalFareLower;
        float finalFareUpper;
        if (isFromDate && isToDate && toDate != null && isFareLower && isFareUpper) {
            finalFareLower = fareLower;
            finalFareUpper = fareUpper;
            return ride -> ride.isCompleted() && ride.getPickupTime().isAfter(LocalDateTime.of(fromDate, LocalTime.MIDNIGHT))
                    && ride.getPickupTime().isBefore(LocalDateTime.of(toDate, LocalTime.MIDNIGHT))
                    && ride.getFare() >= finalFareLower && ride.getFare() <= finalFareUpper;
        } else if (isFromDate && isToDate && toDate != null && !isFareLower && !isFareUpper) {
            return ride -> ride.isCompleted() && ride.getPickupTime().isAfter(LocalDateTime.of(fromDate, LocalTime.MIDNIGHT))
                    && ride.getPickupTime().isBefore(LocalDateTime.of(toDate, LocalTime.MIDNIGHT));
        } else if (!isFromDate && !isToDate && isFareLower && isFareUpper) {
            finalFareLower = fareLower;
            finalFareUpper = fareUpper;
            return ride -> ride.getFare() >= finalFareLower && ride.getFare() <= finalFareUpper;
        } else if (isFromDate && !isToDate && isFareLower && !isFareUpper) {
            finalFareLower = fareLower;
            return ride -> ride.isCompleted() && ride.getPickupTime().isAfter(LocalDateTime.of(fromDate, LocalTime.MIDNIGHT))
                    && ride.getFare() >= finalFareLower;
        } else if (isFromDate && !isToDate && !isFareLower && !isFareUpper) {
            return ride -> ride.isCompleted() && ride.getPickupTime().isAfter(LocalDateTime.of(fromDate, LocalTime.MIDNIGHT));
        } else if (!isFromDate && !isToDate && isFareLower) {
            finalFareLower = fareLower;
            return ride -> ride.getFare() >= finalFareLower;
        } else {
            return null;
        }
    }
}