package validators.commands;

import validators.Errors;
import validators.Validator;

/**
 * The type Argument validator.
 */
public class ArgumentValidator extends Validator {

    /**
     * The Argument.
     */
    protected String argument;

    /**
     * Instantiates a new Argument validator.
     *
     * @param argument the argument
     */
    public ArgumentValidator(String argument){
        this.argument = argument;
    }

    /**
     * Is not has argument boolean.
     *
     * @return the boolean
     */
    protected boolean isNotHasArgument(){
        return argument.equals("");
    }

    @Override
    protected void addAllErrors() {
        addError(this::isNotHasArgument, Errors.NOTHASARGUMENT);
    }
}
