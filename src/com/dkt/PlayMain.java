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
	 * ��Ҫʵ�ָ���ļ�������
	 * repaint --> update --> paint ����������Component �ж��壬���ã�������Ϸ����
	 * repaint();�Ự���涯����Ĭ�����������ɫ����
	 * update(); ��paint()������ ���ƻ�ͼ��
	 * paint(); ִ���û��Զ���滭����
	 */
	
	//���ڵĸ߿�
	public static int WIDTH =1300;
	public static int HEIGHT=900;
	Image bufferImage=null;
	Random color_rd=new Random();
	//�����ƶ���
	public static boolean a=false,w=false,s=false,d=false,j=false;
	//������Ϸ����runʱ��
	public static double startTime=0;
	public static double endTime=0;
	public static boolean timeFlag=true;
	//��������ʱ�ı���
	public static String bg_image[]={"bg2.jpg","bg3.jpg","bg4.jpg",};
	//���屳������
	public static int bg_y=0;
	//����ɻ���ʼλ��
	public static int my_y=HEIGHT-100;
	public static int my_x=WIDTH/2-30;
	//�����ӵ���ʼλ��
	public static int bullet_x=my_x;
	public static int bullet_y=my_y;
	//����ؿ�
	public static int customs=1;
	//�������Ѫ��
	public static int host_hp=1000;
	//�����ܸ���
	public static int count=0;
	//������ҷ���
	public static int host_score=0;
	//����boss����
	public static int boss_score=500;
	//���峬���ӵ�
	public static int super_bullet=10;
	//���ֵл��������ӵ�����,����
	public static int fireBullet=30;
	public static int prop=1;
	public static int enermy=15;
	//�ӵ�ͼƬ����
	public static List<Bullet> list_bullet = new ArrayList<Bullet>();
	public static String p_bullet="bullet1.png";
	//�л�ͼƬ����
	//��ŷɻ�����
	public static List<Enermy> list_enermy = new ArrayList<Enermy>();
	
	//��ը������
	public static List<Explode> list_explode = new ArrayList<Explode>();
	
	//�����༯��
	public static List<Prop> list_prop = new ArrayList<Prop>();
	
	//boss�༯��
	public static List<Boss> list_boss = new ArrayList<Boss>();
	//�����ڲ���
	class MyThread extends Thread{
		//��д����
		public void run() {
			//ͨ������ִ���ж�
			while(true){
				try {
					//ִ�лỰ���涯��
					repaint();// ��ʼ���Ʊ���ͼƬ
					my_Move();//�ƶ��ɻ�
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
		//˫�ػ���������˸
		bufferImage = createImage(this.getWidth(), this.getHeight());   //����ͼ�λ�����  
	    GraImage = bufferImage.getGraphics();       //��ȡͼ�λ�������ͼ��������  
	    paint(GraImage);        //��paint�����б�д�Ļ�ͼ���̶�ͼ�λ�������ͼ  
	    GraImage.dispose();     //�ͷ�ͼ����������Դ  
	    g.drawImage(bufferImage, 0, 0, this);   //��ͼ�λ��������Ƶ���Ļ�� 
		/*if(bufferImage==null){	
			bufferImage =this.createImage(WIDTH, HEIGHT);		
		}
		//Graphics ��ͼ��
		 Graphics bg = bufferImage.getGraphics(); 
		 paint(bg);	 
		 g.drawImage(bufferImage, 0, 0, null);*/
	}
	public void gameStart(Graphics g){
		//����������ʼ����Ϸ
		Toolkit tk = Toolkit.getDefaultToolkit();
		//��ȡ��ԴͼƬ
		Image bg =  tk.getImage(PlayMain.class.getClassLoader().getResource("image/begin111.jpg"));
		g.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
		//RGB��ɫ����
		g.setColor(new Color(color_rd.nextInt(256),
							 color_rd.nextInt(256),
							 color_rd.nextInt(256)));
		//������������
		g.setFont(new Font("����", Font.BOLD, 30));
		g.drawString("����Enter��ʼ��Ϸ", WIDTH/2-150, HEIGHT-50);
		g.setColor(new Color(250));
		
		g.drawString("����J,�����ӵ�K", WIDTH/2-150, HEIGHT-350);
		g.drawString("��W��S��A��D", WIDTH/2-150, HEIGHT-300);
	}
	//����ö��״̬Ϊһ������
	public static GameState state = GameState.start;
	//�� paint ����������;  ���ƴ����
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
	//�ɻ��ƶ�
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
			//�����ӵ�
			Bullet b = new Bullet(PlayMain.my_x+18, PlayMain.my_y-20, 5, 24, 30, 100, p_bullet, true, true);
			//���ӵ����뼯����
			list_bullet.add(b);
			Bullet b2 = new Bullet(PlayMain.my_x+26, PlayMain.my_y-20, 5, 24, 30, 100, p_bullet, true, true);
			//���ӵ����뼯����
			list_bullet.add(b2);
			
			//���÷��� ���䷢������
			new GameSound("Beam.mp3", false).start();
			
		}
	}
	
	public void gameRun(Graphics g){
		System.out.println("����gamerun����");
		//������Ϸ���б���
		Toolkit tk=Toolkit.getDefaultToolkit();
		//��ȡ����ͼƬ
		Image bg=tk.getImage(PlayMain.class.getClassLoader().
				getResource("image/"+bg_image[customs>3?1:customs-1]));
		//��ʼ��ͼ
		g.drawImage(bg, 0,bg_y, WIDTH, HEIGHT,null );
		bg_y+=6;
		g.drawImage(bg, 0, -HEIGHT+bg_y,WIDTH,HEIGHT,null );
		//ѭ�����Ʊ���
		if(bg_y>=HEIGHT){
			bg_y=0;
		}
		System.out.println("����Ʊ���ͼ");
		//����ɻ�����
		Image player =  tk.getImage(PlayMain.class.getClassLoader().getResource("image/player.png"));
		
		g.drawImage(player,my_x, my_y, null);
		System.out.println("���Ʒɻ�");
		
		//�����ӵ��෢���ӵ�
		for(int i=0;i<list_bullet.size();i++){
			Bullet b =list_bullet.get(i);
			//�ж��ӵ�
			if(b.isLive){
				b.drawBullet(g);
			}else {
				list_bullet.remove(i);
			}
			//��ըЧ��
			for (int j = 0; j < list_explode.size(); j++) {
				Explode e = list_explode.get(j);
				if(e.isLive){
					e.drawExplode(g);
				}else {
					list_explode.remove(j);
				}
			}
		}
		 //* ����л�����
		Random en_rd = new Random();
		//�л� �ӵ�
		for (int i = 0; i < list_enermy.size(); i++) {
			//ȡ���л�
			Enermy p = list_enermy.get(i);
			//�ж� �Ƿ�����
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
		//���л����� ������
		if(en_rd.nextInt(enermy) == 0){
			Enermy p = new Enermy(en_rd.nextInt(WIDTH), 0, 30, 40, 10, 50, "enermy3.png", true, 100);
			list_enermy.add(p);
		}
		//���뼯�ϵ���
		if(en_rd.nextInt(prop)==0){
			Prop p = new Prop(en_rd.nextInt(WIDTH), 0, 40, 40, 5, true);
			list_prop.add(p);
		}
		//�ٰ�ͼƬ���뵽ҳ�����
		for (int i = 0; i < list_prop.size(); i++) {
			Prop prop=list_prop.get(i);
			if(prop.isLive){
				prop.drawProp(g);
			}else {
				list_prop.remove(i);
			}
		}
		//��boss���뼯��
		if(host_score==boss_score){
			Boss b = new Boss(WIDTH/2-350, 50, 600, 200, 0, 150, "boss001.gif", true, 50000);
			list_boss.add(b);
		}
		////�ٰ�ͼƬ���뵽ҳ�����
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
			//boss�����ӵ�
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
				//boss�����ӵ�
				if (en_rd.nextInt(fireBullet)==0) {
					Boss p = list_boss.get(0);
					Bullet b =	new Bullet(p.x+250, p.y+150, 15, 30, 25, 150, "bullet001.png", false, true);
						list_bullet.add(b);
				}
		}*/
			
		//���Ѫ��.****************************
		g.setColor(new Color(255,255,255));
		g.setFont(new Font("����", Font.BOLD, 25));
		g.drawString("�� "+customs+" ��",10,50);
		g.drawString("���Ѫ��:"+host_hp,10,80);
		g.drawString("�ݻٵл�:"+count,10,110);
		g.drawString("��ҷ���:"+host_score,10,140);
		g.drawString("Ŀ�����:"+boss_score,10,170);
		g.drawString("�����ӵ�:"+super_bullet,10,200);
		//ʱ��
		Calendar ca = Calendar.getInstance();
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int mi = ca.get(Calendar.MINUTE);
		int se = ca.get(Calendar.SECOND);
		g.drawString(hour+":"+mi+":"+se,WIDTH-140,50);
	}
	//���캯��
	public PlayMain(){
		//�����Ƿ�ɼ�
		this.setVisible(true);
		//�������
		this.setTitle("�ɻ���ս 19.168.236.11�汾");
		//���ô����С
		this.setSize(WIDTH, HEIGHT);
		
		//���ô����Ƿ��������
		this.setResizable(false);
		//���ô���λ��
		this.setLocationRelativeTo(null);
		//�رմ���
		this.addWindowListener(new CloseWindow());
		
		//��������
		GameSound gameSound=new GameSound("zengjia.mp3",true);
		gameSound.start();
		
		//����������������
		MyThread  myThread = new MyThread();
		myThread.start();
		
		
		//��������
		this.addKeyListener(new KeyDown());
		
		
	}
	public static void main(String[] args) {
		new PlayMain();
	}

}
