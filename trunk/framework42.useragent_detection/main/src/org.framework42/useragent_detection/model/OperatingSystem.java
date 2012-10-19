package org.framework42.useragent_detection.model;

import java.io.Serializable;

public interface OperatingSystem extends Serializable {

    public OperatingSystemBranch getBranch();

    public String getOperatingSystemName();

    public float getMajorVersion();

    public String getFullVersion();

}
