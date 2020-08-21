
package com.microfocus.silkcentral.webservices.tests.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for nodeFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="nodeFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testDefinitionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testPlanningFilterId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nodeFilter", propOrder = {
    "createdBy",
    "testDefinitionType",
    "testPlanningFilterId"
})
public class NodeFilter {

    protected String createdBy;
    protected String testDefinitionType;
    protected int testPlanningFilterId;

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
     * Gets the value of the testDefinitionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestDefinitionType() {
        return testDefinitionType;
    }

    /**
     * Sets the value of the testDefinitionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestDefinitionType(String value) {
        this.testDefinitionType = value;
    }

    /**
     * Gets the value of the testPlanningFilterId property.
     * 
     */
    public int getTestPlanningFilterId() {
        return testPlanningFilterId;
    }

    /**
     * Sets the value of the testPlanningFilterId property.
     * 
     */
    public void setTestPlanningFilterId(int value) {
        this.testPlanningFilterId = value;
    }

}
