package org.framework42.creditcheck.model;

public enum ApplicationChannel {

    NONE(0), INTERNET(1), PHONE(2), LETTER(3), DIRECT_MARKETING(4), TELEPHONE_MARKETING(5), COMPRICER(6);

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

        throw new IllegalArgumentException("No application channel with getId "+id+" exists!");

    }

}
