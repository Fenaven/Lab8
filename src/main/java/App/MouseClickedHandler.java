package App;

import App.Controllers.MainController;
import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Classes.HumanBeingInformation;
import Commands.myCommands.RemoveByIdCommand;
import Commands.myCommands.Update;
import Database.Authentication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import myUtilities.allForReaders.ReaderFromConsole;
import validators.Errors;

public class MouseClickedHandler  implements EventHandler<MouseEvent> {
    private final TableCell<HumanBeing, String> cell;
    private final TableView<HumanBeing> tableView;
    private final TableView<HumanBeingInformation> humanBeingFieldInformation;
    private final TableView<HumanBeingInformation> humanBeingInformationEdit;
    private final AnchorPane paneTableField;
    private final Button deleteButton;
    private final Text errorText;
    private final Button updateTableFieldButton;
    private final TableColumn<HumanBeingInformation, Control> editColumn;

    public MouseClickedHandler(TableCell<HumanBeing, String> cell, TableView<HumanBeing> tableView,
                               TableView<HumanBeingInformation> humanBeingFieldInformation,
                               TableView<HumanBeingInformation> humanBeingInformationEdit, AnchorPane paneTableField,
                               Button deleteButton, Text errorText, Button updateTableFieldButton,
                               TableColumn<HumanBeingInformation, Control> editColumn) {
        this.cell = cell;
        this.tableView = tableView;
        this.humanBeingFieldInformation = humanBeingFieldInformation;
        this.humanBeingInformationEdit = humanBeingInformationEdit;
        this.paneTableField = paneTableField;
        this.deleteButton = deleteButton;
        this.errorText = errorText;
        this.updateTableFieldButton = updateTableFieldButton;
        this.editColumn = editColumn;
    }
    @Override
    public void handle(MouseEvent event) {
        errorText.setVisible(false);
        MainController.setDoubleClickedOnField(true);
        final int index = cell.getTableRow().getIndex();
        ObservableList<HumanBeing> humanBeings = tableView.getItems();
        if (index < humanBeings.size()) {
            humanBeingFieldInformation.setVisible(false);
            paneTableField.setVisible(false);
            HumanBeing humanBeing = humanBeings.get(index);
            TableFields tableFields = new TableFields(humanBeing);
            ObservableList<HumanBeingInformation> dataField = tableFields.getTableFields();
            if (humanBeing.getUser().equals(Authentication.getCurrentUser())) {
                humanBeingInformationEdit.setItems(dataField);
                humanBeingInformationEdit.refresh();
                paneTableField.setVisible(true);
                deleteButton.setOnAction(actionEvent -> {
                    RemoveByIdCommand removeElement = new RemoveByIdCommand(new ReaderFromConsole());
                    Errors error = removeElement.isExecute(String.valueOf(humanBeing.getId()));
                    if(error == Errors.NOTHAVEERRORS){
                        MainController.setDoubleClickedOnField(false);
                        paneTableField.setVisible(false);
                        tableView.setItems(FXCollections.observableArrayList(
                                HumanBeingCollection.getHumanBeings()
                        ));
                        tableView.refresh();
                    }else {
                        errorText.setText(error.getError());
                        errorText.setVisible(true);
                    }
                });
                updateTableFieldButton.setOnAction(e -> {
                   ObservableList<HumanBeingInformation> itemsMainTable = humanBeingInformationEdit.getItems();
                    boolean isUpdated = true;
                    Update update = new Update(humanBeing);
                    for(int i = 0; i < itemsMainTable.size(); i++){
                        Control cell = editColumn.getCellObservableValue(i).getValue();
                        if(cell instanceof TextField text){
                            String textValue = text.getText();
                            if(!textValue.isEmpty()){
                                Errors error = update.updateHuman(i, textValue);
                                if(error != Errors.NOTHAVEERRORS){
                                    errorText.setText(error.getError());
                                    errorText.setVisible(true);
                                    isUpdated = false;
                                    break;
                                }
                            }
                        } else if (cell instanceof ChoiceBox) {
                            ChoiceBox<String> choiceBox = (ChoiceBox<String>) cell;
                            String value = choiceBox.getValue();
                            if(!value.equals("null")){
                                Errors error = update.updateHuman(i, value.toLowerCase());
                                if(error != Errors.NOTHAVEERRORS){
                                    errorText.setText(error.getError());
                                    errorText.setVisible(true);
                                    isUpdated = false;
                                    break;
                                }

                            }

                        }
                    }
                    if(isUpdated){
                        System.out.println("Im updating");
                        update.updateCollection();
                        tableView.setItems(FXCollections.observableArrayList(
                                HumanBeingCollection.getHumanBeings()
                        ));
                        tableView.refresh();
                        paneTableField.setVisible(false);
                        MainController.setDoubleClickedOnField(false);
                    }
                });
            } else {
                humanBeingFieldInformation.setItems(dataField);
                humanBeingFieldInformation.refresh();
                humanBeingFieldInformation.setVisible(true);
            }
        }
    }

}
