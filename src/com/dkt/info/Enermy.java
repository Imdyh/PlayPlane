package com.dkt.info;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.dkt.PlayMain;

public class Enermy {
	//�л�����
	public int x,y;
	//��С
	public int width,height;
	//�ٶ�
	public int speed;
	//�˺�
	public int kill;
	//ͼƬ
	public String image;
	
	//�Ƿ���
	public boolean isLive;
	
	//�л� Ѫ��
	public int hp;
	
	//���캯��
	public Enermy(int x, int y, int width, int height, int speed, int kill, String image, boolean isLive, int hp) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.kill = kill;
		this.image = image;
		this.isLive = isLive;
		this.hp = hp;
	}
	
	//���Ƶл�
	public void drawEnermy(Graphics g){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image i = tk.getImage(Enermy.class.getClassLoader().getResource("image/"+image));
		
		g.drawImage(i, x, y, width,height,null);
		
		//�����ƶ�����
		hitHost();
		move();
	}
	
	
	//�ƶ�����
	public void move(){
		if (isLive) {
			y+=speed;
		}
		if (y>=PlayMain.HEIGHT) {
			isLive = false;
		}
	}
	
	
	//�л����� 
	public Rectangle getEnermyRectangle(){
		return new Rectangle(x,y,width,height);
	}
	
	//�л��� ������ײ
	public void hitHost(){
		Rectangle hostRectangle = new Rectangle(PlayMain.my_x,PlayMain.my_y,50,44);
		for (int i = 0; i < PlayMain.list_enermy.size(); i++) {
			
			Enermy e = PlayMain.list_enermy.get(i);
			//�ж� �л� �� ���� �Ƿ�����
			if (e.getEnermyRectangle().intersects(hostRectangle)==true) {
				
				//��ըЧ��
				Explode explod = new Explode(this.x, this.y);
				PlayMain.list_explode.add(explod);
				//�л� ���� ���� ��Ѫ..
				e.isLive = false;
				
				PlayMain.host_hp-=100;
				if (PlayMain.host_hp<=0) {
					PlayMain.state = GameState.over;
				}
				
				//ɱ������ �ӣ� �÷ּӣ�
				PlayMain.count++;
				PlayMain.host_score+=100;
				//�Ƴ��л�
				PlayMain.list_enermy.remove(i);
				break;
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
}
