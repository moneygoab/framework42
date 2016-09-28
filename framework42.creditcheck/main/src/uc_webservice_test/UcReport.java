
package uc_webservice_test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://www.uc.se/schemas/ucOrderReply/}xmlReply" minOccurs="0"/>
 *         &lt;element ref="{http://www.uc.se/schemas/ucOrderReply/}htmlReply" minOccurs="0"/>
 *         &lt;element ref="{http://www.uc.se/schemas/ucOrderReply/}reviewReply" minOccurs="0"/>
 *         &lt;element name="pdfReply" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xmlReply",
    "htmlReply",
    "reviewReply",
    "pdfReply"
})
@XmlRootElement(name = "ucReport", namespace = "http://www.uc.se/schemas/ucOrderReply/")
public class UcReport {

    @XmlElement(namespace = "http://www.uc.se/schemas/ucOrderReply/")
    protected XmlReply xmlReply;
    @XmlElement(namespace = "http://www.uc.se/schemas/ucOrderReply/")
    protected String htmlReply;
    @XmlElement(namespace = "http://www.uc.se/schemas/ucOrderReply/")
    protected String reviewReply;
    @XmlElement(namespace = "http://www.uc.se/schemas/ucOrderReply/")
    protected byte[] pdfReply;

    /**
     * Gets the value of the xmlReply property.
     * 
     * @return
     *     possible object is
     *     {@link XmlReply }
     *     
     */
    public XmlReply getXmlReply() {
        return xmlReply;
    }

    /**
     * Sets the value of the xmlReply property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlReply }
     *     
     */
    public void setXmlReply(XmlReply value) {
        this.xmlReply = value;
    }

    /**
     * Gets the value of the htmlReply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHtmlReply() {
        return htmlReply;
    }

    /**
     * Sets the value of the htmlReply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHtmlReply(String value) {
        this.htmlReply = value;
    }

    /**
     * Gets the value of the reviewReply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewReply() {
        return reviewReply;
    }

    /**
     * Sets the value of the reviewReply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewReply(String value) {
        this.reviewReply = value;
    }

    /**
     * Gets the value of the pdfReply property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPdfReply() {
        return pdfReply;
    }

    /**
     * Sets the value of the pdfReply property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPdfReply(byte[] value) {
        this.pdfReply = value;
    }

}
