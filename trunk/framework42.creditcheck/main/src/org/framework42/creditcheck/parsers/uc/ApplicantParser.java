package org.framework42.creditcheck.parsers.uc;

import org.apache.log4j.Logger;
import org.framework42.creditcheck.model.Applicant;
import org.framework42.creditcheck.model.ApplicantNames;
import org.framework42.creditcheck.model.CreditBureauApplication;
import org.framework42.creditcheck.model.impl.ApplicantImpl;
import org.framework42.creditcheck.model.impl.ApplicantNamesImpl;
import org.framework42.model.*;
import org.framework42.model.impl.PostalCodeImpl;
import org.framework42.model.impl.SimpleSecureAddressImpl;
import uc_webservice.Group;
import uc_webservice.Term;
import uc_webservice.UcReply;

import java.math.BigDecimal;

public enum ApplicantParser {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.nummer42.creditcheck");

    public Applicant createMainApplicant(UcReply reply, CreditBureauApplication application) {

        Group applicantInformationGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W080", 0);

        Group decisionGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W131", 0);

        float risk = 0;

        for(Term term: decisionGroup.getTerm()) {

            if("W13111".equals(term.getId())) {

                String cleaned = term.getValue().replaceAll("%", "").trim().replaceAll(",",".");
                risk = Float.parseFloat(cleaned);

            }
        }

        Group incomeGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W495", 0);

        int income = 0;

        for(Term term: incomeGroup.getTerm()) {

            if("W49522".equals(term.getId())) {

                income = Integer.parseInt(term.getValue());

            }
        }

        return new ApplicantImpl(
                application.getMainApplicant().getId(),
                application.getMainApplicant().getGovernmentId(),
                new BigDecimal(risk),
                application.getMainApplicant().getBirthDate(),
                createApplicantNames(applicantInformationGroup),
                createAddress(applicantInformationGroup),
                application.getMainApplicant().getContactMethods(),
                income
        );
    }

    public Applicant createCoApplicant(UcReply reply, CreditBureauApplication application) {

        try {

            Group applicantInformationGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W080", 1);

            Group decisionGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W131", 0);

            float risk = 0;

            for(Term term: decisionGroup.getTerm()) {

                if("W13125".equals(term.getId())) {

                    String cleaned = term.getValue().replaceAll("%", "").trim().replaceAll(",",".");
                    risk = Float.parseFloat(cleaned);
                }
            }

            Group incomeGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W495", 1);

            int income = 0;

            for(Term term: incomeGroup.getTerm()) {

                if("W49522".equals(term.getId())) {

                    income = Integer.parseInt(term.getValue());

                }
            }

            return new ApplicantImpl(
                    application.getCoApplicant().getId(),
                    application.getCoApplicant().getGovernmentId(),
                    new BigDecimal(risk),
                    application.getCoApplicant().getBirthDate(),
                    createApplicantNames(applicantInformationGroup),
                    createAddress(applicantInformationGroup),
                    application.getCoApplicant().getContactMethods(),
                    income
            );

        } catch(IllegalAccessError e) {

            logger.debug("No co-applicant in uc response.");

        } catch(IndexOutOfBoundsException e) {

            logger.debug("No co-applicant in uc response.");

        }

        return null;
    }

    private ApplicantNames createApplicantNames(Group group) {

        String fullName = "";
        String firstName = "";
        String surname = "";

        for(Term term: group.getTerm()) {

            if("W08003".equals(term.getId())) {

                fullName = term.getValue();

            } else if("W08085".equals(term.getId())) {

                firstName = term.getValue();

            } else if("W08084".equals(term.getId())) {

                surname = term.getValue();
            }
        }

        return new ApplicantNamesImpl(
                firstName,
                surname,
                fullName
        );
    }

    private TrustedAddress createAddress(Group group) {

        String firstName = "";
        String surname = "";

        String streetAddress = "";
        PostalCode postalCode = new PostalCodeImpl(PostalCodeFormat.UNKNOWN, "00000");
        String city = "";
        Country country = Country.SWEDEN;

        for(Term term: group.getTerm()) {

            if("W08085".equals(term.getId())) {

                firstName = term.getValue();

            } else if("W08084".equals(term.getId())) {

                surname = term.getValue();

            } else if("W08004".equals(term.getId())) {

                streetAddress = term.getValue();

            } else if("W08005".equals(term.getId())) {

                logger.debug("Postal code from UC: "+term.getValue());
                postalCode = new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, term.getValue());

            } else if("W08006".equals(term.getId())) {

                city = term.getValue();

            } else if("W08052".equals(term.getId())) {

                country = Country.parseFromString(term.getValue());

            }
        }

        return new SimpleSecureAddressImpl(
                0,
                firstName+" "+surname,
                "",
                streetAddress,
                postalCode,
                city,
                country,
                InformationProvider.POPULATION_REGISTERS
        );
    }


}
