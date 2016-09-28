
package uc_webservice_test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="repositoryaction" type="{http://www.uc.se/schemas/ucOrderRequest/}repositoryaction" minOccurs="0"/>
 *         &lt;element name="businessReportQuery" type="{http://www.uc.se/schemas/ucOrderRequest/}reportQuery"/>
 *       &lt;/sequence>
 *       &lt;attribute name="product" use="required" type="{http://www.uc.se/schemas/ucOrderRequest/}businessProduct" />
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
    "repositoryaction",
    "businessReportQuery"
})
@XmlRootElement(name = "businessReport")
public class BusinessReport {

    @XmlElement(required = true)
    protected Customer customer;
    @XmlSchemaType(name = "token")
    protected Repositoryaction repositoryaction;
    @XmlElement(required = true)
    protected ReportQuery businessReportQuery;
    @XmlAttribute(name = "product", namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String product;
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
     * Gets the value of the repositoryaction property.
     * 
     * @return
     *     possible object is
     *     {@link Repositoryaction }
     *     
     */
    public Repositoryaction getRepositoryaction() {
        return repositoryaction;
    }

    /**
     * Sets the value of the repositoryaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Repositoryaction }
     *     
     */
    public void setRepositoryaction(Repositoryaction value) {
        this.repositoryaction = value;
    }

    /**
     * Gets the value of the businessReportQuery property.
     * 
     * @return
     *     possible object is
     *     {@link ReportQuery }
     *     
     */
    public ReportQuery getBusinessReportQuery() {
        return businessReportQuery;
    }

    /**
     * Sets the value of the businessReportQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportQuery }
     *     
     */
    public void setBusinessReportQuery(ReportQuery value) {
        this.businessReportQuery = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduct(String value) {
        this.product = value;
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
