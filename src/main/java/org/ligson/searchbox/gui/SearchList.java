package org.ligson.searchbox.gui;

import sun.awt.shell.ShellFolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;

@SuppressWarnings("restriction")
public class SearchList extends JComboBox<File> implements ItemListener {
    /**
     *
     */
    private static final long serialVersionUID = -8185370649752511623L;
    private MainWin main;

    public MainWin getMain() {
        return main;
    }

    public void setMain(MainWin main) {
        this.main = main;
    }

    public static Icon toIcon(File file) {
        ShellFolder shellFolder;
        try {
            shellFolder = ShellFolder.getShellFolder(file);
        } catch (FileNotFoundException e) {
            return null;
        }
        Icon icon = new ImageIcon(shellFolder.getIcon(true));
        return icon;
    }

    public SearchList(MainWin main) {
        super();
        this.main = main;
        setRenderer(new ListCellRenderer<File>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends File> list, File value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel jLabel = new JLabel(value.getAbsolutePath());
                jLabel.setOpaque(true);
                jLabel.setIcon(toIcon(value));
                //jLabel.set
                if (isSelected) {
                    jLabel.setBackground(list.getSelectionBackground());
                    jLabel.setForeground(list.getSelectionForeground());
                } else {
                    jLabel.setBackground(list.getBackground());
                    jLabel.setForeground(list.getForeground());
                }
                return jLabel;
            }
        });
        addItemListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, 0);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            File file = (File) e.getItem();
            main.getCurrentIcon().setToolTipText(file.getName());
            main.getCurrentIcon().setCurrentFile(file);
            //main.getSearchBox().setText(file.getName());
        }
    }
}
