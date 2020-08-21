/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.microfocus.silkcentral.democlient.gui.utils.UITool;
import com.microfocus.silkcentral.democlient.webservice.TestPlanningHandler;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyMetaInfo;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyValue;

/**
 * Dialog for creating a new node in planning tree.
 */
public class NewNodeDialog extends JDialog {

  private static final long serialVersionUID = 1L;
  private final TestPlanningHandler planningHandler;
  private int kind;
  private final String mNodeTypeStr;

  private final JButton removeBtn;

  /**
   * Creates a new dialog
   * 
   * @param parent
   *          The parent window
   * @param parentNodeId
   *          The ID of the parent node, where to add the new node.
   * @param parentNodeName
   *          Name of the parent node.
   * @param planningHandler
   *          Provides the webservice planning functionality
   * @param nodeTypeStr
   *          Defines which type the new node should be e.g. "Test Container", "Test Folder" or any type of test definition.
   * @throws Exception
   *           Any exception thrown during opening dialog.
   */
  public NewNodeDialog(Frame parent, final int parentNodeId, String parentNodeName, final TestPlanningHandler planningHandler, String nodeTypeStr)
      throws Exception {
    super(parent, false);

    if (nodeTypeStr.equalsIgnoreCase("Test Container")) {
      kind = 1;
    } else if (nodeTypeStr.equalsIgnoreCase("Test Folder")) {
      kind = 2;
    } else {
      kind = 3;
    }
    mNodeTypeStr = nodeTypeStr;

    setTitle("Add new " + nodeTypeStr + " node");

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.planningHandler = planningHandler;
    final List<PropertyValue> properties = new ArrayList<>();

    final PropertiesTable table = new PropertiesTable();
    Collection<PropertyMetaInfo> propertyInfoForNodeType = planningHandler.getPropertyInfoForNodeType(nodeTypeStr);
    PropertyEditorPanel editor = new PropertyEditorPanel(parentNodeName, propertyInfoForNodeType, new PropertyEditorPanel.Listener() {

      @Override
      public void addValue(PropertyMetaInfo propertyInfo, String value) {
        PropertyValue propertyValue = NewNodeDialog.this.planningHandler.createPropertyValue(propertyInfo, value);
        table.addPropertyValue(propertyValue);
        properties.add(propertyValue);
      }

    });

    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        removeBtn.setEnabled(table.getSelectedRowCount() > 0);
      }
    });

    JButton okBtn = new JButton("OK");
    okBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        try {
          setType(properties);
          planningHandler.addNode(parentNodeId, properties, kind);
          setVisible(false);
          dispose();
        } catch (Exception e) {
          UITool.handleError(NewNodeDialog.this, e);
        }
      }

      private void setType(List<PropertyValue> properties) {
        boolean present = properties.stream().filter(p -> "testdef_type".equalsIgnoreCase(p.getName())).findFirst().isPresent();
        if (!present) {
          PropertyValue property = new PropertyValue();
          property.setPropertyTypeID("_node_properties_");
          property.setPropertyID("_node_properties_testdef_type");
          property.setName("testdef_type");
          property.setValue(mNodeTypeStr);
          properties.add(property);
        }
      }

    });

    JButton cancelBtn = new JButton("Cancel");
    cancelBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
      }

    });

    removeBtn = new JButton("Remove");
    removeBtn.setEnabled(false);
    removeBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int row = table.getSelectedRow();
        table.removeRow(row);
        properties.remove(row);
      }
    });

    JScrollPane propPanel = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    JPanel tablePn = new JPanel(new BorderLayout(0, 4));
    tablePn.add(propPanel, BorderLayout.CENTER);
    tablePn.add(UITool.createButtonsPane(removeBtn), BorderLayout.SOUTH);

    JPanel content = new JPanel(new BorderLayout(0, 4));
    content.setBorder(BorderFactory.createEmptyBorder(11, 11, 10, 10));
    content.add(editor, BorderLayout.NORTH);
    content.add(tablePn, BorderLayout.CENTER);
    content.add(UITool.createButtonsPane(okBtn, cancelBtn), BorderLayout.SOUTH);
    setContentPane(content);
    pack();
  }

}
