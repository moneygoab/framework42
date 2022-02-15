package org.framework42.kreditz.services;

import org.apache.http.client.utils.URIBuilder;
import org.framework42.model.Country;
import org.json.RESTJSONCaller;
import org.json.RESTJSONResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;

public enum ProviderAuthMaker {

    I;

    public void getProviderLink(String baseURL, String accessToken, Country market, String caseId, String longGovernmentId, Locale locale, int providerId, String redirectURL) throws IOException {

        HashMap<String,String> headers = new HashMap<>();

        headers.put("Authorization", "Bearer "+accessToken);

        /*String urlParams = "market="+market.getAlpha2Code()+
                "&case_id="+caseId+
                "&ssn_number="+longGovernmentId+
                "&locale="+locale.toString()+
                "&type=private"+
                "&provider_id="+providerId+
                "&redirect_uri="+redirectURL;*/

        URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme("https");
        uriBuilder.setHost(baseURL+"/kreditz/api/v2/authorizations/providers_link");

        uriBuilder.addParameter("market", market.getAlpha2Code())
        .addParameter("case_id", caseId)
        .addParameter("ssn_number", longGovernmentId)
        .addParameter("locale", locale.toString())
        .addParameter("type", "PRIVATE")
        .addParameter("provider_id", providerId+"")
        .addParameter("redirect_uri", redirectURL)
        ;

        try {

            RESTJSONResponse resp = RESTJSONCaller.INSTANCE.makeGetCall("", "", uriBuilder.build().toString(), "", headers);



            if (resp.getStatus() == 200 && resp.getObject().has("status") && resp.getObject().getBoolean("status")) {

                System.out.println(resp.getObject().toString());

            } else {

                throw new IOException(resp.getStatus() + " " + resp.getObject().toString(2));
            }

        } catch (URISyntaxException e) {

            throw new IOException(e);
        }
    }

}
