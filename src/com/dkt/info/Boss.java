package com.dkt.info;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import com.dkt.PlayMain;

public class Boss{

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
		public  boolean isLive;
		
		//�л� Ѫ��
		public int hp;
		
		//���캯��
		public Boss(int x, int y, int width, int height, int speed, int kill, String image, boolean isLive, int hp) {
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
		public void drawBoss(Graphics g){
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image img =tk.getImage(Boss.class.getClassLoader().getResource("image/"+image));
			g.drawImage(img, x, y, width, height, null);
			
			// ��Ѫ��
			g.setColor(Color.WHITE);
			g.drawRect(x, y - 10, 176, 8);
			g.setColor(Color.RED);
			g.fillRect(x + 1, y - 9, hp / 284 - 1, 7);
			
			move();
		}
		
		//��ʼ�������ƶ�����
		Random rd = new Random();
		private int left_right = 5*(int)Math.pow(-1, rd.nextInt(2));
		public void move(){
			//�ж��Ƿ���
			if(isLive){
				x+=left_right;
			}
			//�жϵ��˱߽�
			if(x>=PlayMain.WIDTH-555){
				left_right=-5;
			}
			if(x<=0){
				left_right=5;
			}
		}
		//boss���� 
		public Rectangle getBossRectangle(){
			return new Rectangle(x,y,width,height);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
