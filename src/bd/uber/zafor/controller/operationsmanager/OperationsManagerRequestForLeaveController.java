package bd.uber.zafor.controller.operationsmanager;

import bd.uber.BinFilePath;
import bd.uber.FXMLFilePath;
import bd.uber.LeaveRequest;
import bd.uber.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.ResourceBundle;

public class OperationsManagerRequestForLeaveController implements Initializable {
    @FXML
    private VBox leaveRequestVBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextArea reasonTextArea;

    private int employeeId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startDatePicker.setConverter(Util.STRING_CONVERTER_FOR_LOCAL_DATE);
        endDatePicker.setConverter(Util.STRING_CONVERTER_FOR_LOCAL_DATE);
        startDatePicker.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
        endDatePicker.setDayCellFactory(param -> Util.getInstance().getConstrainedDateCell(LocalDate.now(), null));
    }

    public void setInitData(int employeeId) {
        this.employeeId = employeeId;
        loadLeaveRequests();
    }

    @FXML
    private void onRequestForLeave() {
        LocalDate startDate = startDatePicker.getValue();
        if (startDate == null) {
            Util.getInstance().showWarningMessage("Start date cannot be empty!");
            return;
        }

        LocalDate endDate = endDatePicker.getValue();
        if (endDate == null) {
            Util.getInstance().showWarningMessage("End date cannot be empty!");
            return;
        }

        String reason = reasonTextArea.getText();
        if (reason == null || reason.trim().isEmpty()) {
            Util.getInstance().showWarningMessage("Leave request reason cannot be empty!");
            return;
        }

        Util.getInstance().getWorkers().execute(() -> {
            LeaveRequest leaveRequest = new LeaveRequest(employeeId, reason, startDate, endDate);
            if (Util.getInstance().getDb().addObject(
                    leaveRequest,
                    BinFilePath.LEAVE_REQUEST
            )) {
                Platform.runLater(() -> {
                    Util.getInstance().showSuccessMessage("Request submitted successfully.");
                    addLeaveRequestView(leaveRequest, 0);
                });
            } else {
                Platform.runLater(() -> Util.getInstance().showSuccessMessage("Failed to submit request!"));
            }
        });
    }

    private void loadLeaveRequests() {
        Util.getInstance().getWorkers().execute(() ->
                Util.getInstance().getDb().<LeaveRequest>getObjectList(
                                BinFilePath.LEAVE_REQUEST,
                                l -> l.getEmployeeId() == employeeId
                        ).stream().sorted(Comparator.comparing(LeaveRequest::getStartDate, Comparator.reverseOrder()))
                        .forEach(leaveRequest -> addLeaveRequestView(leaveRequest, -1))
        );
    }

    private void addLeaveRequestView(LeaveRequest leaveRequest, int idx) {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.LEAVE_REQUEST_VIEW);
                AnchorPane pane = loader.load();
                ((LeaveRequestViewController) loader.getController()).setInitData(leaveRequest);
                Platform.runLater(() -> {
                            if (idx == -1) leaveRequestVBox.getChildren().add(pane);
                            else leaveRequestVBox.getChildren().add(idx, pane);
                        }
                );
            } catch (IOException ignored) {
                // log the error
            }
        });
    }
}