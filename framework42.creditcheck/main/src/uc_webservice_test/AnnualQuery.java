
package uc_webservice_test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for annualQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="annualQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="object" type="{http://www.uc.se/schemas/ucOrderRequest/}objectRef"/>
 *         &lt;element name="reportYear" type="{http://www.uc.se/schemas/ucOrderRequest/}annualReportYear" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "annualQuery", propOrder = {
    "object",
    "reportYear"
})
public class AnnualQuery {

    @XmlElement(required = true)
    protected String object;
    protected String reportYear;

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
     * Gets the value of the reportYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportYear() {
        return reportYear;
    }

    /**
     * Sets the value of the reportYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportYear(String value) {
        this.reportYear = value;
    }

}
