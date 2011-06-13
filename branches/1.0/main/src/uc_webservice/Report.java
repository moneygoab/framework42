
package uc_webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{http://www.uc.se/schemas/ucOrderReply/}group" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="styp" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="index" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lagomid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lagomlopnr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "group"
})
@XmlRootElement(name = "report", namespace = "http://www.uc.se/schemas/ucOrderReply/")
public class Report {

    @XmlElement(namespace = "http://www.uc.se/schemas/ucOrderReply/", required = true)
    protected List<Group> group;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderReply/", required = true)
    protected String id;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderReply/", required = true)
    protected String name;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderReply/", required = true)
    protected String styp;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderReply/", required = true)
    protected String index;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderReply/")
    protected String lagomid;
    @XmlAttribute(namespace = "http://www.uc.se/schemas/ucOrderReply/")
    protected String lagomlopnr;

    /**
     * Gets the value of the group property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the group property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Group }
     * 
     * 
     */
    public List<Group> getGroup() {
        if (group == null) {
            group = new ArrayList<Group>();
        }
        return this.group;
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

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the styp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyp() {
        return styp;
    }

    /**
     * Sets the value of the styp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyp(String value) {
        this.styp = value;
    }

    /**
     * Gets the value of the index property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndex(String value) {
        this.index = value;
    }

    /**
     * Gets the value of the lagomid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLagomid() {
        return lagomid;
    }

    /**
     * Sets the value of the lagomid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLagomid(String value) {
        this.lagomid = value;
    }

    /**
     * Gets the value of the lagomlopnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLagomlopnr() {
        return lagomlopnr;
    }

    /**
     * Sets the value of the lagomlopnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLagomlopnr(String value) {
        this.lagomlopnr = value;
    }

}
