package com.microfocus.silkcentral.democlient.samples;

import java.net.MalformedURLException;
import java.net.URL;

import com.microfocus.silkcentral.webservices.requirements.stubs.PropertyValue;
import com.microfocus.silkcentral.webservices.requirements.stubs.Requirement;
import com.microfocus.silkcentral.webservices.requirements.stubs.RequirementsService;
import com.microfocus.silkcentral.webservices.requirements.stubs.RequirementsServiceService;
import com.microfocus.silkcentral.webservices.requirements.stubs.WSFaultException;

public class AddingRequirement {

  private static final int PROJECT_ID = 0; // project-id of SC project

  /** propertyID for requirement risk */
  public static final String PROPERTY_RISK = "Risk";

  /** propertyID for requirement reviewed */
  public static final String PROPERTY_REVIEWED = "Reviewed";

  /** propertyID for requirement priority */
  public static final String PROPERTY_PRIORITY = "Priority";

  /** propertyID for requirement obsolete property */
  public static final String PROPERTY_OBSOLETE = "Obsolete";

  private final String mHost = "localhost";
  private final int mPort = 19120;
  private final String mUsername = "admin";
  private final String mPassword = "admin";

  public static void main(String[] args) {
    AddingRequirement sample = new AddingRequirement();
    sample.addRequirement();
  }

  private void addRequirement() {
    try {
      // Get the Requirements service
      RequirementsServiceService port = new RequirementsServiceService(new URL("http", mHost, mPort, "/Services1.0/jaxws/requirements?wsdl"));
      RequirementsService requirementsService = port.getRequirementsServicePort();

      // Login to Silk Central
      String sessionId = requirementsService.logonUser(mUsername, mPassword);

      // Construct Top Level Requirement
      Requirement topLevelRequirement = new Requirement();
      topLevelRequirement.setName("tmReqMgt TopLevelReq");
      topLevelRequirement.setDescription("tmReqMgt TopLevel Desc");

      PropertyValue propRisk = new PropertyValue();
      propRisk.setPropertyId(PROPERTY_RISK);
      propRisk.setValue("2");
      PropertyValue propPriority = new PropertyValue();
      propPriority.setPropertyId(PROPERTY_PRIORITY);
      propPriority.setValue("3");
      PropertyValue[] properties = new PropertyValue[] {propRisk, propPriority};

      /*
       * First add requirement skeleton, get its ID service is a binding stub, see above getRequirementsService() snippet sessionId is the stored session ID,
       * see above login() snippet
       */
      int requirementID = requirementsService.updateRequirement(sessionId, PROJECT_ID, topLevelRequirement, -1);

      // Now loop through and set properties
      for (PropertyValue propValue : properties) {
        propValue.setRequirementId(requirementID);
        requirementsService.updateProperty(sessionId, requirementID, propValue);
      }

      // Add Child Requirement
      Requirement childRequirement = new Requirement();
      childRequirement.setName("tmReqMgt ChildReq");
      childRequirement.setDescription("tmReqMgt ChildLevel Desc");
      childRequirement.setParentId(requirementID);
      propRisk = new PropertyValue();
      propRisk.setPropertyId(PROPERTY_RISK);
      propRisk.setValue("1");
      propPriority = new PropertyValue();
      propPriority.setPropertyId(PROPERTY_PRIORITY);
      propPriority.setValue("1");
      properties = new PropertyValue[] {propRisk, propPriority};

      int childReqID = requirementsService.updateRequirement(sessionId, PROJECT_ID, childRequirement, -1);
      // Now loop through and set properties
      for (PropertyValue propValue : properties) {
        propValue.setRequirementId(requirementID);
        requirementsService.updateProperty(sessionId, childReqID, propValue);
      }

      // Print Results
      System.out.println("Login Successful with mSessionID: " + sessionId);
      System.out.println("Top Level Requirement ID: " + requirementID);
      System.out.println("Child Requirement ID: " + childReqID);
    } catch (MalformedURLException | WSFaultException e1) {
      e1.printStackTrace();
    }
  }
}