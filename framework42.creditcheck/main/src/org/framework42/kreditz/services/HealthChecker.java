package org.framework42.kreditz.services;

import org.framework42.model.Country;
import org.json.JSONObject;
import org.json.RESTJSONCaller;
import org.json.RESTJSONResponse;

import java.io.IOException;
import java.util.HashMap;

public enum HealthChecker {

    I;

    public boolean isKreditzUp(String baseURL, String accessToken, Country market) throws IOException {

        HashMap<String,String> headers = new HashMap<>();

        headers.put("Authorization", "Bearer "+accessToken);

        RESTJSONResponse resp = RESTJSONCaller.INSTANCE.makeGetCall("", "", baseURL+"/kreditz/api/v4/ping", "market="+market.getAlpha2Code(), headers);

        return resp!=null && resp.getStatus()==204;
    }

    public JSONObject doHealthCheck(String baseURL, String accessToken, Country market) throws IOException {

        HashMap<String,String> headers = new HashMap<>();

        headers.put("Authorization", "Bearer "+accessToken);

        RESTJSONResponse resp = RESTJSONCaller.INSTANCE.makeGetCall("", "", baseURL+"/kreditz/api/v4/healthcheck", "market="+market.getAlpha2Code(), headers);

        if(resp.getStatus()==200) {

            return resp.getObject();

        } else {

            return resp.getObject();
        }
    }


}
