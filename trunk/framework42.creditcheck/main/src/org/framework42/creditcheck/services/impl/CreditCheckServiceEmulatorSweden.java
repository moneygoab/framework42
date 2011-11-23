package org.framework42.creditcheck.services.impl;

import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.*;
import org.framework42.creditcheck.model.impl.ApplicantImpl;
import org.framework42.creditcheck.model.impl.ApplicantNamesImpl;
import org.framework42.creditcheck.model.impl.CreditBureauApplicationImpl;
import org.framework42.creditcheck.model.impl.SimpleCreditBureauApplicationResponse;
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
import org.framework42.model.impl.*;
import org.framework42.model.users.Gender;
import org.framework42.services.impl.MoneyImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;

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

        CreditDecision decision = generateCreditDecision(application);

        CreditBureauApplicationResponse creditBureauResponse = new SimpleCreditBureauApplicationResponse(
                decision,
                application.getAppliedAmount(),
                "Created by emulator credit check bureau, no html view available.",
                new Random().nextInt(10),
                new MoneyImpl(new BigDecimal(new Random().nextInt(500000)), Currency.getInstance(new Locale("sv", "SE"))),
                0,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE"))),
                ""
        );

        return new CreditBureauApplicationImpl(
                application.getId(),
                ApplicationStatus.APPROVAL_PROCESS,
                application.getApplicationDate(),
                application.getAppliedAmount(),
                application.getApplicationChannel(),
                createApplicant(application.getMainApplicant()),
                createApplicant(application.getCoApplicant()),
                creditBureauResponse
        );
    }

    private CreditDecision generateCreditDecision(CreditBureauApplication application) throws CreditCheckException {

        String governmentId = application.getMainApplicant().getGovernmentId();
        if(governmentId.endsWith("0")) {

            throw new CreditCheckException("Technical test error! Cast to test error handling.");

        } else if(governmentId.endsWith("1") || governmentId.endsWith("4") || governmentId.endsWith("7")) {

            return CreditDecision.APPROVED;

        } else if(governmentId.endsWith("2") || governmentId.endsWith("5") || governmentId.endsWith("8")) {

            return CreditDecision.REVIEW_REQUIRED;

        }else if(governmentId.endsWith("3") || governmentId.endsWith("6") || governmentId.endsWith("9")) {

            return CreditDecision.REJECTED;
        }

        throw new IllegalArgumentException("Can't generate credit decision from government id "+application.getMainApplicant().getGovernmentId()+".");
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
                (int)(Math.random()*300000)+100000
        );
    }

}
