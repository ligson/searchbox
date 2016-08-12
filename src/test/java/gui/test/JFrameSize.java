package gui.test;

import javax.swing.*;

/**
 * Created by trq on 2016/8/12.
 */
public class JFrameSize extends JFrame {
    JFrameSize() {
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrameSize jFrameSize = new JFrameSize();
        jFrameSize.setVisible(true);
        System.out.println(jFrameSize.getContentPane().getHeight());
    }
}
