package com.technology.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-22
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class NumberValidator implements ConstraintValidator<Number, Object> {

    @Override
    public void initialize(Number number) {}

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return o instanceof java.lang.Number;
    }
}