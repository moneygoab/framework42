package org.framework42.creditcheck.model;

import java.time.LocalDateTime;

public enum ApplicationChannel {

    NONE(0), INTERNET(1), PHONE(2), LETTER(3), DIRECT_MARKETING(4), TELEPHONE_MARKETING(5),
    COMPRICER(6), CREDIT_FINDER(11), FREEDOM_FINANCE(12), LENDO(15), MYLOAN(14), ADVISA(13), CONSECTOR(16),
    SAMBLA(17), ZENSUM(19), ENKLARE(20), DIREKTO(21), REDUCERA(22),LIKVIDUM(23), KOLL(24), AXO(25),
    EMAIL(7), AFFILIATE_EUROADS(8), AFFILIATE_ADTRACTION(9), AFFILIATE_GOOGLE_ADWORDS(10), AFFILIATE_EMERCE(18), PARTNER(50);

    private final int id;
    private final boolean esigning;

    ApplicationChannel(int id) {
        this.id = id;
        esigning = true;
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
