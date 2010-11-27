package org.framework42.utils.services;

import org.framework42.utils.services.impl.PasswordGeneratorImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordUtilTester {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testGenerateRandomPassword() {

        PasswordGeneratorImpl.BASE_DECK.generateRandomPassword(10);

        PasswordGeneratorImpl.CASE_BASE_DECK.generateRandomPassword(10);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateRandomPasswordIllegalArgument() {

        PasswordGeneratorImpl.BASE_DECK.generateRandomPassword(-1);

    }

}
