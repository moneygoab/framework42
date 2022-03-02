package org.framework42.kreditz.model.impl;

import org.framework42.kreditz.model.Token;

import java.time.LocalDateTime;

import static org.framework42.utils.NullChecker.notNull;

public class TokenImpl implements Token {

    private final String accessToken;

    private final LocalDateTime validTo;

    public TokenImpl(String accessToken, LocalDateTime validTo) {
        this.accessToken = notNull(accessToken);
        this.validTo = notNull(validTo);
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public LocalDateTime getValidTo() {
        return validTo;
    }


}
