package validators.fields;

import validators.Errors;
import validators.Validator;

/**
 * The type Impact speed validator.
 */
public class ImpactSpeedValidator extends Validator {

    final private String impactSpeed;

    /**
     * Instantiates a new Impact speed validator.
     *
     * @param impactSpeed the impact speed
     */
    public ImpactSpeedValidator(String impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    private boolean isNotCanTransformImpactSpeed(){
        try{
            Integer.parseInt(impactSpeed);
        }catch (NumberFormatException e){
            return true;
        }
        return false;
    }


    @Override
    public void addAllErrors(){
        addError(this::isNotCanTransformImpactSpeed, Errors.NOTCANTRANSFORMTOINT);
    }


}
