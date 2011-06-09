package org.framework42.services.impl;

import org.apache.log4j.Logger;
import org.framework42.exceptions.CreditCheckException;
import org.framework42.model.*;
import org.framework42.model.impl.*;
import org.framework42.services.CreditCheckService;
import org.framework42.services.Money;
import uc_webservice.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class CreditCheckServiceUC implements CreditCheckService {

    private final Logger logger = Logger.getLogger("org.framework42.creditcheck");

    @Override
    public CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) throws CreditCheckException {

        UcOrders orders = new UCOrderService().getUcOrders2();

        IndividualReport report = createIndividualReport(context, application, createUcCustomer(context));
        UcReply reply = orders.individualReport(report);

        return parseUCResponse(reply, application);
    }

    private Customer createUcCustomer(CreditBureauContext context) {

        Customer ucCustomer = new Customer();
        ucCustomer.setUserId(context.getUserId());
        ucCustomer.setName(context.getName());
        ucCustomer.setPassword(context.getPassword());

        return ucCustomer;
    }

    private CreditBureauApplication parseUCResponse(UcReply reply, CreditBureauApplication application) throws CreditCheckException {

        String governmentId = application.getMainApplicant().getGovernmentId();

        if(reply == null) {
            logger.info("Illegal government id of value "+governmentId+" in credit check");
            throw new CreditCheckException(999, "Illegal government id of value "+governmentId);
        }

        validateStatus(reply.getStatus());

        return new CreditBureauApplicationImpl(
                application.getId(),
                ApplicationStatus.APPROVAL_PROCESS,
                application.getApplicationDate(),
                application.getAppliedAmount(),
                application.getApplicationChannel(),
                createMainApplicant(reply, application),
                createCoApplicant(reply, application),
                createCreditBureauResponse(reply, application)
        );
    }

    private Applicant createMainApplicant(UcReply reply, CreditBureauApplication application) {

        Group applicantInformationGroup = findResponseGroup(reply, "W080", 0);

        Group decisionGroup = findResponseGroup(reply, "W131", 0);

        float risk = 0;

        for(Term term: decisionGroup.getTerm()) {

            if("W13111".equals(term.getId())) {

                String cleaned = term.getValue().replaceAll("%", "").trim().replaceAll(",",".");
                risk = Float.parseFloat(cleaned);
            }
        }

        Group incomeGroup = findResponseGroup(reply, "W495", 0);

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

    private Applicant createCoApplicant(UcReply reply, CreditBureauApplication application) {

        try {

            Group applicantInformationGroup = findResponseGroup(reply, "W080", 1);

            Group decisionGroup = findResponseGroup(reply, "W131", 0);

            float risk = 0;

            for(Term term: decisionGroup.getTerm()) {

                if("W13125".equals(term.getId())) {

                    String cleaned = term.getValue().replaceAll("%", "").trim().replaceAll(",",".");
                    risk = Float.parseFloat(cleaned);
                }
            }

            Group incomeGroup = findResponseGroup(reply, "W495", 1);

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
        PostalCode postalCode = null;
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

    private CreditBureauApplicationResponse createCreditBureauResponse(UcReply reply, CreditBureauApplication application) {

        Group decisionGroup = findResponseGroup(reply, "W131", 0);

        Money recommendedCredit = application.getAppliedAmount();
        CreditDecision creditDecision = null;

        for(Term term: decisionGroup.getTerm()) {

            if("W13105".equals(term.getId())) {

                creditDecision = CreditDecision.getByUCCode(term.getValue());

            } else if("W13106".equals(term.getId())) {

                recommendedCredit = new MoneyImpl(new BigDecimal(Integer.parseInt(term.getValue())), Currency.getInstance(new Locale("sv", "SE")));
            }
        }

        if(creditDecision != CreditDecision.APPROVED && creditDecision != CreditDecision.REVIEW_REQUIRED) {

            recommendedCredit = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE")));
        }

        Group sumGroup = findResponseGroup(reply, "W611", 0);

        Money declaredIncome = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE")));

        int numberOfCreditChecks = 0;

        int numberOfDebtCollections = 0;

        Money sumOfDebtCollections = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE")));

        for(Term term: sumGroup.getTerm()) {

            if("W61109".equals(term.getId())) {

                numberOfDebtCollections = Integer.parseInt(term.getValue());

            } else if("W61110".equals(term.getId())) {

                sumOfDebtCollections = new MoneyImpl(new BigDecimal(term.getValue()), Currency.getInstance(new Locale("sv", "SE")));

            } else if("W61111".equals(term.getId())) {

                numberOfCreditChecks = Integer.parseInt(term.getValue());
            }
        }

        return new SimpleCreditBureauApplicationResponse(
                creditDecision,
                recommendedCredit,
                reply.getUcReport().get(0).getHtmlReply(),
                numberOfCreditChecks,
                declaredIncome,
                numberOfDebtCollections,
                sumOfDebtCollections
        );
    }

    private Group findResponseGroup(UcReply reply, String groupId, int reportLevel) {

        List<Group> groups = reply.getUcReport().get(reportLevel).getXmlReply().getReports().get(0).getReport().get(0).getGroup();

        for(Group g: groups) {

            if(groupId.equals(g.getId())) {
                return g;
            }
        }

        logger.error("UCCreditCheckService.findResponseGroup: No decision group found in uc reply (id: "+groupId+")");
        throw new IllegalArgumentException("No decision group found in uc reply (id: "+groupId+")");
    }

    private void validateStatus(Status status) throws CreditCheckException {

        if("error".equals(status.getResult())) {

            Message errorMess = status.getMessage();

            logger.info(Integer.parseInt(errorMess.getId())+": "+ errorMess.getValue());
            throw new CreditCheckException(Integer.parseInt(errorMess.getId()), errorMess.getValue());
        }

    }

    private IndividualReport createIndividualReport(CreditBureauContext context, CreditBureauApplication application, Customer ucCustomer){

        Template template = new Template();
        template.setId(context.getPolicyRules());
        if(application.getCoApplicant()!=null) {
            template.setCoObject(application.getCoApplicant().getGovernmentId());
        }
        TemplateIncome income = new TemplateIncome();
        income.setType("W13137");
        income.setValue(400000);
        template.setIncome(income);

        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setHtmlReply(true);
        reportQuery.setCreditSeeked(application.getAppliedAmount().getAmount().intValue());
        reportQuery.setObject(application.getMainApplicant().getGovernmentId());
        reportQuery.setTemplate(template);
        reportQuery.setXmlReply(true);

        IndividualReport individualReport = new IndividualReport();
        individualReport.setCustomer(ucCustomer);
        individualReport.setIndividualReportQuery(reportQuery);
        individualReport.setProduct(context.getPolicyProduct());

        return individualReport;
    }

}
