package br.org.viniciusribeirogtk.api.customvalidations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastValidator.class)
@Documented
public @interface Past {

    String message() default "{javax.validation.constraints.Past.message}"; //Default javax Pasr message

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}