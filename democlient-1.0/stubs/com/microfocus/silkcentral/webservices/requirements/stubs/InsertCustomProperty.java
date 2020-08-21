
package com.microfocus.silkcentral.webservices.requirements.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for insertCustomProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="insertCustomProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newProperty" type="{http://www.microfocus.com/silkcentral/webservices/requirements}customProperty" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertCustomProperty", propOrder = {
    "sessionId",
    "newProperty"
})
public class InsertCustomProperty {

    protected String sessionId;
    protected CustomProperty newProperty;

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the newProperty property.
     * 
     * @return
     *     possible object is
     *     {@link CustomProperty }
     *     
     */
    public CustomProperty getNewProperty() {
        return newProperty;
    }

    /**
     * Sets the value of the newProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomProperty }
     *     
     */
    public void setNewProperty(CustomProperty value) {
        this.newProperty = value;
    }

}
