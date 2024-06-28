package com.hufshackerton.app.domain;

import com.hufshackerton.global.exception.ErrorCode;
import com.hufshackerton.global.exception.RestApiException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Password {
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+-=\\[\\]{}|;':\",./<>?~`\\\\])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{}|;':\",./<>?~`\\\\]{9,16}";

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String encryptedPassword;

    public static Password encrypt(String plainPassword, BCryptPasswordEncoder encoder) {
        if (!isPasswordValid(plainPassword)) {
            throw new RestApiException(ErrorCode.NOT_VALID_PASSWORD);
        }
        return new Password(encoder.encode(plainPassword));
    }

    public static Boolean isPasswordValid(String plainPassword) {
        return Pattern.matches(PASSWORD_REGEX, plainPassword);
    }

    public Boolean isSamePassword(String plainPassword, BCryptPasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }
}
