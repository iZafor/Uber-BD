package bd.uber;

import bd.uber.zafor.model.Driver;
import bd.uber.zafor.model.SignupForm;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.EventObject;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private final ObjectProperty<SignupForm> signupFormProperty;

    private List<Location> locationList;

    private final Alert alert = new Alert(Alert.AlertType.WARNING);

    private Util() {
        db = new DB();
        signupFormProperty = new SimpleObjectProperty<>(SignupForm.BASIC_INFO);
        workers = Executors.newFixedThreadPool(1);
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
            signUpDriver = new Driver();
            signUpDriver.setId(db.getUserCount(BinFilePath.DRIVER) + 1);
        }
        return signUpDriver;
    }

    public void setSignUpDriver(Driver signUpDriver) {
        this.signUpDriver = signUpDriver;
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
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(windowTitle);
        if (hideOwner && stage.getOwner() != null) {
            stage.getOwner().hide();
        }
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
        Stage ownerStage = (Stage) ((Node) eventObject.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initOwner(ownerStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.setTitle(windowTitle);
        stage.centerOnScreen();
        stage.showAndWait();
    }

    public FXMLLoader getLoader(FXMLFilePath fxmlFilePath) {
        return new FXMLLoader(getClass().getResource(fxmlFilePath.getPath()));
    }

    public List<Location> getLocationList() {
        if (locationList == null) {
            locationList = db.getLocations();
        }
        return locationList;
    }

    public void showWarningMessage(String alertMessage) {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

    public void showSuccessMessage(String successMessage) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText(successMessage);
        alert.showAndWait();
    }
}