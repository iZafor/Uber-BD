package bd.uber.zafor.controller.operationsmanager;

import bd.uber.Location;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LocationViewController {
    @FXML
    private Text locationNameText;

    @FXML
    private Text locationDirectionText;

    @FXML
    private Text distanceText;

    public void setInitData(Location location) {
        locationNameText.setText(location.getName());
        locationDirectionText.setText(location.getDirection().name());
        distanceText.setText(String.valueOf(location.getDistanceFromCentralPoint()));
    }
}