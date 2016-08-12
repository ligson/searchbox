package org.ligson.searchbox.gui.setwin;

import javax.swing.*;

/**
 * Created by trq on 2016/7/29.
 */
public class HotKeyPanel extends JPanel  {
    private SetWin setWin;
    public HotKeyPanel(SetWin setWin) {
        setSize(400,SetWin.realHeight);
        setLocation(100, 0);
        setName("热键设置");
    }
}
