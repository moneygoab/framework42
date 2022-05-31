package org.framework42.kreditz.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.RESTJSONCaller;
import org.json.RESTJSONResponse;

import java.io.IOException;
import java.util.HashMap;

public enum RequestPoller {

    I;

    public static void main(String[] args) throws Exception {

        System.out.println(RequestPoller.I.poll("https://vista.kreditz.com/core", "hRo4PnTuNdZh3QRQRi7H76Taxn4H3S6ZEuB3FLZ9y9HWifwsCu39F9anB8cwS12uQoZPCpTL3UbqFSnYka8eEDvfRc3YkzfTYKJApP7mJQ52MAjrQFCZ11SffJgbzYAk5r3qBDvKoAtgVh5TueUCE1gqRnBZVHhYR3o4JspqkKvvVpMBpxGMoqvGC8Zi8gdGVQsFkCKFeUZyuYi6A2LPcpVH1XaJhHuccDQhfi4MFfV66xGcCF71GnkW77", "769855626176f840052ecab8c64a91bfff83e7bc34cfa6357fb15d7c763c0d74_20220509112550").toString(2));
    }

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

            return resp.getObject();

        } else {

            throw new IOException(resp.getObject().toString(2));
        }
    }

}
