package org.framework42.useragent_detection.model.impl;

import org.framework42.useragent_detection.model.*;

public class ParsedUserAgentImpl implements ParsedUserAgent {

    private final String name;

    private final float version;

    private final UserAgentType type;

    private final DeviceType deviceType;

    private final RenderingEngine renderingEngine;

    private final OperatingSystem operatingSystem;

    public ParsedUserAgentImpl(String name, float version, UserAgentType type, DeviceType deviceType, RenderingEngine renderingEngine, OperatingSystem operatingSystem) {
        this.name = name;
        this.version = version;
        this.type = type;
        this.deviceType = deviceType;
        this.renderingEngine = renderingEngine;
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getVersion() {
        return version;
    }

    @Override
    public UserAgentType getType() {
        return type;
    }

    @Override
    public DeviceType getDeviceType() {
        return deviceType;
    }

    @Override
    public RenderingEngine getRenderingEngine() {
        return renderingEngine;
    }

    @Override
    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    @Override
    public String toString() {
        return "ParsedUserAgentImpl{" +
                "name='" + name + '\'' +
                ", version=" + version +
                ", type=" + type +
                ", deviceType=" + deviceType +
                ", renderingEngine=" + renderingEngine +
                ", operatingSystem=" + operatingSystem +
                '}';
    }
}
