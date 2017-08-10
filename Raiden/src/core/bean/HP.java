package core.bean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import core.config.GameConfig;
import core.util.ImageUtil;

public class HP {
	Image img;
	int x,y;
	int max;
	int HP;
	
	public HP(int x,int y){
		img = ImageUtil.getImage("image/HP.png");
		this.x = x;
		this.y = y;
		max = (int)(Integer.valueOf(GameConfig.myPlaneConfig[1])*0.3);
		HP = max;
		
	}
	
	public void draw(Graphics g){
		Color color = g.getColor();
		g.setColor(new Color(149,53,4));
		g.drawRoundRect(x+40, y, max+1, 24, 5, 5);
		g.setColor(new Color(248,111,24));
		g.fillRoundRect(x+41, y+1, HP, 23, 5, 5);
		g.setColor(color);
		g.drawImage(img,x,y,null);
	}
	
	public void setHP(float preset){
		HP = (int) (preset*max);
	}
	
	
	
	
}
