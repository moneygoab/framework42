package org.framework42.creditcheck.services.impl;

import org.framework42.address.model.impl.SimpleSecureAddressImpl;
import org.framework42.creditcheck.exceptions.AddressCheckException;
import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.*;
import org.framework42.creditcheck.model.impl.*;
import org.framework42.creditcheck.services.CreditCheckService;
import org.framework42.services.impl.MoneyImpl;
import org.framework42.utils.LocalDateUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class CreditCheckServiceInternalInvoice implements CreditCheckService {

    @Override
    public CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) throws CreditCheckException {

        try {

            AddressCheckResponse addressResponse = new AddressCheckServiceSynaImpl().getAddress(context, application.getMainApplicant().getGovernmentId());

            CreditBureauApplication madeApplication = new CreditBureauApplicationImpl(
                    application.getId(),
                    application.getType(),
                    ApplicationStatus.APPROVAL_PROCESS,
                    application.getApplicationDate(),
                    application.getAppliedAmount(),
                    application.getPreviousDebt(),
                    application.getPreviousDebtCoApplicant(),
                    application.getApplicationChannel(),
                    new ApplicantImpl(
                            0,
                            application.getMainApplicant().getGovernmentId(),
                            new BigDecimal(0.1),
                            LocalDateUtil.getFromGovernmentId(application.getMainApplicant().getGovernmentId()),
                            new ApplicantNamesImpl(addressResponse.getFirstName(), addressResponse.getSurname(), addressResponse.getFullName()),
                            new SimpleSecureAddressImpl(0, addressResponse.getAddress().getAddressee(), addressResponse.getAddress().getCareOf(), addressResponse.getAddress().getStreetAddress(), addressResponse.getAddress().getPostalCode(), addressResponse.getAddress().getCity(), addressResponse.getAddress().getCountry(), addressResponse.getAddress().getProvider()),
                            application.getMainApplicant().getContactMethods(),
                            application.getMainApplicant().getAnnualIncome()),
                    null,
                    new SimpleCreditBureauApplicationResponse(
                            CreditDecision.APPROVED,
                            application.getAppliedAmount(),
                            "Internal fallback credit control since all previous credit bureaus is down.",
                            "",
                            0,
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            0,
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            0,
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            ""

                    ),
                    0
                    );

            return madeApplication;

        } catch (AddressCheckException e) {

            throw new CreditCheckException("Couldn't finnish internal credit check, reason: "+e.getMessage());
        }

    }

    @Override
    public MainApplicantExtraApplicationResponse addMainApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) throws CreditCheckException {

        throw new NotImplementedException();
    }

    @Override
    public CoApplicantApplicationResponse addCoApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) {

        throw new NotImplementedException();
    }
}
