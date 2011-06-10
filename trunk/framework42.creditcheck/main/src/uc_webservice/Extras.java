
package uc_webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for extras complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="extras">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="telefax" type="{http://www.uc.se/schemas/ucOrderRequest/}telephone" minOccurs="0"/>
 *         &lt;element name="emailId" type="{http://www.uc.se/schemas/ucOrderRequest/}emailName" minOccurs="0"/>
 *         &lt;element name="object" type="{http://www.uc.se/schemas/ucOrderRequest/}objectRef" minOccurs="0"/>
 *         &lt;element name="bokslut" type="{http://www.uc.se/schemas/ucOrderRequest/}bokslut" maxOccurs="5"/>
 *         &lt;element name="coverage" type="{http://www.uc.se/schemas/ucOrderRequest/}coverage"/>
 *         &lt;element name="originalDocuments" type="{http://www.uc.se/schemas/ucOrderRequest/}originalDocuments"/>
 *         &lt;element name="accountsCoverage" type="{http://www.uc.se/schemas/ucOrderRequest/}accountsCoverage"/>
 *       &lt;/sequence>
 *       &lt;attribute name="deliveryForm" type="{http://www.uc.se/schemas/ucOrderRequest/}deliveryMedia" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extras", propOrder = {
    "telefax",
    "emailId",
    "object",
    "bokslut",
    "coverage",
    "originalDocuments",
    "accountsCoverage"
})
public class Extras {

    protected Telephone telefax;
    protected String emailId;
    protected String object;
    @XmlElement(required = true)
    protected List<Bokslut> bokslut;
    @XmlElement(required = true)
    protected Coverage coverage;
    @XmlElement(required = true)
    protected OriginalDocuments originalDocuments;
    @XmlElement(required = true)
    protected AccountsCoverage accountsCoverage;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/")
    protected DeliveryMedia deliveryForm;

    /**
     * Gets the value of the telefax property.
     * 
     * @return
     *     possible object is
     *     {@link Telephone }
     *     
     */
    public Telephone getTelefax() {
        return telefax;
    }

    /**
     * Sets the value of the telefax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Telephone }
     *     
     */
    public void setTelefax(Telephone value) {
        this.telefax = value;
    }

    /**
     * Gets the value of the emailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Sets the value of the emailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailId(String value) {
        this.emailId = value;
    }

    /**
     * Gets the value of the object property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObject(String value) {
        this.object = value;
    }

    /**
     * Gets the value of the bokslut property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bokslut property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBokslut().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bokslut }
     * 
     * 
     */
    public List<Bokslut> getBokslut() {
        if (bokslut == null) {
            bokslut = new ArrayList<Bokslut>();
        }
        return this.bokslut;
    }

    /**
     * Gets the value of the coverage property.
     * 
     * @return
     *     possible object is
     *     {@link Coverage }
     *     
     */
    public Coverage getCoverage() {
        return coverage;
    }

    /**
     * Sets the value of the coverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Coverage }
     *     
     */
    public void setCoverage(Coverage value) {
        this.coverage = value;
    }

    /**
     * Gets the value of the originalDocuments property.
     * 
     * @return
     *     possible object is
     *     {@link OriginalDocuments }
     *     
     */
    public OriginalDocuments getOriginalDocuments() {
        return originalDocuments;
    }

    /**
     * Sets the value of the originalDocuments property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalDocuments }
     *     
     */
    public void setOriginalDocuments(OriginalDocuments value) {
        this.originalDocuments = value;
    }

    /**
     * Gets the value of the accountsCoverage property.
     * 
     * @return
     *     possible object is
     *     {@link AccountsCoverage }
     *     
     */
    public AccountsCoverage getAccountsCoverage() {
        return accountsCoverage;
    }

    /**
     * Sets the value of the accountsCoverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountsCoverage }
     *     
     */
    public void setAccountsCoverage(AccountsCoverage value) {
        this.accountsCoverage = value;
    }

    /**
     * Gets the value of the deliveryForm property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryMedia }
     *     
     */
    public DeliveryMedia getDeliveryForm() {
        return deliveryForm;
    }

    /**
     * Sets the value of the deliveryForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryMedia }
     *     
     */
    public void setDeliveryForm(DeliveryMedia value) {
        this.deliveryForm = value;
    }

}
