package org.framework42.utils.services.impl;

import org.framework42.utils.services.GovernmentIdValidator;
import org.framework42.utils.services.Modulus10Calculator;

public enum GovernmentIdValidatorImpl implements GovernmentIdValidator {

    INSTANCE;

    private final Modulus10Calculator modulus10Calculator;

    GovernmentIdValidatorImpl() {
        this.modulus10Calculator = new Modulus10CalculatorImpl();
    }

    @Override
    public boolean isValidSwedish(String governmentId) {

        try {

            if (governmentId == null) {

                return false;
            }

            if (governmentId.length() == 12) {

                governmentId = governmentId.substring(2);
            }

            if (governmentId.length() != 10) {

                return false;

            } else if (modulus10Calculator.generateChecksum(governmentId.substring(0, 9)) != Integer.parseInt(governmentId.substring(9))) {

                return false;
            }

            return true;

        } catch (Exception e) {

            return false;
        }

    }

    @Override
    public boolean isPrivatePerson(String governmentId) {
        return Integer.parseInt(governmentId.substring(2,4))<=12;
    }

}
