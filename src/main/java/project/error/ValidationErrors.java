package project.error;

import java.util.List;

/**
 * Copyright!!!
 *
 * Author: Mart Kälmo
 * Source: https://bitbucket.org/mkalmo/hwtests/src/master/src/main/java/tests/model/ValidationErrors.java
 */
public class ValidationErrors {

    private List<ValidationError> errors;

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}
