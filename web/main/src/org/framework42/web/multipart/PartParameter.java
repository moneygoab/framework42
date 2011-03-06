package org.framework42.web.multipart;

public class PartParameter extends Part {

    public PartParameter(String name) {
        super(name, MultipartParamType.PARAMETER);
    }
}
