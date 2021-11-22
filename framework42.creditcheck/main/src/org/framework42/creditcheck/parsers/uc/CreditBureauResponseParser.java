package org.framework42.creditcheck.parsers.uc;

import org.apache.log4j.Logger;
import org.framework42.creditcheck.model.ApplicationProductCategory;
import org.framework42.creditcheck.model.CreditBureauApplicationResponse;
import org.framework42.creditcheck.model.CreditCheckEngagements;
import org.framework42.creditcheck.model.CreditDecision;
import org.framework42.creditcheck.model.impl.CreditCheckEngagementsImpl;
import org.framework42.creditcheck.model.impl.SimpleCreditBureauApplicationResponse;
import org.framework42.services.Money;
import org.framework42.services.impl.MoneyImpl;
import uc_webservice.Group;
import uc_webservice.Term;
import uc_webservice.UcReply;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public enum CreditBureauResponseParser {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.nummer42.creditcheck");

    public CreditBureauApplicationResponse createCreditBureauResponse(UcReply reply, String governmentId) {

        Group decisionGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W131", 0);

        Money recommendedCredit = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE")));
        CreditDecision creditDecision = null;

        if(decisionGroup != null) {
            for(Term term: decisionGroup.getTerm()) {

                if("W13105".equals(term.getId())) {

                    creditDecision = CreditDecision.getByUCCode(term.getValue());
                    //W13106
                } else if("W13142".equals(term.getId())) {

                    recommendedCredit = new MoneyImpl(new BigDecimal(Integer.parseInt(term.getValue())), Currency.getInstance(new Locale("sv", "SE")));
                } else if("W13106".equals(term.getId())) {

                    recommendedCredit = new MoneyImpl(new BigDecimal(Integer.parseInt(term.getValue())), Currency.getInstance(new Locale("sv", "SE")));
                }
            }
        }

        //Group reasonCodesGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W132", 0);
        List<Group> groups = reply.getUcReport().get(0).getXmlReply().getReports().get(0).getReport().get(0).getGroup();
        List<Group> reasonCodesGroup = new ArrayList<Group>();
        for(Group g: groups) {

            if("W132".equals(g.getId())) {
                if(!"0".equals(g.getIndex())) {

                    reasonCodesGroup.add(g);
                }
            }
        }

        String reasonCodes = "";

        for(Group group:reasonCodesGroup) {

            for(Term term: group.getTerm()) {

                if("W13201".equals(term.getId())) {

                    reasonCodes += term.getValue()+"\n";
                }
            }
        }

        Group incomeGroup = null;
        List<Group> incomeGroupList = new ArrayList<>();
        try {
            incomeGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W491", 0);
            incomeGroupList = BaseParser.INSTANCE.findResponseGroupList(reply, "W491", 0);
        } catch(IllegalArgumentException e) {
            logger.debug("No income group found (W491) assuming 0 income.");
        }
        Money declaredIncome = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE")));

        if(incomeGroup != null) {
            for(Term term: incomeGroup.getTerm()) {

                if("W49122".equals(term.getId())) {

                    String val = term.getValue();
                    if(val.equalsIgnoreCase("<1")) {
                        val = "0";
                    }

                    declaredIncome = new MoneyImpl(new BigDecimal(Integer.parseInt(val)*1000), Currency.getInstance(new Locale("sv", "SE")));
                }
            }
        }

        Money declaredIncomePrevious = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE")));
        if(incomeGroupList.size()> 1) {
            for(Term term: incomeGroupList.get(1).getTerm()) {

                if("W49122".equals(term.getId())) {

                    String val = term.getValue();
                    if(val.equalsIgnoreCase("<1")) {
                        val = "0";
                    }

                    declaredIncomePrevious = new MoneyImpl(new BigDecimal(Integer.parseInt(val)*1000), Currency.getInstance(new Locale("sv", "SE")));
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
                logger.debug("First credit check for customer "+governmentId);
            }
        } catch(IllegalArgumentException e) {
            logger.debug("First credit check for customer "+governmentId);
        }

        int numberOfPreviousLoans = 0;

        Money sumOfPreviousLoans = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(new Locale("sv", "SE")));

        List<CreditCheckEngagements> creditCheckEngagements = new ArrayList<>();

        try {
            Group loansGroup = BaseParser.INSTANCE.findResponseGroup(reply, "W450", 0);

            if(loansGroup != null) {

                for(Term term: loansGroup.getTerm()) {

                    if("W45025".equals(term.getId())) {

                        try {

                        sumOfPreviousLoans = new MoneyImpl(new BigDecimal(term.getValue()), Currency.getInstance(new Locale("sv", "SE")));
                        } catch (Exception e) {logger.debug("CreditBureauResponseParser.W45025 - "+e.getMessage());}

                    } if("W45008".equals(term.getId())) {

                        try {

                            numberOfPreviousLoans = Integer.parseInt(term.getValue());
                        } catch (NumberFormatException e) {logger.debug("CreditBureauResponseParser.W45008 - "+e.getMessage());}
                    }
                }

            } else {
                logger.debug("First credit check for customer "+governmentId);
            }

            for(Group pg: BaseParser.INSTANCE.findResponseGroupList(reply, "W450", 0)) {

                LocalDate month = LocalDate.now();

                int amountBlanco = 0;
                int amountInstallment = 0;
                int amountAccountCredit = 0;
                int totalUsed = 0;
                int totalApproved = 0;
                int numberOfEngagements = 0;
                int numberOfCreditors = 0;
                int amountUsedAtMoneyGo = 0;
                int amountApprovedAtMoneyGo = 0;

                for(Term term: pg.getTerm()) {

                    switch(term.getId()) {

                        case "W45001":
                            month = LocalDate.parse(term.getValue(), DateTimeFormatter.ofPattern("yyyyMMdd"));
                            break;
                        case "W45025":
                            amountBlanco = Integer.parseInt(term.getValue());
                            break;
                        case "W45023":
                            amountInstallment = Integer.parseInt(term.getValue());
                            break;
                        case "W45024":
                            amountAccountCredit = Integer.parseInt(term.getValue());
                            break;
                        case "W45002":
                            totalUsed = Integer.parseInt(term.getValue());
                            break;
                        case "W45022":
                            totalApproved = Integer.parseInt(term.getValue());
                            break;
                        case "W45008":
                            numberOfEngagements = Integer.parseInt(term.getValue());
                            break;
                        case "W45009":
                            numberOfCreditors = Integer.parseInt(term.getValue());
                            break;
                        case "W45010":
                            amountUsedAtMoneyGo = Integer.parseInt(term.getValue());
                            break;
                        case "W45012":
                            amountApprovedAtMoneyGo = Integer.parseInt(term.getValue());
                            break;
                    }
                }

                creditCheckEngagements.add(new CreditCheckEngagementsImpl(month, amountBlanco, amountInstallment, amountAccountCredit, totalUsed, totalApproved, numberOfEngagements, numberOfCreditors, amountUsedAtMoneyGo, amountApprovedAtMoneyGo));
            }

        } catch(Exception e) {
            logger.debug("First credit check for customer "+governmentId);
        }



        //

        //

        /*
        String reasonCodes = "";

        if(decisionGroup != null) {
            for(Term term: decisionGroup.getTerm()) {

                if("W13114".equals(term.getValue())) {

                    reasonCodes = term.getValue();
                }
            }
        } */

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
                declaredIncomePrevious,
                numberOfDebtCollections,
                sumOfDebtCollections,
                numberOfPreviousLoans,
                sumOfPreviousLoans,
                reasonCodes,
                creditCheckEngagements
        );
    }

}
