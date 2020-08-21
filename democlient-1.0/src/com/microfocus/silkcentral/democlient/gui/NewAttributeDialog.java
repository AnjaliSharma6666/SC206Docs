/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import com.microfocus.silkcentral.democlient.gui.utils.UITool;
import com.microfocus.silkcentral.democlient.webservice.TestPlanningHandler;

@SuppressWarnings("serial")
public class NewAttributeDialog extends JDialog {

  private final TestPlanningHandler mPlanningHandler;
  private final DefaultTableModel mOptionsTableModel;

  public NewAttributeDialog(final Frame parent, TestPlanningHandler planningHandler) {
    super(parent, true); // modal
    mPlanningHandler = planningHandler;

    final JPanel grid = new JPanel(new GridLayout(-1, 2, 3, 3));

    grid.add(new JLabel("Name"));
    final JTextField name = new JTextField();
    grid.add(name);

    grid.add(new JLabel("Description"));
    final JTextField description = new JTextField();
    grid.add(description);

    grid.add(new JLabel("Attribute Type"));
    final JComboBox type = new JComboBox(new String[] {"Edit", "Normal", "Set"});
    grid.add(type);

    final Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(grid, BorderLayout.NORTH);

    mOptionsTableModel = new DefaultTableModel(new String[] {"Option"}, 0);
    final JTable options = new JTable(mOptionsTableModel);
    JScrollPane comp = new JScrollPane(options, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    comp.setPreferredSize(new Dimension(270, 100));
    contentPane.add(comp);
    options.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "insertOption");
    options.getActionMap().put("insertOption", new AbstractAction("newOption") {
      @Override
      public void actionPerformed(ActionEvent e) {
        mOptionsTableModel.addRow(new String[] {""});
      }
    });

    final JPanel buttons = new JPanel();
    buttons.add(new JButton(new AbstractAction("OK") {
      @Override
      public void actionPerformed(final ActionEvent e) {
        createAttribute(name.getText(), description.getText(), (String) type.getSelectedItem(), getOptions());
      }
    }));

    final JButton cancel = new JButton();
    cancel.setAction(new AbstractAction("Cancel") {
      @Override
      public void actionPerformed(final ActionEvent e) {
        setVisible(false);
      }
    });
    buttons.add(cancel);

    contentPane.add(buttons, BorderLayout.SOUTH);
    pack();
  }

  private List<String> getOptions() {
    final List<String> result = new ArrayList<>(mOptionsTableModel.getRowCount());

    for (int i = 0; i < mOptionsTableModel.getRowCount(); i++) {
      String valueAt = (String) mOptionsTableModel.getValueAt(i, 0);
      if (valueAt != null && valueAt.length() > 0) {
        result.add(valueAt);
      }
    }

    return result;
  }

  protected void createAttribute(final String name, final String description, final String type, List<String> options) {
    try {
      mPlanningHandler.createAttribute(name, description, type, options);
    } catch (Exception e) {
      UITool.handleError(this, e);
    }
    setVisible(false); // close the dialog
  }
}
