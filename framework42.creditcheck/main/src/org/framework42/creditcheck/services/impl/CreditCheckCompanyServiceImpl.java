package org.framework42.creditcheck.services.impl;

import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.CreditBureau;
import org.framework42.creditcheck.model.CreditBureauCompanyApplicationResponse;
import org.framework42.creditcheck.model.CreditBureauContext;
import org.framework42.creditcheck.model.CreditDecision;
import org.framework42.creditcheck.model.impl.CreditBureauCompanyApplicationResponseImpl;
import org.framework42.creditcheck.model.impl.CreditBureauContextImpl;
import org.framework42.creditcheck.parsers.uc.BaseParser;
import org.framework42.creditcheck.services.CreditCheckCompanyService;
import uc_webservice.*;

public class CreditCheckCompanyServiceImpl implements CreditCheckCompanyService {

    public static void main(String[] args) throws Exception {

        CreditBureauContext context = new CreditBureauContextImpl(0, CreditBureau.UC, "UC", "D6AZ3", "T0", "UC", "410", "K4F", false, false);

        CreditBureauCompanyApplicationResponse resp = new CreditCheckCompanyServiceImpl().makeApplication(context, "5591314447");

        System.out.println(resp.getCreditCheckAsHtml());
    }

    public CreditCheckCompanyServiceImpl() {
    }

    @Override
    public CreditBureauCompanyApplicationResponse makeApplication(CreditBureauContext context, String governmentId) throws CreditCheckException {

        UcOrders orders = new UCOrderService().getUcOrders2();

        CompanyReport report = createBusinessReport(context, governmentId, createUcCustomer(context));

        UcReply reply = orders.companyReport(report);

        return parseResponse(reply, governmentId);
    }

    private Customer createUcCustomer(CreditBureauContext context) {

        Customer ucCustomer = new Customer();
        ucCustomer.setUserId(context.getUserId());
        ucCustomer.setPassword(context.getPassword());

        return ucCustomer;
    }

    private CompanyReport createBusinessReport(CreditBureauContext context, String governmentId, Customer ucCustomer){

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

    private CreditBureauCompanyApplicationResponse parseResponse(UcReply reply, String governmentId) throws CreditCheckException {

        BaseParser.INSTANCE.validateReplyAndStatus(reply, governmentId);

        return new CreditBureauCompanyApplicationResponseImpl(
                governmentId,
                reply.getUcReport().get(0).getHtmlReply(),
                parseCreditDecision(reply)
        );
    }

    private CreditDecision parseCreditDecision(UcReply ucReply) {

        Group decisionGroup = BaseParser.INSTANCE.findResponseGroup(ucReply, "W131", 0);

        CreditDecision creditDecision = CreditDecision.NOT_DECIDED_YET;

        if(decisionGroup != null) {
            for (Term term : decisionGroup.getTerm()) {

                if ("W13105".equals(term.getId())) {

                    creditDecision = CreditDecision.getByUCCode(term.getValue());
                }
            }
        }

        return creditDecision;
    }


}
