package org.framework42.creditcheck.parsers.uc;

import org.apache.log4j.Logger;
import org.framework42.creditcheck.model.Applicant;
import org.framework42.creditcheck.model.ApplicantNames;
import org.framework42.creditcheck.model.CreditBureauApplication;
import org.framework42.creditcheck.model.impl.ApplicantImpl;
import org.framework42.creditcheck.model.impl.ApplicantNamesImpl;
import org.framework42.model.*;
import org.framework42.address.model.AddressType;
import org.framework42.address.model.InformationProvider;
import org.framework42.address.model.PostalCode;
import org.framework42.address.model.PostalCodeFormat;
import org.framework42.address.model.TrustedAddress;
import org.framework42.address.model.impl.PostalCodeImpl;
import org.framework42.address.model.impl.SimpleSecureAddressImpl;;
import uc_webservice_test.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public enum ApplicantParserTest {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.nummer42.creditcheck");

    public Applicant createMainApplicant(UcReply reply, CreditBureauApplication application) {

        Group applicantInformationGroup = BaseParserTest.INSTANCE.findResponseGroup(reply, "W080", 0);

        Group decisionGroup = BaseParserTest.INSTANCE.findResponseGroup(reply, "W131", 0);

        float risk = 0;

        for(Term term: decisionGroup.getTerm()) {

            if("W13111".equals(term.getId())) {

                String cleaned = term.getValue().replaceAll("%", "").trim().replaceAll(",",".");
                risk = Float.parseFloat(cleaned);

            }
        }

        Group incomeGroup = null;
        List<Group> incomeGroupList = new ArrayList<>();
        try {
            incomeGroup = BaseParserTest.INSTANCE.findResponseGroup(reply, "W495", 0);
            incomeGroupList = BaseParserTest.INSTANCE.findResponseGroupList(reply, "W495", 0);
        } catch(IllegalArgumentException e) { logger.debug("No income group found for applicant "+application.getMainApplicant().getGovernmentId()+", assuming zero income."); }

        int income = 0;

        if(incomeGroup != null) {
            for(Term term: incomeGroup.getTerm()) {

                if("W49522".equals(term.getId())) {

                    income = Integer.parseInt(term.getValue());
                }
            }
        }

        int incomePrevious = 0;
        if(incomeGroupList.size()>1) {

            for(Term term: incomeGroupList.get(1).getTerm()) {

                if("W49522".equals(term.getId())) {

                    incomePrevious = Integer.parseInt(term.getValue());
                }
            }
        }

        return new ApplicantImpl(
                application.getMainApplicant().getId(),
                application.getMainApplicant().getGovernmentId(),
                new BigDecimal(risk),
                application.getMainApplicant().getBirthDate(),
                createApplicantNamesAutomaticCorrection(applicantInformationGroup),
                createAddress(applicantInformationGroup),
                application.getMainApplicant().getContactMethods(),
                income,
                incomePrevious
        );
    }

    public Applicant createCoApplicant(UcReply reply, CreditBureauApplication application) {

        try {

            Group applicantInformationGroup = BaseParserTest.INSTANCE.findResponseGroup(reply, "W080", 1);

            Group decisionGroup = BaseParserTest.INSTANCE.findResponseGroup(reply, "W131", 0);

            float risk = 0;

            for(Term term: decisionGroup.getTerm()) {

                if("W13125".equals(term.getId())) {

                    String cleaned = term.getValue().replaceAll("%", "").trim().replaceAll(",",".");
                    risk = Float.parseFloat(cleaned);
                }
            }

            Group incomeGroup = null;
            List<Group> incomeGroupList = new ArrayList<>();
            try {

                incomeGroup = BaseParserTest.INSTANCE.findResponseGroup(reply, "W495", 1);
                incomeGroupList = BaseParserTest.INSTANCE.findResponseGroupList(reply, "W495", 0);

            } catch(IllegalArgumentException e) { logger.debug("No income group found for co-applicant "+application.getCoApplicant().getGovernmentId()+", assuming zero income."); }

            int income = 0;

            if(incomeGroup != null) {
                for(Term term: incomeGroup.getTerm()) {

                    if("W49522".equals(term.getId())) {

                        income = Integer.parseInt(term.getValue());

                    }
                }
            }

            int incomePrevious = 0;
            if(incomeGroupList.size()>1) {

                for(Term term: incomeGroupList.get(1).getTerm()) {

                    if("W49522".equals(term.getId())) {

                        incomePrevious = Integer.parseInt(term.getValue());
                    }
                }
            }

            return new ApplicantImpl(
                    application.getCoApplicant().getId(),
                    application.getCoApplicant().getGovernmentId(),
                    new BigDecimal(risk),
                    application.getCoApplicant().getBirthDate(),
                    createApplicantNamesAutomaticCorrection(applicantInformationGroup),
                    createAddress(applicantInformationGroup),
                    application.getCoApplicant().getContactMethods(),
                    income,
                    incomePrevious
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

    public ApplicantNames createApplicantNamesAutomaticCorrection(Group group) {

        String firstName = "";
        String surname = "";
        String fullName = "";

        for(Term term: group.getTerm()) {

            if("W08085".equals(term.getId())) {

                firstName = term.getValue();

            } else if("W08083".equals(term.getId())) {

                fullName = term.getValue();

            }else if("W08084".equals(term.getId())) {

                surname = term.getValue();

            }
        }

        if(firstName==null || firstName.length()==0) {

            firstName = new StringBuilder(new StringBuilder(fullName).reverse().toString().replaceFirst(new StringBuilder(surname).reverse().toString(), "")).reverse().toString();
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
