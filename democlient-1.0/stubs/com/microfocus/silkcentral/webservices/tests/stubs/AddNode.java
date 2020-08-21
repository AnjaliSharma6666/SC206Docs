
package com.microfocus.silkcentral.webservices.tests.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addNode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addNode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="projectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parentNodeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="node" type="{http://www.microfocus.com/silkcentral/webservices/tests}planningNode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addNode", propOrder = {
    "sessionId",
    "projectId",
    "parentNodeId",
    "node"
})
public class AddNode {

    protected String sessionId;
    protected int projectId;
    protected int parentNodeId;
    protected PlanningNode node;

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
     * Gets the value of the projectId property.
     * 
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     */
    public void setProjectId(int value) {
        this.projectId = value;
    }

    /**
     * Gets the value of the parentNodeId property.
     * 
     */
    public int getParentNodeId() {
        return parentNodeId;
    }

    /**
     * Sets the value of the parentNodeId property.
     * 
     */
    public void setParentNodeId(int value) {
        this.parentNodeId = value;
    }

    /**
     * Gets the value of the node property.
     * 
     * @return
     *     possible object is
     *     {@link PlanningNode }
     *     
     */
    public PlanningNode getNode() {
        return node;
    }

    /**
     * Sets the value of the node property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanningNode }
     *     
     */
    public void setNode(PlanningNode value) {
        this.node = value;
    }

}
