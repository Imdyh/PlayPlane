package com.dkt;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Toolkit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.List;

import com.dkt.info.Boss;
import com.dkt.info.Bullet;
import com.dkt.info.CloseWindow;
import com.dkt.info.Enermy;
import com.dkt.info.Explode;
import com.dkt.info.GameSound;
import com.dkt.info.GameState;
import com.dkt.info.KeyDown;
import com.dkt.info.Prop;
@SuppressWarnings("serial")
public class PlayMain extends Frame{
	/**
	 * 需要实现父类的几个方法
	 * repaint --> update --> paint 三个方法在Component 中定义，作用：构建游戏界面
	 * repaint();会话界面动作，默认设置填充颜色功能
	 * update(); 在paint()基础上 完善绘图；
	 * paint(); 执行用户自定义绘画操作
	 */
	
	//窗口的高宽
	public static int WIDTH =1300;
	public static int HEIGHT=900;
	Image bufferImage=null;
	Random color_rd=new Random();
	//定义移动键
	public static boolean a=false,w=false,s=false,d=false,j=false;
	//定义游戏运行run时间
	public static double startTime=0;
	public static double endTime=0;
	public static boolean timeFlag=true;
	//定义运行时的背景
	public static String bg_image[]={"bg2.jpg","bg3.jpg","bg4.jpg",};
	//定义背景坐标
	public static int bg_y=0;
	//定义飞机起始位置
	public static int my_y=HEIGHT-100;
	public static int my_x=WIDTH/2-30;
	//定义子弹起始位置
	public static int bullet_x=my_x;
	public static int bullet_y=my_y;
	//定义关卡
	public static int customs=1;
	//定义玩家血量
	public static int host_hp=1000;
	//定义打败个数
	public static int count=0;
	//定义玩家分数
	public static int host_score=0;
	//定义boss分数
	public static int boss_score=500;
	//定义超级子弹
	public static int super_bullet=10;
	//出现敌机，发射子弹概率,道具
	public static int fireBullet=30;
	public static int prop=1;
	public static int enermy=15;
	//子弹图片集合
	public static List<Bullet> list_bullet = new ArrayList<Bullet>();
	public static String p_bullet="bullet1.png";
	//敌机图片集合
	//存放飞机集合
	public static List<Enermy> list_enermy = new ArrayList<Enermy>();
	
	//爆炸集合类
	public static List<Explode> list_explode = new ArrayList<Explode>();
	
	//道具类集合
	public static List<Prop> list_prop = new ArrayList<Prop>();
	
