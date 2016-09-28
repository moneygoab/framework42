
package uc_webservice_test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="retrieveAction" type="{http://www.uc.se/schemas/ucOrderRequest/}retrieveAction"/>
 *         &lt;element name="annualQuery" type="{http://www.uc.se/schemas/ucOrderRequest/}annualQuery"/>
 *       &lt;/sequence>
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
    "retrieveAction",
    "annualQuery"
})
@XmlRootElement(name = "retrieveAnnualReport")
public class RetrieveAnnualReport {

    @XmlElement(required = true)
    protected Customer customer;
    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    protected RetrieveAction retrieveAction;
    @XmlElement(required = true)
    protected AnnualQuery annualQuery;
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
     * Gets the value of the retrieveAction property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveAction }
     *     
     */
    public RetrieveAction getRetrieveAction() {
        return retrieveAction;
    }

    /**
     * Sets the value of the retrieveAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveAction }
     *     
     */
    public void setRetrieveAction(RetrieveAction value) {
        this.retrieveAction = value;
    }

    /**
     * Gets the value of the annualQuery property.
     * 
     * @return
     *     possible object is
     *     {@link AnnualQuery }
     *     
     */
    public AnnualQuery getAnnualQuery() {
        return annualQuery;
    }

    /**
     * Sets the value of the annualQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnualQuery }
     *     
     */
    public void setAnnualQuery(AnnualQuery value) {
        this.annualQuery = value;
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
