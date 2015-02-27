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
 * User: dpavlov
 * Date: 14-1-22
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = BulstradDistributorValidator.class)
@Documented
public @interface BulstradDistributor {
    String message() default "Вече има запис с този БУЛСТАТ!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
