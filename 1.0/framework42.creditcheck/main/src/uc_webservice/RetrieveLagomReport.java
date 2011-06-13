
package uc_webservice;

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
 *       &lt;/sequence>
 *       &lt;attribute name="datastoreid" use="required" type="{http://www.uc.se/schemas/ucOrderRequest/}lagomDatastoreId" />
 *       &lt;attribute name="objectid" use="required" type="{http://www.uc.se/schemas/ucOrderRequest/}objectRef" />
 *       &lt;attribute name="sequenceNr" use="required" type="{http://www.uc.se/schemas/ucOrderRequest/}lagomSequenceNr" />
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
    "customer"
})
@XmlRootElement(name = "retrieveLagomReport")
public class RetrieveLagomReport {

    @XmlElement(required = true)
    protected Customer customer;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    protected String datastoreid;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    protected String objectid;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    protected int sequenceNr;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
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
     * Gets the value of the datastoreid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatastoreid() {
        return datastoreid;
    }

    /**
     * Sets the value of the datastoreid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatastoreid(String value) {
        this.datastoreid = value;
    }

    /**
     * Gets the value of the objectid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectid() {
        return objectid;
    }

    /**
     * Sets the value of the objectid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectid(String value) {
        this.objectid = value;
    }

    /**
     * Gets the value of the sequenceNr property.
     * 
     */
    public int getSequenceNr() {
        return sequenceNr;
    }

    /**
     * Sets the value of the sequenceNr property.
     * 
     */
    public void setSequenceNr(int value) {
        this.sequenceNr = value;
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
