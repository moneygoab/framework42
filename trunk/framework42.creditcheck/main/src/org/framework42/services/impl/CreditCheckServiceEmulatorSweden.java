package org.framework42.services.impl;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MatchGenerator;
import org.framework42.datageneration.CityContainer;
import org.framework42.datageneration.FirstNamesContainer;
import org.framework42.datageneration.StreetsContainer;
import org.framework42.datageneration.SurnamesContainer;
import org.framework42.datageneration.impl.CityContainerSweden;
import org.framework42.datageneration.impl.FirstNamesContainerSweden;
import org.framework42.datageneration.impl.StreetsContainerSweden;
import org.framework42.datageneration.impl.SurnamesContainerSweden;
import org.framework42.exceptions.CreditCheckException;
import org.framework42.model.*;
import org.framework42.model.impl.*;
import org.framework42.services.CreditCheckService;

import java.math.BigDecimal;
import java.util.ArrayList;
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
                "Created by emulator credit check bureau, no html view available."
        );

        return new CreditBureauApplicationImpl(
                application.getId(),
                ApplicationStatus.APPROVAL_PROCESS,
                application.getApplicationDate(),
                application.getAppliedAmount(),
                application.getApplicationChannel(),
                createApplicant(application.getMainApplicant()),
                createApplicant(application.getCoApplicant())
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



        String firstName = firstNamesContainer.getRandomName();
        String surname = surnamesContainer.getRandomSurname();

        return new ApplicantImpl(
                applicant.getId(),
                applicant.getGovernmentId(),
                new BigDecimal(new Random().nextInt(100)),
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
                new ArrayList<ApplicantContactMethod>(),
                (int)(Math.random()*300000)+100000
        );
    }

}
