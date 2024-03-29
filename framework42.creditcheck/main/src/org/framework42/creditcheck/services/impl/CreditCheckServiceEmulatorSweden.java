package org.framework42.creditcheck.services.impl;

import org.framework42.address.model.InformationProvider;
import org.framework42.address.model.PostalCodeFormat;
import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.*;
import org.framework42.creditcheck.model.impl.*;
import org.framework42.creditcheck.services.CreditCheckService;
import org.framework42.datageneration.CityContainer;
import org.framework42.datageneration.FirstNamesContainer;
import org.framework42.datageneration.StreetsContainer;
import org.framework42.datageneration.SurnamesContainer;
import org.framework42.datageneration.impl.CityContainerSweden;
import org.framework42.datageneration.impl.FirstNamesContainerSweden;
import org.framework42.datageneration.impl.StreetsContainerSweden;
import org.framework42.datageneration.impl.SurnamesContainerSweden;
import org.framework42.model.*;
import org.framework42.address.model.impl.*;
import org.framework42.model.users.Gender;
import org.framework42.services.Money;
import org.framework42.services.impl.MoneyImpl;
import org.framework42.utils.LocalDateUtil;

import java.math.BigDecimal;
import java.util.*;

public class CreditCheckServiceEmulatorSweden implements CreditCheckService {

    private CityContainer cityContainer;

    private FirstNamesContainer firstNamesContainer;

    private StreetsContainer streetsContainer;

    private SurnamesContainer surnamesContainer;

    public CreditCheckServiceEmulatorSweden() {

        cityContainer = new CityContainerSweden();
        firstNamesContainer = new FirstNamesContainerSweden();
        streetsContainer = new StreetsContainerSweden();
        surnamesContainer = new SurnamesContainerSweden();
    }

