package bd.uber.zafor.controller.operationsmanager;

import bd.uber.LeaveRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

public class LeaveRequestViewController {
    @FXML
    private Text startDateText;
    @FXML
    private Text endDateText;
    @FXML
    private Label reasonLabel;
    @FXML
    private Text statusText;

    public void setInitData(LeaveRequest leaveRequest) {
        startDateText.setText(leaveRequest.getStartDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        endDateText.setText(leaveRequest.getEndDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        reasonLabel.setText(leaveRequest.getReason());
        statusText.setText(leaveRequest.getStatus().name());
    }
}