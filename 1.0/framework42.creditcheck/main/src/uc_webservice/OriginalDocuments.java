
package uc_webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for originalDocuments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="originalDocuments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="altCertificate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="articlesOfAssociation" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="certificate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="shareholdersMeetingProtocol" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "originalDocuments")
public class OriginalDocuments {

    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/")
    protected Boolean altCertificate;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/")
    protected Boolean articlesOfAssociation;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/")
    protected Boolean certificate;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderRequest/")
    protected Boolean shareholdersMeetingProtocol;

    /**
     * Gets the value of the altCertificate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAltCertificate() {
        return altCertificate;
    }

    /**
     * Sets the value of the altCertificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAltCertificate(Boolean value) {
        this.altCertificate = value;
    }

    /**
     * Gets the value of the articlesOfAssociation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isArticlesOfAssociation() {
        return articlesOfAssociation;
    }

    /**
     * Sets the value of the articlesOfAssociation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setArticlesOfAssociation(Boolean value) {
        this.articlesOfAssociation = value;
    }

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCertificate(Boolean value) {
        this.certificate = value;
    }

    /**
     * Gets the value of the shareholdersMeetingProtocol property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShareholdersMeetingProtocol() {
        return shareholdersMeetingProtocol;
    }

    /**
     * Sets the value of the shareholdersMeetingProtocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShareholdersMeetingProtocol(Boolean value) {
        this.shareholdersMeetingProtocol = value;
    }

}
