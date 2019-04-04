package com.dkt.info;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import com.dkt.PlayMain;

public class Prop {
	//����λ��
	public int x,y;
	//���߾��εĿ��
	public int width,height;
	//���ߵ��½��ٶ�
	public int speed;
	//�����Ƿ���
	public boolean isLive=true;
	//����ͼƬ
	public String img[]={"ebu002.png","ebu003.png","yao.png","yao002.png","yaoyao.png","yaoyao002.png","baowu.png"};
	//��ȡ�����±�
	public int index;
	//����±�
	Random rd_index= new Random();
	//���캯��
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
	//���Ƶ���
	public void drawProp(Graphics g){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image prop_img = tk.getImage(Prop.class.getClassLoader().getResource("image/"+img[index]));
		//��
		g.drawImage(prop_img, x, y, width,height,null);
		//�����ƶ�
		move();
		eatProp();
	}
	//��ʼ�������ƶ�����
	Random rd = new Random();
	private int left_right = 5*(int)Math.pow(-1, rd.nextInt(2));
	
	//�ƶ�
	public void move(){
		//�ж��Ƿ���
		if(isLive){
			y+=speed;
			x+=left_right;
		}
		//�жϵ��˱߽�
		/*if(x>=PlayMain.WIDTH-40){
			left_right=-5;
		}
		if(x<=0){
			left_right=5;
		}*/
	}
	//��ȡ���߾���
	public Rectangle getpropRectangle(){
		return new Rectangle(this.x,this.y,width,height);
	}
	//�Ե���
	public void eatProp(){
		//��ȡ��������
		Rectangle hostRectangle= new Rectangle(PlayMain.my_x,PlayMain.my_y,50,44);
		//��ȡ���߾���
		Rectangle propRectangle = getpropRectangle();
		//���ߺ���������
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
