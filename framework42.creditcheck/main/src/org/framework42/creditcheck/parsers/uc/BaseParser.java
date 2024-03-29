package org.framework42.creditcheck.parsers.uc;

import org.apache.log4j.Logger;
import org.framework42.creditcheck.exceptions.CreditCheckException;
import uc_webservice.Group;
import uc_webservice.Message;
import uc_webservice.Term;
import uc_webservice.UcReply;

import java.util.ArrayList;
import java.util.List;

public enum BaseParser {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.nummer42.creditcheck");

    public String getValueOfTerm(List<Term> termList, String termValue) {

        for(Term term: termList) {

            if(termValue.equalsIgnoreCase(term.getId())) {

                return term.getValue();
            }
        }

        throw new IllegalArgumentException("No term found with id "+termValue);
    }

    public String getValueOfTerm(UcReply reply, String groupId, String termValue) {

        return getValueOfTerm(findResponseGroup(reply, groupId, 0).getTerm(), termValue);
    }

    public Group findResponseGroup(UcReply reply, String groupId, int reportLevel) {

        List<Group> groups = reply.getUcReport().get(reportLevel).getXmlReply().getReports().get(0).getReport().get(0).getGroup();

        for(Group g: groups) {

            if(groupId.equals(g.getId())) {
                return g;
            }
        }

        throw new IllegalArgumentException("No decision group found in uc reply (id: "+groupId+")");
    }

    public List<Group> findResponseGroupList(UcReply reply, String groupId, int reportLevel) {

        List<Group> responseGroups = new ArrayList<>();

        List<Group> groups = reply.getUcReport().get(reportLevel).getXmlReply().getReports().get(0).getReport().get(0).getGroup();

        for(Group g: groups) {

            if(groupId.equals(g.getId())) {
                responseGroups.add(g);
            }
        }

        return responseGroups;
    }

    public void validateReplyAndStatus(UcReply reply, String governmentId) throws CreditCheckException {

        if(reply == null) {
            logger.info("UC could not be contacted or illegal government id of value "+governmentId+" in credit check");
            throw new CreditCheckException(999, "UC could not be contacted or illegal government id of value "+governmentId);
        }

        if("error".equals(reply.getStatus().getResult())) {

            Message errorMess = reply.getStatus().getMessage();

            logger.info(Integer.parseInt(errorMess.getId())+": "+ errorMess.getValue());
            throw new CreditCheckException(Integer.parseInt(errorMess.getId()), errorMess.getValue());
        }
    }

}
