package bd.uber;

import bd.uber.zafor.model.driver.*;
import javafx.application.Platform;
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

    private SignupDriver signupDriver;
    private List<Location> locationList;

    private Util() {
        db = new DB();
        signupDriver = null;
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

    public SignupDriver getSignupDriver() {
        if (signupDriver == null) {
            signupDriver = new SignupDriver();
        }
        return signupDriver;
    }

    public void resetSignupDriverObjects() {
        signupDriver = null;
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

    public boolean isAnInvalidPassword(String password) {
        int digitsCount = 0;
        int upperCaseCount = 0;
        int lowerCaseCount = 0;
        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                digitsCount++;
            } else if (c >= 'a' && c <= 'z') {
                lowerCaseCount++;
            } else if (c >= 'A' && c <= 'Z') {
                upperCaseCount++;
            }
        }
        return digitsCount < 2 || upperCaseCount < 2 || lowerCaseCount < 2;
    }

    public boolean isAnInvalidNumber(String phoneNumber) {
        for (char c : phoneNumber.toCharArray()) {
            if (c < '0' || c > '9') {
                return true;
            }
        }
        return false;
    }
}