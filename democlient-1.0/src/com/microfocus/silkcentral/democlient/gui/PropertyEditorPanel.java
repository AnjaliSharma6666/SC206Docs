/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.microfocus.silkcentral.democlient.gui.utils.UITool;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyMetaInfo;

/**
 * Panel for creating a new property value
 */
public class PropertyEditorPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  interface Listener {
    void addValue(PropertyMetaInfo propertyInfo, String value);
  }

  private JComboBox propertyCB;
  private JTextField valueTf;
  private JTextArea descriptionTA;

  public PropertyEditorPanel(String parentNodeName, Collection<PropertyMetaInfo> content, final Listener listener) {
    super();

    PropertyMetaInfo[] contentArr = content.toArray(new PropertyMetaInfo[content.size()]);
    Arrays.sort(contentArr, new Comparator<PropertyMetaInfo>() {
      @Override
      public int compare(final PropertyMetaInfo a, final PropertyMetaInfo b) {
        if (a.isIsOptional() == b.isIsOptional()) {
          return a.getName().compareToIgnoreCase(b.getName());
        } else if (a.isIsOptional()) {
          return 1;
        } else {
          return -1;
        }
      }
    });

    propertyCB = new JComboBox(contentArr);
    propertyCB.setRenderer(new DefaultListCellRenderer() {

      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel lb = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        PropertyMetaInfo propInfo = (PropertyMetaInfo) value;
        if (propInfo.isIsOptional()) {
          lb.setText(propInfo.getName());
        } else {
          lb.setText(propInfo.getName() + "*");
        }
        return lb;
      }

    });

    descriptionTA = new JTextArea(getDescription());
    descriptionTA.setLineWrap(true);
    descriptionTA.setOpaque(false);
    descriptionTA.setWrapStyleWord(true);
    descriptionTA.setFont(new Label().getFont());
    descriptionTA.setRows(3);
    descriptionTA.setEditable(false);

    propertyCB.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          descriptionTA.setText(getDescription());
        }
      }
    });

    JButton addBtn = new JButton("Add");

    JPanel southPn = new JPanel(new BorderLayout(0, 4));
    southPn.add(new JLabel("Description:"), BorderLayout.NORTH);
    southPn.add(new JScrollPane(descriptionTA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    southPn.add(UITool.createButtonsPane(addBtn), BorderLayout.SOUTH);

    JPanel panel = new JPanel(new GridLayout(3, 2));

    panel.add(new JLabel("Parent node:"));
    panel.add(new JLabel(parentNodeName));
    panel.add(new JLabel("Property:"));
    panel.add(propertyCB);
    panel.add(new JLabel("Value:"));
    valueTf = new JTextField();
    panel.add(valueTf);

    setLayout(new BorderLayout());
    add(panel, BorderLayout.CENTER);
    add(southPn, BorderLayout.SOUTH);

    addBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        listener.addValue((PropertyMetaInfo) propertyCB.getSelectedItem(), valueTf.getText());
        valueTf.setText("");
      }

    });

  }

  private String getDescription() {
    PropertyMetaInfo metaInfo = (PropertyMetaInfo) propertyCB.getSelectedItem();
    return metaInfo.getDescription();
  }

}
