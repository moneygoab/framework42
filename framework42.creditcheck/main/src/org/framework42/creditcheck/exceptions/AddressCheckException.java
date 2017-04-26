package org.framework42.creditcheck.exceptions;

public class AddressCheckException extends Exception {

    private final boolean shouldTryNextFallback;

    public AddressCheckException(String message, boolean shouldTryNextFallback) {
        super(message);

        this.shouldTryNextFallback = shouldTryNextFallback;
    }

    public boolean isShouldTryNextFallback() {
        return shouldTryNextFallback;
    }
}
