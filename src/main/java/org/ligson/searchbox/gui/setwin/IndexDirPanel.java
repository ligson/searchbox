package org.ligson.searchbox.gui.setwin;

import org.ligson.searchbox.gui.SearchList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
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
    private JFileChooser jFileChooser = new JFileChooser();
    private SetWin setWin;
    private JScrollPane jScrollPane = new JScrollPane();
    private static Border lineBorder = BorderFactory.createEmptyBorder(0, 0, 1, 0);

    public IndexDirPanel(SetWin setWin) {
        setSize(400, SetWin.realHeight);
        setLocation(100, 0);
        setLayout(null);
        //centerPanel.add(indexDirList);
        //centerPanel.setSize(100,200);
        jScrollPane.setSize(395, SetWin.realHeight - 50);
        jScrollPane.setLocation(0, 0);
        //indexDirList.setSize(400, SetWin.realHeight - 50);
        //indexDirList.setLocation(0, 0);
        //indexDirList.setBackground(Color.BLUE);
        jScrollPane.setViewportView(indexDirList);
        //分别设置水平和垂直滚动条自动出现
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(jScrollPane);
        add(bottomPanel);
        bottomPanel.setSize(395, 50);

        bottomPanel.setLocation(0, SetWin.realHeight - 50);
        bottomPanel.add(includeDirBtn);
        bottomPanel.add(excludeDirBtn);
        indexDirList.addListSelectionListener(this);
        includeDirBtn.addActionListener(this);
        excludeDirBtn.addActionListener(this);
        setName("索引目录设置");


        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setMultiSelectionEnabled(true);
        indexDirList.setCellRenderer(new ListCellRenderer<File>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends File> list, File value, int index, boolean isSelected, boolean cellHasFocus) {
                Icon icon = SearchList.toIcon(value);
                JLabel jLabel = new JLabel(value.getName());
                jLabel.setBorder(lineBorder);
                jLabel.setIcon(icon);
                jLabel.setOpaque(true);
                if (isSelected) {
                    jLabel.setBackground(new Color(163, 184, 204));
                } else {
                    jLabel.setBackground(Color.WHITE);
                }
                return jLabel;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource().equals(includeDirBtn)) {
            int n = jFileChooser.showOpenDialog(this);
            if (n == jFileChooser.APPROVE_OPTION) {
                for (File dir : jFileChooser.getSelectedFiles()) {
                    if (!selectFileModel.contains(dir)) {
                        selectFileModel.addElement(dir);
                    }
                }
                //updateUI();
            }
        } else if (e.getSource().equals(excludeDirBtn)) {
            for(File file:indexDirList.getSelectedValuesList()){
                selectFileModel.removeElement(file);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
