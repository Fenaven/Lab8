package App;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Pane;

public class Column<S, T> extends TableCell<S, T> {

    private final Pane hoverPane = new Pane();

    public Column(){
        hoverPane.setStyle("-fx-background-color: black;");
        hoverPane.setOpacity(0);

        setOnMouseEntered(event -> hoverPane.setOpacity(0.2));
        setOnMouseExited(event -> hoverPane.setOpacity(0));
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setText(null);
            setStyle("-fx-background-color:  #00046E;");
        } else {
            if (item instanceof Control) setGraphic((Node) item);
            else setText(item.toString());
            int rowIndex = getIndex();
            if (rowIndex % 2 == 0) {
                setStyle("-fx-background-color: #00D1FF; -fx-alignment: CENTER; -fx-text-fill: white;"); // установка первого цвета
            } else {
                setStyle("-fx-background-color: #00046E; -fx-alignment: CENTER; -fx-text-fill: white;"); // установка второго цвета
            }
        }
    }
}