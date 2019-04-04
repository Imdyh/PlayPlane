package com.dkt.info;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.dkt.PlayMain;

public class Enermy {
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
	public boolean isLive;
	
	//敌机 血量
	public int hp;
	
	//构造函数
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
	
	//绘制敌机
	public void drawEnermy(Graphics g){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image i = tk.getImage(Enermy.class.getClassLoader().getResource("image/"+image));
		
		g.drawImage(i, x, y, width,height,null);
		
		//调用移动方法
		hitHost();
		move();
	}
	
	
	//移动方法
	public void move(){
		if (isLive) {
			y+=speed;
		}
		if (y>=PlayMain.HEIGHT) {
			isLive = false;
		}
	}
	
	
	//敌机矩形 
	public Rectangle getEnermyRectangle(){
		return new Rectangle(x,y,width,height);
	}
	
	//敌机和 主机相撞
	public void hitHost(){
		Rectangle hostRectangle = new Rectangle(PlayMain.my_x,PlayMain.my_y,50,44);
		for (int i = 0; i < PlayMain.list_enermy.size(); i++) {
			
			Enermy e = PlayMain.list_enermy.get(i);
			//判断 敌机 和 主机 是否相遇
			if (e.getEnermyRectangle().intersects(hostRectangle)==true) {
				
				//爆炸效果
				Explode explod = new Explode(this.x, this.y);
				PlayMain.list_explode.add(explod);
				//敌机 死亡 主机 减血..
				e.isLive = false;
				
				PlayMain.host_hp-=100;
				if (PlayMain.host_hp<=0) {
					PlayMain.state = GameState.over;
				}
				
				//杀敌数量 加， 得分加，
				PlayMain.count++;
				PlayMain.host_score+=100;
				//移除敌机
				PlayMain.list_enermy.remove(i);
				break;
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
}
