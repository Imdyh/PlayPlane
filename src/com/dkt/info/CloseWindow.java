package com.dkt.info;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class CloseWindow extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		int result =JOptionPane.showConfirmDialog(null, "ȷ���˳���Ϸ��", "��ʾ",
				JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(result==0){
			System.exit(0);
		}
		System.exit(0);
	}
	
}
