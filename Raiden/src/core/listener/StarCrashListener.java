package core.listener;

import java.util.List;

import core.bean.Star;
import core.config.Constants;
import core.config.GameConfig;
import core.factory.SoundFactory;
import core.ui.MyPlayingPanel;
import core.util.ThreadUtil;

public class StarCrashListener extends Thread {
	
	MyPlayingPanel playPanel;
	List<Star> stars;
	
	public StarCrashListener(MyPlayingPanel playPanel) {
		this.playPanel  = playPanel;
	}
	
	@Override
	public void run() {
		while (playPanel.myPlane.isLive) {
			stars = playPanel.starEmmiter.stars;
			for (Star star : stars) {
				if (star.getRect().intersects(playPanel.myPlane.getRect())) { // 星星与玩家碰撞
					SoundFactory.play("Star");
					stars.remove(star);
					playPanel.score.addScore(Constants.StarScore);
					playPanel.myPlane.starCount++;
					if (GameConfig.BulletLv[0] <= playPanel.myPlane.starCount && playPanel.myPlane.starCount < GameConfig.BulletLv[1]) {
						playPanel.myPlane.bulletLv = 2;
					} else if (GameConfig.BulletLv[1] <= playPanel.myPlane.starCount && playPanel.myPlane.starCount < GameConfig.BulletLv[2]) {
						playPanel.myPlane.bulletLv = 3;
					}else if(GameConfig.BulletLv[2] <= playPanel.myPlane.starCount && playPanel.myPlane.starCount < GameConfig.BulletLv[3]){
						playPanel.myPlane.bulletLv = 4;
					}else if(GameConfig.BulletLv[3] <= playPanel.myPlane.starCount && playPanel.myPlane.starCount < GameConfig.BulletLv[4]){
						playPanel.myPlane.bulletLv = 5;
					}
				}
			}
			ThreadUtil.sleep(Constants.CrashListenerFrequence);
		}
	}
}
