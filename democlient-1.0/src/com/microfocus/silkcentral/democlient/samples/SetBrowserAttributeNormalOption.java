package com.microfocus.silkcentral.democlient.samples;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.microfocus.silkcentral.webservices.tests.stubs.PlanningNode;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyValue;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsService;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsServiceService;
import com.microfocus.silkcentral.webservices.tests.stubs.WSFaultException;

public class SetBrowserAttributeNormalOption {

  String host = "localhost";
  int port = 19120;
  String user = "admin";
  String pwd = "admin";
  int nodeId = 3; // see demo project: test definition 'Correct Login data' (ID=3)
  String browserOption = "IE 6";

  /**
   * @param args
   *          Valid arguments: <code>host port user password nodeID browserOption</code>
   */
  public static void main(String[] args) {
    SetBrowserAttributeNormalOption optionsSetter = new SetBrowserAttributeNormalOption();
    optionsSetter.processArguments(args);

    try {
      String sessionId = optionsSetter.login();
      optionsSetter.setBrowserAttributeOption(sessionId);
      PlanningNode node = optionsSetter.retrieveNode(sessionId);
      boolean noAttributesFound = ReadAttributeOptions.printAttributes(node);
      if (noAttributesFound) {
        System.out.printf("no attributes found for node '%s' (ID=%d)%n", node.getName(), node.getId());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void setBrowserAttributeOption(String sessionId) throws MalformedURLException {
    try {
      getTestsService().updateProperties(sessionId, makeBrowserOption());
    } catch (WSFaultException e) {
      e.printStackTrace();
    }
  }

  private TestsService getTestsService() throws MalformedURLException {
    TestsServiceService testsService = new TestsServiceService(new URL("http", host, port, "/Services1.0/jaxws/tests"));
    return testsService.getTestsServicePort();
  }

  private List<PropertyValue> makeBrowserOption() {
    List<PropertyValue> browserOptions = new ArrayList<>();
    PropertyValue propertyValue = new PropertyValue();
    propertyValue.setPropertyTypeID("_attribute_");
    propertyValue.setPropertyID("_attribute_Browser");
    propertyValue.setName("Browser");
    propertyValue.setValue(browserOption);
    propertyValue.setNodeID(nodeId);
    propertyValue.setType(11); // attribute type NORMAL (see javadoc of PropertyMetaInfo)
    browserOptions.add(propertyValue);

    return browserOptions;
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

  private void processArguments(String[] args) {
    for (int i = 0; i < args.length; i++) {
      switch (i) {
        case 0:
          if ("help".equals(args[i])) {
            System.out.println("valid arguments: host port user password nodeID browserOption");
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
        case 5:
          browserOption = args[i];
      }
    }
  }

}
