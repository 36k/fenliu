package com.hlau.fenliu.valid;

import com.hlau.fenliu.valid.validator.MinSetValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinSetValidator.class)

public @interface MinSet {
    String message() ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
