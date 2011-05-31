package org.framework42.services;

import org.framework42.model.CreditBureau;

public interface CreditCheckServiceFactory {

    public CreditCheckService createForBureau(CreditBureau bureau);

}
