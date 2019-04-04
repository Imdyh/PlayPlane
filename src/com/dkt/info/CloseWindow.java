package com.dkt.info;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class CloseWindow extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		int result =JOptionPane.showConfirmDialog(null, "确定退出游戏？", "提示",
				JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(result==0){
			System.exit(0);
		}
		System.exit(0);
	}
	
}
