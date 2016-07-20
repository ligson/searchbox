package org.ligson.searchbox.gui;

import org.ligson.searchbox.gui.listener.DragListener;

import javax.swing.*;

/**
 * Created by ligson on 2016/7/20.
 */
public class ContentPanel extends JPanel {
    private MainWin mainWin;
    private DragListener dragListener;

    public MainWin getMainWin() {
        return mainWin;
    }

    public void setMainWin(MainWin mainWin) {
        this.mainWin = mainWin;
    }

    public DragListener getDragListener() {
        return dragListener;
    }

    public void setDragListener(DragListener dragListener) {
        this.dragListener = dragListener;
    }

    public ContentPanel(MainWin mainWin) {
        this.mainWin = mainWin;
        dragListener = new DragListener(mainWin);
        addMouseListener(dragListener);
        addMouseMotionListener(dragListener);
        setOpaque(false);
    }
}
