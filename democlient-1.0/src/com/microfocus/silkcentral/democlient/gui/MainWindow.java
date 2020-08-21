/**
 * (C) Copyright 2004 - 2019 Micro Focus or one of its affiliates.
 */

package com.microfocus.silkcentral.democlient.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.microfocus.silkcentral.democlient.DemoClient;
import com.microfocus.silkcentral.democlient.domainobjects.TreeNode;
import com.microfocus.silkcentral.democlient.gui.utils.UITool;
import com.microfocus.silkcentral.democlient.webservice.TestPlanningHandler;
import com.microfocus.silkcentral.webservices.tests.stubs.NamedEntity;
import com.microfocus.silkcentral.webservices.tests.stubs.PlanningNode;
import com.microfocus.silkcentral.webservices.tests.stubs.PropertyValue;

/**
 * The main window.
 */
public class MainWindow extends JFrame {

  private static final long serialVersionUID = 1L;

  private TestPlanningHandler planningHandler;
  private JTree tree;

  public MainWindow(TestPlanningHandler planningHandler) throws Exception {
    super(DemoClient.TITLE);

    this.planningHandler = planningHandler;

    JMenuBar menuBar = new JMenuBar();
    JMenu mainMenu = new JMenu("Web service");
    JMenu helpMenu = new JMenu("Help");
    menuBar.add(mainMenu);
    menuBar.add(helpMenu);
    JMenuItem addManualTestDefItem = new JMenuItem("ManualTest");
    JMenuItem showPropertiesItem = new JMenuItem("Show all properties...");
    JMenuItem refreshItem = new JMenuItem("Refresh Tree", KeyEvent.VK_F5);

    refreshItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
    refreshItem.setMnemonic(KeyEvent.VK_F5);

    JMenuItem exitItem = new JMenuItem("Exit");

    JMenu addNodeMenu = new JMenu("Add new node");
    ActionListener actionListener = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        String nodeTypeStr = source.getText();
        int nodeId = -1;
        String nodeName = "";
        // a test container has no parent id
        if (!nodeTypeStr.equalsIgnoreCase("Test Container")) {
          TreePath path = tree.getSelectionPath();
          if (path == null) {
            JOptionPane.showMessageDialog(MainWindow.this, "No tree item selected.");
            return;
          }

          DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
          if (node != null) {
            nodeId = ((TreeNode) node.getUserObject()).getId();
            nodeName = ((TreeNode) node.getUserObject()).getName();
          } else {
            return;
          }

        }
        try {
          NewNodeDialog dialog = new NewNodeDialog(MainWindow.this, nodeId, nodeName, MainWindow.this.planningHandler, nodeTypeStr);
          UITool.centerAndShow(dialog);
        } catch (Exception ex) {
          UITool.handleError(MainWindow.this, ex);
        }

      }

    };
    List<String> nodeTypeIds = this.planningHandler.getNodeTypeIds();
    for (String nodeType : nodeTypeIds) {
      if (!nodeType.equalsIgnoreCase("ManualTest") && !nodeType.equalsIgnoreCase("Test Definition")) {
        JMenuItem menuItem = new JMenuItem(nodeType);
        addNodeMenu.add(menuItem);
        menuItem.addActionListener(actionListener);
      }
    }
    addNodeMenu.add(addManualTestDefItem);

    JMenuItem addAttributeMenu = createAddAttributeMenu();
    JMenuItem listAttributeMenu = createListAttributeMenu();

    mainMenu.add(addNodeMenu);
    mainMenu.add(listAttributeMenu);
    mainMenu.add(addAttributeMenu);
    mainMenu.addSeparator();
    mainMenu.add(showPropertiesItem);
    mainMenu.addSeparator();
    mainMenu.add(refreshItem);
    mainMenu.addSeparator();
    mainMenu.add(exitItem);

    JMenuItem aboutItem = new JMenuItem("About...");
    helpMenu.add(aboutItem);

    setJMenuBar(menuBar);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final PropertiesTable table = new PropertiesTable();
    tree = new JTree();

    refreshTree();

    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent e) {
        Collection<PropertyValue> propertyValues = null;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node != null && node.getUserObject() instanceof TreeNode) {
          TreeNode planningNode = (TreeNode) node.getUserObject();
          try {
            propertyValues = MainWindow.this.planningHandler.getPropertyValues(planningNode.getId());

          } catch (Exception ex) {
            UITool.handleError(MainWindow.this, ex);
          }
        }
        table.setContent(propertyValues);
      }

    });

    addManualTestDefItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        TreePath path = tree.getSelectionPath();
        if (path == null) {
          JOptionPane.showMessageDialog(MainWindow.this, "No tree item selected.");
          return;
        }
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        if (node != null) {
          int nodeId = ((TreeNode) node.getUserObject()).getId();
          NewManualTestDialog manualTestDialog = new NewManualTestDialog(MainWindow.this, nodeId, MainWindow.this.planningHandler);
          UITool.centerAndShow(manualTestDialog);
        }
      }
    });

    showPropertiesItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          AllPropertiesFrame propertiesDialog = new AllPropertiesFrame(MainWindow.this.planningHandler);
          UITool.centerAndShow(propertiesDialog);
        } catch (Exception ex) {
          UITool.handleError(MainWindow.this, ex);
        }
      }
    });

    refreshItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        refreshTree();
      }
    });

    aboutItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        AboutDialog aboutDlg = new AboutDialog(MainWindow.this, "http://" + MainWindow.this.planningHandler.getLoginContext().getHost(), AboutDialog.Type.CLOSE);
        UITool.centerAndShow(aboutDlg);
      }
    });

    exitItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    for (int i = 0; i < tree.getRowCount(); i++) {
      tree.expandRow(i);
    }

    JScrollPane treePanel = new JScrollPane(tree);
    JScrollPane propPanel = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    setContentPane(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, propPanel));
    pack();

  }

  private JMenuItem createListAttributeMenu() {
    final JMenuItem menu = new JMenuItem("List attributes...");
    menu.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        final ListAttributeDialog dialog = new ListAttributeDialog(MainWindow.this, planningHandler);
        dialog.setSize(250, 200);
        dialog.setTitle("Attributes");
        UITool.centerAndShow(dialog);
      }
    });
    return menu;
  }

  private JMenuItem createAddAttributeMenu() {
    final JMenuItem menu = new JMenuItem("Add attribute...");
    menu.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        final NewAttributeDialog dialog = new NewAttributeDialog(MainWindow.this, planningHandler);
        dialog.setTitle("Add Attribute");
        UITool.centerAndShow(dialog);
      }
    });
    return menu;
  }

  private void refreshTree() {
    try {
      DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
      createTree(root, planningHandler);
      ((DefaultTreeModel) tree.getModel()).setRoot(root);
    } catch (Exception e) {
      UITool.handleError(this, e);
    }
  }

  private void createTree(DefaultMutableTreeNode root, TestPlanningHandler planningHandler) throws Exception {

    Collection<NamedEntity> testContainers = planningHandler.getTestContainers();
    for (NamedEntity container : testContainers) {
      DefaultMutableTreeNode containerNode = new DefaultMutableTreeNode(new TreeNode(container));
      containerNode.setAllowsChildren(true);
      root.add(containerNode);
      Collection<PlanningNode> childNodes = planningHandler.getChildNodes(container.getId());
      for (PlanningNode node : childNodes) {
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(new TreeNode(node));
        containerNode.add(child);
        attachChildren(child, planningHandler);
      }
    }

  }

  private void attachChildren(DefaultMutableTreeNode parentNode, TestPlanningHandler planningHandler) throws Exception {
    TreeNode parent = (TreeNode) parentNode.getUserObject();
    Collection<PlanningNode> childNodes = planningHandler.getChildNodes(parent.getId());
    for (PlanningNode node : childNodes) {
      DefaultMutableTreeNode child = new DefaultMutableTreeNode(new TreeNode(node));
      parentNode.add(child);
      attachChildren(child, planningHandler);
    }
  }

}
