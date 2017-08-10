package core.bean;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import core.config.Constants;
import core.util.ThreadUtil;

public class Particle extends GameObject{
	public float time;
	public float speed;
	public double degree;
	public float alpha;
	public double initTime;
	float a;
	float b;
	
	public Particle(Image img,double x,double y,float time,float speed,double degree) {
		this.img = img;
		this.x = x-img.getWidth(null)/2;
		this.y = y;
		this.time = time;
		this.initTime = time;
		this.speed = speed;
		this.degree = degree;
		alpha = 1f;
		a = Constants.ParticlePartA;
		b = Constants.ParticlePartB;
		
	}

	@Override
	public void move() {
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		time-=Constants.RepaintFrequence;
		subAlpha();
		
	}
	
	public void subAlpha(){
		if(time>initTime*a&&time<=initTime){
			alpha = 1f;
		}else if(time>initTime*b&&time<=initTime*a){
			alpha = 0.5f;
		}
		else if(time>initTime*0.0&&time<=initTime*b){
			alpha = 0.1f;
		}
		
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		Composite comp = g2d.getComposite();
		g2d.setComposite(ac);
		g.drawImage(img, (int)x, (int)y,null);
		g2d.setComposite(comp);
	}
	
}
