package org.framework42.kreditz.model.impl;

import org.framework42.kreditz.model.CredentialType;
import org.framework42.kreditz.model.Provider;
import org.json.JSONObject;

public class ProviderImpl implements Provider {

    private final int id;

    private final CredentialType credentialType;

    private final String groupName;

    private final String displayName;

    private final String type;

    private final String iconURL;

    public ProviderImpl(JSONObject obj) {

        this.id = obj.getInt("id");
        this.credentialType = obj.has("credentials_type") ? CredentialType.valueOf(obj.getString("credentials_type")) : CredentialType.MOBILE_BANKID;
        this.groupName = obj.has("group_name") && !"null".equalsIgnoreCase(obj.get("group_name").toString()) ? obj.getString("group_name") : null;
        this.displayName = obj.getString("display_name");
        this.type = obj.has("type") ? obj.getString("type") : null;
        this.iconURL = obj.getJSONObject("icon").getString("url");
    }

    public ProviderImpl(int id, CredentialType credentialType, String groupName, String displayName, String type, String iconURL) {
        this.id = id;
        this.credentialType = credentialType;
        this.groupName = groupName;
        this.displayName = displayName;
        this.type = type;
        this.iconURL = iconURL;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public CredentialType getCredentialType() {
        return credentialType;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getIconURL() {
        return iconURL;
    }
}
