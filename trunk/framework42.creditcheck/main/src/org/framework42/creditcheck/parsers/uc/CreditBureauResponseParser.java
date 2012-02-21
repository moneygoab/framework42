package org.framework42.creditcheck.parsers.uc;

import org.apache.log4j.Logger;
import org.framework42.creditcheck.model.CreditBureauApplication;
import org.framework42.creditcheck.model.CreditBureauApplicationResponse;
import org.framework42.creditcheck.model.CreditDecision;
import org.framework42.creditcheck.model.impl.SimpleCreditBureauApplicationResponse;
import org.framework42.services.Money;
import org.framework42.services.impl.MoneyImpl;
import uc_webservice.Group;
import uc_webservice.Term;
import uc_webservice.UcReply;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public enum CreditBureauResponseParser {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.nummer42.creditcheck");

    public CreditBureauApplicationResponse createCreditBureauResponse(UcReply reply, CreditBureauApplication application) {

        Group decisionGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W131", 0);

        Money recommendedCredit = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE")));
        CreditDecision creditDecision = null;

        if(decisionGroup != null) {
            for(Term term: decisionGroup.getTerm()) {

                if("W13105".equals(term.getId())) {

                    creditDecision = CreditDecision.getByUCCode(term.getValue());

                } else if("W13106".equals(term.getId())) {

                    recommendedCredit = new MoneyImpl(new BigDecimal(Integer.parseInt(term.getValue())), Currency.getInstance(new Locale("sv", "SE")));
                }
            }
        }

        Group incomeGroup = null;
        try {
            incomeGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W491", 0);
        } catch(IllegalArgumentException e) {
            logger.debug("No income group found (W491) assuming 0 income.");
        }
        Money declaredIncome = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE")));

        if(incomeGroup != null) {
            for(Term term: incomeGroup.getTerm()) {

                if("W49122".equals(term.getId())) {

                    declaredIncome = new MoneyImpl(new BigDecimal(Integer.parseInt(term.getValue())*1000), Currency.getInstance(new Locale("sv", "SE")));
                }
            }
        }

        int numberOfDebtCollections = 0;

        Money sumOfDebtCollections = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE")));

        try {
            Group sumGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W611", 0);

            for(Term term: sumGroup.getTerm()) {

                if("W61109".equals(term.getId())) {

                    numberOfDebtCollections = Integer.parseInt(term.getValue());

                } else if("W61110".equals(term.getId())) {

                    sumOfDebtCollections = new MoneyImpl(new BigDecimal(term.getValue()), Currency.getInstance(new Locale("sv", "SE")));

                }
            }
        } catch(IllegalArgumentException e) {

            logger.debug("Non required data not present for credit check statistics -- " + e);
        }


        int numberOfCreditChecks = 0;

        try {
            Group sumGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W640", 0);

            if(sumGroup != null) {
                for(Term term: sumGroup.getTerm()) {

                    if("W64001".equals(term.getId())) {

                        numberOfCreditChecks = Integer.parseInt(term.getValue());
                    }
                }
            } else {
                logger.debug("First credit check for customer "+application.getMainApplicant().getGovernmentId());
            }
        } catch(IllegalArgumentException e) {
            logger.debug("First credit check for customer "+application.getMainApplicant().getGovernmentId());
        }


        String reasonCodes = "";

        if(decisionGroup != null) {
            for(Term term: decisionGroup.getTerm()) {

                if("W13114".equals(term.getValue())) {

                    reasonCodes = term.getValue();
                }
            }
        }

        String coApplicantHtmlReply = "";
        if(reply.getUcReport().size()>1) {

            coApplicantHtmlReply = reply.getUcReport().get(1).getHtmlReply();
        }

        return new SimpleCreditBureauApplicationResponse(
                creditDecision,
                recommendedCredit,
                reply.getUcReport().get(0).getHtmlReply(),
                coApplicantHtmlReply,
                numberOfCreditChecks,
                declaredIncome,
                numberOfDebtCollections,
                sumOfDebtCollections,
                reasonCodes
        );
    }

}
