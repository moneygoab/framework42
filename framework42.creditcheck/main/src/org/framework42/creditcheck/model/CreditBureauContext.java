package org.framework42.creditcheck.model;

public interface CreditBureauContext {

    public int getId();

    public CreditBureau getCreditBureau();

    public String getUserId();

    public String getPassword();

    public String getName();

    public String getPolicyProduct();

    public String getPolicyRules();

}
