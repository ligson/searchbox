package org.ligson.searchbox.gui.listener;

import org.ligson.searchbox.gui.MainWin;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by ligson on 2016/7/20.
 */
public class DragListener extends MouseAdapter {
    private MainWin mainWin;
    private Point origin = new Point();

    public MainWin getMainWin() {
        return mainWin;
    }

    public void setMainWin(MainWin mainWin) {
        this.mainWin = mainWin;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("drag............");
        // 当鼠标拖动时获取窗口当前位置
        Point p = mainWin.getLocation();
        // 设置窗口的位置
        // 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
        mainWin.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("press");
        origin.setLocation(e.getX(), e.getY());
    }

    public DragListener(MainWin mainWin) {
        this.mainWin = mainWin;
    }
}
