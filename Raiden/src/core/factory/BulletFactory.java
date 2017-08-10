package core.factory;

import java.awt.Image;
import java.util.ArrayList;

import core.bean.Bullet;
import core.config.Constants;
import core.util.ImageUtil;

public class BulletFactory {
	double x, y;
	Image b01 = ImageUtil.getImage("image/img_group/img_bullet/img_bullet20.png"); // 敌机子弹1
	Image b02 = ImageUtil.getImage("image/img_group/img_bullet/img_bullet61.png"); // 敌机子弹2
	Image b1 = ImageUtil.getImage("image/img_group/img_bullet/img_bullet14.png");
	Image b2 = ImageUtil.getImage("image/img_group/img_bullet/img_bullet63.png");
	Image b3 = ImageUtil.getImage("image/img_group/img_bullet/img_bullet91.png");
	Image b4 = ImageUtil.getImage("image/img_group/img_bullet/img_bullet98.png");
	Image b5 = ImageUtil.getImage("image/img_group/img_bullet/img_bullet99.png");
	Image b6 = ImageUtil.getImage("image/img_group/img_bullet/img_bullet15.png");
	int planeWidth;

	public BulletFactory(int planeWidth) {
		this.planeWidth = planeWidth;
	}

	public ArrayList<Bullet> myplaneLv1(double x, double y) {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		bullets.add(new Bullet(b1, x + planeWidth / 2, y, Constants.MyBulletDegree, Constants.BulletSpeed,
				Constants.Bullet1Hert));
		return bullets;
	}

	public ArrayList<Bullet> myplaneLv2(double x, double y) {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		bullets.add(new Bullet(b1, x + planeWidth / 8, y, Constants.MyBulletDegree, Constants.BulletSpeed,
				Constants.Bullet1Hert));
		bullets.add(new Bullet(b1, x + (planeWidth / 8) * 3, y, Constants.MyBulletDegree, Constants.BulletSpeed,
				Constants.Bullet1Hert));
		bullets.add(new Bullet(b1, x + (planeWidth / 8) * 5, y, Constants.MyBulletDegree, Constants.BulletSpeed,
				Constants.Bullet1Hert));
		bullets.add(new Bullet(b1, x + (planeWidth / 8) * 7, y, Constants.MyBulletDegree, Constants.BulletSpeed,
				Constants.Bullet1Hert));
		bullets.add(new Bullet(b2, x + (planeWidth / 4), y - 10, Constants.MyBulletDegree, Constants.BulletSpeed,
				Constants.Bullet2Hert));
		bullets.add(new Bullet(b2, x + (planeWidth / 2), y - 20, Constants.MyBulletDegree, Constants.BulletSpeed,
				Constants.Bullet2Hert));
		bullets.add(new Bullet(b2, x + (planeWidth / 4) * 3, y - 10, Constants.MyBulletDegree, Constants.BulletSpeed,
				Constants.Bullet2Hert));
		return bullets;
	}

	public ArrayList<Bullet> myplaneLv3(double x, double y) {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		bullets.add(new Bullet(b3, x + planeWidth / 4, y, Constants.MyBulletDegree, Constants.BulletSpeed,
				Constants.Bullet3Hert));
		bullets.add(new Bullet(b3, x + (planeWidth / 4) * 3, y, Constants.MyBulletDegree, Constants.BulletSpeed,
				Constants.Bullet3Hert));
		return bullets;
	}

	public ArrayList<Bullet> myplaneLv4(double x, double y) {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();

		bullets.add(new Bullet(b4, x + (planeWidth / 5) * 1, y, (Math.PI * 25) / 18, Constants.BulletSpeed,
				Constants.Bullet4Hert));
		bullets.add(new Bullet(b4, x + (planeWidth / 2), y, (Math.PI * 3) / 2, Constants.BulletSpeed,
				Constants.Bullet4Hert));
		bullets.add(new Bullet(b4, x + (planeWidth / 5) * 4, y, (Math.PI * 29) / 18, Constants.BulletSpeed,
				Constants.Bullet4Hert));
		bullets.add(new Bullet(b5, x, y, (Math.PI * 11) / 9, Constants.BulletSpeed, Constants.Bullet3Hert));
		bullets.add(
				new Bullet(b5, x + planeWidth, y, (Math.PI * 16) / 9, Constants.BulletSpeed, Constants.Bullet3Hert));
		return bullets;
	}
	
	public ArrayList<Bullet> myplaneLv5(double x, double y) {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		for(int i=0;i<=20;i++){
			double degree = Math.PI+Math.PI*Math.random();
			bullets.add(new Bullet(b6, x + (planeWidth / 2), y, degree, Constants.BulletSpeed,
					Constants.Bullet5Hert));
		}
		return bullets;
	}

	public ArrayList<Bullet> EnemyLv1(double x, double y, double degree) {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		bullets.add(new Bullet(b01, x, y, degree, Constants.EnemyBulletSpeed, Constants.EnemyBulletHert1));
		return bullets;
	}
	
	public ArrayList<Bullet> EnemyLv2(double x, double y, double degree) {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		bullets.add(new Bullet(b02, x, y, degree, Constants.EnemyBulletSpeed, Constants.EnemyBulletHert2));
		return bullets;
	}
}
