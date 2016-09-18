package org.ligson.searchbox.gui.setwin;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ligson on 2016/8/13.
 * 定时任务
 */
public class GeneralPanel extends JPanel {
    private SetWin setWin;
    private JLabel taskText = new JLabel("定时索引(分钟):");
    private JTextField taskField = new JTextField();

    public SetWin getSetWin() {
        return setWin;
    }

    public void setSetWin(SetWin setWin) {
        this.setWin = setWin;
    }

    public GeneralPanel(SetWin setWin) {
        this.setWin = setWin;
        setName("通用设置");
        setSize(400, SetWin.realHeight);
        setLocation(100, 0);
        setLayout(null);
        add(taskText);
        taskText.setSize(100,20);
        taskText.setLocation(10,10);
        add(taskField);
        taskField.setSize(50,20);
        taskField.setLocation(120,10);
    }

}
