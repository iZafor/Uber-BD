package bd.uber;


import bd.uber.zafor.controller.driver.DriverSignupController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CreateNewAccountController {
    @FXML
    void onNewAccountAsADriver(ActionEvent event) {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.DRIVER_SIGN_UP_VIEW);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "Driver Signup",
                    true
            );
            ((DriverSignupController) loader.getController()).setInitData(Util.getInstance().getSignupFormProperty());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onNewAccountAsAPassenger(ActionEvent event) {

    }
}