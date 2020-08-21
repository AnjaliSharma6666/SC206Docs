package com.microfocus.silkcentral.democlient.samples;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.microfocus.silkcentral.webservices.tests.stubs.PlanningNode;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyValue;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsService;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsServiceService;
import com.microfocus.silkcentral.webservices.tests.stubs.WSFaultException;

public class AddManualTestWithPropertiesAndSteps {

  private static final int PROJECT_ID = 0; // project-id of SC project
  String host = "localhost";
  int port = 19120;
  String user = "admin";
  String pwd = "admin";
  int parentNodeId = 2; // see demo project: test folder 'Login' (ID=2)
  String browserOption = "IE 6";

  public static void main(String[] args) {
    AddManualTestWithPropertiesAndSteps addSample = new AddManualTestWithPropertiesAndSteps();
    addSample.processArguments(args);

    try {
      String sessionId = addSample.login();
      int newNodeId = addSample.addManualTestWithPropertiesAndSteps(sessionId, PROJECT_ID);
      PlanningNode node = addSample.retrieveNode(sessionId, newNodeId);
      printAllProperties(node);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private int addManualTestWithPropertiesAndSteps(String sessionId, int projectId) throws MalformedURLException {
    PlanningNode manualTestNode = new PlanningNode();

    manualTestNode.setKind(3);
    PropertyValue[] propertyValues = new PropertyValue[] {new PropertyValue() { // test type property
      {
        setPropertyTypeID("_node_properties_");
        setPropertyID("_node_properties_testdef_type");
        setName("testdef_type");
        setValue("_mt_");
      }
    }, new PropertyValue() {
      {
        setPropertyTypeID("_node_properties_");
        setPropertyID("_node_properties_Name");
        setName("Name");
        setValue("test name");
      }
    }, new PropertyValue() {
      {
        setPropertyTypeID("_node_properties_");
        setPropertyID("_node_properties_Description");
        setName("Description");
        setValue("test description");
      }
    }, new PropertyValue() {
      {
        setPropertyTypeID("_mt_");
        setPropertyID("_mt_PlannedTime");
        setName("PlannedTime");
        setValue("08:15"); // HH.mm
      }
    }, new PropertyValue() {
      {
        setPropertyTypeID("_attribute_");
        setPropertyID("_attribute_Browser");
        setName("Browser");
        setValue(browserOption);
        setType(11); // attribute type NORMAL (see javadoc of PropertyMetaInfo)
      }
    }, new PropertyValue() {
      {
        setPropertyTypeID("_attribute_option_");
        setPropertyID("_attribute_option_Platform");
        setName("Platform");
        setValue("Platform");
        setType(12); // attribute type SET (see javadoc of PropertyMetaInfo)
        PropertyValue[] propertyValues = new PropertyValue[] {new PropertyValue() {
          {
            setPropertyTypeID("_attribute_option_");
            setPropertyID("_attribute_option_Platform");
            setName("Platform");
            setValue("Windows 2000");
            setType(12);
          }
        }, new PropertyValue() {
          {
            setPropertyTypeID("_attribute_option_");
            setPropertyID("_attribute_option_Platform");
            setName("Platform");
            setValue("Windows XP");
            setType(12); // attribute type SET (see javadoc of PropertyMetaInfo)
          }
        },};
        getChildren().addAll(Arrays.asList(propertyValues));
      }
    },
        // add 2 STEPS
        new PropertyValue() {
          {
            setPropertyTypeID("_mt_");
            setPropertyID("_mt_ManualTestStep[]");
            setName("ManualTestStep[]");
            PropertyValue[] propertyValues = new PropertyValue[] {
                // STEP 1
                new PropertyValue() { // title
                  {
                    setPropertyTypeID("_mt_ManualTestStep_");
                    setPropertyID("_mt_ManualTestStep_Container");
                    setValue("Step title 1");
                    PropertyValue[] propertyValues = new PropertyValue[] {new PropertyValue() { // order
                      {
                        setPropertyTypeID("_mt_ManualTestStep_");
                        setPropertyID("_mt_ManualTestStep_Order");
                        setValue("0");
                      }
                    }, new PropertyValue() { // action description
                      {
                        setPropertyTypeID("_mt_ManualTestStep_");
                        setPropertyID("_mt_ManualTestStep_Description");
                        setValue("some todo description");
                      }
                    }, new PropertyValue() { // expected results description
                      {
                        setPropertyTypeID("_mt_ManualTestStep_");
                        setPropertyID("_mt_ManualTestStep_ExpectedResults");
                        setValue("some expected result description");
                      }
                    },};
                    getChildren().addAll(Arrays.asList(propertyValues));
                  }
                },
                // STEP 2
                new PropertyValue() { // title
                  {
                    setPropertyTypeID("_mt_ManualTestStep_");
                    setPropertyID("_mt_ManualTestStep_Container");
                    setValue("Step title 1");
                    PropertyValue[] propertyValues = new PropertyValue[] {new PropertyValue() { // title
                      {
                        setPropertyTypeID("_mt_ManualTestStep_");
                        setPropertyID("_mt_ManualTestStep_Container");
                        setValue("Step title 1");
                      }
                    }, new PropertyValue() { // order
                      {
                        setPropertyTypeID("_mt_ManualTestStep_");
                        setPropertyID("_mt_ManualTestStep_Order");
                        setValue("1");
                      }
                    }, new PropertyValue() { // action description
                      {
                        setPropertyTypeID("_mt_ManualTestStep_");
                        setPropertyID("_mt_ManualTestStep_Description");
                        setValue("some todo description");
                      }
                    }, new PropertyValue() { // expected results description
                      {
                        setPropertyTypeID("_mt_ManualTestStep_");
                        setPropertyID("_mt_ManualTestStep_ExpectedResults");
                        setValue("some expected result description");
                      }
                    },};
                    getChildren().addAll(Arrays.asList(propertyValues));
                  }
                },};
            getChildren().addAll(Arrays.asList(propertyValues));
          }
        },};
    manualTestNode.getPropertyValues().addAll(Arrays.asList(propertyValues));

    // TODO project ID
    int newNodeId = -1;
    try {
      newNodeId = getTestsService().addNode(sessionId, projectId, parentNodeId,
          manualTestNode /*
                          * throw error on duplicate test name, false would return the existing ID
                          */);
    } catch (WSFaultException e) {
      e.printStackTrace();
    }

    return newNodeId;
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

  private PlanningNode retrieveNode(String sessionId, int nodeId) throws MalformedURLException {
    try {
      return getTestsService().getNode(sessionId, nodeId);
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
            System.out.println("valid arguments: host port user password parentNodeId browserOption");
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
          parentNodeId = Integer.parseInt(args[i]);
          break;
        case 5:
          browserOption = args[i];
      }
    }
  }

  public static void printAllProperties(PlanningNode node) {
    List<PropertyValue> propertyValues = node.getPropertyValues();
    printPropertiesRecursive(propertyValues, 0);
  }

  private static void printPropertiesRecursive(List<PropertyValue> propertyValues, int depth) {
    depth++;
    for (PropertyValue propertyValue : propertyValues) {
      String propertyID = propertyValue.getPropertyID();
      String propertyTypeID = propertyValue.getPropertyTypeID();
      System.out.printf("%sfound property ID '%s' of type '%s' value: '%s'%n", getBlanks(2 * depth), propertyID, propertyTypeID, propertyValue.getValue());
      printPropertiesRecursive(propertyValue.getChildren(), depth);
    }
  }

  private static String getBlanks(int count) {
    char[] arr = new char[count];
    Arrays.fill(arr, ' ');
    return new String(arr);
  }
}