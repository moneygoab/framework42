package org.framework42.useragent_detection.model.impl;

import org.framework42.useragent_detection.model.OperatingSystem;
import org.framework42.useragent_detection.model.OperatingSystemBranch;

public class OperatingSystemImpl implements OperatingSystem {

    private OperatingSystemBranch branch;

    private String operatingSystemName;

    private float majorVersion;

    private String fullVersion;

    public OperatingSystemImpl(OperatingSystemBranch branch, String operatingSystemName, float majorVersion, String fullVersion) {
        this.branch = branch;
        this.operatingSystemName = operatingSystemName;
        this.majorVersion = majorVersion;
        this.fullVersion = fullVersion;
    }

    @Override
    public OperatingSystemBranch getBranch() {
        return branch;
    }

    @Override
    public void setBranch(OperatingSystemBranch branch) {
        this.branch = branch;
    }

    @Override
    public String getOperatingSystemName() {
        return operatingSystemName;
    }

    @Override
    public void setOperatingSystemName(String operatingSystemName) {
        this.operatingSystemName = operatingSystemName;
    }

    @Override
    public float getMajorVersion() {
        return majorVersion;
    }

    @Override
    public void setMajorVersion(float majorVersion) {
        this.majorVersion = majorVersion;
    }

    @Override
    public String getFullVersion() {
        return fullVersion;
    }

    @Override
    public void setFullVersion(String fullVersion) {
        this.fullVersion = fullVersion;
    }
}
