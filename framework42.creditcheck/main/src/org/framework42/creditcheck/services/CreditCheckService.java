package org.framework42.creditcheck.services;

import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.CoApplicantApplicationResponse;
import org.framework42.creditcheck.model.CreditBureauApplication;
import org.framework42.creditcheck.model.CreditBureauContext;
import org.framework42.creditcheck.model.MainApplicantExtraApplicationResponse;

public interface CreditCheckService {

    CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) throws CreditCheckException;

    MainApplicantExtraApplicationResponse addMainApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId) throws CreditCheckException;

    CoApplicantApplicationResponse addCoApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId);

}
