/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.util.Collection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.microfocus.silkcentral.webservices.tests.stubs.PropertyValue;

/**
 * A non-modifiable table that contains rows of property values.
 */
public class PropertiesTable extends JTable {

  private static final long serialVersionUID = 1L;
  private DefaultTableModel model;

  public PropertiesTable() {
    super();
    model = new DefaultTableModel(new String[] {"Name", "PropertyId", "Value", "Type", "PropertyTypeId", "ChildProperties"}, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    setModel(model);

    getColumnModel().getColumn(0).setPreferredWidth(180);
    getColumnModel().getColumn(1).setPreferredWidth(150);
    getColumnModel().getColumn(2).setPreferredWidth(80);
    getColumnModel().getColumn(3).setPreferredWidth(50);
    getColumnModel().getColumn(4).setPreferredWidth(120);

    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    setAutoCreateRowSorter(true); /* since java 1.6 */
  }

  public void setContent(Collection<PropertyValue> properties) {

    model.setRowCount(0);
    if (properties != null) {
      for (PropertyValue prop : properties) {
        addPropertyValue(prop);
      }
    }
  }

  public void addPropertyValue(PropertyValue propValue) {
    model.addRow(new Object[] {propValue.getName(), propValue.getPropertyID(), propValue.getValue(), propValue.getType(), propValue.getPropertyTypeID(),
        getChildPropertiesAsString(propValue)});
  }

  private String getChildPropertiesAsString(PropertyValue propValue) {
    List<PropertyValue> children = propValue.getChildren();
    if (children == null) {
      return "";
    }
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < children.size() - 1; i++) {
      builder.append(children.get(i).getValue()).append(", ");
    }
    if (children.size() > 0) {
      builder.append(children.get(children.size() - 1).getValue());
    }
    return builder.toString();
  }

  public void removeRow(int rowInx) {
    model.removeRow(rowInx);

  }

}
