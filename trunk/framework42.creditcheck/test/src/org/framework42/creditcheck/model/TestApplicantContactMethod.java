package org.framework42.creditcheck.model;

import org.framework42.creditcheck.model.impl.ApplicantContactMethodImpl;
import org.framework42.address.model.ContactMethod;
import org.framework42.address.model.InformationProvider;
import org.junit.Test;

public class TestApplicantContactMethod {

    @Test
    public void testInitiate() {

        new ApplicantContactMethodImpl(
                0,
                ContactMethod.EMAIL,
                InformationProvider.CUSTOMER,
                "test@test.com"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitiateNegativeId(){

        new ApplicantContactMethodImpl(
                -1,
                ContactMethod.EMAIL,
                InformationProvider.CUSTOMER,
                "test@test.com"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitiateNullContactMethod(){

        new ApplicantContactMethodImpl(
                0,
                null,
                InformationProvider.CUSTOMER,
                "test@test.com"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitiateNullInformationProvider(){

        new ApplicantContactMethodImpl(
                0,
                ContactMethod.EMAIL,
                null,
                "test@test.com"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitiateNullAddress(){

        new ApplicantContactMethodImpl(
                0,
                ContactMethod.EMAIL,
                InformationProvider.CUSTOMER,
                null
        );
    }

}
