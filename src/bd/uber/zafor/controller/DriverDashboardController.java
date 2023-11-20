package bd.uber.zafor.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.Driver;
import bd.uber.zafor.model.DriverStatus;
import bd.uber.zafor.model.Ride;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DriverDashboardController implements Initializable {
    @FXML
    private Text driverNameText;

    @FXML
    private Text currentDateText;

    @FXML
    private ComboBox<DriverStatus> driverStatusComboBox;

    @FXML
    private VBox ridesVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        driverStatusComboBox.getItems().addAll(DriverStatus.values());
        currentDateText.setText(LocalDate.now().toString());
    }

    public void setInitData(Driver driver) {
        driverNameText.setText(driver.getName());

        for (Ride ride : driver.getRideList()) {
            ridesVBox.getChildren().add(getRideView(ride));
        }
    }

    private HBox getRideView(Ride ride) {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.RIDE_VIEW);
            return loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}