package bd.uber.redwan;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Redwan
 */
public class RepairTeamMemberSceneController implements Initializable {

    @FXML
    private TextArea findingsTextArea;
    @FXML
    private ListView<RepairRequest> repairRequestsListView;
    @FXML
    private TextField reportIdTextField;
    @FXML
    private TextField driverIdTextField;

    private RepairTeamMember repairTeamMember;
    private Map<String, RepairRequest> requestMap; // Map to store Request ID -> RepairRequest
    @FXML
    private TextArea totalCostTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<RepairRequest> repairRequestsList = RepairRequest.generateSampleRepairRequests();
        requestMap = new HashMap<>();

        for (RepairRequest request : repairRequestsList) {
            requestMap.put(request.getRequestId(), request);
            repairRequestsListView.getItems().add(request);
        }

        repairRequestsListView.setCellFactory(param -> new ListCell<RepairRequest>() {
            @Override
            protected void updateItem(RepairRequest item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getRequestId());
                }
            }
        });

        repairRequestsListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends RepairRequest> observable, RepairRequest oldValue, RepairRequest newValue) -> {
                    if (newValue != null) {
                        reportIdTextField.setText(newValue.getRequestId());
                    }
                }
        );
    }

    @FXML
    private void loadReportButton(ActionEvent event) {
        String selectedRequestId = reportIdTextField.getText();
        RepairRequest selectedRequest = requestMap.get(selectedRequestId);

        if (selectedRequest != null) {
            int index = findIndexInListView(selectedRequestId);
            if (index != -1) {
                repairRequestsListView.getSelectionModel().select(index);
                showInitialDamageReports(selectedRequest);
            } else {
                findingsTextArea.setText("Request ID not found in the list.");
            }
        } else {
            findingsTextArea.setText("No request found for this ID: " + selectedRequestId);
        }
    }

    private int findIndexInListView(String requestId) {
        ObservableList<RepairRequest> repairRequests = repairRequestsListView.getItems();
        for (int i = 0; i < repairRequests.size(); i++) {
            if (repairRequests.get(i).getRequestId().equals(requestId)) {
                return i;
            }
        }
        return -1;
    }

    private void showInitialDamageReports(RepairRequest request) {
        List<DamageReport> damageReports = request.getDamageReports();

        if (!damageReports.isEmpty()) {
            StringBuilder initialReports = new StringBuilder();
            for (DamageReport report : damageReports) {
                initialReports.append(report.toString()).append("\n");
                initialReports.append("Findings: ").append(report.getFindings()).append("\n\n");
            }
            findingsTextArea.setText(initialReports.toString());

            // Set the driverIdTextField with the driver ID from the request
            driverIdTextField.setText(String.valueOf(request.getDriverId()));
        } else {
            findingsTextArea.setText("No initial damage reports available for this request.");
        }
    }

    @FXML
    private void totalCostButton(ActionEvent event) {
        String text = findingsTextArea.getText();
        int totalCost = calculateTotalCost(text);
        totalCostTextArea.setText("Total Cost: $" + totalCost);
    }

    private int calculateTotalCost(String text) {
        int totalCost = 0;

        // Split the text by line and look for lines containing "Estimated Cost: $"
        String[] lines = text.split("\n");
        for (String line : lines) {
            if (line.contains("Estimated Cost: $")) {
                // Extract the number from the line using regular expression
                String costString = line.replaceAll(".*Estimated Cost: \\$(\\d+).*", "$1");
                try {
                    int cost = Integer.parseInt(costString);
                    totalCost += cost;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        return totalCost;
    }

    @FXML
    private void notifyDriverButton(ActionEvent event) {
    String driverId = driverIdTextField.getText();
    String totalCost = totalCostTextArea.getText();

    if (!driverId.isEmpty() && !totalCost.isEmpty()) {
        String notification = "Notified to Driver, ID: " + driverId + ", repair will cost: " + totalCost;

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(notification);
        alert.showAndWait();
    } else {
        // Handle the case where either driverId or totalCost is empty
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Missing Information");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in the Driver ID and calculate the total cost.");
        alert.showAndWait();
    }
    }


    @FXML
    private void backButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        AnchorPane root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}
