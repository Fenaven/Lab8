package validators.commands;

import validators.Errors;
import validators.Validator;

/**
 * The type Remove greater key validator.
 */
public class RemoveGreaterKeyValidator extends Validator {

    /**
     * The Argument.
     */
    protected String argument;

    /**
     * Instantiates a new Remove greater key validator.
     *
     * @param argument the argument
     */
    public RemoveGreaterKeyValidator(String argument) { this.argument = argument; }

    /**
     * Is not has argument boolean.
     *
     * @return the boolean
     */
    protected boolean isNotHasArgument(){
        return argument.equals("");
    }

    /**
     * Is not can transform to uuid boolean.
     *
     * @return the boolean
     */
    protected boolean isNotCanTransformToID(){
        String regex = "^-?\\d+$";
        return !argument.matches(regex);
    }

    @Override
    protected void addAllErrors() {
        addError(this::isNotHasArgument, Errors.NOTHASARGUMENT);
        addError(this::isNotCanTransformToID, Errors.NOTCANTRANSFORMTOUUID);
    }
}
