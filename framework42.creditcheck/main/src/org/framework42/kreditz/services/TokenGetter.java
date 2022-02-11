package org.framework42.kreditz.services;

import org.framework42.kreditz.model.Token;
import org.framework42.kreditz.model.impl.TokenImpl;
import org.json.JSONObject;
import org.json.RESTJSONCaller;
import org.json.RESTJSONResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum TokenGetter {

    I;
    //https://vista.kreditz.com/core
    public Token createAccessToken(String baseURL, String clientId, String clientSecret) throws IOException {

        JSONObject payload = new JSONObject();

        payload.put("client_id", clientId);
        payload.put("client_secret", clientSecret);

        RESTJSONResponse resp = RESTJSONCaller.INSTANCE.makePostCall("", "", baseURL+"/kreditz/api/v2/authorizations/access_token", payload.toString(2), "application/json");

        if(resp.getStatus()==200 && resp.getObject().has("status") && "true".equalsIgnoreCase(resp.getObject().getString("status"))) {

            return new TokenImpl(
                    resp.getObject().getJSONObject("data").getString("access_token"),
                    LocalDateTime.parse(resp.getObject().getJSONObject("data").getString("access_token_expired_at").split("\\.")[0], DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );

        } else {

            throw new IOException(resp.getObject().toString(2));
        }
    }

}
