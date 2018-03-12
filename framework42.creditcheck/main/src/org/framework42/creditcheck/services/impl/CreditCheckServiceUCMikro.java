package org.framework42.creditcheck.services.impl;

import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.*;
import org.framework42.creditcheck.model.impl.CreditBureauApplicationImpl;
import org.framework42.creditcheck.parsers.uc.ApplicantParser;
import org.framework42.creditcheck.parsers.uc.BaseParser;
import org.framework42.creditcheck.parsers.uc.CreditBureauResponseParser;
import org.framework42.creditcheck.services.CreditCheckService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uc_webservice.*;

public class CreditCheckServiceUCMikro implements CreditCheckService {

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

        if(context.isSendNewTotalDebt()) {

            TemplateParams ownParameters = new TemplateParams();
            Templateparam totalDebt = new Templateparam();
            totalDebt.setId(0);
            totalDebt.setValue(application.getPreviousDebt().getAmount().add(application.getAppliedAmount().getAmount()).intValue() + "");
            ownParameters.getTemplateparam().add(totalDebt);

            Templateparam totalCoDebt = new Templateparam();
            totalCoDebt.setId(1);
            totalCoDebt.setValue(application.getPreviousDebtCoApplicant().getAmount().intValue() + "");
            ownParameters.getTemplateparam().add(totalCoDebt);

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

        BaseParser.INSTANCE.validateReplyAndStatus(reply, application.getMainApplicant().getGovernmentId());

        return new CreditBureauApplicationImpl(
                application.getId(),
                application.getType(),
                ApplicationStatus.APPROVAL_PROCESS,
                application.getApplicationDate(),
                application.getAppliedAmount(),
                application.getPreviousDebt(),
                application.getPreviousDebtCoApplicant(),
                application.getApplicationChannel(),
                ApplicantParser.INSTANCE.createMainApplicant(reply, application),
                ApplicantParser.INSTANCE.createCoApplicant(reply, application),
                CreditBureauResponseParser.INSTANCE.createCreditBureauResponse(reply, application.getMainApplicant().getGovernmentId()),
                application.getExtendedApplicationId()
        );
    }

    @Override
    public MainApplicantExtraApplicationResponse addMainApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) throws CreditCheckException {

        throw new NotImplementedException();
    }

    @Override
    public CoApplicantApplicationResponse addCoApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) {

        throw new NotImplementedException();
    }
}
