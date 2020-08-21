/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.microfocus.silkcentral.democlient.gui.utils.UITool;

/**
 * A simple dialog for asking the user for host, username and password.
 */
public class LoginDialog extends JDialog {

  private static final long serialVersionUID = 1L;

  public enum Result {
    OK, CANCEL
  }

  private Result result;

  private String hostname;
  private String password;
  private String username;

  public LoginDialog() {
    super((JFrame) null, true);
    setTitle("Login");

    BorderLayout mainLayout = new BorderLayout(0, 10);

    GridLayout layout = new GridLayout(-1, 2);
    JPanel credentialsPn = new JPanel(layout);

    final JTextField hostTf = new JTextField("localhost:19120");
    final JTextField userTf = new JTextField("admin");
    final JPasswordField passwordTf = new JPasswordField("admin");

    credentialsPn.add(new JLabel("Host:Port"));
    credentialsPn.add(hostTf);
    credentialsPn.add(new JLabel("User"));
    credentialsPn.add(userTf);
    credentialsPn.add(new JLabel("Password"));
    credentialsPn.add(passwordTf);

    JButton connectBtn = new JButton("Connect");
    JButton cancelBtn = new JButton("Cancel");

    cancelBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        result = Result.CANCEL;
        setVisible(false);
      }
    });

    connectBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        result = Result.OK;
        setVisible(false);
        username = userTf.getText();
        password = new String(passwordTf.getPassword());
        hostname = hostTf.getText();
      }
    });

    JPanel contentPn = new JPanel(mainLayout);
    contentPn.setBorder(BorderFactory.createEmptyBorder(11, 11, 10, 10));
    contentPn.add(credentialsPn, BorderLayout.CENTER);
    contentPn.add(UITool.createButtonsPane(connectBtn, cancelBtn), BorderLayout.SOUTH);

    setContentPane(contentPn);

    pack();

  }

  public Result getResult() {
    return result;
  }

  public String getHostname() {
    return hostname;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }
}
