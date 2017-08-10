package core.emmiter;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Emmiter {
	public Image img;
	public double x;
	public double y;
	public boolean isPause;
	
	public Emmiter(double x,double y){
		this.x = x;
		this.y = y;
		this.isPause = true;
	}
	public Emmiter(){
		this.isPause = true;
	}
	
	public void launch(){};
	
	public abstract void draw(Graphics g);
	
	
}
