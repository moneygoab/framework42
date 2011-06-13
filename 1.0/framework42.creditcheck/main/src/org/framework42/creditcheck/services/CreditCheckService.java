package org.framework42.creditcheck.services;

import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.CreditBureauApplication;
import org.framework42.creditcheck.model.CreditBureauContext;

public interface CreditCheckService {

    public CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) throws
            CreditCheckException;

}
