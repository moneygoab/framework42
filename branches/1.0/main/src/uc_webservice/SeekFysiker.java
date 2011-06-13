
package uc_webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for seekFysiker complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="seekFysiker">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seekName" type="{http://www.uc.se/schemas/ucOrderRequest/}seekName" minOccurs="0"/>
 *         &lt;element name="adress" type="{http://www.uc.se/schemas/ucOrderRequest/}adress" minOccurs="0"/>
 *         &lt;element name="seekCategoryFys" type="{http://www.uc.se/schemas/ucOrderRequest/}seekCategoryFys"/>
 *       &lt;/sequence>
 *       &lt;attribute name="xmlReply" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="htmlReply" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="reviewReply" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "seekFysiker", propOrder = {
    "seekName",
    "adress",
    "seekCategoryFys"
})
public class SeekFysiker {

    protected String seekName;
    protected Adress adress;
    @XmlElement(required = true)
    protected SeekCategoryFys seekCategoryFys;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    protected boolean xmlReply;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    protected boolean htmlReply;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/")
    protected Boolean reviewReply;

    /**
     * Gets the value of the seekName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeekName() {
        return seekName;
    }

    /**
     * Sets the value of the seekName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeekName(String value) {
        this.seekName = value;
    }

    /**
     * Gets the value of the adress property.
     * 
     * @return
     *     possible object is
     *     {@link Adress }
     *     
     */
    public Adress getAdress() {
        return adress;
    }

    /**
     * Sets the value of the adress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Adress }
     *     
     */
    public void setAdress(Adress value) {
        this.adress = value;
    }

    /**
     * Gets the value of the seekCategoryFys property.
     * 
     * @return
     *     possible object is
     *     {@link SeekCategoryFys }
     *     
     */
    public SeekCategoryFys getSeekCategoryFys() {
        return seekCategoryFys;
    }

    /**
     * Sets the value of the seekCategoryFys property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeekCategoryFys }
     *     
     */
    public void setSeekCategoryFys(SeekCategoryFys value) {
        this.seekCategoryFys = value;
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

}
