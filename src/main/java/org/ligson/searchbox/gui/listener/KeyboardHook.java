package org.ligson.searchbox.gui.listener;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;
import org.ligson.searchbox.gui.MainWin;

import java.util.Date;

public class KeyboardHook implements Runnable {
	private static HHOOK hhk;
	private static LowLevelKeyboardProc keyboardHook;
	private final static User32 lib = User32.INSTANCE;
	private boolean enable = true;
	private static Long lastClick = null;
	private static int clickNum = 0;
	private MainWin main;

	public KeyboardHook(MainWin main) {
		super();
		this.main = main;
	}

	public MainWin getMain() {
		return main;
	}

	public void setMain(MainWin main) {
		this.main = main;
	}

	@Override
	public void run() {
		HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
		keyboardHook = new LowLevelKeyboardProc() {
			public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
				
				if (!enable) {
					System.exit(0);
				}
				
				if ((wParam.intValue()==WinUser.WM_KEYUP)&&(info.vkCode == 162||info.vkCode==163)) {
					clickNum++;
					
					if(clickNum==1){
						lastClick = new Date().getTime();
					}else{
						if (new Date().getTime() - lastClick < 600) {
							main.triggerVisible();
						}
						lastClick = null;
						clickNum = 0;
					}
				}

				return lib.CallNextHookEx(hhk, nCode, wParam, info.getPointer());
			}
		};
		hhk = lib.SetWindowsHookEx(User32.WH_KEYBOARD_LL, keyboardHook, hMod, 0);
		int result;
		MSG msg = new MSG();
		while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
			if (result == -1) {
				System.err.println("error in get message");
				break;
			} else {
				System.err.println("got message");
				lib.TranslateMessage(msg);
				lib.DispatchMessage(msg);
			}
		}
		lib.UnhookWindowsHookEx(hhk);

	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
