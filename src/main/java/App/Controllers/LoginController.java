package App.Controllers;

import App.ChangeSceneHandler;
import App.Localization;
import App.MainApplication;
import Database.Authentication;
import Languages.CurrentLanguage;
import Languages.Language;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, Localization {

    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private TextField loginField;
    @FXML
    private Label loginLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button toRegisterButton;
    @FXML
    private AnchorPane introPane;

    @FXML
    private Label humanLabel1;
    @FXML
    private Label humanLabel2;
    @FXML
    private Label humanLabel3;
    @FXML
    private Label humanLabel4;
    @FXML
    private Label humanLabel5;
    @FXML
    private Label humanLabel6;
    @FXML private Label humanLabel7;
    @FXML private Label humanLabel8;
    @FXML private Label humanLabel9;
    @FXML private Label humanLabel10;
    @FXML private Label humanLabel11;
    @FXML private Label humanLabel12;
    @FXML private Label beingLabel1;
    @FXML private Label beingLabel2;
    @FXML private Label beingLabel3;
    @FXML private Label beingLabel4;
    @FXML private Label beingLabel5;
    @FXML private Label beingLabel6;
    @FXML private Label beingLabel7;
    @FXML private Label beingLabel8;
    @FXML private Label beingLabel9;
    @FXML private Label beingLabel10;
    @FXML private Label beingLabel11;
    @FXML private Label beingLabel12;
    @FXML private Label beingLabelCenter;

    @FXML
    private Text errorText;

    @FXML
    private Label humanLabelCenter;

    @FXML
    private MenuItem ruLanguage;

    @FXML
    private MenuItem beLanguage;

    @FXML
    private MenuItem huLanguage;
    @FXML
    private MenuItem enLanguage;
    @FXML private Label languageLabel;
    @FXML private MenuButton currentLanguageMenu;

    private final int SCREEN_WIDTH = 1080;

    private final int SCREEN_HEIGHT = 648;

    private static final double MOVEMENT_SPEED = 2.0;

    private static volatile boolean isRunning = true;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isRunning=true;
        errorText.setVisible(false);
        Label[] humanLabels = {humanLabel1, humanLabel2, humanLabel3, humanLabel4, humanLabel5, humanLabel6, humanLabelCenter,
                humanLabel7, humanLabel8, humanLabel9, humanLabel10, humanLabel11, humanLabel12};
        Label[] beingLabels = {beingLabel1, beingLabel2, beingLabel3, beingLabel4, beingLabel5, beingLabel6, beingLabelCenter,
                beingLabel7, beingLabel8, beingLabel9, beingLabel10, beingLabel11, beingLabel12};
        setLanguage();
        if(MainApplication.isAnimate()) introPane.setVisible(false);
        introPane.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                MainApplication.setAnimate(true);
                animatePanes(leftPane, rightPane);
                introPane.setDisable(true);
            }
        });

        animatesLabels(humanLabels, humanLabelCenter);
        animatesLabels(beingLabels, beingLabelCenter);
        Platform.runLater(() -> {
            Stage stage = (Stage) introPane.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                stopAnimationThread();
                Platform.exit();
                System.exit(0);
            });
        });

        errorText.setVisible(false);
        loginButton.setOnAction(event -> {
            String login = loginField.getText();
            String password = passwordField.getText();
            boolean isLogin = Authentication.isLogin(login, password);
            if(isLogin){
                ChangeSceneHandler changeSceneHandler = new ChangeSceneHandler("/scenes/main/main.fxml");
                changeSceneHandler.handle(event);
                stopAnimationThread();
            }
            else {
                errorText.setVisible(true);
            }
        });

        toRegisterButton.setOnAction(new ChangeSceneHandler("/scenes/registration/registration.fxml"));
        ruLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Language.ru);
            CurrentLanguage.setCurrentLanguageString("ru");
            setLanguage();
        });
        beLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Language.be);
            CurrentLanguage.setCurrentLanguageString("be");
            setLanguage();
        });
        huLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Language.hu);
            CurrentLanguage.setCurrentLanguageString("hu");
            setLanguage();
        });
        enLanguage.setOnAction(event -> {
            CurrentLanguage.setCurrentLanguage(Language.en);
            CurrentLanguage.setCurrentLanguageString("en");

            setLanguage();
        });
    }



    private void animatesLabels(Label[] labels, Label frontier) {
        Thread animationThread = new Thread(() -> {
            try {


                while (isRunning) {

                    animateLabelsDown(labels, frontier);
                    animateLabelsUp(labels, frontier);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        animationThread.start();
    }

    private void animateLabelsDown(Label[] labels, Label frontier) throws InterruptedException {

        while (frontier.getLayoutY() < SCREEN_HEIGHT - frontier.getHeight()) {
            Platform.runLater(() -> {
                for (Label label : labels) {
                    label.setLayoutY(label.getLayoutY() + MOVEMENT_SPEED);
                }
            });
            Thread.sleep(10);
        }

    }

    private void animateLabelsUp(Label[] labels, Label frontier) throws InterruptedException {

        while (frontier.getLayoutY() > 0) {
            Platform.runLater(() -> {
                for (Label label : labels) {
                    label.setLayoutY(label.getLayoutY() - MOVEMENT_SPEED);
                }
            });
            Thread.sleep(10);
        }

    }

    private void animatePanes(AnchorPane leftPane, AnchorPane rightPane) {
        Thread animationThread = new Thread(() -> {
            try {
                while (leftPane.getLayoutX() > -leftPane.getWidth() || rightPane.getLayoutX() < SCREEN_WIDTH) {
                    Platform.runLater(() -> {
                        leftPane.setLayoutX(leftPane.getLayoutX() - MOVEMENT_SPEED);
                        rightPane.setLayoutX(rightPane.getLayoutX() + MOVEMENT_SPEED);
                    });
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        animationThread.start();

    }

    @Override
    public void setLanguage() {
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        currentLanguageMenu.setText(currentLanguage.getString(CurrentLanguage.getCurrentLanguageString()));
        ruLanguage.setText(currentLanguage.getString("ru"));
        beLanguage.setText(currentLanguage.getString("be"));
        huLanguage.setText(currentLanguage.getString("hu"));
        enLanguage.setText(currentLanguage.getString("en"));
        languageLabel.setText(currentLanguage.getString("language"));
        loginLabel.setText(currentLanguage.getString("loginLabel"));
        loginButton.setText(currentLanguage.getString("loginButton"));
        loginField.setPromptText(currentLanguage.getString("loginField"));
        passwordField.setPromptText(currentLanguage.getString("passwordField"));
        toRegisterButton.setText(currentLanguage.getString("toRegistrationButton"));
        errorText.setText(currentLanguage.getString("errorTextLogin"));
    }
    private void stopAnimationThread() {
        isRunning = false;
    }
}