package core.emmiter;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import core.bean.Cloud;
import core.config.Constants;
import core.config.GameConfig;
import core.util.ImageUtil;
import core.util.ThreadUtil;

public class CloudEmmiter extends Emmiter {
	static List<Cloud> clouds = new CopyOnWriteArrayList<Cloud>();
	Cloud cloud;
	Image img1;
	Image img2;
	public CloudEmmiter(){
		img1 = ImageUtil.getImage("image/img_cloud_1.png");
		img2 = ImageUtil.getImage("image/img_cloud_2.png");
		new emmit().start();
		new update().start();
		new remove().start();
		
	}
	
	public void draw(Graphics g){
		for(Cloud cloud : clouds){
			cloud.draw(g);
		}
	}
	
	class emmit extends Thread{
		public void run(){
			while (GameConfig.isExit) {
				double random = Math.random(); //随机数确定云类型
				int Emmitfrequence = (int) (2000 + 5000 * random);
				if (GameConfig.isPause) {
					if (random >= 0.5) {
						cloud = new Cloud(img1, random);
					} else {
						cloud = new Cloud(img2, random);
					}
					clouds.add(cloud);
				}
				ThreadUtil.sleep(Emmitfrequence);
			}
		}
	}
	
	class update extends Thread{
		public void run(){
			while (GameConfig.isExit) {
				if (GameConfig.isPause) {
					for (Cloud cloud : clouds) {
						cloud.move();
					} 
				}
				ThreadUtil.sleep(Constants.RepaintFrequence);
			}
		}
	}
	
	class remove extends Thread{
		public void run(){
			while (GameConfig.isExit) {
				if (GameConfig.isPause) {
					for (Cloud cloud : clouds) {
						if (cloud.getY() >= Constants.FrameHeight) {
							clouds.remove(cloud);
						}
					} 
				}
				ThreadUtil.sleep(Constants.CloudRemoveFrequence);
			}
		}
	}
}
