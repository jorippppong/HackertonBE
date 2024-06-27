package com.hufshackerton.global.validator;

import com.hufshackerton.app.service.MemberQueryService;
import com.hufshackerton.global.annotation.ExistEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailExistValidator implements ConstraintValidator<ExistEmail, String> {
    private final MemberQueryService memberQueryService;
    @Override
    public void initialize(ExistEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !memberQueryService.existByEmail(value);
    }
}