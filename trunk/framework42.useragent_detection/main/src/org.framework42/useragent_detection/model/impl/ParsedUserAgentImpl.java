package org.framework42.useragent_detection.model.impl;

import org.framework42.useragent_detection.model.*;

public class ParsedUserAgentImpl implements ParsedUserAgent {

    private String name;

    private float version;

    private UserAgentType type;

    private DeviceType deviceType;

    private RenderingEngine renderingEngine;

    private OperatingSystem operatingSystem;

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
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setVersion(float version) {
        this.version = version;
    }

    @Override
    public void setType(UserAgentType type) {
        this.type = type;
    }

    @Override
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public void setRenderingEngine(RenderingEngine renderingEngine) {
        this.renderingEngine = renderingEngine;
    }

    @Override
    public void setOperationSystem(OperatingSystem operationSystem) {
        this.operatingSystem = operationSystem;
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
