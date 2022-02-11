package org.framework42.kreditz.model.impl;

import org.framework42.kreditz.model.CredentialType;
import org.framework42.kreditz.model.Provider;
import org.framework42.model.Country;
import org.json.JSONObject;

public class ProviderImpl implements Provider {

    private final int id;

    private final Country market;

    private final CredentialType credentialType;

    private final String name;

    private final String displayName;

    private final String type;

    private final String iconURL;

    public ProviderImpl(JSONObject obj) {

        this.id = obj.getInt("id");
        this.market = Country.parseFromString(obj.getString("market"));
        this.credentialType = CredentialType.valueOf(obj.getString("credentials_type"));
        this.name = obj.getString("name");
        this.displayName = obj.getString("display_name");
        this.type = obj.getString("type");
        this.iconURL = obj.getJSONObject("icon").getString("url");
    }

    public ProviderImpl(int id, Country market, CredentialType credentialType, String name, String displayName, String type, String iconURL) {
        this.id = id;
        this.market = market;
        this.credentialType = credentialType;
        this.name = name;
        this.displayName = displayName;
        this.type = type;
        this.iconURL = iconURL;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Country getMarket() {
        return market;
    }

    @Override
    public CredentialType getCredentialType() {
        return credentialType;
    }

    @Override
    public String getName() {
        return name;
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
