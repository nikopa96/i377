package project.error;

import lombok.Getter;

public class ValidationError {

    @Getter
    private String code;

    public ValidationError(String code) {
        this.code = code;
    }
}
