
package uc_webservice_test;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for seekCategoryJur.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="seekCategoryJur">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Juridisk person"/>
 *     &lt;enumeration value="AB"/>
 *     &lt;enumeration value="HKB"/>
 *     &lt;enumeration value="Företag"/>
 *     &lt;enumeration value="Enskild firma"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "seekCategoryJur")
@XmlEnum
public enum SeekCategoryJur {

    @XmlEnumValue("Juridisk person")
    JURIDISK_PERSON("Juridisk person"),
    AB("AB"),
    HKB("HKB"),
    @XmlEnumValue("F\u00f6retag")
    FÖRETAG("F\u00f6retag"),
    @XmlEnumValue("Enskild firma")
    ENSKILD_FIRMA("Enskild firma");
    private final String value;

    SeekCategoryJur(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SeekCategoryJur fromValue(String v) {
        for (SeekCategoryJur c: SeekCategoryJur.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
