
package uc_webservice;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://www.uc.se/schemas/ucOrderReply/}reports" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.uc.se/schemas/ucOrderReply/}lagomlista" minOccurs="0"/>
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
    "reports",
    "lagomlista"
})
@XmlRootElement(name = "xmlReply", namespace = "http://www.uc.se/schemas/ucOrderReply/")
public class XmlReply {

    @XmlElement(namespace = "http://www.uc.se/schemas/ucOrderReply/", required = true)
    protected List<Reports> reports;
    @XmlElement(namespace = "http://www.uc.se/schemas/ucOrderReply/")
    protected Lagomlista lagomlista;

    /**
     * Gets the value of the reports property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reports property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReports().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reports }
     * 
     * 
     */
    public List<Reports> getReports() {
        if (reports == null) {
            reports = new ArrayList<Reports>();
        }
        return this.reports;
    }

    /**
     * Gets the value of the lagomlista property.
     * 
     * @return
     *     possible object is
     *     {@link Lagomlista }
     *     
     */
    public Lagomlista getLagomlista() {
        return lagomlista;
    }

    /**
     * Sets the value of the lagomlista property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lagomlista }
     *     
     */
    public void setLagomlista(Lagomlista value) {
        this.lagomlista = value;
    }

}
