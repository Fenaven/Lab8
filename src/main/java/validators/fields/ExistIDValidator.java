package validators.fields;

import Classes.HumanBeingCollection;
import validators.Errors;
import validators.Validator;

/**
 * The type Exist uuid validator.
 */
public class ExistIDValidator extends Validator {

    private final String id;

    /**
     * Instantiates a new Exist uuid validator.
     *
     * @param id the id
     */
    public ExistIDValidator(String id) {
        this.id = id;
    }

    private boolean isEmpty(){
        return id.equals("");
    }

    /**
     * Is not can transform to uuid boolean.
     *
     * @return the boolean
     */
    protected boolean isNotCanTransformToUUID(){
        String regex = "^-?\\d+$";
        return !id.matches(regex);
    }

    private boolean isNotExistElement(){
        return HumanBeingCollection.getHumanBeings().stream().anyMatch(humanBeing -> humanBeing.getId() != Long.parseLong(id));
    }

    @Override
    public void addAllErrors(){
        addError(this::isEmpty, Errors.EMPTYFIELD);
        addError(this::isNotCanTransformToUUID, Errors.NOTCANTRANSFORMTOINT);
        addError(this::isNotExistElement, Errors.NOTHASELEMENT);
    }

}
