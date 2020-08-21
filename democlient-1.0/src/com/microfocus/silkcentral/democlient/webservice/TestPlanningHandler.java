/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.webservice;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.microfocus.silkcentral.webservices.tests.stubs.AttributeOption;
import com.microfocus.silkcentral.webservices.tests.stubs.ManualTestStep;
import com.microfocus.silkcentral.webservices.tests.stubs.NamedEntity;
import com.microfocus.silkcentral.webservices.tests.stubs.PlanningNode;
import com.microfocus.silkcentral.webservices.tests.stubs.Project;
import com.microfocus.silkcentral.webservices.tests.stubs.ProjectAttribute;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyMetaInfo;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyValue;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsService;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsServiceService;

/**
 * This class wraps all planning web service functionality used by the demo client.
 * 
 *
 */
public class TestPlanningHandler {

  private final TestsService mTestsService;
  private final LoginContext context;
  private Project currentProject;

  /**
   * Creates the client stub for the planning web service.
   * 
   * @param context
   *          The context contains the sessionId that is used in all web service calls as means of authentication.
   * @throws Exception
   *           Any exception thrown during initializing planning web service stub.
   */
  public TestPlanningHandler(LoginContext context) throws Exception {
    this.context = context;

    // initialize the web service stub for the planning web service
    TestsServiceService testsService = new TestsServiceService(new URL("http://" + this.context.getHost() + "/Services1.0/jaxws/tests"));
    mTestsService = testsService.getTestsServicePort();
  }

  /**
   * Returns all active projects that are accessible to the current user
   * 
   * @return all active projects.
   * @throws Exception
   *           Any exception thrown during getting projects.
   */
  public Collection<Project> getProjects() throws Exception {
    return mTestsService.getProjects(context.getSessionId());
  }

  /**
   * Sets the project for the current session. All subsequent planning web service calls will be executed in the context of the project specified.
   * 
   * @param project
   *          specifies the context for all subsequent planning web service calls.
   * @throws Exception
   *           Any exception thrown during setting project.
   */
  public void setCurrentProject(Project project) throws Exception {
    currentProject = project;
  }

  /**
   * Returns all test containers of the current project (see method {@link TestPlanningHandler#setCurrentProject(Project)}).
   * 
   * @return A collection of TestContainers represented by the class {@link NamedEntity}.
   * @throws Exception
   *           Any exception thrown during getting test containers.
   */
  public Collection<NamedEntity> getTestContainers() throws Exception {
    return mTestsService.getTestContainers(context.getSessionId(), currentProject.getId());
  }

  /**
   * Returns all child nodes for a specified parent node. Note: The properties of the child nodes are not set. Use
   * {@link TestPlanningHandler#getPropertyValues(int)} to retrieve all properties for a node.
   * 
   * @param parentId
   *          The id of the parent node.
   * @return all child nodes represented by the class {@link PlanningNode}.
   * @throws Exception
   *           Any exception thrown during getting child nodes.
   */
  public Collection<PlanningNode> getChildNodes(int parentId) throws Exception {
    List<PlanningNode> childNodes = mTestsService.getChildNodes(context.getSessionId(), currentProject.getId(), parentId, null);
    if (childNodes != null) {
      return childNodes;
    } else {
      return new ArrayList<>();
    }
  }

  /**
   * Returns all properties for a specified node.
   * 
   * @param nodeId
   *          specifies the node for which the properties are loaded.
   * @return the node's properties represented by the class {@link PropertyValue}.
   * @throws Exception
   *           Any exception thrown during getting property values.
   */
  public Collection<PropertyValue> getPropertyValues(int nodeId) throws Exception {
    PlanningNode node = mTestsService.getNode(context.getSessionId(), nodeId);

    Collection<PropertyValue> values = new ArrayList<>();
    for (PropertyValue property : node.getPropertyValues()) {
      values.add(property);
    }
    return values;
  }

  /**
   * Returns all available test planning node types (e.g. 'Test Container', 'SilkTest', 'JUnit', 'ThirdParty').
   * 
   * @return all available node types.
   * @throws Exception
   *           Any exception thrown during getting planning node types.
   */
  public List<String> getNodeTypeIds() throws Exception {
    return mTestsService.getNodeTypeIds(context.getSessionId());
  }

