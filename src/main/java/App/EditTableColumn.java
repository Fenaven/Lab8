package App;

import javafx.scene.Node;
import javafx.scene.control.TableCell;

public class EditTableColumn<S, Control> extends TableCell<S, Control> {


    @Override
    protected void updateItem(Control item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setText(null);
            setStyle("-fx-background-color:  #210B2B;");
        } else {
            setGraphic((Node) item);
            int rowIndex = getIndex();
            if (rowIndex % 2 == 0) {
                setStyle("-fx-background-color: #4F2662; -fx-alignment: CENTER; -fx-text-fill: white;"); // установка первого цвета
            } else {
                setStyle("-fx-background-color: #210B2B; -fx-alignment: CENTER; -fx-text-fill: white;"); // установка второго цвета
            }
        }
    }
}

