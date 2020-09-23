package org.framework42.log;

public enum DebugLevel {

    DEBUG(1), INFO(10), ERROR(20), FATAL(30);

    private final int level;

    DebugLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
