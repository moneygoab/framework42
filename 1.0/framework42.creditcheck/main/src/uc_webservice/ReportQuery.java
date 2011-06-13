
package uc_webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for reportQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reportQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="object" type="{http://www.uc.se/schemas/ucOrderRequest/}objectRef"/>
 *         &lt;element name="orderedBy" type="{http://www.uc.se/schemas/ucOrderRequest/}name" minOccurs="0"/>
 *         &lt;element name="reference" type="{http://www.uc.se/schemas/ucOrderRequest/}referenceName" minOccurs="0"/>
 *         &lt;element name="override" type="{http://www.uc.se/schemas/ucOrderRequest/}override" minOccurs="0"/>
 *         &lt;element name="creditSeeked" type="{http://www.uc.se/schemas/ucOrderRequest/}creditseekAmount" minOccurs="0"/>
 *         &lt;element name="template" type="{http://www.uc.se/schemas/ucOrderRequest/}template" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="xmlReply" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="htmlReply" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="reviewReply" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="extendedDetails" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="lang" type="{http://www.uc.se/schemas/ucOrderRequest/}language" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reportQuery", propOrder = {
    "object",
    "orderedBy",
    "reference",
    "override",
    "creditSeeked",
    "template"
})
public class ReportQuery {

    @XmlElement(required = true)
    protected String object;
    protected String orderedBy;
    protected String reference;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String override;
    protected Integer creditSeeked;
    protected Template template;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    protected boolean xmlReply;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    protected boolean htmlReply;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/")
    protected Boolean reviewReply;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/")
    protected Boolean extendedDetails;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/")
    protected Language lang;

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
     * Gets the value of the orderedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderedBy() {
        return orderedBy;
    }

    /**
     * Sets the value of the orderedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderedBy(String value) {
        this.orderedBy = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Gets the value of the override property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverride() {
        return override;
    }

    /**
     * Sets the value of the override property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverride(String value) {
        this.override = value;
    }

    /**
     * Gets the value of the creditSeeked property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCreditSeeked() {
        return creditSeeked;
    }

    /**
     * Sets the value of the creditSeeked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCreditSeeked(Integer value) {
        this.creditSeeked = value;
    }

    /**
     * Gets the value of the template property.
     * 
     * @return
     *     possible object is
     *     {@link Template }
     *     
     */
    public Template getTemplate() {
        return template;
    }

    /**
     * Sets the value of the template property.
     * 
     * @param value
     *     allowed object is
     *     {@link Template }
     *     
     */
    public void setTemplate(Template value) {
        this.template = value;
    }

    /**
     * Gets the value of the xmlReply property.
     * 
     */
    public boolean isXmlReply() {
        return xmlReply;
    }

    /**
     * Sets the value of the xmlReply property.
     * 
     */
    public void setXmlReply(boolean value) {
        this.xmlReply = value;
    }

    /**
     * Gets the value of the htmlReply property.
     * 
     */
    public boolean isHtmlReply() {
        return htmlReply;
    }

    /**
     * Sets the value of the htmlReply property.
     * 
     */
    public void setHtmlReply(boolean value) {
        this.htmlReply = value;
    }

    /**
     * Gets the value of the reviewReply property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReviewReply() {
        return reviewReply;
    }

    /**
     * Sets the value of the reviewReply property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReviewReply(Boolean value) {
        this.reviewReply = value;
    }

    /**
     * Gets the value of the extendedDetails property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExtendedDetails() {
        return extendedDetails;
    }

    /**
     * Sets the value of the extendedDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExtendedDetails(Boolean value) {
        this.extendedDetails = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setLang(Language value) {
        this.lang = value;
    }

}