    @Override
    public CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) throws CreditCheckException {

        CreditDecision decision = generateCreditDecision(application.getMainApplicant().getGovernmentId());

        Money recommendedAmount;
        if(decision==CreditDecision.REJECTED || decision==CreditDecision.REVIEW_REQUIRED) {

            recommendedAmount = new MoneyImpl(BigDecimal.ZERO, application.getAppliedAmount().getCurrency());

        } else {

            //recommendedAmount = application.getAppliedAmount();
            recommendedAmount = new MoneyImpl( new BigDecimal( (((application.getAppliedAmount().getAmount().intValue() + 999) / 1000 ) * 1000) ), application.getAppliedAmount().getCurrency());
        }

        CreditBureauApplicationResponse creditBureauResponse = new SimpleCreditBureauApplicationResponse(
                decision,
                recommendedAmount,
                "Created by emulator credit check bureau, no html view available.",
                "Craeted by emulator credit check bureau, no html view available.",
                new Random().nextInt(10),
                new MoneyImpl(new BigDecimal(new Random().nextInt(500000)), Currency.getInstance(new Locale("sv", "SE"))),
                new MoneyImpl(new BigDecimal(new Random().nextInt(500000)), Currency.getInstance(new Locale("sv", "SE"))),
                0,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE"))),
                0,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE"))),
                "",
                new ArrayList<>()
        );

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
    }

    private CreditDecision generateCreditDecision(String governmentId) throws CreditCheckException {

        if(governmentId.endsWith("0")) {

            throw new CreditCheckException("Technical test error! Cast to test error handling.");

        } else if(governmentId.endsWith("1") || governmentId.endsWith("4") || governmentId.endsWith("7") || governmentId.endsWith("5") ) {

            return CreditDecision.APPROVED;

        } else if(governmentId.endsWith("2") || governmentId.endsWith("5") || governmentId.endsWith("8")) {

            return CreditDecision.REVIEW_REQUIRED;

        }else if(governmentId.endsWith("3") || governmentId.endsWith("6") || governmentId.endsWith("9")) {

            return CreditDecision.REJECTED;
        }

        throw new IllegalArgumentException("Can't generate credit decision from government id "+governmentId+".");
    }

    private Applicant createApplicant(Applicant applicant) {

        if(applicant == null || "".equals(applicant.getGovernmentId())) {
            return null;
        }

        Gender gender = Gender.MALE;
        if(Long.parseLong(applicant.getGovernmentId().substring(6, 9))%2 == 0) {
            gender = Gender.FEMALE;
        }

        String firstName = firstNamesContainer.getRandomName(gender);
        String surname = surnamesContainer.getRandomSurname();

        return new ApplicantImpl(
                applicant.getId(),
                applicant.getGovernmentId(),
                new BigDecimal(new Random().nextInt(7)),
                applicant.getBirthDate(),
                new ApplicantNamesImpl(firstName, surname, firstName + " " + surname),
                new SimpleSecureAddressImpl(
                        0,
                        firstName + " " + surname,
                        "",
                        streetsContainer.getStreetName(),
                        new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, (new Random().nextInt(80000)+10000)+""),
                        cityContainer.getCity(),
                        Country.SWEDEN,
                        InformationProvider.POPULATION_REGISTERS
                ),
                applicant.getContactMethods(),
                (int)(Math.random()*300000)+100000,
                (int)(Math.random()*300000)+100000
        );
    }

    @Override
    public MainApplicantExtraApplicationResponse addMainApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) {

        Gender gender = Gender.MALE;
        if(Long.parseLong(governmentId.substring(6, 9))%2 == 0) {
            gender = Gender.FEMALE;
        }

        String firstName = firstNamesContainer.getRandomName(gender);
        String surname = surnamesContainer.getRandomSurname();

        try {

            CreditDecision decision = generateCreditDecision(governmentId);

            Money recommendedAmount;
            if(decision==CreditDecision.REJECTED || decision==CreditDecision.REVIEW_REQUIRED) {

                recommendedAmount = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK"));

            } else {

                recommendedAmount = new MoneyImpl(new BigDecimal(appliedAmount), Currency.getInstance("SEK"));
            }

            return new MainApplicantExtraApplicationResponseImpl(
                    applicationId,
                    new ApplicantImpl(
                            0,
                            governmentId,
                            BigDecimal.ZERO,
                            LocalDateUtil.getFromGovernmentId(governmentId),
                            new ApplicantNamesImpl(firstName, surname, firstName + " " + surname),
                            new SimpleSecureAddressImpl(
                                    0,
                                    firstName + " " + surname,
                                    "",
                                    streetsContainer.getStreetName(),
                                    new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, (new Random().nextInt(80000) + 10000) + ""),
                                    cityContainer.getCity(),
                                    Country.SWEDEN,
                                    InformationProvider.POPULATION_REGISTERS
                            ),
                            new ArrayList<ApplicantContactMethod>(),
                            (int) (Math.random() * 300000) + 100000,
                            (int)(Math.random()*300000)+100000
                    ),
                    "Created by emulator credit check bureau, no html view available.",
                    new SimpleCreditBureauApplicationResponse(
                            decision,
                            recommendedAmount,
                            "Created by emulator credit check bureau, no html view available.",
                            "",
                            0,
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            0,
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK")),
                            0,
                            new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE"))),
                            "",
                            new ArrayList<>()
                            )
            );

        } catch (CreditCheckException e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CoApplicantApplicationResponse addCoApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) {

        Gender gender = Gender.MALE;
        if(Long.parseLong(governmentId.substring(6, 9))%2 == 0) {
            gender = Gender.FEMALE;
        }

        String firstName = firstNamesContainer.getRandomName(gender);
        String surname = surnamesContainer.getRandomSurname();

        return new CoApplicantApplicationResponseImpl(
                applicationId,
                new ApplicantImpl(
                        0,
                        governmentId,
                        BigDecimal.ZERO,
                        LocalDateUtil.getFromGovernmentId(governmentId),
                        new ApplicantNamesImpl(firstName, surname, firstName + " " + surname),
                        new SimpleSecureAddressImpl(
                                0,
                                firstName + " " + surname,
                                "",
                                streetsContainer.getStreetName(),
                                new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, (new Random().nextInt(80000)+10000)+""),
                                cityContainer.getCity(),
                                Country.SWEDEN,
                                InformationProvider.POPULATION_REGISTERS
                        ),
                        new ArrayList<ApplicantContactMethod>(),
                        (int)(Math.random()*300000)+100000,
                        (int)(Math.random()*300000)+100000
                ),
                "Created by emulator credit check bureau, no html view available."
        );
    }
}
