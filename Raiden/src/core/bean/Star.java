package core.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import core.config.Constants;
import core.util.ImageUtil;

public class Star extends GameObject {
	static ArrayList<Image> imgs = new ArrayList<Image>(); // 图片序列
	float count; // 图片计数
	float G = 9.8f;
	float V = 7; // 初速度
	float t = 0;
	boolean isDown = false;
	static {
		for (int i = 1; i <= 9; i++) {
			imgs.add(ImageUtil.getImage("image/img_group/star/" + i + ".png"));
		}
	}

	public void draw(Graphics g) {
		if (count < imgs.size()) {
			g.drawImage(imgs.get((int) count), (int) x, (int) y, null);
			count += Constants.StarRotateSpeed;
		} else {
			count = 0;
		}
	}

	public Star(double x, double y) {
		super(imgs.get(0), x, y);
		this.x = x - imgs.get(0).getWidth(null) / 2;
		this.y = y - imgs.get(0).getHeight(null) / 2;
	}

	@Override
	public void move() {
		t += 0.08;
		y -= V * t - (G * (t * t)) / 2;

	}

}
