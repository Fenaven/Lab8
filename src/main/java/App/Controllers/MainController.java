package App.Controllers;

import App.*;
import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Classes.HumanBeingInformation;
import Commands.CommandEater;
import Commands.CommandPattern;
import Commands.myCommands.ClearCommand;
import Commands.myCommands.ExecuteScriptCommand;
import Commands.myCommands.RemoveByIdCommand;
import Database.Authentication;
import Languages.CurrentLanguage;
import Languages.Language;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import myUtilities.allForReaders.ReaderFromConsole;
import validators.Errors;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;


public class MainController implements Initializable, Localization {
    @FXML
    private TableView<HumanBeing> tableHumanBeingInfo;
    @FXML
    private TableColumn<HumanBeing, Long> idColumn;
    @FXML
    private TableColumn<HumanBeing, String> nameColumn;
    @FXML
    private Button leaveButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteByIdButton;
    @FXML
    private TableView<HumanBeingInformation> humanBeingFieldInformation;
    @FXML
    private TableColumn<HumanBeingInformation, String> nameField;
    @FXML
    private TableColumn<HumanBeingInformation, String> valueField;
    @FXML
    private TableView<HumanBeingInformation> humanBeingInformationEdit;
    @FXML
    private TableColumn<HumanBeingInformation, Control> editColumn;
    @FXML
    private TableColumn<HumanBeingInformation, String> valueFieldUpdate;
    @FXML
    private TableColumn<HumanBeingInformation, String> nameFieldUpdate;
    @FXML
    private Text errorTextTableField;
    @FXML
    private Button updateTableFieldButton;
    @FXML
    private Button deleteTableFieldButton;
    @FXML
    private Button closeTableFieldButton;
    @FXML
    private AnchorPane paneTableField;
    @FXML
    private Button executeScriptButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button mapButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button helpButton;
    @FXML private MenuButton currentLanguageMenu;

    @FXML private MenuItem ruLanguage;

    @FXML private MenuItem beLanguage;

    @FXML private MenuItem huLanguage;

    @FXML private MenuItem enLanguage;

    @FXML private Label languageLabel;

    @FXML private Label nickname;

  @FXML private Button changeCommandsButton;

    @FXML private Button infoButton;
    @FXML private Button submitToDeleteButton;
    @FXML private Label idToDeleteLabel;
    @FXML private Button submitToBackDeleteButton;
    @FXML private AnchorPane deleteByIdPane;
    @FXML private Text errorDeleteText;

@FXML private TextField deleteElementField;

    @FXML private Label searchLabel;

    @FXML private Label nameLabel;

    private static boolean doubleClickedOnField = false;

    private final Duration searchDelay = Duration.seconds(0.5);
    private javafx.animation.Timeline searchTimeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableHumanBeingInfo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        editColumn.setCellValueFactory(new PropertyValueFactory<>("updateField"));
        paneTableField.setVisible(false);
        nameField.setCellValueFactory(new PropertyValueFactory<>("nameField"));
        valueField.setCellValueFactory(new PropertyValueFactory<>("valueField"));
        nameFieldUpdate.setCellValueFactory(new PropertyValueFactory<>("nameField"));
        valueFieldUpdate.setCellValueFactory(new PropertyValueFactory<>("valueField"));
        nameField.setCellFactory(column -> new Column<>());
        valueField.setCellFactory(column -> new Column<>());
        nameFieldUpdate.setCellFactory(column -> new Column<>());
        valueFieldUpdate.setCellFactory(column -> new Column<>());
        humanBeingFieldInformation.setVisible(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellFactory(column -> new Column<>());
        editColumn.setCellFactory(column -> new Column<>());
        nameColumn.setCellFactory(column -> {
                    TableCell<HumanBeing, String> cell = new Column<>();
                    cell.setOnMouseEntered(new MouseEnteredHandler(cell, tableHumanBeingInfo, humanBeingFieldInformation,
                            humanBeingInformationEdit, paneTableField));
                    cell.setOnMouseClicked(new MouseClickedHandler(cell, tableHumanBeingInfo, humanBeingFieldInformation,
                            humanBeingInformationEdit, paneTableField, deleteTableFieldButton, errorTextTableField,
                            updateTableFieldButton, editColumn));
                    cell.setOnMouseExited(event -> {
                        if(!doubleClickedOnField){
                            humanBeingFieldInformation.setVisible(false);
                            paneTableField.setVisible(false);
                        }
                    });

                    return cell;
                }
        );

        nickname.setText(Authentication.getCurrentUser());

        errorTextTableField.setVisible(false);
        closeTableFieldButton.setOnAction(event -> {
            paneTableField.setVisible(false);
            doubleClickedOnField = false;
        });

        updateTable(HumanBeingCollection.getHumanBeings());

        addButton.setOnAction(event -> {
            Scene root = addButton.getScene();
            ModalSceneHandler handler = new ModalSceneHandler("/scenes/add/add.fxml", root);
            handler.handle(event);
            updateTable(HumanBeingCollection.getHumanBeings());
        });

        deleteByIdButton.setOnAction(event -> {
            errorDeleteText.setVisible(false);
            setCommandsButtonVisible(false);
            deleteByIdPane.setVisible(true);
            updateTable(HumanBeingCollection.getHumanBeings());

        });

        submitToDeleteButton.setOnAction(event -> {
            RemoveByIdCommand removeElement = new RemoveByIdCommand(new ReaderFromConsole());
            String id = deleteElementField.getText();
            Errors error = removeElement.isExecute(id);
            if(error == Errors.NOTHAVEERRORS){
                deleteByIdPane.setVisible(false);
                setCommandsButtonVisible(true);
                tableHumanBeingInfo.setItems(FXCollections.observableArrayList(
                        HumanBeingCollection.getHumanBeings()
                ));
                tableHumanBeingInfo.refresh();
            }else {
                errorDeleteText.setText(error.getError());
                errorDeleteText.setVisible(true);
            }
        });

        submitToBackDeleteButton.setOnAction(event -> {
            deleteByIdPane.setVisible(false);
            setCommandsButtonVisible(true);
        });

        leaveButton.setOnAction(event -> {
            doubleClickedOnField = false;;
            ChangeSceneHandler changeSceneHandler = new ChangeSceneHandler("/scenes/login/login.fxml");
            changeSceneHandler.handle(event);
        });

        executeScriptButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(CurrentLanguage.getCurrentLanguage().getString("chooseScript"));
            CommandPattern executeScript = new ExecuteScriptCommand();
            CommandEater.setSplit(new String[]{"", fileChooser.showOpenDialog(tableHumanBeingInfo.getScene().getWindow()).getAbsolutePath()});
            executeScript.execute();
            updateTable(HumanBeingCollection.getHumanBeings());
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (searchTimeline != null) {
                searchTimeline.stop();
            }

            // Запускаем таймлайн с задержкой
            searchTimeline = new javafx.animation.Timeline();
            searchTimeline.getKeyFrames().add(
                    new javafx.animation.KeyFrame(searchDelay, event -> performSearch(newValue))
            );
            searchTimeline.play();
        });

        mapButton.setOnAction(new ChangeSceneHandler("/scenes/map/map.fxml"));

        clearButton.setOnAction(event -> {

                CommandPattern clearCommand = new ClearCommand();
                CommandEater.setSplit(new String[]{"clear"});
                clearCommand.execute();
                updateTable(HumanBeingCollection.getHumanBeings());

        });

        helpButton.setOnAction(event -> {
            ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
            showAlert("infoAboutCommands",
                    "infoForCommands");
        });

        changeCommandsButton.setOnAction(event -> {
            Button[] commandButtons = {helpButton, infoButton, clearButton, addButton, mapButton,
            deleteByIdButton, executeScriptButton};
            for(Button commandButton : commandButtons){
                commandButton.setVisible(!commandButton.isVisible());
            }

        });

        infoButton.setOnAction(event -> {
            showAlert("infoAboutCollection",
                    "info");
        });

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
        setLanguage();
    }

    private void setCommandsButtonVisible(boolean isVisible){
        Button[] commandButtons = {addButton, mapButton, deleteByIdButton, executeScriptButton, changeCommandsButton};
        for (Button commandButton : commandButtons){
            commandButton.setVisible(isVisible);
        }
    }

