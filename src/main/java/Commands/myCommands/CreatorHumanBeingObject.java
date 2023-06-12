package Commands.myCommands;

import Classes.HumanBeing;
import validators.Errors;
import validators.Validator;
import validators.fields.CoordinatesValidator;
import validators.fields.ImpactSpeedValidator;
import validators.fields.NameValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The type Creator human being object.
 */
public class CreatorHumanBeingObject {


    private Errors error;




    public HumanBeing create(String... fields){
        error = validate(fields[0], fields[1], fields[2]);
        HumanBeing humanBeing = new HumanBeing();
        List<Predicate<String>> notNullSetters = new ArrayList<>(humanBeing.getNotNullSetters().values());
        List<Consumer<String>> setters = new ArrayList<>(humanBeing.getSetters().values());
        if(fields.length != (notNullSetters.size() + setters.size()))
            throw new RuntimeException("ERROR! Setters have a different number of fields than the one passed on");
        for(int i = 0; i < notNullSetters.size(); i ++){
            notNullSetters.get(i).test(fields[i]);
        }
        for(int i = 0; i < setters.size(); i++){
           setters.get(i).accept(fields[notNullSetters.size()+i]);
        }
        return humanBeing;
    }

    private Errors validate(String name, String coordinates, String impactSpeed) {
        List<Validator> validators = new ArrayList<>();
        validators.add(new NameValidator(name));
        validators.add(new CoordinatesValidator(coordinates.split(",")));
        validators.add(new ImpactSpeedValidator(impactSpeed));
        Errors error = Errors.NOTHAVEERRORS;
        for (Validator validator : validators) {
            error = validator.validateAll();
            if (error != Errors.NOTHAVEERRORS) return error;
        }
        return error;

    }

    public Errors getError() {
        return error;
    }
}
