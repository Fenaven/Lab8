package validators.commands;

import Classes.HumanBeingCollection;
import validators.Errors;

/**
 * The type Remove element validator.
 */
public class RemoveElementValidator extends RemoveGreaterKeyValidator {

    /**
     * Instantiates a new Remove element validator.
     *
     * @param argument the argument
     */
    public RemoveElementValidator(String argument) { super(argument); }

    /**
     * Is not has element boolean.
     *
     * @return the boolean
     */
    protected boolean isNotHasElement(){
        int id = Integer.parseInt(argument);
        return HumanBeingCollection.getHumanBeings().stream().allMatch(humanBeing -> humanBeing.getId() != id);
    }

    @Override
    protected void addAllErrors() {
        addError(this::isNotHasArgument, Errors.NOTHASARGUMENT);
        addError(this::isNotCanTransformToID, Errors.NOTCANTRANSFORMTOUUID);
        addError(this::isNotHasElement, Errors.NOTHASELEMENT);
    }
}
