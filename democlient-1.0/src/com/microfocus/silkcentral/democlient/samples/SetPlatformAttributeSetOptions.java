package com.microfocus.silkcentral.democlient.samples;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import com.microfocus.silkcentral.webservices.tests.stubs.PlanningNode;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyValue;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsService;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsServiceService;
import com.microfocus.silkcentral.webservices.tests.stubs.WSFaultException;

/**
 * Assigns options of attribute 'Platform' (type SET) to a node.
 *
 */
public class SetPlatformAttributeSetOptions {

  String host = "localhost";
  int port = 19120;
  String user = "admin";
  String pwd = "admin";
  int nodeId = 12; // see demo project: test definition 'Attach File to Requirement' (ID=12)

  /**
   * @param args
   *          Valid arguments: <code>host port user password nodeID</code>
   */
  public static void main(String[] args) {
    SetPlatformAttributeSetOptions optionsSetter = new SetPlatformAttributeSetOptions();
    optionsSetter.processArguments(args);

    try {
      String sessionId = optionsSetter.login();
      optionsSetter.setPlatformAttributeOptions(sessionId);
      PlanningNode node = optionsSetter.retrieveNode(sessionId);
      boolean noAttributesFound = ReadAttributeOptions.printAttributes(node);
      if (noAttributesFound) {
        System.out.printf("no attributes found for node '%s' (ID=%d)%n", node.getName(), node.getId());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void setPlatformAttributeOptions(String sessionId) throws MalformedURLException {
    try {
      getTestsService().updateProperties(sessionId, Arrays.asList(makePlatformOptions()));
    } catch (WSFaultException e) {
      e.printStackTrace();
    }
  }

  private PropertyValue[] makePlatformOptions() {
    return new PropertyValue[] {new PropertyValue() {
      {
        setPropertyTypeID("_attribute_option_");
        setPropertyID("_attribute_option_Platform");
        setName("Platform");
        setValue("Platform");
        setNodeID(nodeId);
        setType(12); // attribute type SET (see javadoc of PropertyMetaInfo)
        PropertyValue[] propertyValues = new PropertyValue[] {new PropertyValue() {
          {
            setPropertyTypeID("_attribute_option_");
            setPropertyID("_attribute_option_Platform");
            setName("Platform");
            setValue("Windows 2000");
            setNodeID(nodeId);
            setType(12);
          }
        }, new PropertyValue() {
          {
            setPropertyTypeID("_attribute_option_");
            setPropertyID("_attribute_option_Platform");
            setName("Platform");
            setValue("Windows 2003");
            setNodeID(nodeId);
            setType(12); // attribute type SET (see javadoc of PropertyMetaInfo)
          }
        }, new PropertyValue() {
          {
            setPropertyTypeID("_attribute_option_");
            setPropertyID("_attribute_option_Platform");
            setName("Platform");
            setValue("Windows XP");
            setNodeID(nodeId);
            setType(12); // attribute type SET (see javadoc of PropertyMetaInfo)
          }
        },};
        getChildren().addAll(Arrays.asList(propertyValues));
      }
    }};
  }

  private PlanningNode retrieveNode(String sessionId) throws MalformedURLException {
    try {
      return getTestsService().getNode(sessionId, nodeId);
    } catch (WSFaultException e) {
      e.printStackTrace();
    }
    return null;
  }

  private String login() throws MalformedURLException {
    try {
      return getTestsService().logonUser(user, pwd);
    } catch (WSFaultException e) {
      e.printStackTrace();
    }
    return null;
  }

  private TestsService getTestsService() throws MalformedURLException {
    TestsServiceService testsService = new TestsServiceService(new URL("http", host, port, "/Services1.0/jaxws/tests?wsdl"));
    return testsService.getTestsServicePort();
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