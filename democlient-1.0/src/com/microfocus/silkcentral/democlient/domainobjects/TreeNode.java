/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.domainobjects;

import com.microfocus.silkcentral.webservices.tests.stubs.NamedEntity;
import com.microfocus.silkcentral.webservices.tests.stubs.PlanningNode;

public class TreeNode {

  private final String name;
  private final String description;
  private final int id;

  public TreeNode(NamedEntity testContainer) {
    name = testContainer.getName();
    description = testContainer.getDescription();
    id = testContainer.getId();
  }

  public TreeNode(PlanningNode planningNode) {
    name = planningNode.getName();
    description = planningNode.getDescription();
    id = planningNode.getId();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return name;
  }
}