	//boss类集合
	public static List<Boss> list_boss = new ArrayList<Boss>();
	//定义内部类
	class MyThread extends Thread{
		//重写方法
		public void run() {
			//通过条件执行判断
			while(true){
				try {
					//执行会话界面动作
					repaint();// 开始绘制背景图片
					my_Move();//移动飞机
					Thread.sleep(80);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	Graphics GraImage = null; 
	public void update(Graphics g) {
		super.paint(g);
		//双重缓冲消除闪烁
		bufferImage = createImage(this.getWidth(), this.getHeight());   //创建图形缓冲区  
	    GraImage = bufferImage.getGraphics();       //获取图形缓冲区的图形上下文  
	    paint(GraImage);        //用paint方法中编写的绘图过程对图形缓冲区绘图  
	    GraImage.dispose();     //释放图形上下文资源  
	    g.drawImage(bufferImage, 0, 0, this);   //将图形缓冲区绘制到屏幕上 
		/*if(bufferImage==null){	
			bufferImage =this.createImage(WIDTH, HEIGHT);		
		}
		//Graphics 绘图类
		 Graphics bg = bufferImage.getGraphics(); 
		 paint(bg);	 
		 g.drawImage(bufferImage, 0, 0, null);*/
	}
	public void gameStart(Graphics g){
		//创建背景初始化游戏
		Toolkit tk = Toolkit.getDefaultToolkit();
		//获取资源图片
		Image bg =  tk.getImage(PlayMain.class.getClassLoader().getResource("image/begin111.jpg"));
		g.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
		//RGB颜色编码
		g.setColor(new Color(color_rd.nextInt(256),
							 color_rd.nextInt(256),
							 color_rd.nextInt(256)));
		//设置字体内容
		g.setFont(new Font("隶书", Font.BOLD, 30));
		g.drawString("按下Enter开始游戏", WIDTH/2-150, HEIGHT-50);
		g.setColor(new Color(250));
		
		g.drawString("开火J,超级子弹K", WIDTH/2-150, HEIGHT-350);
		g.drawString("上W下S左A右D", WIDTH/2-150, HEIGHT-300);
	}
	//定义枚举状态为一个变量
	public static GameState state = GameState.start;
	//在 paint 方法中启用;  绘制此组件
	public void paint(Graphics g){
		switch (state) {
			case start:
				gameStart(g);
				break;
			case run:
				gameRun(g);
				break;
			case over:
				///////////**************************
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image img= tk.getImage(PlayMain.class.getClassLoader().getResource("image/GAMEOVER2.GIF"));
				g.drawImage(img, 0, 0, WIDTH,HEIGHT,null);
				PlayMain.state=GameState.over;
				/////////********************************
				break;
			case win:
				
				break;
			default:
				break;
		}
	}
	//飞机移动
	public void my_Move(){
		if(w){
			if(my_y>30){
				my_y-=15;
			}
		}
		if(s){
			if(my_y<HEIGHT-45){
				my_y+=15;
			}
		}
		if(a){
			if(my_x>0){
				my_x-=15;
			}
		}
		if(d){
			if(my_x<=WIDTH-40){
				my_x+=15;
			}
		}
		if(j){
			//发射子弹
			Bullet b = new Bullet(PlayMain.my_x+18, PlayMain.my_y-20, 5, 24, 30, 100, p_bullet, true, true);
			//将子弹加入集合中
			list_bullet.add(b);
			Bullet b2 = new Bullet(PlayMain.my_x+26, PlayMain.my_y-20, 5, 24, 30, 100, p_bullet, true, true);
			//将子弹加入集合中
			list_bullet.add(b2);
			
			//调用方法 发射发出声音
			new GameSound("Beam.mp3", false).start();
			
		}
	}
	
	public void gameRun(Graphics g){
		System.out.println("进入gamerun（）");
		//创建游戏运行背景
		Toolkit tk=Toolkit.getDefaultToolkit();
		//获取背景图片
		Image bg=tk.getImage(PlayMain.class.getClassLoader().
				getResource("image/"+bg_image[customs>3?1:customs-1]));
		//开始绘图
		g.drawImage(bg, 0,bg_y, WIDTH, HEIGHT,null );
		bg_y+=6;
		g.drawImage(bg, 0, -HEIGHT+bg_y,WIDTH,HEIGHT,null );
		//循环绘制背景
		if(bg_y>=HEIGHT){
			bg_y=0;
		}
		System.out.println("完成制背景图");
		//定义飞机对象
		Image player =  tk.getImage(PlayMain.class.getClassLoader().getResource("image/player.png"));
		
		g.drawImage(player,my_x, my_y, null);
		System.out.println("绘制飞机");
		
		//调用子弹类发射子弹
		for(int i=0;i<list_bullet.size();i++){
			Bullet b =list_bullet.get(i);
			//判断子弹
			if(b.isLive){
				b.drawBullet(g);
			}else {
				list_bullet.remove(i);
			}
			//爆炸效果
			for (int j = 0; j < list_explode.size(); j++) {
				Explode e = list_explode.get(j);
				if(e.isLive){
					e.drawExplode(g);
				}else {
					list_explode.remove(j);
				}
			}
		}
		 //* 加入敌机对象
		Random en_rd = new Random();
		//敌机 子弹
		for (int i = 0; i < list_enermy.size(); i++) {
			//取出敌机
			Enermy p = list_enermy.get(i);
			//判断 是否死亡
			if (p.isLive) {
				p.drawEnermy(g);
			}else{
				list_enermy.remove(i);
			}
			
			if (en_rd.nextInt(fireBullet)==0) {
				Bullet b =	new Bullet(p.x+8, p.y+40, 15, 20, 25, 100, "bullet3.png", false, true);
				list_bullet.add(b);
			}
		}
		//将敌机加入 集合中
		if(en_rd.nextInt(enermy) == 0){
			Enermy p = new Enermy(en_rd.nextInt(WIDTH), 0, 30, 40, 10, 50, "enermy3.png", true, 100);
			list_enermy.add(p);
		}
		//存入集合道具
		if(en_rd.nextInt(prop)==0){
			Prop p = new Prop(en_rd.nextInt(WIDTH), 0, 40, 40, 5, true);
			list_prop.add(p);
		}
		//再把图片加入到页面绘制
		for (int i = 0; i < list_prop.size(); i++) {
			Prop prop=list_prop.get(i);
			if(prop.isLive){
				prop.drawProp(g);
			}else {
				list_prop.remove(i);
			}
		}
		//将boss加入集合
		if(host_score==boss_score){
			Boss b = new Boss(WIDTH/2-350, 50, 600, 200, 0, 150, "boss001.gif", true, 50000);
			list_boss.add(b);
		}
		////再把图片加入到页面绘制
		for (int i = 0; i < list_boss.size(); i++) {
			Boss boss=list_boss.get(0);
			if (boss.isLive==true) {
				boss.drawBoss(g);
			}
			if(boss.isLive==false){
					list_boss.remove(0);
					customs++;
					count++;
				}
			//boss发射子弹
			if (en_rd.nextInt(fireBullet)==0) {
				Boss p = list_boss.get(0);
				Bullet b =	new Bullet(p.x+250, p.y+150, 15, 30, 25, 150, "bullet001.png", false, true);
					for (int j = 0; j < 20; j++) {
						list_bullet.add(b);
					}
			}
			
		}
		
		/*
		if(list_boss.size()!=0){
				Boss boss=list_boss.get(0);
				if (boss.isLive==true) {
					boss.drawBoss(g);
				}
				if(boss.isLive==false){
						list_boss.remove(0);
						customs++;
						count++;
					}
				//boss发射子弹
				if (en_rd.nextInt(fireBullet)==0) {
					Boss p = list_boss.get(0);
					Bullet b =	new Bullet(p.x+250, p.y+150, 15, 30, 25, 150, "bullet001.png", false, true);
						list_bullet.add(b);
				}
		}*/
			
		//玩家血量.****************************
		g.setColor(new Color(255,255,255));
		g.setFont(new Font("隶书", Font.BOLD, 25));
		g.drawString("第 "+customs+" 关",10,50);
		g.drawString("玩家血量:"+host_hp,10,80);
		g.drawString("摧毁敌机:"+count,10,110);
		g.drawString("玩家分数:"+host_score,10,140);
		g.drawString("目标分数:"+boss_score,10,170);
		g.drawString("超级子弹:"+super_bullet,10,200);
		//时间
		Calendar ca = Calendar.getInstance();
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int mi = ca.get(Calendar.MINUTE);
		int se = ca.get(Calendar.SECOND);
		g.drawString(hour+":"+mi+":"+se,WIDTH-140,50);
	}
	//构造函数
	public PlayMain(){
		//窗体是否可见
		this.setVisible(true);
		//窗体标题
		this.setTitle("飞机大战 19.168.236.11版本");
		//设置窗体大小
		this.setSize(WIDTH, HEIGHT);
		
		//设置窗体是否可以拉伸
		this.setResizable(false);
		//设置窗体位置
		this.setLocationRelativeTo(null);
		//关闭窗口
		this.addWindowListener(new CloseWindow());
		
		//播放音乐
		GameSound gameSound=new GameSound("zengjia.mp3",true);
		gameSound.start();
		
		//加入启动背景方法
		MyThread  myThread = new MyThread();
		myThread.start();
		
		
		//监听键盘
		this.addKeyListener(new KeyDown());
		
		
	}
	public static void main(String[] args) {
		new PlayMain();
	}

}
