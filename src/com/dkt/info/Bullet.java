package com.dkt.info;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.dkt.PlayMain;

public class Bullet {
	
	//����  + ����
	//����
	public int x,y;
	//��С
	public int width,height;
	//�ٶ�
	public int speed;
	//�˺�
	public int kill;
	//ͼƬ
	public String image;
	//�û�
	public boolean isGood;
	//�Ƿ���Ч
	public boolean isLive;
	//���캯�� �в�
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
	
	
	// �����ӵ�
	public void drawBullet(Graphics g){
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		//��ȡͼƬ
		Image img = toolkit.getImage(Bullet.class.getClassLoader()
				.getResource("image/"+image));
		
		g.drawImage(img, x, y, width,height,null);
		//�����ӵ� �ƶ�
		move();
	}
	
	//�ӵ� �ƶ�
	public void move(){
		//�ƶ�
		if(isGood){
			y-=speed;
			
		}else{
			y+=speed;
		}
		
		//�Ƿ���Ч
		if (y<=0 || y>=PlayMain.HEIGHT+50) {
			isLive=false;
		}
		//�����ӵ�
		hitEnermy();
		hitHost();
		hitBullet();
		hitBoss();
		boss_hitHost();
	}
	
	//�ӵ�����
	public Rectangle getBulletRectangle(){
		return new Rectangle(x,y,width,height);
	}
	
	//�����ӵ�,������л�
	public void hitEnermy(){
		if (isGood) {
			Rectangle bRectangle = getBulletRectangle();
			//ȡ�����еĵл�
			for (int i = 0; i <PlayMain.list_enermy.size(); i++) {
				Enermy e = PlayMain.list_enermy.get(i);
				//�ж��Ƿ���У������н���
				if (e.getEnermyRectangle().intersects(bRectangle)==true) {
					e.hp-=kill;
					this.isLive=false;
					//�жϵл��Ƿ�����
					if(e.hp<=0){
						//��ըЧ��
						Explode explode = new Explode(x-20, y-20);
						PlayMain.list_explode.add(explode);
						//��ը����
						new GameSound("Explode.mp3", false).start();
						//�����÷֣��л���ʧ
						PlayMain.host_score+=100;
						PlayMain.count++;
						PlayMain.list_enermy.remove(i);
						break;
					}
				}
			}
		}
	}
	//�л�������
	public void hitHost(){
		//�ж��ӵ��Ǻû�
		if (isGood==false) {
			//��ȡ�ɻ�����
			Rectangle hostRectangle= new Rectangle(PlayMain.my_x,PlayMain.my_y,50,44);
			//��ȡ�ӵ�����
			Rectangle bulletRectangle = this.getBulletRectangle();
			
			if(hostRectangle.intersects(bulletRectangle)==true){
				this.isLive = false;
				PlayMain.host_hp-=kill;
				//��ըЧ��
				Explode explod = new Explode(x-20, y-20);
				PlayMain.list_explode.add(explod);
				
				if(PlayMain.host_hp<=0){
					//��ըЧ��
					Explode explode = new Explode(x-20, y-20);
					PlayMain.list_explode.add(explode);
					PlayMain.state = GameState.over;
				}
			}
		}
	}
	
	
	//�ӵ���ײ
	public void hitBullet(){
		if(isGood){
			Rectangle bRectangle = this.getBulletRectangle();
			for (int i=0;i<PlayMain.list_bullet.size();i++){
				Bullet bullet= PlayMain.list_bullet.get(i);
				//�Ӽ�����ȡ���ӵ������ж�
				if(bullet.isGood==false){
					//�ж��Ƿ����
					if(bullet.getBulletRectangle().intersects(bRectangle)==true){
						this.isLive = false;
						bullet.isLive =false;
						
						//�����Ƴ�
						PlayMain.list_bullet.remove(i);
						break;
					}
				}
				
			}
		}
	}
	//������boss
	public void hitBoss(){
		if (isGood) {
			Rectangle bRectangle = getBulletRectangle();
			//ȡ�����еĵл�
			for (int i = 0; i <PlayMain.list_boss.size(); i++) {
				Boss e = PlayMain.list_boss.get(i);
				//�ж��Ƿ���У������н���
				if (e.getBossRectangle().intersects(bRectangle)==true) {
					e.hp-=kill;
					//�ӵ���ʧ
					this.isLive=false;
					//�жϵл��Ƿ�����
					if(e.hp<=0){
						//��ըЧ��
						Explode explode = new Explode(x-20, y-20);
						PlayMain.list_explode.add(explode);
						//��ը����
						new GameSound("Explode.mp3", false).start();
						//�����÷֣��л���ʧ
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
	//boss������
		public void boss_hitHost(){
			//�ж��ӵ��Ǻû�
			if (isGood==false) {
				//��ȡ�ɻ�����
				Rectangle hostRectangle= new Rectangle(PlayMain.my_x,PlayMain.my_y,50,44);
				//��ȡ�ӵ�����
				Rectangle bulleRectangle = this.getBulletRectangle();
				
				if(bulleRectangle.intersects(hostRectangle)==true){
					this.isLive = false;
					PlayMain.host_hp-=kill;
					//��ըЧ��
					Explode explod = new Explode(x-20, y-20);
					PlayMain.list_explode.add(explod);
					
					if(PlayMain.host_hp<=0){
						//��ըЧ��
						Explode explode = new Explode(x-20, y-20);
						PlayMain.list_explode.add(explode);
						PlayMain.state = GameState.over;
					}
				}
			}
		}
	
	
	
	
}
	
	
	
	
	
	



































