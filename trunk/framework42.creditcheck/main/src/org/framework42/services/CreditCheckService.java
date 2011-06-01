package org.framework42.services;

import org.framework42.exceptions.CreditCheckException;
import org.framework42.model.CreditBureauApplication;
import org.framework42.model.CreditBureauContext;

public interface CreditCheckService {

    public CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) throws CreditCheckException;

}
