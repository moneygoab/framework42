package org.framework42.useragent_detection.model;

import java.io.Serializable;

public enum RenderingEngine implements Serializable {

    TRIDENT(1), // Internet Explorer
    GECKO(2), // Firefox
    WEBKIT(3), // Safari / Chrome
    PRESTO(4), // Opera
    WORD(5), // MS Word
    MOZILLA(6), // Old Mozilla, pre Firefox
    KHTML(7), // KDE
    UNKNOWN(0);

    private final int id;

    private RenderingEngine(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RenderingEngine getById(int id) {

        for(RenderingEngine engine: RenderingEngine.values()) {

            if(engine.getId() == id) {

                return engine;
            }
        }

        throw new IllegalArgumentException("No rendering engine with id "+id+" exists!");
    }

}
