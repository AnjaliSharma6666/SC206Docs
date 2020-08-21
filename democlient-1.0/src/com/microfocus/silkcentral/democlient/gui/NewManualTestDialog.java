/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.microfocus.silkcentral.democlient.gui.utils.UITool;
import com.microfocus.silkcentral.democlient.webservice.TestPlanningHandler;
import com.microfocus.silkcentral.webservices.tests.stubs.ManualTestStep;

public class NewManualTestDialog extends JDialog {

  public NewManualTestDialog(Frame owner, final int parentNodeId, final TestPlanningHandler planningHandler) {
    super(owner, true);
    setTitle("New Manual Test");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    JPanel content = (JPanel) getContentPane();
    content.setLayout(new BorderLayout());

    final JTextField nameTf = new JTextField();
    final JTextField descriptionTf = new JTextField();
    final JTextField plannedTimeTf = new JTextField();

    JPanel testDefPn = new JPanel(new GridLayout(-1, 2));
    testDefPn.add(new JLabel("Name:"));
    testDefPn.add(nameTf);
    testDefPn.add(new JLabel("Description:"));
    testDefPn.add(descriptionTf);
    testDefPn.add(new JLabel("Planned time [hh:mm]:"));
    testDefPn.add(plannedTimeTf);

    final StepsTableModel model = new StepsTableModel();
    JTable stepsTable = new JTable(model);
    stepsTable.getColumnModel().getColumn(0).setHeaderValue("#");
    stepsTable.getColumnModel().getColumn(1).setHeaderValue("Name");
    stepsTable.getColumnModel().getColumn(2).setHeaderValue("Description");
    stepsTable.getColumnModel().getColumn(3).setHeaderValue("Expected Result");

    stepsTable.getColumnModel().getColumn(0).setPreferredWidth(20);
    stepsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
    stepsTable.getColumnModel().getColumn(2).setPreferredWidth(180);
    stepsTable.getColumnModel().getColumn(3).setPreferredWidth(180);

    JButton newStepBtn = new JButton("New Step");

    JPanel stepsPn = new JPanel(new BorderLayout());
    stepsPn.add(new JScrollPane(stepsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    stepsPn.add(UITool.createButtonsPane(newStepBtn), BorderLayout.SOUTH);

    JButton okBtn = new JButton("OK");
    JButton cancelBtn = new JButton("Cancel");

    content.add(testDefPn, BorderLayout.NORTH);
    content.add(stepsPn, BorderLayout.CENTER);
    content.add(UITool.createButtonsPane(okBtn, cancelBtn), BorderLayout.SOUTH);

    newStepBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.addRow();
      }
    });

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
        try {
          planningHandler.addManualTestNode(parentNodeId, nameTf.getText(), descriptionTf.getText(), plannedTimeTf.getText(), model.getSteps());
          setVisible(false);
          dispose();
        } catch (Exception e) {
          UITool.handleError(NewManualTestDialog.this, e);
        }
      }
    });

    setSize(600, 300);

  }

  class StepsTableModel extends AbstractTableModel {

    private final List<ManualTestStep> steps;

    public StepsTableModel() {

      steps = new ArrayList<>();

    }

    public void addRow() {
      ManualTestStep manualTestStep = new ManualTestStep();
      manualTestStep.setName("new Step");
      steps.add(manualTestStep);

      fireTableDataChanged();
    }

    public ManualTestStep[] getSteps() {
      return steps.toArray(new ManualTestStep[steps.size()]);
    }

    @Override
    public int getRowCount() {
      return steps.size();
    }

    @Override
    public int getColumnCount() {
      return 4;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
      return col > 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      ManualTestStep step = steps.get(rowIndex);
      if (columnIndex == 0) {
        return rowIndex;
      } else if (columnIndex == 1) {
        return step.getName();
      } else if (columnIndex == 2) {
        return step.getDescription();
      } else if (columnIndex == 3) {
        return step.getExpectedResult();
      } else {
        throw new RuntimeException("invalid column-index: " + columnIndex);
      }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
      ManualTestStep step = steps.get(row);
      if (col == 1) {
        step.setName((String) value);
      } else if (col == 2) {
        step.setDescription((String) value);
      } else if (col == 3) {
        step.setExpectedResult((String) value);
      } else {
        throw new RuntimeException("invalid col: " + col);
      }
    }
  }

}
