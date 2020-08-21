/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.microfocus.silkcentral.democlient.DemoClient;
import com.microfocus.silkcentral.democlient.gui.utils.UITool;

/**
 * A simple about box for legacy information.
 */
public class AboutDialog extends JDialog {
  private static final String URL_CUSTOMER_DOWNLOADS = "https://www.microfocus.com/products/silk-portfolio/silk-central/";
  private static final String URL_MICROFOCUS = "https://www.microfocus.com";

  private static String DISCLAIMER = "THE SILK CENTRAL WEB SERVICE DEMO CLIENT (\"DEMO CLIENT\") IS PROVIDED \"AS IS\" AND WITH ALL FAULTS AND "
      + "WITHOUT ANY WARRANTY WHATSOEVER. MICRO FOCUS AND ITS AFFILIATES DISCLAIMS ALL WARRANTIES AND CONDITIONS, WHETHER "
      + "EXPRESS, IMPLIED, OR STATUTORY, AS TO ANY MATTER WHATSOEVER, INCLUDING BUT NOT LIMITED TO, ANY WARRANTIES, DUTIES, "
      + "OR CONDITIONS OR RELATED TO MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, LACK OF VIRUSES, ACCURACY OR "
      + "COMPLETENESS, TITLE, QUIET ENJOYMENT, QUIET POSSESSION AND NONINFRINGEMENT. " + "<br />"
      + "YOUR USE OF THE DEMO CLIENT IS AT YOUR OWN RISK. IN NO EVENT SHALL MICRO FOCUS AND ITS AFFILIATES BE LIABLE FOR ANY "
      + "DIRECT, INDIRECT, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES OF ANY KIND, INCLUDING, BUT NOT LIMITED TO, LOST "
      + "PROFITS, INCURRED OR ARISING OUT OF YOUR USE OF THE DEMO CLIENT. "
      + "<p color=\"#FF0000\" align=\"center\">"
      + "<b>THE DEMO CLIENT IS DESIGNED TO DEMONSTRATE THE USAGE OF SILK CENTRAL'S TESTS WEB SERVICES AND SHOULD NOT BE "
      + "USED IN A PRODUCTIVE ENVIRONMENT.</b></p>";

  private static final String ABOUT_MESSAGE = "<html>\n" +
      "<head>" +
      "</head>" +
      "<body style=\"font-family:Avalon\">" +
      "<h1>About Test Planning Web Service Demo Client</h1>" +
      "<p align=\"center\">" +
      "(C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.<br/>" +
      "</p>" +
      "<p style=\"font-size:80%\">" +
      DISCLAIMER +
      "</p>" +
      "<p style=\"font-size:90%\">" +
      "<b>Related links:</b><br />" +
      "<a href=\"" + URL_CUSTOMER_DOWNLOADS + "\">Silk Central Customer Downloads</a><br />" +
      "<a href=\"${javaDocURL}/silkroot/doc/javadoc/com/microfocus/silkcentral/webservices/tests/TestsService.html\">Web service Javadoc</a><br />" +
      "<a href=\"" + URL_MICROFOCUS + "\">Micro Focus website</a><br />" +
      "</p>" +
      "</body>\n" +
      "</html>";

  private boolean accepted;

  public static enum Type {
    CLOSE, ACCEPT
  }

  public AboutDialog(Frame parent, String javaDocURL, Type type) {
    super(parent, true);
    setTitle("About " + DemoClient.TITLE);
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    JPanel contentPn = (JPanel) getContentPane();
    contentPn.setBackground(Color.WHITE);
    contentPn.setLayout(new BorderLayout());
    contentPn.setBorder(BorderFactory.createEmptyBorder(11, 11, 10, 10));

    JButton closeBtn = new JButton();
    closeBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        accepted = true;
        setVisible(false);
        dispose();
      }
    });

    JPanel buttonPn = new JPanel();
    if (type == Type.ACCEPT) {
      JButton declineBtn = new JButton("Decline");
      declineBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.exit(0);
        }
      });
      buttonPn.add(declineBtn);
      closeBtn.setText("Accept");
    } else {
      closeBtn.setText("Close");
    }

    buttonPn.add(closeBtn);
    buttonPn.setBackground(Color.WHITE);

    contentPn.add(createContent(javaDocURL), BorderLayout.CENTER);
    contentPn.add(buttonPn, BorderLayout.SOUTH);

    setSize(630, 470);
  }

  private Component createContent(String javaDocURL) {
    String aboutMessage = ABOUT_MESSAGE.replaceAll("\\$\\{javaDocURL\\}", javaDocURL);
    JEditorPane aboutEP = new JEditorPane("text/html; charset=UTF-8", aboutMessage);
    aboutEP.setEditable(false);
    aboutEP.setOpaque(false);

    aboutEP.addHyperlinkListener(new HyperlinkListener() {
      @Override
      public void hyperlinkUpdate(HyperlinkEvent event) {
        if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
          try {
            Desktop.getDesktop().browse(event.getURL().toURI());
          } catch (Exception e) {
            UITool.handleError(AboutDialog.this, e);
          }
        }
      }
    });

    return aboutEP;

  }

  public boolean accepted() {
    return accepted;
  }

}
