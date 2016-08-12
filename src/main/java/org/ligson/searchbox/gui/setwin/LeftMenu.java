package org.ligson.searchbox.gui.setwin;

import org.ligson.searchbox.gui.SearchList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by trq on 2016/7/29.
 */
public class LeftMenu extends JList<JPanel> implements ListSelectionListener {
    private DefaultListModel<JPanel> dlm = new DefaultListModel<>();
    IndexDirPanel indexDirPanel;
    HotKeyPanel hotKeyPanel;
    private SetWin setWin;

    public LeftMenu(SetWin setWin) {
        this.setWin = setWin;
        this.hotKeyPanel = new HotKeyPanel(setWin);
        indexDirPanel = new IndexDirPanel(setWin);
        setCellRenderer(new ListCellRenderer<JPanel>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel jLabel = new JLabel(value.getName());
                jLabel.setOpaque(true);
                if (isSelected) {
                    jLabel.setBackground(new Color(163, 184, 204));
                } else {
                    jLabel.setBackground(Color.WHITE);
                }
                return jLabel;
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

        if (getSelectedValue() == indexDirPanel) {
            setWin.remove(hotKeyPanel);
            setWin.add(indexDirPanel);
        } else if (getSelectedValue() == hotKeyPanel) {
            setWin.remove(indexDirPanel);
            setWin.add(hotKeyPanel);
        }
        setWin.repaint();
    }
}
