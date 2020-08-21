
package com.microfocus.silkcentral.webservices.requirements.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteRequirement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteRequirement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requirementId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recursiv" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteRequirement", propOrder = {
    "sessionId",
    "requirementId",
    "recursiv"
})
public class DeleteRequirement {

    protected String sessionId;
    protected int requirementId;
    protected boolean recursiv;

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
     * Gets the value of the requirementId property.
     * 
     */
    public int getRequirementId() {
        return requirementId;
    }

    /**
     * Sets the value of the requirementId property.
     * 
     */
    public void setRequirementId(int value) {
        this.requirementId = value;
    }

    /**
     * Gets the value of the recursiv property.
     * 
     */
    public boolean isRecursiv() {
        return recursiv;
    }

    /**
     * Sets the value of the recursiv property.
     * 
     */
    public void setRecursiv(boolean value) {
        this.recursiv = value;
    }

}
