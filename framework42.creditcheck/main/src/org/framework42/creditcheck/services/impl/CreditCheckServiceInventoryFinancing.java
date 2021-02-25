package org.framework42.creditcheck.services.impl;

import org.apache.log4j.Logger;
import org.framework42.address.model.InformationProvider;
import org.framework42.address.model.PostalCodeFormat;
import org.framework42.address.model.impl.PostalCodeImpl;
import org.framework42.address.model.impl.SimpleSecureAddressImpl;
import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.*;
import org.framework42.creditcheck.model.impl.ApplicantImpl;
import org.framework42.creditcheck.model.impl.ApplicantNamesImpl;
import org.framework42.creditcheck.model.impl.CreditBureauApplicationImpl;
import org.framework42.creditcheck.model.impl.SimpleCreditBureauApplicationResponse;
import org.framework42.creditcheck.services.CreditCheckService;
import org.framework42.model.Country;
import org.framework42.services.impl.MoneyImpl;
import org.framework42.utils.LocalDateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

public class CreditCheckServiceInventoryFinancing implements CreditCheckService {

    private final static Logger logger = Logger.getLogger("org.framework42.creditcheck");


    public CreditCheckServiceInventoryFinancing() {
    }

    @Override
    public CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) throws CreditCheckException {

        return createAnswer(application);
    }

    @Override
    public MainApplicantExtraApplicationResponse addMainApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) throws CreditCheckException {
        return null;
    }

    @Override
    public CoApplicantApplicationResponse addCoApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) {
        return null;
    }

    private CreditBureauApplication createAnswer(CreditBureauApplication application) {

        return new CreditBureauApplicationImpl(
                0,
                application.getType(),
                application.getStatus(),
                application.getApplicationDate(),
                application.getAppliedAmount(),
                application.getPreviousDebt(),
                application.getPreviousDebtCoApplicant(),
                application.getApplicationChannel(),
                new ApplicantImpl(
                        application.getMainApplicant().getId(),
                        application.getMainApplicant().getGovernmentId(),
                        BigDecimal.ZERO,
                        LocalDateUtil.getFromGovernmentId(application.getMainApplicant().getGovernmentId()),
                        new ApplicantNamesImpl(
                                "",
                                "",
                                ""
                        ),
                        new SimpleSecureAddressImpl(
                                0,
                                "",
                                "",
                                "",
                                new PostalCodeImpl(PostalCodeFormat.getByCountry(Country.SWEDEN), ""),
                                "",
                                Country.SWEDEN,
                                InformationProvider.POPULATION_REGISTERS
                        ),
                        application.getMainApplicant().getContactMethods(),
                        0
                ),
                application.getCoApplicant(),
                new SimpleCreditBureauApplicationResponse(
                        CreditDecision.APPROVED,
                        application.getAppliedAmount(),
                        "No detailed information available.",
                        "",
                        0,
                        new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                        0,
                        new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                        0,
                        new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                        "",
                        new ArrayList<>()
                ),
                application.getExtendedApplicationId()
        );
    }

}
