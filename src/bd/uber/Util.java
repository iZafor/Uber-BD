package bd.uber;

import bd.uber.zafor.model.driver.*;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

public class Util {
    private static Util util;
    public static final StringConverter<LocalDate> STRING_CONVERTER_FOR_LOCAL_DATE = new StringConverter<LocalDate>() {
        @Override
        public String toString(LocalDate object) {
            return object == null ? "" : object.toString();
        }

        @Override
        public LocalDate fromString(String string) {
            return string == null || string.isEmpty() ? null : LocalDate.parse(string);
        }
    };

    private final DB db;
    private final ExecutorService workers;

    private Driver signUpDriver;
    private ContactDetails signupDriverContactDetails;
    private VehicleInfo signupDriverVehicleInfo;
    private VehicleStatus signUpDriverVehicleStatus;
    private InsurancePolicy signUpDriverInsurancePolicy;
    private DrivingLicense signUpDriverDrivingLicense;
    private final ObjectProperty<SignupForm> signupFormProperty;
    private List<Location> locationList;

    private Util() {
        db = new DB();
        signupFormProperty = new SimpleObjectProperty<>(SignupForm.BASIC_INFO);
        workers = Executors.newFixedThreadPool(5);
    }

    public static Util getInstance() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }

    public ExecutorService getWorkers() {
        return workers;
    }

    public DB getDb() {
        return db;
    }

    public Driver getSignUpDriver() {
        if (signUpDriver == null) {
            signUpDriver = new Driver(db.getObjectCount(BinFilePath.DRIVER) + 1);
        }
        return signUpDriver;
    }

    public ContactDetails getSignupDriverContactDetails() {
        if (signupDriverContactDetails == null) {
            signupDriverContactDetails = new ContactDetails(db.getObjectCount(BinFilePath.CONTACT_DETAILS) + 1);
        }
        return signupDriverContactDetails;
    }

    public VehicleInfo getSignupDriverVehicleInfo() {
        if (signupDriverVehicleInfo == null) {
            signupDriverVehicleInfo = new VehicleInfo(db.getObjectCount(BinFilePath.VEHICLE_INFO) + 1);
        }
        return signupDriverVehicleInfo;
    }

    public VehicleStatus getSignupDriverVehicleStatus() {
        if (signUpDriverVehicleStatus == null) {
            signUpDriverVehicleStatus = new VehicleStatus(db.getObjectCount(BinFilePath.VEHICLE_STATUS) + 1);
        }
        return signUpDriverVehicleStatus;
    }

    public InsurancePolicy getSignUpDriverInsurancePolicy() {
        if (signUpDriverInsurancePolicy == null) {
            signUpDriverInsurancePolicy = new InsurancePolicy(db.getObjectCount(BinFilePath.INSURANCE_POLICY) + 1);
        }
        return signUpDriverInsurancePolicy;
    }

    public DrivingLicense getSignUpDriverDrivingLicense() {
        if (signUpDriverDrivingLicense == null) {
            signUpDriverDrivingLicense = new DrivingLicense(db.getObjectCount(BinFilePath.DRIVING_LICENSE) + 1);
        }
        return signUpDriverDrivingLicense;
    }

    public void resetSignupDriverObjects() {
        signUpDriver = null;
        signupDriverContactDetails = null;
        signupDriverVehicleInfo = null;
        signUpDriverInsurancePolicy = null;
        signUpDriverVehicleStatus = null;
        signUpDriverDrivingLicense = null;
    }

    public ObjectProperty<SignupForm> getSignupFormProperty() {
        return signupFormProperty;
    }

    public void setSignupForm(SignupForm form) {
        signupFormProperty.setValue(form);
    }

    /**
     * Returns a bounded DateCell instance.
     * null value will exclude that filter.
     *
     * @param dateBefore cells will be disabled before this date
     * @param dateAfter  cells will be disabled after this date
     * @return DateCell
     */
    public DateCell getConstrainedDateCell(LocalDate dateBefore, LocalDate dateAfter) {
        return new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || (dateBefore != null && item.isBefore(dateBefore)) || (dateAfter != null && item.isAfter(dateAfter)));
            }
        };
    }

    /**
     * Shows a scene to the source window attached to the eventObject.
     *
     * @param parent      node to show
     * @param eventObject source for the window
     * @param windowTitle title for the new window
     * @param hideOwner   to hide the owner window if there is any
     */
    public void showScene(Parent parent, EventObject eventObject, String windowTitle, boolean hideOwner) {
        Stage stage = (Stage) ((Node) eventObject.getSource()).getScene().getWindow();
        stage.hide();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(windowTitle);
        if (hideOwner && stage.getOwner() != null) {
            stage.getOwner().hide();
        }
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/uber.png"))));
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Shows a scene to a whole new window.
     *
     * @param scene       configured scene to show
     * @param windowTitle title for the new window
     */
    public void showScene(Scene scene, String windowTitle) {
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(windowTitle);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/uber.png"))));
        stage.show();
    }

    /**
     * Shows a scene to a new window adding ownership
     * and modality to the provided window.
     *
     * @param scene       configured scene to show
     * @param eventObject source for the owner window
     * @param windowTitle title for the new window
     */
    public void showScene(Scene scene, EventObject eventObject, String windowTitle) {
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initOwner(((Node) eventObject.getSource()).getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.setTitle(windowTitle);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/uber.png"))));
        stage.showAndWait();
    }

    public FXMLLoader getLoader(FXMLFilePath fxmlFilePath) {
        return new FXMLLoader(getClass().getResource(fxmlFilePath.getPath()));
    }

    public List<Location> getLocationList() {
        if (locationList == null) {
            locationList = db.getObjectList(BinFilePath.LOCATION);
        }
        return locationList;
    }

    public void showWarningMessage(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

    public void showError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public void showSuccessMessage(String successMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(successMessage);
        alert.showAndWait();
    }

    public <E> void updateObject(E e, BinFilePath binFilePath, Predicate<E> predicate, String successMessage, String failureMessage) {
        workers.execute(() -> {
            if (db.updateObjectFile(e, binFilePath, predicate, false) && successMessage != null) {
                Platform.runLater(() -> showSuccessMessage(successMessage));
            } else {
                Platform.runLater(() -> {
                    if (failureMessage != null) {
                        showError(failureMessage);
                    }
                });
            }
        });
    }
}