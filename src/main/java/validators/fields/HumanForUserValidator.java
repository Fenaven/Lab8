package validators.fields;

import Classes.HumanBeing;
import Database.Authentication;
import validators.Errors;
import validators.Validator;

/**
 * The type Human for user validator.
 */
public class HumanForUserValidator extends Validator {

    private HumanBeing human;

    /**
     * Instantiates a new Human for user validator.
     *
     * @param human the human
     */
    public HumanForUserValidator(HumanBeing human) {
        this.human = human;
    }

    private boolean NotCreateThisUser(){
        return !human.getUser().equals(Authentication.getCurrentUser());
    }

    protected void addAllErrors(){
        addError(this::NotCreateThisUser, Errors.NOTCREATETHISUSER);
    }
}
