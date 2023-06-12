package Commands.myCommands;


import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Database.Connection;
import validators.Errors;
import validators.Validator;
import validators.fields.CoordinatesValidator;
import validators.fields.ImpactSpeedValidator;
import validators.fields.NameValidator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class InsertHumanBeing {

    public Errors execute(String name, String coordinates, String impactSpeed, String realHero, String hasToothPick,
                          String weaponType, String mood, String carCool) {
        Errors error = validate(name, coordinates, impactSpeed);
        if (error == Errors.NOTHAVEERRORS) {
            CreatorHumanBeingObject creatorHumanBeingObject = new CreatorHumanBeingObject();
            HumanBeing humanBeing = creatorHumanBeingObject.create(name, coordinates, impactSpeed, realHero, hasToothPick,
                    weaponType, mood, carCool);
            ResultSet resultSet = Connection.executePreparedStatement("SELECT nextval ('id')");

            try {
                resultSet.next();
                humanBeing.setId(resultSet.getLong(1));
                HumanBeingCollection.addToDB(humanBeing);
                HumanBeingCollection.getHumanBeings().add(humanBeing);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return error;
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

}
