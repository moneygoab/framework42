package org.framework42.useragent_detection.model;

public enum DeviceType {

    COMPUTER(1), MOBILE_PHONE(2), TABLET(3), SERVER(4), GAME_CONSOLE(5), DIGITAL_MEDIA_RECEIVER(6), UNKNOWN(0);

    private final int id;

    private DeviceType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DeviceType getById(int id) {

        for(DeviceType type: DeviceType.values()) {

            if(type.getId() == id) {

                return type;
            }
        }

        throw new IllegalArgumentException("No device type with id "+id+" exists!");
    }

}
