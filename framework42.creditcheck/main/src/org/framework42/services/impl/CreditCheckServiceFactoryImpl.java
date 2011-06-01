package org.framework42.services.impl;

import org.framework42.model.CreditBureau;
import org.framework42.services.CreditCheckService;
import org.framework42.services.CreditCheckServiceFactory;

public class CreditCheckServiceFactoryImpl implements CreditCheckServiceFactory {

    @Override
    public CreditCheckService createForBureau(CreditBureau bureau) {

        if(bureau == CreditBureau.UC) {

            return new CreditCheckServiceUC();

        } else if(bureau == CreditBureau.EMULATOR_SWEDEN) {

            return new CreditCheckServiceEmulatorSweden();
        }


        throw new IllegalArgumentException("Can't create service, no implementation for credit bureau "+bureau.name()+" exists!");
    }

}
