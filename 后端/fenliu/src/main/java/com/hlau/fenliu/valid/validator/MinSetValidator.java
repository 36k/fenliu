package com.hlau.fenliu.valid.validator;

import com.hlau.fenliu.entity.Aspiration;
import com.hlau.fenliu.valid.MinSet;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class MinSetValidator implements ConstraintValidator<MinSet, Object> {

    @Override
    public void initialize(MinSet constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Set<Aspiration> aspirations= (Set<Aspiration>) o;
        if(aspirations.size()<3){
            return false;
        }
        return true;
    }
}
