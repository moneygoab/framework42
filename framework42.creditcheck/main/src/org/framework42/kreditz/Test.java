package org.framework42.kreditz;

import org.apache.http.client.utils.URIBuilder;
import org.framework42.kreditz.model.Provider;
import org.framework42.kreditz.model.Token;
import org.framework42.kreditz.services.ProviderAuthMaker;
import org.framework42.kreditz.services.ProviderGetter;
import org.framework42.kreditz.services.RequestPoller;
import org.framework42.kreditz.services.TokenGetter;
import org.framework42.model.Country;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

public class Test {

    public static void main(String[] args) throws IOException {

        //String baseURL = "vista.kreditz.com/core";
        String baseURL = "https://vista.kreditz.com/core";

        String clientID = "919c661d8129eb145a532c8d448124";

        String clientSecret = "65f72c6c4036e120512fafa76b4bccb8";

        String accessToken = "C5ysKgdK1sbvb1DpMUiEGkvdAUUdHJtUGMJPtkUKxETu5esAN8v2s6KiQfy4ZKe4AfEpTygRp5uN6c38cJ91jtPQv57CiJ2CHAAREMW2vKZfpVggW6aAdceKFGxttvnTi3vhGCaP6Tpc63H4oCqPZ3CZvkW5AEuB24mbJTMd4vp7zivtgPGx5VM2PL41kypw5J6KoP2VFPETSDvLovJxruJ6QSstTgKcQFBKNXFD5aww2nJu1Ahf3aRtai";

        String validTo = "2022-02-24T09:53:14";

//        Token token = TokenGetter.I.createAccessToken(baseURL, clientID, clientSecret);

//        System.out.println(token.getAccessToken());


        //List<Provider> providerList = ProviderGetter.I.getProviderList(baseURL, accessToken, Country.SWEDEN);

        //System.out.println(providerList.size());

  //      String redirecURL = "https://www.moneygo.se";

    //    ProviderAuthMaker.I.getProviderLink(baseURL, accessToken, Country.SWEDEN, "202202151116", "197511133519", "Test Fredrik", "fredrik.gustavsson@moneygo.se", new Locale("sv", "SE"), 19, redirecURL);

        //RequestPoller.I.poll(baseURL, accessToken, "202202151116");
    }

    //https://vista.kreditz.com/banking_authentication/9b0a0c13-edfa-48b5-81a2-b4d0f2115813?locale=sv_SE&redirect_uri=https%3A%2F%2Fwww.moneygo.se&version=166834703641a4cf8a4f25c4d24da2

}
