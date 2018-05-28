package org.framework42.creditcheck.model;

import java.time.LocalDateTime;

public enum ApplicationChannel {

    NONE(0), INTERNET(1), PHONE(2), LETTER(3), DIRECT_MARKETING(4), TELEPHONE_MARKETING(5),
    COMPRICER(6), CREDIT_FINDER(11,true), FREEDOM_FINANCE(12,true), LENDO(15,true), MYLOAN(14,true), ADVISA(13,true), CONSECTOR(16,true), SAMBLA(17,true), ZENSUM(19,true),
    EMAIL(7), AFFILIATE_EUROADS(8), AFFILIATE_ADTRACTION(9), AFFILIATE_GOOGLE_ADWORDS(10), AFFILIATE_EMERCE(18), PARTNER(50);

    private final int id;
    private final boolean esigning;

    ApplicationChannel(int id) {
        this.id = id;
        esigning = false;
    }

    ApplicationChannel(int id, boolean esigning) {
        this.id = id;
        this.esigning = esigning;
    }


    public boolean isEsigning() {
        return esigning;
    }

    public int getId() {
        return id;
    }

    public static ApplicationChannel getById(int id) {

        for(ApplicationChannel channel : ApplicationChannel.values()) {

            if(id == channel.getId()) {

                return channel;

            }

        }

        throw new IllegalArgumentException("No application channel with id "+id+" exists!");

    }

    public static void main(String[] args) {

        System.out.println(LocalDateTime.now().compareTo(LocalDateTime.now().plusMinutes(1)));
    }

}
