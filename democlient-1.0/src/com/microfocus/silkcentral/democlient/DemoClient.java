/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.microfocus.silkcentral.democlient.gui.AboutDialog;
import com.microfocus.silkcentral.democlient.gui.LoginDialog;
import com.microfocus.silkcentral.democlient.gui.MainWindow;
import com.microfocus.silkcentral.democlient.gui.ProjectChooserDialog;
import com.microfocus.silkcentral.democlient.gui.utils.UITool;
import com.microfocus.silkcentral.democlient.webservice.ConnectionHandler;
import com.microfocus.silkcentral.democlient.webservice.LoginContext;
import com.microfocus.silkcentral.democlient.webservice.TestPlanningHandler;
import com.microfocus.silkcentral.webservices.tests.stubs.Project;

/**
 * The Demo Client main class.
 */
public class DemoClient {

  public static final String TITLE = "Tests Web service Demo Client";

  private void run() throws Exception {

    AboutDialog disclamerDialog = new AboutDialog(null, "http://desktop-5ivatg8:19120", AboutDialog.Type.ACCEPT);
    UITool.centerAndShow(disclamerDialog);
    if (!disclamerDialog.accepted()) {
      return;
    }

    LoginDialog loginDialog = new LoginDialog();

    UITool.centerAndShow(loginDialog);
    loginDialog.dispose();
    if (loginDialog.getResult() != LoginDialog.Result.OK) {
      return;
    }

    // Step 1: login
    ConnectionHandler connectionHandler = new ConnectionHandler();
    LoginContext loginContext = connectionHandler.login(loginDialog.getHostname(), loginDialog.getUsername(), loginDialog.getPassword());

    // Step 2: get all available projects
    TestPlanningHandler planningHandler = new TestPlanningHandler(loginContext);
    Collection<Project> allProjects = planningHandler.getProjects();

    ProjectChooserDialog projectChooser = new ProjectChooserDialog(null, allProjects);
    UITool.centerAndShow(projectChooser);
    Project p = projectChooser.getSelectedProject();
    if (p == null) {
      return;
    }

    // Step 3: select a project
    planningHandler.setCurrentProject(p);

    // Step 4: now the client is set up for all webservice operations (e.g. addNode, updateNode)
    JFrame frame = new MainWindow(planningHandler);
    UITool.centerAndShow(frame);
  }

  public static void main(String[] args) {

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      // ignore
    }

    DemoClient client = new DemoClient();
    try {
      client.run();
    } catch (Exception e) {
      UITool.handleError(null, e);
    }

  }
}
