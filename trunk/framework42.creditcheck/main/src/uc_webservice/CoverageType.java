
package uc_webservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for coverageType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="coverageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="next"/>
 *     &lt;enumeration value="future"/>
 *     &lt;enumeration value="incomplete"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "coverageType")
@XmlEnum
public enum CoverageType {

    @XmlEnumValue("next")
    NEXT("next"),
    @XmlEnumValue("future")
    FUTURE("future"),
    @XmlEnumValue("incomplete")
    INCOMPLETE("incomplete");
    private final String value;

    CoverageType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CoverageType fromValue(String v) {
        for (CoverageType c: CoverageType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
