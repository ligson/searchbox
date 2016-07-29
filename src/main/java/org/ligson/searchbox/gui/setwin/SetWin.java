package org.ligson.searchbox.gui.setwin;

import org.ligson.searchbox.gui.MainWin;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by trq on 2016/7/29.
 * 设置窗口
 */
public class SetWin extends JFrame {
    private MainWin mainWin;


    private GridBagLayout gridBagLayout = new GridBagLayout();
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();

    private JPanel rightPanel = new JPanel(new GridLayout(1, 1));
    private LeftMenu leftMenu = new LeftMenu(this);


    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public SetWin(MainWin mainWin) throws HeadlessException {
        this.mainWin = mainWin;
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("设置");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(gridBagLayout);
        add(leftMenu);
        add(rightPanel);
        rightPanel.setBackground(Color.BLUE);

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagLayout.setConstraints(leftMenu, gridBagConstraints);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3;
        gridBagConstraints.weighty = 1;
        gridBagLayout.setConstraints(rightPanel, gridBagConstraints);
    }

    public MainWin getMainWin() {
        return mainWin;
    }

    public void setMainWin(MainWin mainWin) {
        this.mainWin = mainWin;
    }
}
