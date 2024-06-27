package com.hufshackerton.global.validator;

import com.hufshackerton.app.service.MemberQueryService;
import com.hufshackerton.global.annotation.ExistNickname;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NicknameExistValidator implements ConstraintValidator<ExistNickname, String> {
    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(ExistNickname constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !memberQueryService.existByNickname(value);
    }
}
