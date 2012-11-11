package org.framework42.useragent_detection.model;

import java.io.Serializable;

public interface ParsedUserAgent extends Serializable {

    public String getName();

    public void setName(String name);

    public float getVersion();

    public void setVersion(float version);

    public UserAgentType getType();

    public void setType(UserAgentType type);

    public DeviceType getDeviceType();

    public void setDeviceType(DeviceType deviceType);

    public RenderingEngine getRenderingEngine();

    public void setRenderingEngine(RenderingEngine renderingEngine);

    public OperatingSystem getOperatingSystem();

    public void setOperationSystem (OperatingSystem operationSystem);

}
