
package uc_webservice_test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customer" type="{http://www.uc.se/schemas/ucOrderRequest/}customer"/>
 *         &lt;element name="seekJuridiker" type="{http://www.uc.se/schemas/ucOrderRequest/}seekJuridiker"/>
 *       &lt;/sequence>
 *       &lt;attribute name="identifier" use="required" type="{http://www.uc.se/schemas/ucOrderRequest/}objectRef" />
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customer",
    "seekJuridiker"
})
@XmlRootElement(name = "identitySeekJur")
public class IdentitySeekJur {

    @XmlElement(required = true)
    protected Customer customer;
    @XmlElement(required = true)
    protected SeekJuridiker seekJuridiker;
    @XmlAttribute(name = "identifier", namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    protected String identifier;
    @XmlAttribute(name = "version", namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    protected String version;

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setCustomer(Customer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the seekJuridiker property.
     * 
     * @return
     *     possible object is
     *     {@link SeekJuridiker }
     *     
     */
    public SeekJuridiker getSeekJuridiker() {
        return seekJuridiker;
    }

    /**
     * Sets the value of the seekJuridiker property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeekJuridiker }
     *     
     */
    public void setSeekJuridiker(SeekJuridiker value) {
        this.seekJuridiker = value;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifier(String value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
