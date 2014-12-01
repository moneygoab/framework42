package org.framework42.creditcheck.services.impl;

import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.*;
import org.framework42.creditcheck.model.impl.ApplicantImpl;
import org.framework42.creditcheck.model.impl.CoApplicantApplicationResponseImpl;
import org.framework42.creditcheck.model.impl.CreditBureauApplicationImpl;
import org.framework42.creditcheck.model.impl.MainApplicantExtraApplicationResponseImpl;
import org.framework42.creditcheck.parsers.uc.ApplicantParser;
import org.framework42.creditcheck.parsers.uc.BaseParser;
import org.framework42.creditcheck.parsers.uc.CreditBureauResponseParser;
import org.framework42.creditcheck.services.CreditCheckService;
import uc_webservice.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class CreditCheckServiceUC implements CreditCheckService {

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

    private IndividualReport createIndividualReport(CreditBureauContext context, CreditBureauApplication application, Customer ucCustomer){

        Template template = new Template();
        template.setId(context.getPolicyRules());
        if(application.getCoApplicant()!=null) {
            template.setCoObject(application.getCoApplicant().getGovernmentId());
        }

        if(application.getMainApplicant().getAnnualIncome() != 0) {
            TemplateIncome income = new TemplateIncome();
            income.setType("W13137");
            income.setValue(application.getMainApplicant().getAnnualIncome());
            template.setIncome(income);
        }

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

    private CreditBureauApplication parseUCResponse(UcReply reply, CreditBureauApplication application) throws CreditCheckException {

        BaseParser.INSTANCE.validateReplyAndStatus(reply, application.getMainApplicant().getGovernmentId());

        return new CreditBureauApplicationImpl(
                application.getId(),
                application.getType(),
                ApplicationStatus.APPROVAL_PROCESS,
                application.getApplicationDate(),
                application.getAppliedAmount(),
                application.getApplicationChannel(),
                ApplicantParser.INSTANCE.createMainApplicant(reply, application),
                ApplicantParser.INSTANCE.createCoApplicant(reply, application),
                CreditBureauResponseParser.INSTANCE.createCreditBureauResponse(reply, application),
                application.getExtendedApplicationId()
        );
    }

    @Override
    public MainApplicantExtraApplicationResponse addMainApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) throws CreditCheckException {

        UcReply reply = makeExtraApplicantCreditCheck(context, appliedAmount, governmentId);

        if("error".equalsIgnoreCase(reply.getStatus().getResult())) {

            throw new CreditCheckException(reply.getStatus().getMessage().getId()+" - "+reply.getStatus().getMessage().getValue());
        }

            Applicant extraApplicant = parseExtraApplicant(governmentId, reply);

            MainApplicantExtraApplicationResponse response = new MainApplicantExtraApplicationResponseImpl(
                    applicationId,
                    extraApplicant,
                    reply.getUcReport().get(0).getHtmlReply()
            );

            return response;
    }

    @Override
    public CoApplicantApplicationResponse addCoApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) {

        UcReply reply = makeExtraApplicantCreditCheck(context, appliedAmount, governmentId);

        Applicant coApplicant = parseExtraApplicant(governmentId, reply);

        CoApplicantApplicationResponse response = new CoApplicantApplicationResponseImpl(
                applicationId,
                coApplicant,
                reply.getUcReport().get(0).getHtmlReply()
        );

        return response;
    }

    private Applicant parseExtraApplicant(String governmentId, UcReply reply) {

        UcReport report = reply.getUcReport().get(0);

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
        } catch(IllegalArgumentException e) {  }

        int income = 0;

        if(incomeGroup != null) {
            for(Term term: incomeGroup.getTerm()) {

                if("W49522".equals(term.getId())) {

                    income = Integer.parseInt(term.getValue());

                }
            }
        }

        Group applicantInformationGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W080", 0);

        return new ApplicantImpl(
                0,
                governmentId,
                new BigDecimal(risk),
                new Date(),
                ApplicantParser.INSTANCE.createApplicantNames(applicantInformationGroup),
                ApplicantParser.INSTANCE.createAddress(applicantInformationGroup),
                new ArrayList<ApplicantContactMethod>(),
                income
        );
    }

    private UcReply makeExtraApplicantCreditCheck(CreditBureauContext context, int appliedAmount, String governmentId) {

        UcOrders orders = new UCOrderService().getUcOrders2();

        Template template = new Template();
        template.setId(context.getPolicyRules());

        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setHtmlReply(true);
        reportQuery.setCreditSeeked(appliedAmount);
        reportQuery.setObject(governmentId);
        reportQuery.setTemplate(template);
        reportQuery.setXmlReply(true);

        IndividualReport individualReport = new IndividualReport();
        individualReport.setCustomer(createUcCustomer(context));
        individualReport.setIndividualReportQuery(reportQuery);
        individualReport.setProduct(context.getPolicyProduct());

        return orders.individualReport(individualReport);
    }

}
