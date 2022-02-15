package org.framework42.kreditz.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.RESTJSONCaller;
import org.json.RESTJSONResponse;

import java.io.IOException;
import java.util.HashMap;

public enum RequestPoller {

    I;

    public JSONObject poll(String baseURL, String accessToken, String caseId) throws IOException {

        HashMap<String,String> headers = new HashMap<>();

        headers.put("Authorization", "Bearer "+accessToken);

        RESTJSONResponse resp = RESTJSONCaller.INSTANCE.makeGetCall("", "", baseURL+"/kreditz/api/v2/bank_data/find_by_case/"+caseId, "", headers);

        boolean sucess = false;

        if(resp.getObject().has("status") && !resp.getObject().isNull("status")) {

            try {

                sucess = resp.getObject().getBoolean("status");

            } catch (JSONException e) {

                sucess = "success".compareTo(resp.getObject().getString("status"))==0;
            }
        }

        if(resp.getStatus()==200 && sucess) {

            System.out.println(resp.getObject().toString(2));

        } else {

            throw new IOException(resp.getObject().toString(2));
        }

        return resp.getObject();
    }

}
