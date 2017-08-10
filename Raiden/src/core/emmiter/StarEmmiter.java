package core.emmiter;

import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import core.bean.EnemyPlane;
import core.bean.Star;
import core.config.Constants;
import core.config.GameConfig;
import core.emmiter.EnemyPlaneEmmiter.emmit;
import core.emmiter.EnemyPlaneEmmiter.remove;
import core.emmiter.EnemyPlaneEmmiter.update;
import core.util.ThreadUtil;

public class StarEmmiter extends Emmiter {
	public List<Star> stars;

	public StarEmmiter() {
		stars = new CopyOnWriteArrayList<Star>();
	}

	public void addStar(double x, double y,int count) {
		for(int i=0;i<count;i++){
			Star s = new Star(x, y);
			stars.add(s);
			ThreadUtil.sleep(Constants.StarFrequence);
		}
	}
	
	@Override
	public void launch() {
		new update().start();
		new remove().start();
	}

	@Override
	public void draw(Graphics g) {
		for (Star s : stars) {
			s.draw(g);
//			if (s.isOver) {
//				explodes.remove(e);
//			}
		}
	}
	
	class update extends Thread {
		public void run() {

			while (GameConfig.isExit) {
				if (GameConfig.isPause) {
					for (Star star : stars) {
						star.move();
					}
				}
				ThreadUtil.sleep(Constants.RepaintFrequence);
			}

		}
	}

	class remove extends Thread {
		public void run() {
			while (GameConfig.isExit) {
				if (GameConfig.isPause) {
					for (Star star : stars) {
						if (star.getY() >= Constants.FrameHeight) {
							stars.remove(star);
						}
					}
				}
				ThreadUtil.sleep(Constants.StarRemoveFrequence);
			}
		}
	}

}
