
package com.microfocus.silkcentral.webservices.tests.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addManualTestStep complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addManualTestStep">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="step" type="{http://www.microfocus.com/silkcentral/webservices/tests}manualTestStep" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addManualTestStep", propOrder = {
    "sessionId",
    "testId",
    "step"
})
public class AddManualTestStep {

    protected String sessionId;
    protected int testId;
    protected ManualTestStep step;

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

    /**
     * Gets the value of the step property.
     * 
     * @return
     *     possible object is
     *     {@link ManualTestStep }
     *     
     */
    public ManualTestStep getStep() {
        return step;
    }

    /**
     * Sets the value of the step property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManualTestStep }
     *     
     */
    public void setStep(ManualTestStep value) {
        this.step = value;
    }

}
