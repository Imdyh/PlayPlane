package com.dkt.info;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.dkt.PlayMain;

public class Bullet {
	
	//属性  + 方法
	//坐标
	public int x,y;
	//大小
	public int width,height;
	//速度
	public int speed;
	//伤害
	public int kill;
	//图片
	public String image;
	//好坏
	public boolean isGood;
	//是否有效
	public boolean isLive;
	//构造函数 有参
	public Bullet(int x, int y, int width, int height, int speed, int kill, String image, boolean isGood,
			boolean isLive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.kill = kill;
		this.image = image;
		this.isGood = isGood;
		this.isLive = isLive;
	}
	
	
	// 绘制子弹
	public void drawBullet(Graphics g){
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		//获取图片
		Image img = toolkit.getImage(Bullet.class.getClassLoader()
				.getResource("image/"+image));
		
		g.drawImage(img, x, y, width,height,null);
		//调用子弹 移动
		move();
	}
	
	//子弹 移动
	public void move(){
		//移动
		if(isGood){
			y-=speed;
			
		}else{
			y+=speed;
		}
		
		//是否有效
		if (y<=0 || y>=PlayMain.HEIGHT+50) {
			isLive=false;
		}
		//发射子弹
		hitEnermy();
		hitHost();
		hitBullet();
		hitBoss();
		boss_hitHost();
	}
	
	//子弹矩形
	public Rectangle getBulletRectangle(){
		return new Rectangle(x,y,width,height);
	}
	
	//发射子弹,主机打敌机
	public void hitEnermy(){
		if (isGood) {
			Rectangle bRectangle = getBulletRectangle();
			//取集合中的敌机
			for (int i = 0; i <PlayMain.list_enermy.size(); i++) {
				Enermy e = PlayMain.list_enermy.get(i);
				//判断是否打中，矩形有交集
				if (e.getEnermyRectangle().intersects(bRectangle)==true) {
					e.hp-=kill;
					this.isLive=false;
					//判断敌机是否死亡
					if(e.hp<=0){
						//爆炸效果
						Explode explode = new Explode(x-20, y-20);
						PlayMain.list_explode.add(explode);
						//爆炸声音
						new GameSound("Explode.mp3", false).start();
						//主机得分，敌机消失
						PlayMain.host_score+=100;
						PlayMain.count++;
						PlayMain.list_enermy.remove(i);
						break;
					}
				}
			}
		}
	}
	//敌机打主机
	public void hitHost(){
		//判断子弹是好坏
		if (isGood==false) {
			//获取飞机矩形
			Rectangle hostRectangle= new Rectangle(PlayMain.my_x,PlayMain.my_y,50,44);
			//获取子弹矩形
			Rectangle bulletRectangle = this.getBulletRectangle();
			
			if(hostRectangle.intersects(bulletRectangle)==true){
				this.isLive = false;
				PlayMain.host_hp-=kill;
				//爆炸效果
				Explode explod = new Explode(x-20, y-20);
				PlayMain.list_explode.add(explod);
				
				if(PlayMain.host_hp<=0){
					//爆炸效果
					Explode explode = new Explode(x-20, y-20);
					PlayMain.list_explode.add(explode);
					PlayMain.state = GameState.over;
				}
			}
		}
	}
	
	
	//子弹对撞
	public void hitBullet(){
		if(isGood){
			Rectangle bRectangle = this.getBulletRectangle();
			for (int i=0;i<PlayMain.list_bullet.size();i++){
				Bullet bullet= PlayMain.list_bullet.get(i);
				//从集合中取出子弹进行判断
				if(bullet.isGood==false){
					//判断是否打中
					if(bullet.getBulletRectangle().intersects(bRectangle)==true){
						this.isLive = false;
						bullet.isLive =false;
						
						//打中移除
						PlayMain.list_bullet.remove(i);
						break;
					}
				}
				
			}
		}
	}
	//主机打boss
	public void hitBoss(){
		if (isGood) {
			Rectangle bRectangle = getBulletRectangle();
			//取集合中的敌机
			for (int i = 0; i <PlayMain.list_boss.size(); i++) {
				Boss e = PlayMain.list_boss.get(i);
				//判断是否打中，矩形有交集
				if (e.getBossRectangle().intersects(bRectangle)==true) {
					e.hp-=kill;
					//子弹消失
					this.isLive=false;
					//判断敌机是否死亡
					if(e.hp<=0){
						//爆炸效果
						Explode explode = new Explode(x-20, y-20);
						PlayMain.list_explode.add(explode);
						//爆炸声音
						new GameSound("Explode.mp3", false).start();
						//主机得分，敌机消失
						e.isLive=false;
						PlayMain.host_score+=1000;
						PlayMain.count++;
						PlayMain.list_boss.remove(i);
						break;
					}
				}
			}
		}
	}
	//boss打主机
		public void boss_hitHost(){
			//判断子弹是好坏
			if (isGood==false) {
				//获取飞机矩形
				Rectangle hostRectangle= new Rectangle(PlayMain.my_x,PlayMain.my_y,50,44);
				//获取子弹矩形
				Rectangle bulleRectangle = this.getBulletRectangle();
				
				if(bulleRectangle.intersects(hostRectangle)==true){
					this.isLive = false;
					PlayMain.host_hp-=kill;
					//爆炸效果
					Explode explod = new Explode(x-20, y-20);
					PlayMain.list_explode.add(explod);
					
					if(PlayMain.host_hp<=0){
						//爆炸效果
						Explode explode = new Explode(x-20, y-20);
						PlayMain.list_explode.add(explode);
						PlayMain.state = GameState.over;
					}
				}
			}
		}
	
	
	
	
}
	
	
	
	
	
	



































