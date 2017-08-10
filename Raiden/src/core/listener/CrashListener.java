package core.listener;

import java.util.List;

import core.bean.Bullet;
import core.bean.EnemyPlane;
import core.bean.MyPlane;
import core.bean.Star;
import core.config.Constants;
import core.config.GameConfig;
import core.emmiter.EnemyPlaneEmmiter;
import core.factory.SoundFactory;
import core.ui.MyPlayingPanel;
import core.util.ThreadUtil;

public class CrashListener extends Thread {
	List<EnemyPlane> enemies;
	List<Bullet> myBullets;
	List<Star> stars;
	MyPlayingPanel playPanel;
	public boolean isPause;

	public CrashListener(MyPlayingPanel playPanel) {
		this.playPanel = playPanel;
		isPause = true;
	}

	public void run() {
		while (playPanel.myPlane.isLive) {
			if (GameConfig.isPause) {
				enemies = EnemyPlaneEmmiter.enemies;
				myBullets = playPanel.myPlane.bullets;
				loop1:for (EnemyPlane enemy : enemies) {
					if(enemy.isLive){
						if (playPanel.myPlane.getRect().intersects(enemy.getRect())) { // 玩家与敌机碰撞
							playPanel.myPlane.HP -= enemy.lv; // 伤害值与等技数对应
							playPanel.hp.setHP((float) playPanel.myPlane.HP / (float) playPanel.myPlane.dhp);
							if (playPanel.myPlane.HP <= 0) {
								playPanel.myPlane.isLive = false;
								playPanel.explodeEmmiter.addExplode(playPanel.myPlane.getX(), playPanel.myPlane.getY(),1);
								SoundFactory.play("Explode3");
								playPanel.over(); // 失败退出
								return;
							}
						}
						for (Bullet mb : myBullets) {
							if (enemy.getRect().intersects(mb.getRect())) { // 敌机与玩家子弹碰撞
								SoundFactory.play("BeShut");
								playPanel.explodeEmmiter.addExplode(mb.getX() + mb.getWidth() / 2, mb.getY(),0);
								enemy.HP -= mb.hert; // 子弹伤害
								myBullets.remove(mb);
								if (enemy.HP <= 0) {
									enemy.isLive = false;
//									enemies.remove(enemy);	//删除敌机
									if(enemy.lv>0&&enemy.lv<=3){
										SoundFactory.play("Explode1");
										playPanel.explodeEmmiter.addExplode(mb.getX() + mb.getWidth() / 2, mb.getY(),1);
									}else if(enemy.lv>3&&enemy.lv<=5){
										SoundFactory.play("Explode2");
										playPanel.explodeEmmiter.addExplode(mb.getX() + mb.getWidth() / 2, mb.getY(),2);
									}else {
										SoundFactory.play("Explode3");
										playPanel.explodeEmmiter.addExplode(mb.getX() + mb.getWidth() / 2, mb.getY(),2);
									}
									playPanel.starEmmiter.addStar(enemy.getX() + enemy.getWidth() / 2, enemy.getY(),enemy.lv);
									playPanel.score.addScore((int) (Math.pow(6, enemy.lv)));
									playPanel.myPlane.destroyEnemyCount++;
									if(playPanel.myPlane.destroyEnemyCount>=playPanel.targetScore){
										playPanel.over();
										return;
									}
									break loop1;
								}

							}
						}
					}
						if (enemy.lv>=GameConfig.minEnemyEmmitBulletLv) {
							for (Bullet eb : enemy.bullets) {
								if (eb.getRect().intersects(playPanel.myPlane.getRect())) { // 敌机子弹与玩家碰撞
									playPanel.myPlane.HP -= eb.hert;
									playPanel.hp.setHP((float) playPanel.myPlane.HP / (float) playPanel.myPlane.dhp);
									if (playPanel.myPlane.HP <= 0) {
										playPanel.myPlane.isLive = false;
										playPanel.explodeEmmiter.addExplode(playPanel.myPlane.getX(),
												playPanel.myPlane.getY(), 1);
										SoundFactory.play("Explode3");
										playPanel.over(); // 失败退出
										return;
									}
								}
							} 
						}
					
				}
			}
			ThreadUtil.sleep(Constants.CrashListenerFrequence);
		}
	}
}
