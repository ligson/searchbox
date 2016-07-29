package org.ligson.searchbox.gui.setwin;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by trq on 2016/7/29.
 */
public class LeftMenu extends JList<JPanel> implements ListSelectionListener {
    private DefaultListModel<JPanel> dlm = new DefaultListModel<>();
    IndexDirPanel indexDirPanel = new IndexDirPanel();
    HotKeyPanel hotKeyPanel = new HotKeyPanel();
    private SetWin setWin;

    public LeftMenu(SetWin setWin) {
        this.setWin = setWin;
        setCellRenderer(new ListCellRenderer<JPanel>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel value, int index, boolean isSelected, boolean cellHasFocus) {
                return new JLabel(value.getName());
            }
        });
        setModel(dlm);
        dlm.addElement(indexDirPanel);
        dlm.addElement(hotKeyPanel);
        addListSelectionListener(this);
        setSelectedIndex(0);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Component[] components = setWin.getRightPanel().getComponents();
        if (getSelectedValue() == indexDirPanel) {
            if (components != null) {
                setWin.getRightPanel().remove(hotKeyPanel);
            }
            setWin.getRightPanel().add(indexDirPanel);
            setWin.getRightPanel().updateUI();
        } else if (getSelectedValue() == hotKeyPanel) {
            if (components != null) {
                setWin.getRightPanel().remove(indexDirPanel);
            }
            setWin.getRightPanel().add(hotKeyPanel);
            setWin.getRightPanel().updateUI();
        }
    }
}
