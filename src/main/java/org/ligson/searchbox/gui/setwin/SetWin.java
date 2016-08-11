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
    private LeftMenu leftMenu = new LeftMenu(this);

    public SetWin(MainWin mainWin) throws HeadlessException {
        this.mainWin = mainWin;
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("设置");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(null);
        add(leftMenu);
        leftMenu.setSize(100, 500);
        leftMenu.setLocation(0, 0);
    }

    public MainWin getMainWin() {
        return mainWin;
    }

    public void setMainWin(MainWin mainWin) {
        this.mainWin = mainWin;
    }

    public static void main(String[] args) {
        SetWin setWin = new SetWin(null);
        setWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setWin.setVisible(true);
    }
}
