package org.framework42.kreditz.model;

import org.framework42.model.Country;

public interface Provider {

    int getId();

    Country getMarket();

    CredentialType getCredentialType();

    String getName();

    String getDisplayName();

    String getType();

    String getIconURL();

}
