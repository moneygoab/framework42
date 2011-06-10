package org.framework42.utils.services;

import org.framework42.model.PasswordStrength;

public interface PasswordStrengthCalculator {

    public PasswordStrength calculateStrength(String password);

}
