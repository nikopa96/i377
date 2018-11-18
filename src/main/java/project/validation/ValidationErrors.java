package project.validation;

import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidationErrors {

    @Getter
    private List<ValidationError> errors = new ArrayList<>();

    public ValidationErrors addError(FieldError fieldError) {
        List<String> args = Stream.of(Objects.requireNonNull(fieldError.getArguments()))
                .filter(arg -> !(arg instanceof DefaultMessageSourceResolvable))
                .map(String::valueOf).collect(Collectors.toList());

        errors.add(new ValidationError(Objects.requireNonNull(fieldError.getCodes())[0], args));

        return this;
    }
}
