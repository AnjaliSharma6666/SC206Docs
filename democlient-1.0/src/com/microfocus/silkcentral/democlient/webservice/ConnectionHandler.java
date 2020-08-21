/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.webservice;

import java.net.URL;

import com.microfocus.silkcentral.webservices.tests.stubs.TestsService;
import com.microfocus.silkcentral.webservices.tests.stubs.TestsServiceService;

/**
 * Handler for accessing the planning webservice.
 */
public class ConnectionHandler {

  public LoginContext login(String hostname, String username, String password) throws Exception {
    TestsServiceService testsService = new TestsServiceService(new URL("http://" + hostname + "/Services1.0/jaxws/tests"));
    TestsService planningService = testsService.getTestsServicePort();

    String sessionId = planningService.logonUser(username, password);
    if (sessionId == null) {
      throw new Exception("login failed");
    }
    LoginContext context = new LoginContext(hostname, username, password, sessionId);

    return context;
  }

}
