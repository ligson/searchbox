package org.ligson.searchbox.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/***
 * 系统托盘
 * 
 * @author ligson
 *
 */
public class SysTray extends MouseAdapter implements ActionListener {
	private MainWin main;
	private SystemTray systemTray = SystemTray.getSystemTray();
	private Image image = Toolkit.getDefaultToolkit()
			.getImage(MainWin.class.getClassLoader().getResource("assets/img/tray_icon.png"));

	private PopupMenu trayMenu = new PopupMenu();
	private MenuItem setItem = new MenuItem("设置");
	private MenuItem helpItem = new MenuItem("帮助");
	private MenuItem exitItem = new MenuItem("退出");

	private TrayIcon trayIcon = new TrayIcon(image, "桌面搜索", trayMenu);

	public SysTray(MainWin main) {
		super();
		this.main = main;
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws Exception {
		exitItem.addActionListener(this);
		trayMenu.add(setItem);
		trayMenu.add(helpItem);
		trayMenu.add(exitItem);
		systemTray.add(trayIcon);
		trayIcon.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(exitItem)) {
			main.getHook().setEnable(false);
			main.setVisible(false);
			systemTray.remove(trayIcon);
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(trayIcon)) {
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				main.triggerVisible();
			}
		}
	}

}
