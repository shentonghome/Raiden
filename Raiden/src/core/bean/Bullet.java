package core.bean;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import core.config.Constants;

public class Bullet extends GameObject {
	double degree;
	public int hert;

	public Bullet(double x, double y, double degree) {
		this.x = x;
		this.y = y;
		this.degree = degree;
		this.speed = Constants.BulletSpeed;
		height = Constants.BulletSize;
		width = height;
		hert = Constants.Bullet1Hert;
	}

	public Bullet(Image img, double x, double y, double degree, int speed, int hert) {
		super(img, x - img.getWidth(null) / 2, y - img.getHeight(null));
		this.degree = degree;
		this.speed = speed;
		this.hert = hert;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform trans = g2d.getTransform();
		g2d.rotate(degree + Math.PI / 2, getX() + getWidth() / 2, getY() + getHeight() / 2); // 旋转
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 去锯齿
		g.drawImage(img, (int) x, (int) y, null); // 画子弹
		g2d.setTransform(trans); // 恢复画笔

	}

	public void move() { // 移动
		x += speed * Math.cos(degree);
		y += speed * Math.sin(degree);
	}

}
