package core.bean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import core.config.Constants;
import core.config.GameConfig;
import core.emmiter.EnemyPlaneEmmiter;
import core.factory.SoundFactory;
import core.ui.MyPlayingPanel;
import core.util.ImageUtil;
import core.util.ThreadUtil;

public class Bigbang {
	Image bang;
	Image border;
	int x, y;
	int count; // 炸弹数量
	int x1, y1;
	MyPlayingPanel playPanel;
	boolean isKill;

	public Bigbang(int x, int y, MyPlayingPanel playPanel) {
		this.x = x;
		this.y = y;
		this.playPanel = playPanel;
		bang = ImageUtil.getImage("image/img_ui13.png");
		border = ImageUtil.getImage("image/img_ui25.png");
		count = 3;
		x1 = x + border.getWidth(null);
		y1 = y + border.getHeight(null);
		isKill = true;
	}

	public void draw(Graphics g) {
		g.drawImage(bang, x, y, null);
		g.drawImage(border, x - 10, y - 10, null);
		g.setColor(new Color(207, 57, 0));
		g.fillRoundRect(x1 - 35, y1 - 20, 20, 20, 20, 20);
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(count), x1 - 29, y1 - 5);
	}

	public void minusCount() {
		count--;
	}

	class kill extends Thread {
		@Override
		public void run() {
			isKill = false;
			minusCount();
			for(int i=0;i<5;i++){
				SoundFactory.play("Explode3");
				playPanel.explodeEmmiter.addExplode(Constants.FrameWidth*Math.random(),(Constants.FrameHeight*0.7)*Math.random(),2);// 添加爆炸
			}
			for (EnemyPlane enemy : EnemyPlaneEmmiter.enemies) {
				if (enemy.lv < 5) {
					enemy.isLive = false;
					EnemyPlaneEmmiter.enemies.remove(enemy);
					playPanel.starEmmiter.addStar(enemy.getX() + enemy.getWidth() / 2, enemy.getY(), enemy.lv);
					playPanel.score.addScore((int) (Math.pow(6, enemy.lv)));
					playPanel.myPlane.destroyEnemyCount++;
					if (playPanel.myPlane.destroyEnemyCount >= playPanel.targetScore) {
						playPanel.over();
						return;
					}
				}
			}
			ThreadUtil.sleep(Constants.BigBangTime);
			isKill = true;
		}
	}

	public void kill(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_CONTROL)&&count>0) {
			if (isKill) {
				new kill().start();
			}
		}
	}
	
	public void killAll(){
		new killAll().start();
	}
	
	class killAll extends Thread {	//清除全部敌机
		@Override
		public void run() {
			for(int i=0;i<8;i++){
				SoundFactory.play("Explode3");
				playPanel.explodeEmmiter.addExplode(Constants.FrameWidth*Math.random(),(Constants.FrameHeight*0.7)*Math.random(),2);// 添加爆炸
			}
			EnemyPlaneEmmiter.enemies.clear();
		}
	
	}

}
