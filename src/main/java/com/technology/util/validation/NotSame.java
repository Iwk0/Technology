package com.technology.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-3-11
 * Time: 9:51
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NotSameValidator.class)
@Documented
public @interface NotSame {
    String message() default "NotSame";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}