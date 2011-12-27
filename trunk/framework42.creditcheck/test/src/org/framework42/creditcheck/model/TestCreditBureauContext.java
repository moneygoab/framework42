package org.framework42.creditcheck.model;

import org.framework42.creditcheck.model.impl.CreditBureauContextImpl;
import org.junit.Test;

public class TestCreditBureauContext {

    @Test
    public void testInstantiate() {

        new CreditBureauContextImpl(
                0,
                CreditBureau.EMULATOR_SWEDEN,
                "test",
                "testuser",
                "testpassword",
                "",
                "3",
                "TST"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullCreditBureau() {

        new CreditBureauContextImpl(
                0,
                null,
                "test",
                "testuser",
                "testpassword",
                "",
                "3",
                "TST"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullUser() {

        new CreditBureauContextImpl(
                0,
                CreditBureau.EMULATOR_SWEDEN,
                "test",
                null,
                "testpassword",
                "",
                "3",
                "TST"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullPassword() {

        new CreditBureauContextImpl(
                0,
                CreditBureau.EMULATOR_SWEDEN,
                "test",
                "testuser",
                null,
                "",
                "3",
                "TST"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullName() {

        new CreditBureauContextImpl(
                0,
                CreditBureau.EMULATOR_SWEDEN,
                "test",
                "testuser",
                "testpassword",
                null,
                "3",
                "TST"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullPolicyProduct() {

        new CreditBureauContextImpl(
                0,
                CreditBureau.EMULATOR_SWEDEN,
                "test",
                "testuser",
                "testpassword",
                "",
                null,
                "TST"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullPolicyRule() {

        new CreditBureauContextImpl(
                0,
                CreditBureau.EMULATOR_SWEDEN,
                "test",
                "testuser",
                "testpassword",
                "",
                "3",
                null
        );
    }

}
