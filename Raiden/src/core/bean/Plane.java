package core.bean;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import core.config.Constants;
import core.config.GameConfig;
import core.emmiter.ParticleEmmiter;
import core.factory.BulletFactory;
import core.factory.SoundFactory;
import core.util.ThreadUtil;

public class Plane extends GameObject {
	public boolean left, right, up, down; // 方向
	public int HP; // 生命值
	public List<Bullet> bullets = new CopyOnWriteArrayList<Bullet>();
	boolean isFire;
	BulletEmmiter Bemmiter;
	BulletRemover Bremover;
	public ParticleEmmiter Pemmiter;
	UpdateBullet updateB;
	public boolean isParticle;
	public int bulletLv = 1;
	public int lastBulletLv = bulletLv;
	public int starCount;
	public final int dhp;	//默认生命值

	public Plane(String path, double x, double y, int HP) {
		super(path);
		this.x = x;
		this.y = y;
		this.HP = HP;
		this.dhp = HP;
		
		isParticle = false;
		// bulletImg =
		// ImageUtil.getImage("image/img_group/img_bullet/img_bullet14.png");
		Pemmiter = new ParticleEmmiter(x + width / 2, y + height);
		new UpdateBullet().start();
	}

	public Plane(Image img, double x, double y, int HP) {
		super(img, x, y);
		this.HP = HP;
		this.dhp = HP;
		isParticle = false;
		this.down = true;
		// new UpdateBullet().start();

	}

	public void draw(Graphics g) {
		if (isLive) {
			if (isParticle) {
				Pemmiter.draw(g);
			}
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform trans = g2d.getTransform();
			g2d.rotate(degree - Math.PI / 2, getX() + getWidth() / 2, getY() + getHeight() / 2); // 旋转
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 去锯齿
			g.drawImage(img, (int) x, (int) y, null); // 画飞机
			g2d.setTransform(trans); // 恢复画笔
		}
		for (Bullet mb : bullets) {
			mb.draw(g);
		}
	}

	public class BulletEmmiter extends Thread { // 添加子弹
		@Override
		public void run() {
			BulletFactory bf = new BulletFactory(width);
			Class clazz = bf.getClass();
			while (isLive&&GameConfig.isExit) {
				if (isFire && GameConfig.isPause) {
					try {
						if(lastBulletLv!=bulletLv){
							SoundFactory.play("WeaponUP");
						}
						Method m = clazz.getDeclaredMethod("myplaneLv" + bulletLv, double.class, double.class);
						ArrayList<Bullet> bs = (ArrayList) m.invoke(bf, x, y);
						for (Bullet b : bs) {
							bullets.add(b);
						}
						lastBulletLv = bulletLv;
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				ThreadUtil.sleep(Constants.BulletEmmiterFrequence);
			}
		}
	}

	class BulletRemover extends Thread {
		public void run() {
			while (isLive&&GameConfig.isExit) {
				if (GameConfig.isPause) {
					for (Bullet mb : bullets) {
						if (mb.y <= 0) {
							mb.isLive = false;
							bullets.remove(mb);
						} else if (mb.x <= 0) {
							mb.isLive = false;
							bullets.remove(mb);
						} else if (mb.x >= Constants.FrameWidth) {
							mb.isLive = false;
							bullets.remove(mb);
						}
					}
				}
				ThreadUtil.sleep(Constants.BulletRemoveFrequence);
			}
		}
	}

	@Override
	public void move() {
	}

	class UpdateBullet extends Thread {
		public void run() {
			while (GameConfig.isExit) {
				if (GameConfig.isPause) {
					for (Bullet b : bullets) {
						b.move();
					}
				}
				ThreadUtil.sleep(Constants.RepaintFrequence);
			}
		}
	}

}
