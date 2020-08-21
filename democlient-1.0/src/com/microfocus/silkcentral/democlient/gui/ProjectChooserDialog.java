/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.microfocus.silkcentral.democlient.gui.utils.UITool;
import com.microfocus.silkcentral.webservices.tests.stubs.Project;

/**
 * Simple dialog for choosing a project.
 */
public class ProjectChooserDialog extends JDialog {

  private static final long serialVersionUID = 1L;
  private Project selectedProject;

  public ProjectChooserDialog(Frame owner, Collection<Project> projects) {
    super(owner, "Choose Project", true);
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    JPanel contentPane = (JPanel) getContentPane();
    contentPane.setLayout(new BorderLayout(0, 10));
    contentPane.setBorder(BorderFactory.createEmptyBorder(11, 11, 10, 10));

    JButton okBtn = new JButton("OK");
    JButton cancelBtn = new JButton("Cancel");

    final JComboBox combo = new JComboBox();
    for (Project project : projects) {
      combo.addItem(project);
    }

    JPanel comboPn = new JPanel(new BorderLayout());
    comboPn.add(new JLabel("Project:"), BorderLayout.WEST);
    comboPn.add(combo, BorderLayout.CENTER);

    combo.setRenderer(new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel lb = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Project project = (Project) value;
        lb.setText(project.getName());
        return lb;
      }

    });

    contentPane.add(UITool.createButtonsPane(okBtn, cancelBtn), BorderLayout.SOUTH);
    contentPane.add(comboPn, BorderLayout.NORTH);

    cancelBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
      }
    });

    okBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        selectedProject = (Project) combo.getSelectedItem();
        setVisible(false);
        dispose();
      }
    });

    pack();
  }

  public Project getSelectedProject() {
    return selectedProject;
  }

}
