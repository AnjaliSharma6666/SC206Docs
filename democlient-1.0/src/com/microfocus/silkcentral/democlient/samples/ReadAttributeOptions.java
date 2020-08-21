package com.microfocus.silkcentral.democlient.samples;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.microfocus.silkcentral.webservices.tests.stubs.PlanningNode;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyValue;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsService;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsServiceService;
import com.microfocus.silkcentral.webservices.tests.stubs.WSFaultException;

public class ReadAttributeOptions {

  String host = "localhost";
  int port = 19120;
  String user = "admin";
  String pwd = "admin";
  int nodeId = 3; // see demo project: test definition 'Correct Login data' (ID=3)

  /**
   * @param args
   *          Valid arguments: <code>host port user password nodeID</code>
   */
  public static void main(String[] args) {
    ReadAttributeOptions optionsReader = new ReadAttributeOptions();
    optionsReader.processArguments(args);

    try {
      String sessionId = optionsReader.login();
      PlanningNode node = optionsReader.retrieveNode(sessionId);
      boolean noAttributesFound = printAttributes(node);
      if (noAttributesFound) {
        System.out.printf("no attributes found for node '%s' (ID=%d)%n", node.getName(), node.getId());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static boolean printAttributes(PlanningNode node) {
    boolean noAttributesFound = true;
    List<PropertyValue> propertyValues = node.getPropertyValues();
    for (PropertyValue propertyValue : propertyValues) {
      String propertyID = propertyValue.getPropertyID();
      String propertyTypeID = propertyValue.getPropertyTypeID();
      if (propertyTypeID.startsWith("_attribute_")) {
        noAttributesFound = false;
        System.out.printf("found property ID '%s' of type '%s' value: '%s'%n", propertyID, propertyTypeID, propertyValue.getValue());
        // print options if exist
        List<PropertyValue> options = propertyValue.getChildren();
        for (PropertyValue option : options) {
          System.out.printf("  + option value: '%s'%n", option.getValue());
        }
      }
    }
    return noAttributesFound;
  }

  private PlanningNode retrieveNode(String sessionId) throws MalformedURLException {
    try {
      return getTestsService().getNode(sessionId, nodeId);
    } catch (WSFaultException e) {
      e.printStackTrace();
    }
    return null;
  }

  private TestsService getTestsService() throws MalformedURLException {
    TestsServiceService testsService = new TestsServiceService(new URL("http", host, port, "/Services1.0/jaxws/tests"));
    return testsService.getTestsServicePort();
  }

  private String login() throws MalformedURLException {
    try {
      return getTestsService().logonUser(user, pwd);
    } catch (WSFaultException e) {
      e.printStackTrace();
    }
    return null;
  }

  private void processArguments(String[] args) {
    for (int i = 0; i < args.length; i++) {
      switch (i) {
        case 0:
          if ("help".equals(args[i])) {
            System.out.println("valid arguments: host port user password nodeID");
            System.exit(0);
          } else {
            host = args[i];
          }
          break;
        case 1:
          port = Integer.parseInt(args[i]);
          break;
        case 2:
          user = args[i];
          break;
        case 3:
          pwd = args[i];
        case 4:
          nodeId = Integer.parseInt(args[i]);
          break;
      }
    }
  }

}