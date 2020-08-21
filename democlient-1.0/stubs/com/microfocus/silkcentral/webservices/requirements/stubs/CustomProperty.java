
package com.microfocus.silkcentral.webservices.requirements.stubs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="changedAt" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="changedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listItems" type="{http://www.microfocus.com/silkcentral/webservices/requirements}customPropertyListItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="maxVal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="minVal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="precision" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="projectID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="propertyId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="propertyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customProperty", propOrder = {
    "changedAt",
    "changedBy",
    "createdAt",
    "createdBy",
    "listItems",
    "maxVal",
    "minVal",
    "precision",
    "projectID",
    "propertyId",
    "propertyName",
    "type"
})
public class CustomProperty {

    protected long changedAt;
    protected String changedBy;
    protected long createdAt;
    protected String createdBy;
    @XmlElement(nillable = true)
    protected List<CustomPropertyListItem> listItems;
    protected Double maxVal;
    protected Double minVal;
    protected int precision;
    protected int projectID;
    protected int propertyId;
    protected String propertyName;
    protected int type;

    /**
     * Gets the value of the changedAt property.
     * 
     */
    public long getChangedAt() {
        return changedAt;
    }

    /**
     * Sets the value of the changedAt property.
     * 
     */
    public void setChangedAt(long value) {
        this.changedAt = value;
    }

    /**
     * Gets the value of the changedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangedBy() {
        return changedBy;
    }

    /**
     * Sets the value of the changedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangedBy(String value) {
        this.changedBy = value;
    }

    /**
     * Gets the value of the createdAt property.
     * 
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the value of the createdAt property.
     * 
     */
    public void setCreatedAt(long value) {
        this.createdAt = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

    /**
     * Gets the value of the listItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomPropertyListItem }
     * 
     * 
     */
    public List<CustomPropertyListItem> getListItems() {
        if (listItems == null) {
            listItems = new ArrayList<CustomPropertyListItem>();
        }
        return this.listItems;
    }

    /**
     * Gets the value of the maxVal property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxVal() {
        return maxVal;
    }

    /**
     * Sets the value of the maxVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxVal(Double value) {
        this.maxVal = value;
    }

    /**
     * Gets the value of the minVal property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinVal() {
        return minVal;
    }

    /**
     * Sets the value of the minVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinVal(Double value) {
        this.minVal = value;
    }

    /**
     * Gets the value of the precision property.
     * 
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Sets the value of the precision property.
     * 
     */
    public void setPrecision(int value) {
        this.precision = value;
    }

    /**
     * Gets the value of the projectID property.
     * 
     */
    public int getProjectID() {
        return projectID;
    }

    /**
     * Sets the value of the projectID property.
     * 
     */
    public void setProjectID(int value) {
        this.projectID = value;
    }

    /**
     * Gets the value of the propertyId property.
     * 
     */
    public int getPropertyId() {
        return propertyId;
    }

    /**
     * Sets the value of the propertyId property.
     * 
     */
    public void setPropertyId(int value) {
        this.propertyId = value;
    }

    /**
     * Gets the value of the propertyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Sets the value of the propertyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyName(String value) {
        this.propertyName = value;
    }

    /**
     * Gets the value of the type property.
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

}
