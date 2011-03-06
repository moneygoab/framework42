package org.framework42.web.multipart;

public class PartFile extends Part {

    public PartFile(String name) {
        super(name, MultipartParamType.FILE);
    }
}
