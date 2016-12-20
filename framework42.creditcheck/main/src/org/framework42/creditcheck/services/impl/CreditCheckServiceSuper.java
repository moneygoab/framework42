package org.framework42.creditcheck.services.impl;

import org.apache.log4j.Logger;
import org.framework42.creditcheck.dao.InternalCreditCheckDAO;
import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.*;
import org.framework42.creditcheck.model.impl.ApplicantImpl;
import org.framework42.creditcheck.model.impl.ApplicantNamesImpl;
import org.framework42.creditcheck.model.impl.CreditBureauApplicationImpl;
import org.framework42.creditcheck.model.impl.SimpleCreditBureauApplicationResponse;
import org.framework42.creditcheck.services.CreditCheckService;
import org.framework42.model.Country;
import org.framework42.address.model.InformationProvider;
import org.framework42.address.model.PostalCodeFormat;
import org.framework42.address.model.impl.SimpleSecureAddressImpl;
import org.framework42.address.model.impl.PostalCodeImpl;
import org.framework42.services.impl.MoneyImpl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class CreditCheckServiceSuper implements CreditCheckService {

    private final static Logger logger = Logger.getLogger("org.framework42.creditcheck");

    private final InternalCreditCheckDAO internalCreditCheckDAO;

    public CreditCheckServiceSuper(InternalCreditCheckDAO internalCreditCheckDAO) {

        this.internalCreditCheckDAO = internalCreditCheckDAO;
    }

    @Override
    public CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) throws CreditCheckException {

        InternalCreditCheck internalCreditCheck = internalCreditCheckDAO.makeCreditCheck(application.getMainApplicant().getGovernmentId());

        if(internalCreditCheck.isDebtCollected()) {

            return createAnswer(application, internalCreditCheck, CreditDecision.REJECTED, "- Skuldsaldo/anmärkning hos KFM");

        } else {

            return createAnswer(application, internalCreditCheck, CreditDecision.APPROVED, "");
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

    private CreditBureauApplication createAnswer(CreditBureauApplication application, InternalCreditCheck internalCreditCheck, CreditDecision decision, String reasonCodes) {

        return new CreditBureauApplicationImpl(
                0,
                application.getType(),
                application.getStatus(),
                application.getApplicationDate(),
                application.getAppliedAmount(),
                application.getApplicationChannel(),
                new ApplicantImpl(
                        application.getMainApplicant().getId(),
                        application.getMainApplicant().getGovernmentId(),
                        BigDecimal.ZERO,
                        new Date(),
                        new ApplicantNamesImpl(
                                internalCreditCheck.getFirstName(),
                                internalCreditCheck.getLastName(),
                                internalCreditCheck.getFullName()
                        ),
                        new SimpleSecureAddressImpl(
                                0,
                                internalCreditCheck.getFirstName()+" "+internalCreditCheck.getLastName(),
                                internalCreditCheck.getCareOf(),
                                internalCreditCheck.getAddress(),
                                new PostalCodeImpl(PostalCodeFormat.getByCountry(Country.SWEDEN), internalCreditCheck.getPostalCode()),
                                internalCreditCheck.getCity(),
                                internalCreditCheck.getCountry(),
                                InformationProvider.POPULATION_REGISTERS
                        ),
                        application.getMainApplicant().getContactMethods(),
                        internalCreditCheck.getDeclaredIncome()
                ),
                application.getCoApplicant(),
                new SimpleCreditBureauApplicationResponse(
                        decision,
                        //TODO: Remove hard coded 2000 SEK.
                        new MoneyImpl(new BigDecimal(2000), Currency.getInstance("SEK")),
                        "No detailed information available.",
                        "",
                        0,
                        new MoneyImpl(new BigDecimal(internalCreditCheck.getDeclaredIncome()), Currency.getInstance("SEK")),
                        internalCreditCheck.getDebtCollectionInfo().getNumberOfACollections()+internalCreditCheck.getDebtCollectionInfo().getNumberOfECollections(),
                        new MoneyImpl(new BigDecimal(internalCreditCheck.getDebtCollectionInfo().getSumOfACollections()+internalCreditCheck.getDebtCollectionInfo().getSumOfECollections()), Currency.getInstance("SEK")),
                        0,
                        new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                        reasonCodes
                ),
                application.getExtendedApplicationId()
        );
    }


}
