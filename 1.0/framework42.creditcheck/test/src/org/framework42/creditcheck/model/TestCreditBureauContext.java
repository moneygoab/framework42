package org.framework42.creditcheck.model;

import org.framework42.creditcheck.model.impl.CreditBureauContextImpl;
import org.junit.Test;

public class TestCreditBureauContext {

    @Test
    public void testInstantiate() {

        new CreditBureauContextImpl(
                CreditBureau.EMULATOR_SWEDEN,
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
                null,
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
                CreditBureau.EMULATOR_SWEDEN,
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
                CreditBureau.EMULATOR_SWEDEN,
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
                CreditBureau.EMULATOR_SWEDEN,
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
                CreditBureau.EMULATOR_SWEDEN,
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
                CreditBureau.EMULATOR_SWEDEN,
                "testuser",
                "testpassword",
                "",
                "3",
                null
        );
    }

}
