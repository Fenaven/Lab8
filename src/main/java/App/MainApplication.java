package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApplication extends Application {

    private static boolean animate = false;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scenes/login/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("HumanBeingApplication");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    }

    public static void open() {
        launch();
    }

    public static boolean isAnimate() {
        return animate;
    }

    public static void setAnimate(boolean animate) {
        MainApplication.animate = animate;
    }
}