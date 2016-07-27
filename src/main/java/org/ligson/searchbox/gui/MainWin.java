package org.ligson.searchbox.gui;


import org.ligson.searchbox.gui.listener.KeyboardHook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class MainWin extends JFrame implements WindowFocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 341162064964310681L;
	private SearchBox searchBox = new SearchBox(this);
	private SysTray sysTray = new SysTray(this);
	private KeyboardHook hook = new KeyboardHook(this);
	private JLabel currentIcon = new JLabel();
	private JLabel bg = new JLabel();
	private ContentPanel contentPanel = new ContentPanel(this);
	public SearchBox getSearchBox() {
		return searchBox;
	}

	public void setSearchBox(SearchBox searchBox) {
		this.searchBox = searchBox;
	}

	public KeyboardHook getHook() {
		return hook;
	}

	public void setHook(KeyboardHook hook) {
		this.hook = hook;
	}

	public SysTray getSysTray() {
		return sysTray;
	}

	public void setSysTray(SysTray sysTray) {
		this.sysTray = sysTray;
	}


	public MainWin() {
		setSize(300, 145);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		currentIcon.setVerticalTextPosition(JLabel.BOTTOM);
		currentIcon.setHorizontalTextPosition(JLabel.CENTER);
		add(currentIcon);
		currentIcon.setText("appName");
		Image image = Toolkit.getDefaultToolkit()
				.getImage(MainWin.class.getClassLoader().getResource("assets/img/tray_icon.png"));
		ImageIcon icon = new ImageIcon(image);
		currentIcon.setIcon(icon);
		currentIcon.setBounds(125, 0,50,75);
		currentIcon.setForeground(Color.WHITE);
		//add(searchBox, BorderLayout.CENTER);
		//add(searchBox, BorderLayout.SOUTH);
		//add(searchList, BorderLayout.CENTER);


		setUndecorated(true);
		addWindowFocusListener(this);

		Image bgImage = Toolkit.getDefaultToolkit()
				.getImage(MainWin.class.getClassLoader().getResource("assets/img/frame.png"));
		ImageIcon bgIcon = new ImageIcon(bgImage);
		
		bg.setIcon(bgIcon);
		bg.setBounds(0, 0, getWidth(), getHeight());
		setContentPane(contentPanel);
		getLayeredPane().add(bg,new Integer(Integer.MIN_VALUE));

		getLayeredPane().add(searchBox,1);
		searchBox.setBounds(10,81,280,25);
		searchBox.setOpaque(false);
		searchBox.setBorder(null);

		Thread thread = new Thread(getHook());
		thread.start();	
		setBackground(new Color(0, 0, 0, 0));
	}

	public void triggerVisible() {
		if (isVisible()) {
			setVisible(false);
		} else {
			setVisible(true);
		}
	}

	public static void main(String[] args) throws Exception {
		final MainWin main = new MainWin();
		main.setVisible(true);
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {		
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		//setVisible(false);
	}
}
