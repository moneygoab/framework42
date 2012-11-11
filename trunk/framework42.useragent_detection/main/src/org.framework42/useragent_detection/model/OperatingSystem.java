package org.framework42.useragent_detection.model;

import java.io.Serializable;

public interface OperatingSystem extends Serializable {

    public OperatingSystemBranch getBranch();

    public void setBranch(OperatingSystemBranch branch);

    public String getOperatingSystemName();

    public void setOperatingSystemName(String operatingSystemName);

    public float getMajorVersion();

    public void setMajorVersion(float majorVersion);

    public String getFullVersion();

    public void setFullVersion(String fullVersion);

}
