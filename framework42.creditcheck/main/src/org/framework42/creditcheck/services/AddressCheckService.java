package org.framework42.creditcheck.services;

import org.framework42.creditcheck.exceptions.AddressCheckException;
import org.framework42.creditcheck.model.AddressCheckResponse;
import org.framework42.creditcheck.model.CreditBureauContext;
import org.framework42.model.TrustedAddress;

public interface AddressCheckService {

    public AddressCheckResponse getAddress(CreditBureauContext context, String governmentId) throws AddressCheckException;

}