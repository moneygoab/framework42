
package uc_webservice_test;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retrieveAction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="retrieveAction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="list"/>
 *     &lt;enumeration value="latest"/>
 *     &lt;enumeration value="specific"/>
 *     &lt;minLength value="4"/>
 *     &lt;maxLength value="8"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "retrieveAction")
@XmlEnum
public enum RetrieveAction {

    @XmlEnumValue("list")
    LIST("list"),
    @XmlEnumValue("latest")
    LATEST("latest"),
    @XmlEnumValue("specific")
    SPECIFIC("specific");
    private final String value;

    RetrieveAction(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RetrieveAction fromValue(String v) {
        for (RetrieveAction c: RetrieveAction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
