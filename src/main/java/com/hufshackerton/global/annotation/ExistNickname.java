package com.hufshackerton.global.annotation;

import com.hufshackerton.global.validator.NicknameExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NicknameExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistNickname {
    String message() default "존재하는 닉네임 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
