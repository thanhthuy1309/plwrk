package jp.gmo.net.custom.validator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CustomDateValidator.class)
public @interface ValidateCustomDate {

    String message() default CustomDateValidator.message;
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}