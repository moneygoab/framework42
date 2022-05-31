package org.framework42.creditcheck.services.impl;

import org.apache.log4j.Logger;
import org.framework42.address.model.AddressType;
import org.framework42.address.model.InformationProvider;
import org.framework42.address.model.PostalCodeFormat;
import org.framework42.address.model.impl.PostalCodeImpl;
import org.framework42.address.model.impl.SimpleSecureAddressImpl;
import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.CreditBureau;
import org.framework42.creditcheck.model.CreditBureauCompanyApplicationResponse;
import org.framework42.creditcheck.model.CreditBureauContext;
import org.framework42.creditcheck.model.CreditDecision;
import org.framework42.creditcheck.model.impl.CreditBureauCompanyApplicationResponseImpl;
import org.framework42.creditcheck.model.impl.CreditBureauContextImpl;
import org.framework42.creditcheck.parsers.uc.BaseParser;
import org.framework42.creditcheck.services.CreditCheckCompanyService;
import org.framework42.model.Country;
import org.framework42.utils.LocalDateUtil;
import org.framework42.utils.services.impl.GovernmentIdValidatorImpl;
import uc_webservice.*;

import java.time.DateTimeException;

public class CreditCheckCompanyServiceImpl implements CreditCheckCompanyService {

    public static void main(String[] args) throws Exception {

        CreditBureauContext context = new CreditBureauContextImpl(0, CreditBureau.UC, "UC", "D6AZ3", "X0", "UC", "410", "GOH", false, false, false);

        CreditBureauCompanyApplicationResponse resp = new CreditCheckCompanyServiceImpl().makeApplication(context, "8212119153");

        System.out.println(resp.getCreditCheckAsHtml());
    }

    public CreditCheckCompanyServiceImpl() {
    }

    @Override
    public CreditBureauCompanyApplicationResponse makeApplication(CreditBureauContext context, String governmentId) throws CreditCheckException {

        UcOrders orders = new UCOrderService().getUcOrders2();

        UcReply reply = null;

        boolean realCompany = true;

        if(GovernmentIdValidatorImpl.INSTANCE.isPrivatePerson(governmentId)) {

            BusinessReport report = createBusinessReport(context, governmentId, createUcCustomer(context));

            reply = orders.businessReport(report);

            realCompany = false;

        } else {

            CompanyReport report = createCompanyReport(context, governmentId, createUcCustomer(context));

            reply = orders.companyReport(report);
        }

        return parseResponse(reply, realCompany, governmentId);
    }

    private Customer createUcCustomer(CreditBureauContext context) {

        Customer ucCustomer = new Customer();
        ucCustomer.setUserId(context.getUserId());
        ucCustomer.setPassword(context.getPassword());

        return ucCustomer;
    }

    private BusinessReport createBusinessReport(CreditBureauContext context, String governmentId, Customer ucCustomer) {

        Template template = new Template();
        template.setId(context.getPolicyRules());


        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setHtmlReply(true);
        //reportQuery.setCreditSeeked(application.getAppliedAmount().getAmount().intValue());
        reportQuery.setObject(governmentId);
        reportQuery.setTemplate(template);
        reportQuery.setXmlReply(true);

        BusinessReport businessReport = new BusinessReport();
        businessReport.setCustomer(ucCustomer);
        businessReport.setBusinessReportQuery(reportQuery);
        businessReport.setProduct(context.getPolicyProduct());

        return businessReport;
    }

    private CompanyReport createCompanyReport(CreditBureauContext context, String governmentId, Customer ucCustomer){

        Template template = new Template();
        template.setId(context.getPolicyRules());


        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setHtmlReply(true);
        //reportQuery.setCreditSeeked(application.getAppliedAmount().getAmount().intValue());
        reportQuery.setObject(governmentId);
        reportQuery.setTemplate(template);
        reportQuery.setXmlReply(true);

        CompanyReport businessReport = new CompanyReport();
        businessReport.setCustomer(ucCustomer);
        businessReport.setCompanyReportQuery(reportQuery);
        businessReport.setProduct(context.getPolicyProduct());

        return businessReport;
    }

    private CreditBureauCompanyApplicationResponse parseResponse(UcReply reply, boolean realCompany, String governmentId) throws CreditCheckException {

        BaseParser.INSTANCE.validateReplyAndStatus(reply, governmentId);

        if(realCompany) {

            Group decisionGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W010", 0);

            return new CreditBureauCompanyApplicationResponseImpl(
                    governmentId,
                    BaseParser.INSTANCE.getValueOfTerm(decisionGroup.getTerm(), "W01080"),
                    reply.getUcReport().get(0).getHtmlReply(),
                    parseCreditDecision(reply),
                    new SimpleSecureAddressImpl(
                            0,
                            BaseParser.INSTANCE.getValueOfTerm(decisionGroup.getTerm(), "W01080"),
                            "",
                            BaseParser.INSTANCE.getValueOfTerm(decisionGroup.getTerm(), "W01081"),
                            new PostalCodeImpl(PostalCodeFormat.getByCountry(Country.SWEDEN), BaseParser.INSTANCE.getValueOfTerm(decisionGroup.getTerm(), "W01003")),
                            BaseParser.INSTANCE.getValueOfTerm(decisionGroup.getTerm(), "W01082"),
                            Country.SWEDEN,
                            InformationProvider.POPULATION_REGISTERS
                    )
            );

        } else {

            Group decisionGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W080", 0);

            return new CreditBureauCompanyApplicationResponseImpl(
                    governmentId,
                    BaseParser.INSTANCE.getValueOfTerm(decisionGroup.getTerm(), "W08070"),
                    reply.getUcReport().get(0).getHtmlReply(),
                    parseCreditDecision(reply),
                    new SimpleSecureAddressImpl(
                            0,
                            BaseParser.INSTANCE.getValueOfTerm(decisionGroup.getTerm(), "W08070"),
                            "",
                            BaseParser.INSTANCE.getValueOfTerm(decisionGroup.getTerm(), "W08071"),
                            new PostalCodeImpl(PostalCodeFormat.getByCountry(Country.SWEDEN), BaseParser.INSTANCE.getValueOfTerm(decisionGroup.getTerm(), "W08005")),
                            BaseParser.INSTANCE.getValueOfTerm(decisionGroup.getTerm(), "W08072"),
                            Country.SWEDEN,
                            InformationProvider.POPULATION_REGISTERS
                    )
            );
        }
    }

    private CreditDecision parseCreditDecision(UcReply ucReply) {

        CreditDecision creditDecision = CreditDecision.REVIEW_REQUIRED;

        try {

            Group decisionGroup = BaseParser.INSTANCE.findResponseGroup(ucReply, "W131", 0);

            if (decisionGroup != null) {
                for (Term term : decisionGroup.getTerm()) {

                    if ("W13105".equals(term.getId())) {

                        creditDecision = CreditDecision.getByUCCode(term.getValue());
                    }
                }
            }

        } catch (IllegalArgumentException e) {

            Logger.getLogger("org.nummer42.creditcheck").error("No credit decision in UC response, check if correct Uc template is set. Defaults to Review Required.");
        }

        return creditDecision;
    }


}
