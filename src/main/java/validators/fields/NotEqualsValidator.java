package validators.fields;

import validators.Errors;
import validators.Validator;

import java.util.Arrays;

/**
 * The type Not equals validator.
 */
public class NotEqualsValidator extends Validator {

    private String[] needEquals;
    private String request;

    /**
     * Instantiates a new Not equals validator.
     *
     * @param request    the request
     * @param needEquals the need equals
     */
    public NotEqualsValidator(String request, String... needEquals){
        this.request = request;
        this.needEquals = needEquals;
    }

    private boolean isNotEquals(){
        return !Arrays.stream(needEquals).anyMatch(str -> str.equals(request));
    }

    public void addAllErrors(){
        addError(this::isNotEquals, Errors.ISNOTEXISTENTOPTION);
    }

}
