
package uc_webservice_test;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for repositoryaction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="repositoryaction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="list"/>
 *     &lt;enumeration value="new"/>
 *     &lt;enumeration value="old"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "repositoryaction")
@XmlEnum
public enum Repositoryaction {

    @XmlEnumValue("list")
    LIST("list"),
    @XmlEnumValue("new")
    NEW("new"),
    @XmlEnumValue("old")
    OLD("old");
    private final String value;

    Repositoryaction(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Repositoryaction fromValue(String v) {
        for (Repositoryaction c: Repositoryaction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
