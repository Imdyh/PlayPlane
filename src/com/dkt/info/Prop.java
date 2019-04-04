package com.dkt.info;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import com.dkt.PlayMain;

public class Prop {
	//道具位置
	public int x,y;
	//道具矩形的宽高
	public int width,height;
	//道具的下降速度
	public int speed;
	//道具是否存活
	public boolean isLive=true;
	//道具图片
	public String img[]={"ebu002.png","ebu003.png","yao.png","yao002.png","yaoyao.png","yaoyao002.png","baowu.png"};
	//获取道具下标
	public int index;
	//随机下标
	Random rd_index= new Random();
	//构造函数
	public Prop(int x, int y, int width, int height, int speed, boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.isLive = isLive;
		this.index = rd_index.nextInt(7);
	}
	//绘制道具
	public void drawProp(Graphics g){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image prop_img = tk.getImage(Prop.class.getClassLoader().getResource("image/"+img[index]));
		//画
		g.drawImage(prop_img, x, y, width,height,null);
		//调用移动
		move();
		eatProp();
	}
	//初始化道具移动方向
	Random rd = new Random();
	private int left_right = 5*(int)Math.pow(-1, rd.nextInt(2));
	
	//移动
	public void move(){
		//判断是否存活
		if(isLive){
			y+=speed;
			x+=left_right;
		}
		//判断到了边界
		/*if(x>=PlayMain.WIDTH-40){
			left_right=-5;
		}
		if(x<=0){
			left_right=5;
		}*/
	}
	//获取道具矩形
	public Rectangle getpropRectangle(){
		return new Rectangle(this.x,this.y,width,height);
	}
	//吃道具
	public void eatProp(){
		//获取主机矩形
		Rectangle hostRectangle= new Rectangle(PlayMain.my_x,PlayMain.my_y,50,44);
		//获取道具矩形
		Rectangle propRectangle = getpropRectangle();
		//道具和主机相遇
		if(hostRectangle.intersects(propRectangle)==true){
			switch (index) {
			case 0:
				PlayMain.host_hp+=100;
				break;
			case 1:
				PlayMain.host_hp+=70;
				break;
			case 2:
				PlayMain.super_bullet+=10;
				break;
			case 3:
				PlayMain.host_hp+=300;
				break;	
			case 4:
				PlayMain.host_hp+=10;
				break;	
			case 5:
				PlayMain.super_bullet+=20;
				break;	
			case 6:
				PlayMain.p_bullet="baowu.png";
				break;	
			default:
				break;
			}
			this.isLive=false;
			if(PlayMain.host_hp>=5000){
				PlayMain.host_hp=5000;
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
