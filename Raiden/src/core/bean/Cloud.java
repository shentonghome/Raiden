package core.bean;

import java.awt.Graphics;
import java.awt.Image;

import core.config.Constants;

public class Cloud extends GameObject{
	
	public Cloud(String path,double position){
		super(path);
		x = (Constants.FrameWidth+this.width)*position-this.width;
		y = -img.getHeight(null);
	}
	public Cloud(Image img,double position){
		this.img = img;
		this.width = img.getWidth(null);
		this.x = (Constants.FrameWidth+this.width)*position-this.width;
		this.y = -img.getHeight(null);
	}
	@Override
	public void move() {
		y+=Constants.CloudSpeed;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
	}
	
	
	
}
