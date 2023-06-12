package validators.commands;

import validators.Errors;
import validators.Validator;

/**
 * The type Impact speed validator.
 */
public class ImpactSpeedValidator extends Validator {

    /**
     * The Argument.
     */
    protected String argument;

    /**
     * Instantiates a new Impact speed validator.
     *
     * @param argument the argument
     */
    public ImpactSpeedValidator(String argument){
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

    /**
     * Is not can transform to int boolean.
     *
     * @return the boolean
     */
    protected boolean isNotCanTransformToInt(){
        try{
            Integer.parseInt(argument);
        }catch (NumberFormatException ex){
            return true;
        }
        return false;
    }

    @Override
    protected void addAllErrors(){
        addError(this::isNotHasArgument, Errors.NOTHASARGUMENT);
        addError(this::isNotCanTransformToInt, Errors.NOTCANTRANSFORMTOINT);
    }
}

