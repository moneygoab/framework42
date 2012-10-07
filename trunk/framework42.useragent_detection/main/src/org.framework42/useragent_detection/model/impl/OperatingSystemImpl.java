package org.framework42.useragent_detection.model.impl;

import org.framework42.useragent_detection.model.OperatingSystem;
import org.framework42.useragent_detection.model.OperatingSystemBranch;

public class OperatingSystemImpl implements OperatingSystem {

    private final OperatingSystemBranch branch;

    private final String operatingSystemName;

    private final float majorVersion;

    private final String fullVersion;

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
    public String getOperatingSystemName() {
        return operatingSystemName;
    }

    @Override
    public float getMajorVersion() {
        return majorVersion;
    }

    @Override
    public String getFullVersion() {
        return fullVersion;
    }
}
