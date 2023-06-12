package validators.fields;

import validators.Errors;
import validators.Validator;

/**
 * The type Name validator.
 */
public class NameValidator extends Validator {

    final private String name;

    /**
     * Instantiates a new Name validator.
     *
     * @param name the name
     */
    public NameValidator(String name) {
        this.name = name;
    }

    private boolean isEmpty(){
        return name.trim().equals("");
    }

    private boolean isHaveSpace(){ return name.contains(" ");}

    @Override
    public void addAllErrors(){
        addError(this::isEmpty, Errors.EMPTYFIELD);
        addError(this::isHaveSpace, Errors.ISHAVESPACE);
    }
}
