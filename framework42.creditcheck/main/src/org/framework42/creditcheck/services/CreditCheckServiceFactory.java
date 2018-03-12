package org.framework42.creditcheck.services;

import org.framework42.creditcheck.model.CreditBureau;

public interface CreditCheckServiceFactory {

    CreditCheckService createForBureau(CreditBureau bureau);

}
