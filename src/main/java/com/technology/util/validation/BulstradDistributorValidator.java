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
public class BulstradDistributorValidator  implements ConstraintValidator<BulstradDistributor, String> {

    @Override
    public void initialize(BulstradDistributor bulstradDistributor) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            int number = Integer.valueOf(s);
            return  true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
