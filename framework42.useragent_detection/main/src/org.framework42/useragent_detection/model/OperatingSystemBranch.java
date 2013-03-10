package org.framework42.useragent_detection.model;

import java.io.Serializable;

public enum OperatingSystemBranch implements Serializable {

    WINDOWS(1), MAC_OS(2), LINUX(3), UNIX(4), AMIGA(5), UNKNOWN(0);

    private final int id;

    private OperatingSystemBranch(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static OperatingSystemBranch getById(int id) {

        for(OperatingSystemBranch os: OperatingSystemBranch.values()) {

            if(os.getId() == id) {

                return os;
            }
        }

        throw new IllegalArgumentException("No operating system branch with id "+id+" exists!");
    }

}
