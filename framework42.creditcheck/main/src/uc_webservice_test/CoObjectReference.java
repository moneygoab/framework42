
package uc_webservice_test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for coObjectReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="coObjectReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objectReference" type="{http://www.uc.se/schemas/ucOrderRequest/}objectRef"/>
 *         &lt;element name="income" type="{http://www.uc.se/schemas/ucOrderRequest/}income"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "coObjectReference", propOrder = {
    "objectReference",
    "income"
})
public class CoObjectReference {

    @XmlElement(required = true)
    protected String objectReference;
    protected int income;

    /**
     * Gets the value of the objectReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectReference() {
        return objectReference;
    }

    /**
     * Sets the value of the objectReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectReference(String value) {
        this.objectReference = value;
    }

    /**
     * Gets the value of the income property.
     * 
     */
    public int getIncome() {
        return income;
    }

    /**
     * Sets the value of the income property.
     * 
     */
    public void setIncome(int value) {
        this.income = value;
    }

}
