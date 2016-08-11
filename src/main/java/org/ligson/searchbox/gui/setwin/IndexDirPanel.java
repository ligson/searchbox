package org.ligson.searchbox.gui.setwin;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by trq on 2016/7/29.
 */
public class IndexDirPanel extends JPanel implements ActionListener, ListSelectionListener {
    private DefaultListModel<File> selectFileModel = new DefaultListModel<>();
    private JList<File> indexDirList = new JList<>(selectFileModel);
    private JButton includeDirBtn = new JButton("增加");
    private JButton excludeDirBtn = new JButton("减少");
    private JPanel bottomPanel = new JPanel();
    //private JPanel centerPanel = new JPanel();
    private GridBagLayout gridBagLayout = new GridBagLayout();
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private JFileChooser jFileChooser = new JFileChooser();
    private GridLayout gridLayout = new GridLayout(4, 1);

    public IndexDirPanel() {
        setSize(400, 500);
        setLocation(100, 0);
        setLayout(null);
        //centerPanel.add(indexDirList);
        //centerPanel.setSize(100,200);
        indexDirList.setSize(400, 450);
        indexDirList.setLocation(0, 0);
        indexDirList.setBackground(Color.BLUE);
        add(indexDirList);
        add(bottomPanel);
        bottomPanel.setBackground(Color.cyan);
        bottomPanel.setSize(400, 50);
        bottomPanel.setLocation(0, 450);
        bottomPanel.add(includeDirBtn);
        bottomPanel.add(excludeDirBtn);
        indexDirList.addListSelectionListener(this);
        includeDirBtn.addActionListener(this);
        excludeDirBtn.addActionListener(this);
        setName("索引目录设置");


       /* gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 3;
        gridBagLayout.setConstraints(indexDirList, gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagLayout.setConstraints(bottomPanel, gridBagConstraints);*/

        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setMultiSelectionEnabled(true);
        indexDirList.setCellRenderer(new ListCellRenderer<File>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends File> list, File value, int index, boolean isSelected, boolean cellHasFocus) {
                return new JLabel(value.getName());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource().equals(includeDirBtn)) {
            int n = jFileChooser.showOpenDialog(this);
            if (n == jFileChooser.APPROVE_OPTION) {
                System.out.println(jFileChooser.getSelectedFile());
                System.out.println(jFileChooser.getSelectedFiles().length);
                for (File dir : jFileChooser.getSelectedFiles()) {
                    selectFileModel.addElement(dir);
                }
                updateUI();
            }
        } else if (e.getSource().equals(excludeDirBtn)) {

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
