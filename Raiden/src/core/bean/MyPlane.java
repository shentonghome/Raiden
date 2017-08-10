package core.bean;

import java.awt.event.KeyEvent;
import core.config.Constants;

public class MyPlane extends Plane {

	int count;
	boolean isStartAnimation = true;
	boolean isEndAnimation = false;
	public int destroyEnemyCount;

	public MyPlane(String path, double x, double y, int HP, double speed) {
		super(path, x, y, HP);
		this.speed = speed;
		this.isParticle = true;
		this.degree = -Math.PI * 1.5;
		this.destroyEnemyCount = 0;
	}

	public void press(KeyEvent e) {
		if (!isEndAnimation) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_UP:
				up = true;
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				break;
			case KeyEvent.VK_SPACE:
				isFire = true;
				if (Bemmiter == null && Bremover == null) {
					Bemmiter = new BulletEmmiter();
					Bremover = new BulletRemover();
					Bemmiter.start();
					Bremover.start();
				}
				break;
			}
		}
	}

	public void relase(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_SPACE:
			isFire = false;
			break;
		}
	}

	@Override
	public void move() {

		startAnimation();
		if (left && x >= 0) {
			if (isParticle) {
				Pemmiter.x -= speed;
			}
			x -= speed;
		}
		if (right && x <= Constants.FrameWidth - width) {
			if (isParticle) {
				Pemmiter.x += speed;
			}
			x += speed;
		}
		if (up && y >= 0 || isEndAnimation) {
			if (isParticle) {
				Pemmiter.y -= speed;
				Pemmiter.up = true;
			}
			y -= speed;
		}
		if (down && y <= Constants.FrameHeight - height) {
			if (isParticle) {
				Pemmiter.y += speed;
				Pemmiter.down = true;
			}
			y += speed;
		}
	}

	public void startAnimation() { // 开始动画
		if (isStartAnimation) {
			count++;
			int maxDistance = 200;
			if (count * speed <= maxDistance) {
				up = true;
			} else {
				up = false;
				isStartAnimation = false;
				count = 0;
			}
		}
	}

	public void endAnimation() {// 结束动画
		isEndAnimation = true;
	}

}
