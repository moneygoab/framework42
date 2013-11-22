package org.framework42.creditcheck.model;

public enum ApplicationChannel {

    NONE(0), INTERNET(1), PHONE(2), LETTER(3), DIRECT_MARKETING(4), TELEPHONE_MARKETING(5), COMPRICER(6), CREDIT_FINDER(11), FREEDOM_FINANCE(12), ADVISA(13), EMAIL(7),
    AFFILIATE_EUROADS(8), AFFILIATE_ADTRACTION(9), AFFILIATE_GOOGLE_ADWORDS(10), PARTNER(50);

    private final int id;

    ApplicationChannel(int id) {
        this.id = id;
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

}
