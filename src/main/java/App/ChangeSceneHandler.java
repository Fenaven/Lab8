package App;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeSceneHandler implements EventHandler<ActionEvent> {

    private final String pathScene;

    public ChangeSceneHandler(String scene){
        this.pathScene = scene;
    }

    @Override
    public void handle(ActionEvent event) {
        Parent menuParent = null;
        try {
            menuParent = FXMLLoader.load(getClass().getResource(pathScene));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene menuScene = new Scene(menuParent);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }
}
