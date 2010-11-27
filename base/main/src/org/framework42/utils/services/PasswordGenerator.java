package org.framework42.utils.services;

/**
 * Utility class for generating passwords
 */
public interface PasswordGenerator {

    /**
     * Generates a random password given a specific deck of characters.
     *
     * @param passwordLength The length of the password, must be 1 or larger.
     * @return String                       The generated password.
     * @throws IllegalArgumentException Thrown if the password length is less then 1.
     */
    public String generateRandomPassword(int passwordLength);

}
