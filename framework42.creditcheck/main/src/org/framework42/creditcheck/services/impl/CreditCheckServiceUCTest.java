package org.framework42.creditcheck.services.impl;

import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.*;
import org.framework42.creditcheck.model.impl.ApplicantImpl;
import org.framework42.creditcheck.model.impl.CoApplicantApplicationResponseImpl;
import org.framework42.creditcheck.model.impl.CreditBureauApplicationImpl;
import org.framework42.creditcheck.model.impl.MainApplicantExtraApplicationResponseImpl;
import org.framework42.creditcheck.parsers.uc.*;
import org.framework42.creditcheck.services.CreditCheckService;
import org.framework42.utils.LocalDateUtil;
import uc_webservice_test.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreditCheckServiceUCTest implements CreditCheckService {

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

        if(context.isSendNewTotalDebt()) {

            uc_webservice_test.TemplateParams ownParameters = new uc_webservice_test.TemplateParams();
            uc_webservice_test.Templateparam totalDebt = new uc_webservice_test.Templateparam();
            totalDebt.setId(0);
            totalDebt.setValue(application.getPreviousDebt().getAmount().add(application.getAppliedAmount().getAmount()).intValue() + "");
            ownParameters.getTemplateparam().add(totalDebt);

            if(context.isSendNewTotalDebtCoApplicant()) {

                uc_webservice_test.Templateparam totalCoDebt = new uc_webservice_test.Templateparam();
                totalCoDebt.setId(1);
                totalCoDebt.setValue(application.getPreviousDebtCoApplicant().getAmount().intValue() + "");
                ownParameters.getTemplateparam().add(totalCoDebt);
            }

            template.setTemplateParams(ownParameters);
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

        BaseParserTest.INSTANCE.validateReplyAndStatus(reply, application.getMainApplicant().getGovernmentId());

        return new CreditBureauApplicationImpl(
                application.getId(),
                application.getType(),
                ApplicationStatus.APPROVAL_PROCESS,
                application.getApplicationDate(),
                application.getAppliedAmount(),
                application.getPreviousDebt(),
                application.getPreviousDebtCoApplicant(),
                application.getApplicationChannel(),
                ApplicantParserTest.INSTANCE.createMainApplicant(reply, application),
                ApplicantParserTest.INSTANCE.createCoApplicant(reply, application),
                CreditBureauResponseParserTest.INSTANCE.createCreditBureauResponse(reply, application.getMainApplicant().getGovernmentId()),
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
                reply.getUcReport().get(0).getHtmlReply(),
                CreditBureauResponseParserTest.INSTANCE.createCreditBureauResponse(reply, governmentId)
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
        } catch(IllegalArgumentException e) {  }

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

        Group applicantInformationGroup = BaseParserTest.INSTANCE.findResponseGroup(reply, "W080", 0);

        return new ApplicantImpl(
                0,
                governmentId,
                new BigDecimal(risk),
                LocalDateUtil.getFromGovernmentId(governmentId),
                ApplicantParserTest.INSTANCE.createApplicantNames(applicantInformationGroup),
                ApplicantParserTest.INSTANCE.createAddress(applicantInformationGroup),
                new ArrayList<>(),
                income,
                incomePrevious
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
