package org.framework42.services.impl;

import org.framework42.model.CreditBureauApplication;
import org.framework42.model.CreditBureauContext;
import org.framework42.services.CreditCheckService;

public class CreditCheckServiceUC implements CreditCheckService {

    @Override
    public CreditBureauApplication makeApplication(CreditBureauContext context, CreditBureauApplication application) {

        return application;
    }
}
