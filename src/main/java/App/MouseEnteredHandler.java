package App;

import App.Controllers.MainController;
import Classes.HumanBeing;
import Classes.HumanBeingInformation;
import Database.Authentication;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MouseEnteredHandler implements EventHandler<MouseEvent> {
    private final TableCell<HumanBeing, String> cell;
    private final TableView<HumanBeing> tableView;
    private final TableView<HumanBeingInformation> humanBeingFieldInformation;
    private final TableView<HumanBeingInformation> humanBeingInformationEdit;
    private final AnchorPane paneTableField;

    public MouseEnteredHandler(TableCell<HumanBeing, String> cell, TableView<HumanBeing> tableView,
                               TableView<HumanBeingInformation> humanBeingFieldInformation,
                               TableView<HumanBeingInformation> humanBeingInformationEdit, AnchorPane paneTableField) {
        this.cell = cell;
        this.tableView = tableView;
        this.humanBeingFieldInformation = humanBeingFieldInformation;
        this.humanBeingInformationEdit = humanBeingInformationEdit;
        this.paneTableField = paneTableField;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!MainController.isDoubleClickedOnField()) {
            final int index = cell.getTableRow().getIndex();
            ObservableList<HumanBeing> humanBeings = tableView.getItems();
            if (index < humanBeings.size()) {
                HumanBeing humanBeing = humanBeings.get(index);
                TableFields tableFields = new TableFields(humanBeing);
                ObservableList<HumanBeingInformation> dataField = tableFields.getTableFields();
                if (humanBeing.getUser().equals(Authentication.getCurrentUser())) {
                    humanBeingInformationEdit.setItems(dataField);
                    humanBeingInformationEdit.refresh();
                    paneTableField.setVisible(true);
                } else {
                    humanBeingFieldInformation.setItems(dataField);
                    humanBeingFieldInformation.refresh();
                    humanBeingFieldInformation.setVisible(true);
                }
            }
        }
    }
}

