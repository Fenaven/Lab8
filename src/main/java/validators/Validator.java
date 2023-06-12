package validators;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The type Validator.
 */
abstract public class Validator {
    private final Map<IsValidating, Errors> validatesMethods = new LinkedHashMap<>();

    /**
     * Add all errors.
     */
    abstract protected void addAllErrors();

    /**
     * Add error.
     *
     * @param method the method
     * @param error  the error
     */
    protected void addError(IsValidating method, Errors error){
        validatesMethods.put(method, error);
    }

    /**
     * Validate all errors.
     *
     * @return the errors
     */
    public Errors validateAll(){
        addAllErrors();
        for(IsValidating method : validatesMethods.keySet()){
            if(method.isNotValidate()){
                return validatesMethods.get(method);
            }
        }
        return Errors.NOTHAVEERRORS;
    }


    /**
     * Is valid boolean.
     *
     * @return the boolean
     */
    public boolean isValid(){
        Errors error = validateAll();
        if(error != Errors.NOTHAVEERRORS){
            System.out.println(error);
            return false;
        }
        return true;
    }

    /**
     * Validate with exit.
     */
    public void validateWithExit(){
        boolean validation = isValid();
        if(!validation)System.exit(130);
    }


}
