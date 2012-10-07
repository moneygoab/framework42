package org.framework42.useragent_detection.model;

public interface OperatingSystem {

    public OperatingSystemBranch getBranch();

    public String getOperatingSystemName();

    public float getMajorVersion();

    public String getFullVersion();

}
