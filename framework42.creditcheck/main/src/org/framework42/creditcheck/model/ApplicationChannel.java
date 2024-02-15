package org.framework42.creditcheck.model;

import java.time.LocalDateTime;

public enum ApplicationChannel {

    NONE(0, false), INTERNET(1, false), PHONE(2, false), LETTER(3, false), DIRECT_MARKETING(4, false), TELEPHONE_MARKETING(5, false),
    COMPRICER(6, true), CREDIT_FINDER(11, true), FREEDOM_FINANCE(12, true), LENDO(15, true), MYLOAN(14, true), ADVISA(13, true), CONSECTOR(16, true),
    SAMBLA(17, true), ZENSUM(19, true), ENKLARE(20, true), DIREKTO(21, true), REDUCERA(22, true),LIKVIDUM(23, true), KOLL(24, true), AXO(25, true),
    TOBORROW(26, true), SVENK_LAANEFOERMEDLING(27, true), VBK(28, true),
    EMAIL(7, false), AFFILIATE_EUROADS(8, false), AFFILIATE_ADTRACTION(9, false), AFFILIATE_GOOGLE_ADWORDS(10, false),
    AFFILIATE_EMERCE(18, false), PARTNER(50, false);

    private final int id;
    private final boolean esigning;
    private final boolean isBroker;

    ApplicationChannel(int id, boolean isBroker) {
        this.id = id;
        esigning = true;
        this.isBroker = isBroker;
    }

    ApplicationChannel(int id, boolean esigning, boolean isBroker) {
        this.id = id;
        this.esigning = esigning;
        this.isBroker = isBroker;
    }


    public boolean isEsigning() {
        return esigning;
    }

    public int getId() {
        return id;
    }

    public boolean isBroker() {
        return isBroker;
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
