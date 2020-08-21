/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.microfocus.silkcentral.democlient.gui.utils.UITool;
import com.microfocus.silkcentral.democlient.webservice.TestPlanningHandler;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyMetaInfo;

/**
 * The AllPropertiesFrame window shows all properties, that are available through the test planning webservice.
 */
public class AllPropertiesFrame extends JFrame {

  private static final long serialVersionUID = 1L;
  private JComboBox propTypeIdCB;
  private JComboBox nodeTypeIdCB;
  private TestPlanningHandler planningHandler;
  private DefaultTableModel tableModel;

  public AllPropertiesFrame(final TestPlanningHandler planningHandler) throws Exception {
    super("All Available Properties");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.planningHandler = planningHandler;

    tableModel = createTableModel();

    JLabel nodeTypeLb = new JLabel("NodeTypeId");
    nodeTypeIdCB = new JComboBox(planningHandler.getNodeTypeIds().toArray());

    JLabel propertyTypeLb = new JLabel("PropertyTypeId");
    propTypeIdCB = new JComboBox();
    updatePropertyCB(planningHandler, nodeTypeIdCB, propTypeIdCB);

    nodeTypeIdCB.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          updatePropertyCB(planningHandler, nodeTypeIdCB, propTypeIdCB);
          updateTable();
        }
      }

    });

    propTypeIdCB.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          updateTable();
        }
      }
    });

    JTable table = new JTable(tableModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.getColumnModel().getColumn(0).setPreferredWidth(250);
    table.getColumnModel().getColumn(1).setPreferredWidth(110);
    table.getColumnModel().getColumn(2).setPreferredWidth(140);
    table.getColumnModel().getColumn(3).setPreferredWidth(100);
    table.getColumnModel().getColumn(4).setPreferredWidth(90);
    table.getColumnModel().getColumn(5).setPreferredWidth(70);
    table.getColumnModel().getColumn(6).setPreferredWidth(70);
    table.getColumnModel().getColumn(7).setPreferredWidth(70);
    table.getColumnModel().getColumn(8).setPreferredWidth(60);
    table.getColumnModel().getColumn(9).setPreferredWidth(95);

    table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {

      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent comp = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        comp.setToolTipText((String) value);
        return comp;
      }

    });

    JScrollPane tablePanel = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    updateTable();

    JPanel content = new JPanel(new BorderLayout(0, 4));
    content.setBorder(BorderFactory.createEmptyBorder(11, 11, 10, 10));

    JPanel panel = new JPanel(new GridLayout(2, 2));
    panel.add(nodeTypeLb);
    panel.add(nodeTypeIdCB);
    panel.add(propertyTypeLb);
    panel.add(propTypeIdCB);

    JButton closeBtn = new JButton("Close");
    closeBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
      }
    });

    content.add(panel, BorderLayout.NORTH);
    content.add(tablePanel, BorderLayout.CENTER);
    content.add(UITool.createButtonsPane(closeBtn), BorderLayout.SOUTH);

    setContentPane(content);

    setSize(800, 500);
  }

  private DefaultTableModel createTableModel() {
    return new DefaultTableModel(new String[] {"PropertyId", "PropertyTypeId", "Name", "Description", "defaultValue", "isEditable", "isMultiSelect",
        "isOptional", "type", "typeLabel"}, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
  }

  private void updateTable() {
    try {
      String nodeTypeId = (String) nodeTypeIdCB.getSelectedItem();
      String propTypeId = (String) propTypeIdCB.getSelectedItem();
      Collection<PropertyMetaInfo> propertyInfoColl = planningHandler.getPropertyInfo(nodeTypeId, propTypeId);
      tableModel.setRowCount(0);
      for (PropertyMetaInfo propInfo : propertyInfoColl) {
        tableModel.addRow(new Object[] {propInfo.getId(), propInfo.getPropertyTypeId(), propInfo.getName(), propInfo.getDescription(),
            propInfo.getDefaultValue(), propInfo.isIsEditable(), propInfo.isIsMultiSelect(), propInfo.isIsOptional(), propInfo.getType(),
            propInfo.getTypeLabel()});
      }

    } catch (Exception e) {
      UITool.handleError(this, e);
    }
  }

  private void updatePropertyCB(final TestPlanningHandler planningHandler, final JComboBox nodeTypeCB, final JComboBox propertyTypeCB) {
    String selectedItem = (String) nodeTypeCB.getSelectedItem();
    try {
      propertyTypeCB.setModel(new DefaultComboBoxModel(planningHandler.getPropertyTypeIds(selectedItem).toArray()));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
