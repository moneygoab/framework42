package org.framework42.creditcheck.services;

import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.CreditBureauCompanyApplicationResponse;
import org.framework42.creditcheck.model.CreditBureauContext;

public interface CreditCheckCompanyService {

    CreditBureauCompanyApplicationResponse makeApplication(CreditBureauContext context, String governmentId) throws CreditCheckException;

}
