package App.Controllers;

import App.Localization;
import Languages.CurrentLanguage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, Localization {

    @FXML private AnchorPane leftPane;
    @FXML private AnchorPane rightPane;
    @FXML private TextField loginField;
    @FXML private Label loginLabel;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button toRegisterButton;
    @FXML private AnchorPane introPane;

    private final int SCREEN_WIDTH = 1500;

    private static final double MOVEMENT_SPEED = 2.0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLanguage();
        introPane.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                animatePanes(leftPane, rightPane);
                System.out.println("Action fixed");
                introPane.setDisable(true);
            }
        });

    }
    private void animatePanes(AnchorPane leftPane, AnchorPane rightPane) {
        Thread animationThread = new Thread(() -> {
            try {
                while (leftPane.getLayoutX() > -leftPane.getWidth() && rightPane.getLayoutX() < SCREEN_WIDTH) {

                    leftPane.setLayoutX(leftPane.getLayoutX() - MOVEMENT_SPEED);
                    rightPane.setLayoutX(rightPane.getLayoutX() + MOVEMENT_SPEED);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        animationThread.start();

    }
@Override
    public void setLanguage(){
        ResourceBundle currentlanguage = CurrentLanguage.getCurrentLanguage();
        loginLabel.setText(currentlanguage.getString("loginLabel"));
        loginButton.setText(currentlanguage.getString("loginButton"));
        loginField.setPromptText(currentlanguage.getString("loginField"));
        passwordField.setPromptText(currentlanguage.getString("passwordField"));
        toRegisterButton.setText(currentlanguage.getString("toRegistrationButton"));
    }


}