  /**
   * Returns all available property types for a given node type (see {@link TestPlanningHandler#getNodeTypeIds()}). Node properties are categorized in various
   * property types (e.g. '_ddtd_' for data driven properties). Some property types are not available for all node types; thus, the node type determines what
   * property types are available.
   * 
   * @param nodeTypeId
   *          specifies the node type.
   * @return all available property types.
   * @throws Exception
   *           Any exception thrown during getting property type Ids.
   */
  public List<String> getPropertyTypeIds(String nodeTypeId) throws Exception {
    return mTestsService.getPropertyTypeIds(context.getSessionId(), currentProject.getId(), nodeTypeId);
  }

  /**
   * Returns information for specified node properties.
   * 
   * @param nodeTypeId
   *          specifies the node type.
   * @param propertyTypeId
   *          specifies the property type.
   * @return information for node properties represented by the class {@link PropertyMetaInfo}.
   * @throws Exception
   *           Any exception thrown during getting property info.
   */
  public Collection<PropertyMetaInfo> getPropertyInfo(String nodeTypeId, String propertyTypeId) throws Exception {
    Collection<PropertyMetaInfo> propertyMetaInfoList = new ArrayList<>();

    // As stated in the documentation of the method getPropertyTypeIds, all properties are grouped in property types.
    // First we need to know all available properties of a specific node type. This is achieved with the webservice call
    // getPropertyIds().
    // List<String> propertyIds = planningService.getPropertyIds(context.getSessionId());
    List<String> propertyIds = mTestsService.getPropertyIdsByNodeType(context.getSessionId(), currentProject.getId(), nodeTypeId, propertyTypeId);
    for (String propertyId : propertyIds) {
      // Now we can get the property information of a property.
      PropertyMetaInfo propertyInfo = mTestsService.getPropertyInfo(context.getSessionId(), currentProject.getId(), nodeTypeId, propertyId);
      propertyMetaInfoList.add(propertyInfo);
    }

    return propertyMetaInfoList;
  }

  /**
   * Returns property information available for a specific node type.
   * 
   * @param nodeTypeId
   *          specifies the node type.
   * @return information for node properties represented by the class {@link PropertyMetaInfo}.
   * @throws Exception
   *           Any exception thrown during getting property info type.
   */
  public Collection<PropertyMetaInfo> getPropertyInfoForNodeType(String nodeTypeId) throws Exception {
    Collection<PropertyMetaInfo> propertyInfo = new HashSet<>();
    List<String> propertyTypeIds = getPropertyTypeIds(nodeTypeId);
    for (String propertyType : propertyTypeIds) {
      propertyInfo.addAll(getPropertyInfo(nodeTypeId, propertyType));
    }
    return propertyInfo;
  }

  public PropertyValue createPropertyValue(PropertyMetaInfo propMetaInfo, String value) {
    // the property ID consists of two parts: the property type ID and the property name
    // the property ID for the name of nodes is '_node_properties_Name'. '_node_properties_' is the property type ID,
    // 'Name' the name of the property.
    String propertyTypeId = propMetaInfo.getId();
    // cut away the name part of the property ID in order to get the property type ID.
    propertyTypeId = propertyTypeId.substring(0, propertyTypeId.length() - propMetaInfo.getName().length());
    // modify count and node ID are internal values of Silk Central. Those values are not editable via web service.
    PropertyValue propertyValue = new PropertyValue();
    propertyValue.setModifyCount(-1);
    propertyValue.setName(propMetaInfo.getName());
    propertyValue.setNodeID(-1);
    propertyValue.setPropertyID(propMetaInfo.getId());
    propertyValue.setPropertyTypeID(propertyTypeId);
    propertyValue.setType(propMetaInfo.getType());
    propertyValue.setValue(value);

    return propertyValue;
  }

  public int addNode(int parentId, Collection<PropertyValue> properties, int kind) throws Exception {
    PlanningNode node = new PlanningNode();

    node.getPropertyValues().addAll(properties);
    node.setKind(kind);// There are 3 different kinds: 1 - Test Container, 2 - Test Folder, 3 - Test Definition
    return mTestsService.addNode(context.getSessionId(), currentProject.getId(), parentId, node);
  }

