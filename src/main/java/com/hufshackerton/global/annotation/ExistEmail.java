package com.hufshackerton.global.annotation;

import com.hufshackerton.global.validator.EmailExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistEmail {
    String message() default "존재하는 이메일 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}