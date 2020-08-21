
package com.microfocus.silkcentral.webservices.requirements.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for unassignTest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="unassignTest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requirementId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="testId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unassignTest", propOrder = {
    "sessionId",
    "requirementId",
    "testId"
})
public class UnassignTest {

    protected String sessionId;
    protected int requirementId;
    protected int testId;

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
     * Gets the value of the testId property.
     * 
     */
    public int getTestId() {
        return testId;
    }

    /**
     * Sets the value of the testId property.
     * 
     */
    public void setTestId(int value) {
        this.testId = value;
    }

}
