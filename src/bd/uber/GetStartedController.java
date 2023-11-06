package bd.uber;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class GetStartedController implements Initializable {
    @FXML
    ComboBox<UserType> userTypeChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeChoiceBox.getItems().addAll(UserType.values());
    }
}