  public void addManualTestNode(int parentNodeId, String name, String description, String plannedTime, ManualTestStep[] steps) throws Exception {
    PlanningNode node = new PlanningNode();
    node.setKind(3);// There are 3 different kinds: 1 - Test Container, 2 - Test Folder, 3 - Test Definition

    // create basic manual test properties: testdef_type, Name, Description, and PlannedTime
    Collection<PropertyValue> properties = new ArrayList<>();
    PropertyValue typeProperty = new PropertyValue();
    typeProperty.setPropertyID("_node_properties_testdef_type");
    typeProperty.setPropertyTypeID("_node_properties_");
    typeProperty.setName("testdef_type");
    typeProperty.setValue("_mt_");
    properties.add(typeProperty);

    PropertyValue nameProperty = new PropertyValue();
    nameProperty.setPropertyID("_node_properties_Name");
    nameProperty.setPropertyTypeID("_node_properties_");
    nameProperty.setName("Name");
    nameProperty.setValue(name);
    properties.add(nameProperty);

    PropertyValue descrProperty = new PropertyValue();
    descrProperty.setPropertyID("_node_properties_Description");
    descrProperty.setPropertyTypeID("_node_properties_");
    descrProperty.setName("Description");
    descrProperty.setValue(description);
    properties.add(descrProperty);

    PropertyValue plannedTimeProperty = new PropertyValue();
    plannedTimeProperty.setPropertyID("_mt_PlannedTime");
    plannedTimeProperty.setPropertyTypeID("_mt_");
    plannedTimeProperty.setName("PlannedTime");
    plannedTimeProperty.setValue(plannedTime);
    properties.add(plannedTimeProperty);

    // create properties for test steps
    // the structure looks as follows:
    // _ManualTestStep[]
    // |_Container (value: step name)
    // |_Order
    // |_ExpectedResult
    // |_Description
    PropertyValue stepsProperty = new PropertyValue();
    stepsProperty.setPropertyID("_mt_ManualTestStep[]");
    stepsProperty.setPropertyTypeID("_mt_");
    stepsProperty.setName("ManualTestStep[]");
    Collection<PropertyValue> children = new ArrayList<>();
    int order = 0;
    for (ManualTestStep step : steps) {
      PropertyValue stepProperty = createStepContainer(step, order++);
      children.add(stepProperty);
    }
    stepsProperty.getChildren().addAll(children);
    properties.add(stepsProperty);

    node.getPropertyValues().addAll(properties);
    mTestsService.addNode(context.getSessionId(), currentProject.getId(), parentNodeId, node);
  }

  private PropertyValue createStepContainer(ManualTestStep step, int orderNumber) {
    // The property _mt_ManualTestStep_Container is a container for manual test step properties.
    // The value for this property holds the name of the step
    PropertyValue container = new PropertyValue();
    container.setPropertyID("_mt_ManualTestStep_Container");
    container.setPropertyTypeID("_mt_ManualTestStep_");
    container.setValue(step.getName());

    // Additionally, order, expected results, and description can be set as child properties of the container
    PropertyValue order = new PropertyValue();
    order.setPropertyID("_mt_ManualTestStep_Order");
    order.setPropertyTypeID("_mt_ManualTestStep_");
    order.setValue(String.valueOf(orderNumber));
    PropertyValue expectedResults = new PropertyValue();
    expectedResults.setPropertyID("_mt_ManualTestStep_ExpectedResults");
    expectedResults.setPropertyTypeID("_mt_ManualTestStep_");
    expectedResults.setValue(step.getExpectedResult());
    PropertyValue description = new PropertyValue();
    description.setPropertyID("_mt_ManualTestStep_Description");
    description.setPropertyTypeID("_mt_ManualTestStep_");
    description.setValue(step.getDescription());

    PropertyValue[] propertyValues = new PropertyValue[] {expectedResults, description, order};
    container.getChildren().addAll(Arrays.asList(propertyValues));
    return container;
  }

  public LoginContext getLoginContext() {
    return context;
  }

  public void createAttribute(String name, String description, String type, List<String> options) throws Exception {
    ProjectAttribute attribute = new ProjectAttribute();
    attribute.setName(name);
    attribute.setDescription(description);
    attribute.setType(type);
    attribute.getOptions().addAll(getOptions(options));
    mTestsService.addAttributes(context.getSessionId(), currentProject.getId(), Arrays.asList(new ProjectAttribute[] {attribute}));
  }

  private List<AttributeOption> getOptions(List<String> options) {
    List<AttributeOption> attributeOptions = new ArrayList<>();
    for (String option : options) {
      AttributeOption attributeOption = new AttributeOption();
      attributeOption.setName(option);
      attributeOptions.add(attributeOption);
    }
    return attributeOptions;
  }

  public List<ProjectAttribute> getAttributes() throws Exception {
    return mTestsService.getAttributes(context.getSessionId(), currentProject.getId());
  }
}
