package org.framework42.creditcheck.services.impl;

import org.framework42.creditcheck.dao.InternalAddressCheckDAO;
import org.framework42.creditcheck.exceptions.AddressCheckException;
import org.framework42.creditcheck.model.AddressCheckResponse;
import org.framework42.creditcheck.model.AddressStatus;
import org.framework42.creditcheck.model.CreditBureauContext;
import org.framework42.creditcheck.model.impl.AddressCheckResponseImpl;
import org.framework42.creditcheck.services.AddressCheckService;
import org.framework42.model.TrustedAddress;
import org.framework42.services.ProxyService;

public class AddressCheckServiceSuperFileImpl extends ProxyService<AddressCheckServiceSuperFileImpl> implements AddressCheckService {

    private final InternalAddressCheckDAO addressCheckDAO;

    public AddressCheckServiceSuperFileImpl(InternalAddressCheckDAO addressCheckDAO) {

        super("org.framework42.creditcheck.services");

        this.addressCheckDAO = addressCheckDAO;
    }

    @Override
    public AddressCheckResponse getAddress(CreditBureauContext context, String governmentId) throws AddressCheckException {

        try {

            return new AddressCheckResponseImpl(AddressStatus.OK, addressCheckDAO.getAddress(governmentId));

        } catch (RuntimeException e) {

            throw new AddressCheckException(e.getMessage());
        }

    }
}
