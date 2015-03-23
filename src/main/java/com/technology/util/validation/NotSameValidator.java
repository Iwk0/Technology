package com.technology.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-3-11
 * Time: 9:52
 */
public class NotSameValidator implements ConstraintValidator<NotSame, String> {

    @Override
    public void initialize(NotSame notSame) {}

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !username.equals("admin");
    }
}