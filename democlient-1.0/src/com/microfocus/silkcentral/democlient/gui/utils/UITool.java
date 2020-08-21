/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui.utils;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UITool {

  private UITool() {

  }

  /**
   * Sets the visibility of a (dialog) window to 'true' and centers the window.
   * 
   * @param window
   *          The window to be shown and centered.
   */
  public static void centerAndShow(Window window) {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();

    int x = (screenSize.width - window.getWidth()) / 2;
    int y = (screenSize.height - window.getHeight()) / 2;

    window.setLocation(x, y);
    window.setVisible(true);
  }

  /**
   * Simple error handling. Shows the exception message within a dialog box.
   * 
   * @param parent
   *          The frame in which the dialog is displayed. If null a default Frame is used.
   * @param exception
   *          Any exception thrown during handling error.
   */
  public static void handleError(Component parent, Exception exception) {
    exception.printStackTrace();
    JOptionPane.showMessageDialog(parent, exception.getMessage());
  }

  /**
   * Returns a panel containing the specified buttons.
   * 
   * @param buttons
   *          Buttons to be added to the created panel.
   * @return a new panel component
   */
  public static Component createButtonsPane(JButton... buttons) {
    JPanel panel = new JPanel(new BorderLayout());
    JPanel grid = new JPanel(new GridLayout(1, -1, 5, 0));
    for (JButton btn : buttons) {
      grid.add(btn);
    }
    panel.add(grid, BorderLayout.EAST);
    return panel;
  }

}
