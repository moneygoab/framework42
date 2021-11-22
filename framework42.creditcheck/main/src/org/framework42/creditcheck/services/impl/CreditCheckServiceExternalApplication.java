package org.framework42.creditcheck.services.impl;

import org.apache.log4j.Logger;
import org.framework42.address.model.InformationProvider;
import org.framework42.address.model.impl.SimpleSecureAddressImpl;
import org.framework42.creditcheck.exceptions.AddressCheckException;
import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.*;
import org.framework42.creditcheck.model.impl.*;
import org.framework42.creditcheck.services.AddressCheckService;
import org.framework42.creditcheck.services.CreditCheckService;
import org.framework42.model.users.Gender;
import org.framework42.services.impl.MoneyImpl;
import org.framework42.utils.LocalDateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

public class CreditCheckServiceExternalApplication implements CreditCheckService {

    private final static AddressCheckService ucAddress = new AddressCheckServiceUCImpl();

    private final static AddressCheckService synaAddress = new AddressCheckServiceSynaImpl();

    private final static CreditBureauContext ucContext = new CreditBureauContextImpl(0, CreditBureau.UC, "UC", "KOYT2", "X0", "UC", "3", "MGS", false, false);

    private final static CreditBureauContext synaContext = new CreditBureauContextImpl(0, CreditBureau.SYNA, "Syna", "500317", "apigo", "Syna Adressfråga", "policy", "46.21.111.160", false, false);

    @Override
    public CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) throws CreditCheckException {

        CreditBureauApplicationResponse creditBureauResponse = new SimpleCreditBureauApplicationResponse(
                CreditDecision.NOT_DECIDED_YET,
                new MoneyImpl(BigDecimal.ZERO, application.getAppliedAmount().getCurrency()),
                "External part is making the credit decision.",
                "External part is making the credit decision.",
                0,
                new MoneyImpl(BigDecimal.ZERO, application.getAppliedAmount().getCurrency()),
                new MoneyImpl(BigDecimal.ZERO, application.getAppliedAmount().getCurrency()),
                0,
                new MoneyImpl(BigDecimal.ZERO, application.getAppliedAmount().getCurrency()),
                0,
                new MoneyImpl(BigDecimal.ZERO, application.getAppliedAmount().getCurrency()),
                "",
                new ArrayList<>()
        );

        try {

            return new CreditBureauApplicationImpl(
                    application.getId(),
                    ApplicationType.NEW,
                    ApplicationStatus.APPROVAL_PROCESS,
                    application.getApplicationDate(),
                    application.getAppliedAmount(),
                    application.getPreviousDebt(),
                    application.getPreviousDebtCoApplicant(),
                    application.getApplicationChannel(),
                    createApplicant(application.getMainApplicant()),
                    createApplicant(application.getCoApplicant()),
                    creditBureauResponse,
                    application.getExtendedApplicationId()
            );

        } catch (AddressCheckException e) {

            Logger.getLogger("org.framework42").error(e);
            throw new CreditCheckException(e.getMessage());
        }
    }

    private Applicant createApplicant(Applicant applicant) throws AddressCheckException {

        if(applicant == null || "".equals(applicant.getGovernmentId())) {
            return null;
        }

        Gender gender = Gender.MALE;
        if(Long.parseLong(applicant.getGovernmentId().substring(6, 9))%2 == 0) {
            gender = Gender.FEMALE;
        }

        AddressCheckResponse address = getAddress(applicant.getGovernmentId());

        String firstName = address.getFirstName();
        String surname = address.getSurname();

        return new ApplicantImpl(
                applicant.getId(),
                applicant.getGovernmentId(),
                BigDecimal.ZERO,
                applicant.getBirthDate(),
                new ApplicantNamesImpl(firstName, surname, firstName + " " + surname),
                new SimpleSecureAddressImpl(
                        0,
                        firstName + " " + surname,
                        address.getAddress().getCareOf(),
                        address.getAddress().getStreetAddress(),
                        address.getAddress().getPostalCode(),
                        address.getAddress().getCity(),
                        address.getAddress().getCountry(),
                        InformationProvider.POPULATION_REGISTERS
                ),
                applicant.getContactMethods(),
                0
        );
    }

    private AddressCheckResponse getAddress(String governmentId) throws AddressCheckException {

        AddressCheckResponse resp = null;

        try {

            resp = ucAddress.getAddress(ucContext, governmentId);

        } catch (AddressCheckException e) {

            if(e.isShouldTryNextFallback()) {

                try {

                    resp = synaAddress.getAddress(synaContext, governmentId);

                } catch (AddressCheckException ex) {

                    throw new AddressCheckException(e.getMessage(), false);
                }

            } else {

                throw new AddressCheckException(e.getMessage(), false);
            }
        }

        return resp;
    }

    @Override
    public MainApplicantExtraApplicationResponse addMainApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) throws CreditCheckException {

        try {

            return new MainApplicantExtraApplicationResponseImpl(
                    applicationId,
                    createApplicant(new ApplicantImpl(governmentId, LocalDateUtil.getFromGovernmentId(governmentId), new ArrayList<>())),
                    "External part is making the credit decision.",
                    new SimpleCreditBureauApplicationResponse(
                            CreditDecision.NOT_DECIDED_YET,
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            "External part is making the credit decision.",
                            "External part is making the credit decision.",
                            0,
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            0,
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            0,
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            "",
                            new ArrayList<>()
                    )
            );

        } catch (AddressCheckException e) {

            Logger.getLogger("org.framework42").error(e);
            throw new CreditCheckException(e.getMessage());
        }
    }

    @Override
    public CoApplicantApplicationResponse addCoApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) {

        try {

            return new CoApplicantApplicationResponseImpl(
                    applicationId,
                    createApplicant(new ApplicantImpl(governmentId, LocalDateUtil.getFromGovernmentId(governmentId), new ArrayList<>())),
                    "External part is making the credit decision."
            );

        } catch (AddressCheckException e) {

            Logger.getLogger("org.framework42").error(e);
            return null;
        }
    }
}
