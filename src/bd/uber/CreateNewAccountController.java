package bd.uber;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreateNewAccountController {
    @FXML
    void onNewAccountAsADriver(ActionEvent event) {
        Util.getInstance().showScene(
                Util.getInstance().getDriverSignUpView(),
                event,
                "Driver Signup",
                true
        );
    }

    @FXML
    void onNewAccountAsAPassenger(ActionEvent event) {

    }
}