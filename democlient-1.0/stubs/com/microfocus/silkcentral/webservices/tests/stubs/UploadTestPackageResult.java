
package com.microfocus.silkcentral.webservices.tests.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for uploadTestPackageResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="uploadTestPackageResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="packageNodeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="xmlPackage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadTestPackageResult", propOrder = {
    "sessionId",
    "packageNodeId",
    "xmlPackage"
})
public class UploadTestPackageResult {

    protected String sessionId;
    protected int packageNodeId;
    protected String xmlPackage;

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
     * Gets the value of the packageNodeId property.
     * 
     */
    public int getPackageNodeId() {
        return packageNodeId;
    }

    /**
     * Sets the value of the packageNodeId property.
     * 
     */
    public void setPackageNodeId(int value) {
        this.packageNodeId = value;
    }

    /**
     * Gets the value of the xmlPackage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlPackage() {
        return xmlPackage;
    }

    /**
     * Sets the value of the xmlPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlPackage(String value) {
        this.xmlPackage = value;
    }

}
