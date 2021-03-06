package org.framework42.creditcheck.dao;

import org.framework42.creditcheck.exceptions.CreditCheckException;
import org.framework42.creditcheck.model.InternalCreditCheck;

public interface InternalCreditCheckDAO {

    InternalCreditCheck makeCreditCheck(String governmentId) throws CreditCheckException;

}
