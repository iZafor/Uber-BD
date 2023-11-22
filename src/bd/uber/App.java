package bd.uber;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Util.getInstance().showScene(
                new Scene(Util.getInstance().getLoader(
                        FXMLFilePath.GET_STARTED_VIEW
                ).load()),
                "Get Started"
        );
    }

    @Override
    public void stop() throws Exception {
        Util.getInstance().getWorkers().shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }
}