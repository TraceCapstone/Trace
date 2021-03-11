package com.trace.trace.services;

import com.trace.trace.ValidPassword;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) { }

    @Override
    public boolean isValid(String p, ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator validator = new PasswordValidator(
                Arrays.asList(
                        new LengthRule(9, 255),
                        new CharacterRule(EnglishCharacterData.UpperCase),
                        new CharacterRule(EnglishCharacterData.LowerCase, 1),
                        new CharacterRule(EnglishCharacterData.Digit, 1),
                        new CharacterRule(EnglishCharacterData.Special, 1),
                        new WhitespaceRule()
                )
        );
        RuleResult r = validator.validate(new PasswordData(p));
        if (r.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(r);

        String messageTemplate = String.join(",", messages);
        constraintValidatorContext.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