@Override
    public void setLanguage(){
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        currentLanguageMenu.setText(currentLanguage.getString(CurrentLanguage.getCurrentLanguageString()));
        ruLanguage.setText(currentLanguage.getString("ru"));
        beLanguage.setText(currentLanguage.getString("be"));
        huLanguage.setText(currentLanguage.getString("hu"));
        enLanguage.setText(currentLanguage.getString("en"));
        languageLabel.setText(currentLanguage.getString("language"));
        mapButton.setText(currentLanguage.getString("mapButton"));
        deleteByIdButton.setText(currentLanguage.getString("deleteByIdButton"));
        executeScriptButton.setText(currentLanguage.getString("executeScriptButton"));
        addButton.setText(currentLanguage.getString("addButton"));
        leaveButton.setText(currentLanguage.getString("leaveButton"));
        changeCommandsButton.setText(currentLanguage.getString("commands"));
        clearButton.setText(currentLanguage.getString("clearButton"));
        infoButton.setText(currentLanguage.getString("showInfoButton"));
        helpButton.setText(currentLanguage.getString("helpButton"));
        searchLabel.setText(currentLanguage.getString("searchLabel"));
        searchField.setPromptText(currentLanguage.getString("searchField"));
        updateTableFieldButton.setText(currentLanguage.getString("updateTableFieldButton"));
        deleteTableFieldButton.setText(currentLanguage.getString("deleteTableFieldButton"));
        closeTableFieldButton.setText(currentLanguage.getString("closeTableFieldButton"));
        nameLabel.setText(currentLanguage.getString("name"));
       idToDeleteLabel.setText(currentLanguage.getString("idToDeleteLabel"));
    }


    private void performSearch(String searchTerm) {
        // Очищаем предыдущий результат поиска
        tableHumanBeingInfo.getItems().clear();

        // Выполняем поиск и добавляем найденные элементы в таблицу
        for (HumanBeing humanBeing : HumanBeingCollection.getHumanBeings()) {
            if (String.valueOf(humanBeing.getId()).contains(searchTerm) || humanBeing.getName().contains(searchTerm)) {
                tableHumanBeingInfo.getItems().add(humanBeing);
            }
        }
    }

    public static boolean isDoubleClickedOnField() {
        return doubleClickedOnField;
    }

    public static void setDoubleClickedOnField(boolean doubleClickedOnField) {
        MainController.doubleClickedOnField = doubleClickedOnField;
    }
    public void updateTable(Collection<HumanBeing> data){
        System.out.println("data: " + data);
        tableHumanBeingInfo.setItems(FXCollections.observableArrayList(
                data
        ));
        tableHumanBeingInfo.refresh();
    }

    private void showAlert(String title, String result){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(CurrentLanguage.getCurrentLanguage().getString(title));
        alert.setHeaderText(null);
        alert.setContentText(CurrentLanguage.getCurrentLanguage().getString(result));
        ButtonType okButton = new ButtonType(CurrentLanguage.getCurrentLanguage().getString("ok"));
        alert.setResizable(true);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

}
