package org.framework42.utils.services.impl;

import org.framework42.services.ProxyService;
import org.framework42.utils.services.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorImpl extends ProxyService implements EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidatorImpl(String loggerId) {
        super(loggerId);

        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public boolean isValidEmail(String email) {

        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
