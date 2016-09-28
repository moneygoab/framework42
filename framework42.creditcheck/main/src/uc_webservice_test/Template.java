
package uc_webservice_test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for template complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="template">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="income" type="{http://www.uc.se/schemas/ucOrderRequest/}templateIncome" minOccurs="0"/>
 *         &lt;element name="coObject" type="{http://www.uc.se/schemas/ucOrderRequest/}objectRef" minOccurs="0"/>
 *         &lt;element name="coObjectIncome" type="{http://www.uc.se/schemas/ucOrderRequest/}income" minOccurs="0"/>
 *         &lt;element name="templateParams" type="{http://www.uc.se/schemas/ucOrderRequest/}templateParams" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "template", propOrder = {
    "income",
    "coObject",
    "coObjectIncome",
    "templateParams"
})
public class Template {

    protected TemplateIncome income;
    protected String coObject;
    protected Integer coObjectIncome;
    protected TemplateParams templateParams;
    @XmlAttribute(name = "id", namespace = "http://www.uc.se/schemas/ucOrderRequest/", required = true)
    protected String id;

    /**
     * Gets the value of the income property.
     * 
     * @return
     *     possible object is
     *     {@link TemplateIncome }
     *     
     */
    public TemplateIncome getIncome() {
        return income;
    }

    /**
     * Sets the value of the income property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemplateIncome }
     *     
     */
    public void setIncome(TemplateIncome value) {
        this.income = value;
    }

    /**
     * Gets the value of the coObject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoObject() {
        return coObject;
    }

    /**
     * Sets the value of the coObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoObject(String value) {
        this.coObject = value;
    }

    /**
     * Gets the value of the coObjectIncome property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCoObjectIncome() {
        return coObjectIncome;
    }

    /**
     * Sets the value of the coObjectIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCoObjectIncome(Integer value) {
        this.coObjectIncome = value;
    }

    /**
     * Gets the value of the templateParams property.
     * 
     * @return
     *     possible object is
     *     {@link TemplateParams }
     *     
     */
    public TemplateParams getTemplateParams() {
        return templateParams;
    }

    /**
     * Sets the value of the templateParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemplateParams }
     *     
     */
    public void setTemplateParams(TemplateParams value) {
        this.templateParams = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
