package org.framework42.kreditz.model;

import org.framework42.model.Country;

public interface Provider {

    int getId();

    CredentialType getCredentialType();

    String getGroupName();

    String getDisplayName();

    String getType();

    String getIconURL();

}
