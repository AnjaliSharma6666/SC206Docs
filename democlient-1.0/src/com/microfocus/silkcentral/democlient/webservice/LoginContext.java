/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.webservice;

/**
 * Holds information about the session.
 */
public class LoginContext {
  public LoginContext(String hostname, String username, String password, String sessionId) {
    super();
    this.hostname = hostname;
    this.password = password;
    this.sessionId = sessionId;
    this.username = username;
  }

  private String username;
  private String password;
  private String hostname;
  private String sessionId;

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setHost(String host) {
    hostname = host;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getHost() {
    return hostname;
  }

  public String getSessionId() {
    return sessionId;
  }

}
