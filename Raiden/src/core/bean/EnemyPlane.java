package core.bean;

import java.awt.Image;

import core.config.Constants;
import core.config.GameConfig;
import core.factory.BulletFactory;
import core.factory.SoundFactory;
import core.util.ImageUtil;
import core.util.ThreadUtil;

public class EnemyPlane extends Plane {
	public String moveType;
	public int lv;
	int time;
	BulletFactory bf;
	public int time_die;

	public EnemyPlane(Image img, double x, double y, int HP, int lv) {
		super(img, x, y, HP);
		this.lv = lv;
		bf = new BulletFactory(width);
		parperedEmmit();
	}

	public void move() {
		x += speed * Math.cos(degree);
		y += speed * Math.sin(degree);
		ifDiethantimeGo(); // 如果敌机死亡则开始计时
	}

	public void parperedEmmit() {
		// System.out.println("当前难度:"+GameConfig.currentDifficulty);
		// System.out.println("min:"+GameConfig.minEnemyEmmitBulletLv);
		// System.out.println("emmit:"+GameConfig.EnemyBulletEmmiterFrequence);
		if (lv >= GameConfig.minEnemyEmmitBulletLv) {
			new autoEmmit().start();
			new BulletRemover().start();
			new UpdateBullet().start();

		}
	}

	public void emmitBullet() {
		int x1 = (int) (x + width / 2);
		int y1 = (int) (y + height);
		int rx = (int) (x + width / 2);
		int ry = (int) (y + width / 2);
		int x2 = (int) ((x1 - rx) * Math.cos(degree) - (y1 - ry) * Math.sin(degree) + rx);
		int y2 = (int) ((x1 - rx) * Math.sin(degree) + (y1 - ry) * Math.cos(degree) + ry);
		if(lv>=5){
			for (Bullet b : bf.EnemyLv2(x2, y2, degree)) {
				bullets.add(b);
			}
		}else{
			for (Bullet b : bf.EnemyLv1(x2, y2, degree)) {
				bullets.add(b);
			}
		}
	}

	class autoEmmit extends Thread {// 自动发射
		public void run() {
			while (isLive && GameConfig.isExit) {
				time++;
				if (isPause) {
					if (time % 2 == 0 && isLive) {
						emmitBullet();
					}
				}
				int y = (int) Math.round((-100 * lv + GameConfig.EnemyBulletEmmiterFrequence));//不同等级的敌机发射频率不同
				ThreadUtil.sleep(y);
			}
		}
	}

	public void ifDiethantimeGo() {
		if (!isLive) {
			time_die++;
		}
	}

}
