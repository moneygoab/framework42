package org.framework42.creditcheck.parsers.uc;

import org.apache.log4j.Logger;
import org.framework42.ServiceBinderInterface;
import org.framework42.creditcheck.model.Applicant;
import org.framework42.creditcheck.model.ApplicantNames;
import org.framework42.creditcheck.model.CreditBureauApplication;
import org.framework42.creditcheck.model.impl.ApplicantImpl;
import org.framework42.creditcheck.model.impl.ApplicantNamesImpl;
import org.framework42.model.*;
import org.framework42.model.AddressType;
import org.framework42.model.InformationProvider;
import org.framework42.model.PostalCode;
import org.framework42.model.PostalCodeFormat;
import org.framework42.model.TrustedAddress;
import org.framework42.model.impl.PostalCodeImpl;
import org.framework42.model.impl.SimpleSecureAddressImpl;
import uc_webservice.Group;
import uc_webservice.Term;
import uc_webservice.UcReply;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

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

        Group incomeGroup = null;
        try {
            incomeGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W495", 0);
        } catch(IllegalArgumentException e) { logger.debug("No income group found for applicant "+application.getMainApplicant().getGovernmentId()+", assuming zero income."); }

        int income = 0;

        if(incomeGroup != null) {
            for(Term term: incomeGroup.getTerm()) {

                if("W49522".equals(term.getId())) {

                    income = Integer.parseInt(term.getValue());

                }
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

            Group incomeGroup = null;
            try {
                BaseParser.INSTANCE.findResponseGroup(reply, "W495", 1);
            } catch(IllegalArgumentException e) { logger.debug("No income group found for co-applicant "+application.getCoApplicant().getGovernmentId()+", assuming zero income."); }

            int income = 0;

            if(incomeGroup != null) {
                for(Term term: incomeGroup.getTerm()) {

                    if("W49522".equals(term.getId())) {

                        income = Integer.parseInt(term.getValue());

                    }
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

    public ApplicantNames createApplicantNames(Group group) {

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

    public TrustedAddress createAddress(Group group) {

        String firstName = "";
        String surname = "";
        String fullName = "";

        String careOf = "";
        String streetAddress = "";
        String postalCodeString = "";
        PostalCode postalCode = new PostalCodeImpl(PostalCodeFormat.UNKNOWN, "00000");
        String city = "";
        Country country = Country.SWEDEN;

        for(Term term: group.getTerm()) {

            if("W08085".equals(term.getId())) {

                firstName = term.getValue();

            } else if("W08083".equals(term.getId())) {

                fullName = term.getValue();

            }else if("W08084".equals(term.getId())) {

                surname = term.getValue();

            } else if("W08004".equals(term.getId())) {

                if(streetAddress.length()>0) {

                    streetAddress = term.getValue()+streetAddress;

                } else {

                    streetAddress = term.getValue();
                }

            } else if("W08005".equals(term.getId())) {

                postalCodeString = term.getValue();

            } else if("W08006".equals(term.getId())) {

                city = term.getValue();

            } else if("W08052".equals(term.getId())) {

                country = Country.parseFromString(term.getValue());

            } else if("W08050".equals(term.getId())) {

                careOf = term.getValue();

            } else if("W08051".equals(term.getId())) {

                    streetAddress = streetAddress+term.getValue();

            } else if("W08072".equals(term.getId())) {

                city = term.getValue();

            }
        }

        if(firstName.length()==0) {

            firstName = fullName;
        }

        return new SimpleSecureAddressImpl(
                0,
                firstName+" "+surname,
                careOf,
                streetAddress,
                new PostalCodeImpl(PostalCodeFormat.getByCountry(country), postalCodeString),
                city,
                country,
                AddressType.PERMANENT,
                new Date(),
                new GregorianCalendar(9999, 0, 1).getTime(),
                InformationProvider.POPULATION_REGISTERS
        );
    }


}
