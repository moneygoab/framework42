package org.framework42.creditcheck.services;

import org.framework42.creditcheck.model.CreditBureau;

public interface CreditCheckServiceFactory {

    public CreditCheckService createForBureau(CreditBureau bureau);

}
