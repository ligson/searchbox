package gui.test;

import javax.swing.*;

/**
 * Created by ligso on 2016/7/20.
 */
public class DragTest  extends JFrame{
    DragTest(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(100,100);
    }

    public static void main(String[] args) {
        DragTest test = new DragTest();
        test.setVisible(true);

    }
}
