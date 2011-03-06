package org.framework42.web.multipart;

public abstract class Part {

    private final String name;

    private final MultipartParamType type;

    Part(String name, MultipartParamType type) {

        this.name = name;
        this.type = type;
    }

    public String getName() {

        return name;
    }

    public MultipartParamType getType() {

        return type;
    }

}
