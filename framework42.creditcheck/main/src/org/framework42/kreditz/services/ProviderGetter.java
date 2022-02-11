package org.framework42.kreditz.services;

import org.framework42.kreditz.model.Provider;
import org.framework42.kreditz.model.impl.ProviderImpl;
import org.framework42.model.Country;
import org.json.JSONArray;
import org.json.RESTJSONCaller;
import org.json.RESTJSONResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum ProviderGetter {

    I;

    public List<Provider> getProviderList(String baseURL, String accessToken, Country market) throws IOException {

        List<Provider> foundList = new ArrayList<>();

        HashMap<String,String> headers = new HashMap<>();

        headers.put("Authorization", "Bearer "+accessToken);

        RESTJSONResponse resp = RESTJSONCaller.INSTANCE.makeGetCall("", "", baseURL+"/kreditz/api/v2/providers", "market="+market.getAlpha2Code(), headers);

        if(resp.getStatus()==200 && resp.getObject().has("status") && "true".equalsIgnoreCase(resp.getObject().getString("status"))) {

            JSONArray array = resp.getObject().getJSONArray("data");

            for(int i=0;i<array.length();i++) {

                foundList.add(new ProviderImpl(array.getJSONObject(i)));
            }

        } else {

            throw new IOException(resp.getObject().toString(2));
        }

        return foundList;
    }

}
