/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.microfocus.silkcentral.democlient.gui.utils.UITool;
import com.microfocus.silkcentral.democlient.webservice.TestPlanningHandler;
import com.microfocus.silkcentral.webservices.tests.stubs.ProjectAttribute;

@SuppressWarnings("serial")
public class ListAttributeDialog extends JDialog {

  public ListAttributeDialog(MainWindow mainWindow, TestPlanningHandler planningHandler) {
    try {
      JPanel panel = new JPanel(new BorderLayout(5, 5));
      JPanel attrList = new JPanel(new GridLayout(-1, 1));
      JScrollPane cpanel = new JScrollPane(attrList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));

      panel.add(cpanel, BorderLayout.CENTER);
      panel.add(buttons, BorderLayout.SOUTH);
      add(panel);

      List<ProjectAttribute> attributes = planningHandler.getAttributes();
      for (ProjectAttribute attribute : attributes) {
        JPanel rpanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        rpanel.add(new JLabel(attribute.getName() + " (" + attribute.getType() + ")"));
        attrList.add(rpanel);
      }

      JButton cancel = new JButton();
      cancel.setAction(new AbstractAction("Cancel") {
        @Override
        public void actionPerformed(final ActionEvent e) {
          setVisible(false);
        }
      });
      buttons.add(cancel);

    } catch (Exception e) {
      UITool.handleError(this, e);
      setVisible(false);
    }
  }

}
