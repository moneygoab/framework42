package org.framework42.creditcheck.services;

import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.CoApplicantApplicationResponse;
import org.framework42.creditcheck.model.CreditBureauApplication;
import org.framework42.creditcheck.model.CreditBureauContext;
import org.framework42.creditcheck.model.MainApplicantExtraApplicationResponse;

public interface CreditCheckService {

    public CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) throws CreditCheckException;

    public MainApplicantExtraApplicationResponse addMainApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId);

    public CoApplicantApplicationResponse addCoApplicant(CreditBureauContext context, int appliedAmount, String governmentId, int applicationId);

}
