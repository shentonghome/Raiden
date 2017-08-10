package core.bean;

import java.awt.Graphics;
import java.awt.Image;

import core.config.Constants;
import core.config.GameConfig;
import core.util.ImageUtil;
import core.util.ThreadUtil;

public class Background {
	Image bgImage;
	
	double y1,y2;	//两张背景坐标
	public boolean isPause;
	
	
	public Background(String path){
		bgImage = ImageUtil.getImage(path);
		y2 = -bgImage.getHeight(null);
		isPause = true;
		launch();
		
	}
	
	public void launch(){
		new update().start();
	}
	
	public void draw(Graphics g){
		g.drawImage(bgImage, 0, (int)y1, null);
		g.drawImage(bgImage, 0, (int)y2, null);
	}
	
	class update extends Thread{
		public void run(){
			while (GameConfig.isExit) {
				move();
				ThreadUtil.sleep(Constants.RepaintFrequence);
			}
		}
	}
	
	public void move(){
		if (GameConfig.isPause) {
			y1 += Constants.BackGroundSpeed;
			y2 += Constants.BackGroundSpeed;
			if (y2 >= 0) {
				y1 = 0;
				y2 = -bgImage.getHeight(null);
			} 
		}
	}
	
}
