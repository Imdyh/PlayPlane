package com.dkt.info;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import com.dkt.PlayMain;

public class Boss{

	//敌机坐标
		public int x,y;
		//大小
		public int width,height;
		//速度
		public int speed;
		//伤害
		public int kill;
		//图片
		public String image;
		
		//是否存活
		public  boolean isLive;
		
		//敌机 血量
		public int hp;
		
		//构造函数
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
			
			// 画血量
			g.setColor(Color.WHITE);
			g.drawRect(x, y - 10, 176, 8);
			g.setColor(Color.RED);
			g.fillRect(x + 1, y - 9, hp / 284 - 1, 7);
			
			move();
		}
		
		//初始化道具移动方向
		Random rd = new Random();
		private int left_right = 5*(int)Math.pow(-1, rd.nextInt(2));
		public void move(){
			//判断是否存活
			if(isLive){
				x+=left_right;
			}
			//判断到了边界
			if(x>=PlayMain.WIDTH-555){
				left_right=-5;
			}
			if(x<=0){
				left_right=5;
			}
		}
		//boss矩形 
		public Rectangle getBossRectangle(){
			return new Rectangle(x,y,width,height);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
