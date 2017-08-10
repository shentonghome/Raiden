package core.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import core.bean.GameObject.update;
import core.config.Constants;
import core.config.GameConfig;
import core.util.ImageUtil;
import core.util.ThreadUtil;


public abstract class GameObject {
	Image img;
	double x,y;
	public double speed;
	int width,height;
	public boolean isLive;
	public boolean isPause;
	public double degree;
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,width,height);
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setX(double x){
		this.x = x; 
	}
	
	public void setY(double y){
		this.y = y;
	}

	public GameObject(String path, double x, double y, double speed) {
		this.img = ImageUtil.getImage(path);
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.isLive = true;		//判断是否存活
		this.isPause = true;
	}
	
	public GameObject() {
		this.isLive = true;
		this.isPause = true;
	}
	
	public GameObject(String path){
		this.img = ImageUtil.getImage(path);
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.isLive = true;
		this.isPause = true;
	}
	
	public GameObject(Image img,double x,double y){
		this.isLive = true;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.img = img;
		this.x = x;
		this.y = y;
		isPause = true;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void launch(){
		new update().start();
	}

	class update extends Thread{	//线程控制移动
		@Override
		public void run() {
			while(isLive&&GameConfig.isExit){
				if (GameConfig.isPause) {
					move();
				}
				ThreadUtil.sleep(Constants.RepaintFrequence);
			}
		}
	}

	public abstract void move();
	public abstract void draw(Graphics g);
}