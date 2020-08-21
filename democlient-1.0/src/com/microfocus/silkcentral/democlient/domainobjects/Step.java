/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.domainobjects;

public class Step {

  private String name;
  private String description;
  private String expectedResult;

  public Step(String name, String description, String expectedResult) {
    this.name = name;
    this.description = description;
    this.expectedResult = expectedResult;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getExpectedResult() {
    return expectedResult;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public void setExpectedResult(final String expectedResult) {
    this.expectedResult = expectedResult;
  }
}
