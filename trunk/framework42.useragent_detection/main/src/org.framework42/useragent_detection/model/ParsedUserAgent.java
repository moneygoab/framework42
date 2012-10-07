package org.framework42.useragent_detection.model;

import java.io.Serializable;

public interface ParsedUserAgent extends Serializable {

    public String getName();

    public float getVersion();

    public UserAgentType getType();

    public DeviceType getDeviceType();

    public RenderingEngine getRenderingEngine();

    public OperatingSystem getOperatingSystem();

}
