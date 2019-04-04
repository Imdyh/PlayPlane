package com.dkt.info;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.dkt.PlayMain;

public class KeyDown extends KeyAdapter{

	//重写按下方法
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			if(PlayMain.state==GameState.start){
				PlayMain.state=GameState.run;
				PlayMain.startTime=System.currentTimeMillis();
			}
			break;
		case KeyEvent.VK_W:
			PlayMain.w=true;
			break;
		case KeyEvent.VK_S:
			PlayMain.s=true;
			break;
		case KeyEvent.VK_A:
			PlayMain.a=true;
			break;
		case KeyEvent.VK_D:
			PlayMain.d=true;
			break;
		case KeyEvent.VK_J:
			PlayMain.j=true;
			break;
		case KeyEvent.VK_K:
			if(PlayMain.super_bullet>0){
				for (int i = 0; i < 20; i++) {
					Bullet bullet= new Bullet(i*70, 750, 50, 44, 90,1000, "hero.GIF", true, true);
					PlayMain.list_bullet.add(bullet);
				}
				PlayMain.super_bullet--;
			}
			break;
		default:
			break;
		}
	}

	//重写松开方法
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			PlayMain.w=false;
			break;
		case KeyEvent.VK_S:
			PlayMain.s=false;
			break;
		case KeyEvent.VK_A:
			PlayMain.a=false;
			break;
		case KeyEvent.VK_D:
			PlayMain.d=false;
			break;
		case KeyEvent.VK_J:
			PlayMain.j=false;
			break;

		default:
			break;
		}
		
	}
}
