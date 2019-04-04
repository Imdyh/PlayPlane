package com.dkt.info;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Explode {
	public int x,y;
	public String image[]={"e1.gif","e2.gif","e3.gif","e4.gif","e5.gif","e6.gif","e7.gif","e8.gif","e9.gif","e10.gif"};
	public int index=0;
	public boolean isLive=true;
	//¹¹Ôìº¯Êý
	public Explode(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	//»æÖÆ±¬Õ¨
	public void drawExplode(Graphics g){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.getImage(Explode.class.getClassLoader().getResource("image/"+this.image[index]));
		g.drawImage(image, x, y, null);
		//±¬Õ¨Éù
		
		
		index++;
		if(index>=this.image.length){
			this.isLive=false;
		}
	}
	
	
}
