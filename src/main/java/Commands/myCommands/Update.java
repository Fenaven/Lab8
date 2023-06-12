package Commands.myCommands;

import Classes.Fields;
import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Database.Connection;
import myUtilities.allForReaders.ReaderFromConsole;
import validators.Errors;
import validators.fields.CoordinatesValidator;
import validators.fields.ImpactSpeedValidator;
import validators.fields.NameValidator;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Update {

    private final HumanBeing human;

    public Update(HumanBeing human) {
        this.human = human;
    }

    public HumanBeing getHuman() {
        return human;
    }

    public Errors updateHuman(int idField, String valueField){
        Map<Fields, Predicate<String>> notNullSetters = human.getNotNullSetters();
        Errors error = Errors.NOTHAVEERRORS;
        if(idField == 0) {
            error = new NameValidator(valueField).validateAll();
        } else if (idField == 1) {
            error = new CoordinatesValidator(valueField.split(",")).validateAll();
        } else if (idField == 2) {
            error = new ImpactSpeedValidator(valueField).validateAll();
        }
        Fields field = Fields.getForOrder(idField+1);
        if(error == Errors.NOTHAVEERRORS) {
            Predicate<String> notNullSetter = notNullSetters.get(field);
            System.out.println(valueField);
            if(notNullSetter == null) updateNullSetter(human, field, valueField);
            else notNullSetter.test(valueField);

        }
    return error;
    }

    public void updateCollection(){
        RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(new ReaderFromConsole());
        Errors errorsCommand = removeByIdCommand.isExecute(String.valueOf(human.getId()));
        if (errorsCommand == Errors.NOTHAVEERRORS) {
            HumanBeingCollection.add(human);
            HumanBeingCollection.addToDB(human);
        }
    }

    public void updateNullSetter(HumanBeing human, Fields field, String valueField){
        Map<Fields, Consumer<String>> setters = human.getSetters();
        setters.get(field).accept(valueField);
    }
}
