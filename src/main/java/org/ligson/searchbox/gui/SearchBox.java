package org.ligson.searchbox.gui;

import org.apache.commons.collections.CollectionUtils;
import org.ligson.searchbox.SearchServiceImpl;
import org.ligson.searchbox.model.PageModel;
import org.ligson.searchbox.service.SearchService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class SearchBox extends JTextField implements ActionListener, DocumentListener, KeyListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private MainWin main;
    private static int offset = 0;
    private static SearchService searchService = new SearchServiceImpl();
    private SearchList searchList;

    public MainWin getMainWin() {
        return main;
    }

    public void setMainWin(MainWin main) {
        this.main = main;
    }

    public SearchBox(MainWin main) {
        super();
        this.main = main;
        addActionListener(this);
        addKeyListener(this);
        searchList = new SearchList(main);
        getDocument().addDocumentListener(this);
        setLayout(new BorderLayout());
        add(searchList, BorderLayout.SOUTH);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        offset = 0;
        textChange();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        offset = 0;
        textChange();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        offset = 0;
        textChange();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File file = (File) searchList.getSelectedItem();
        try {
            Runtime.getRuntime().exec("cmd /c start " + file.getAbsolutePath());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void textChange() {

        String key = getText().trim();
        if (key != "\n" && key != "\r" && key.length() != 0) {
            offset = offset < 0 ? 0 : offset;
            PageModel<File> pageModel = searchService.search(key, offset, 10);
            System.out.println(pageModel.getTotal());
            if (pageModel.getTotal() > 0 && CollectionUtils.isNotEmpty(pageModel.getDatas())) {
                searchList.removeAllItems();
                for (File file : pageModel.getDatas()) {
                    searchList.addItem(file);
                }
                searchList.setPopupVisible(true);
                searchList.setSelectedIndex(0);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (searchList.getSelectedIndex() > 1) {
                searchList.setSelectedIndex(searchList.getSelectedIndex() - 1);
            } else {
                searchList.setSelectedIndex(searchList.getItemCount() - 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (searchList.getSelectedIndex() < searchList.getItemCount()) {
                if (searchList.getSelectedIndex() == searchList.getItemCount() - 1) {
                    searchList.setSelectedIndex(0);
                } else {
                    searchList.setSelectedIndex(searchList.getSelectedIndex() + 1);
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            offset -= 10;
            textChange();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            offset += 10;
            textChange();
        }

    }
}
