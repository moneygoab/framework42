
package uc_webservice_test;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for seekCategoryFys.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="seekCategoryFys">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Fysisk person"/>
 *     &lt;enumeration value="Kvinna"/>
 *     &lt;enumeration value="Man"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "seekCategoryFys")
@XmlEnum
public enum SeekCategoryFys {

    @XmlEnumValue("Fysisk person")
    FYSISK_PERSON("Fysisk person"),
    @XmlEnumValue("Kvinna")
    KVINNA("Kvinna"),
    @XmlEnumValue("Man")
    MAN("Man");
    private final String value;

    SeekCategoryFys(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SeekCategoryFys fromValue(String v) {
        for (SeekCategoryFys c: SeekCategoryFys.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
