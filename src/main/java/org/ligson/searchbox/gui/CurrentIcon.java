package org.ligson.searchbox.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by trq on 2016/7/28.
 */
public class CurrentIcon extends JLabel {
    private MainWin mainWin;
    private File currentFile;
    private static int width = 50;
    private  static  int height = 75;
    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
        Icon icon = SearchList.toIcon(currentFile);
        setText(currentFile.getName());
        this.setIcon(icon);
    }

    public CurrentIcon(MainWin mainWin) {
        this.mainWin = mainWin;
        this.setVerticalTextPosition(JLabel.BOTTOM);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setBounds(135, 0, 50, 75);
        this.setForeground(Color.WHITE);
    }

    public MainWin getMainWin() {
        return mainWin;
    }

    public void setMainWin(MainWin mainWin) {
        this.mainWin = mainWin;
    }
}
