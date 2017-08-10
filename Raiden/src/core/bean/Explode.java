package core.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import core.config.Constants;
import core.util.ImageUtil;

public class Explode extends GameObject {
	static ArrayList<Image> imgs0 = new ArrayList<Image>(); // 微爆炸图片序列
	static ArrayList<Image> imgs1 = new ArrayList<Image>(); // 小爆炸图片序列
	static ArrayList<Image> imgs2 = new ArrayList<Image>(); // 大爆炸图片序列
	float count0; // 图片计数0
	float count1; // 图片计数1
	float count2; // 图片计数2
	public boolean isOver;
	int type;

	public void init() {
		if (type == 1) {
			if (imgs1.size() == 0) {
				for (int i = 1; i <= 7; i++) {
					imgs1.add(ImageUtil.getImage("image/particle/smallExplode/" + i + ".png"));
				}
			}
		} else if(type==2){
			if (imgs2.size() == 0) {
				for (int i = 1; i <= 15; i++) {
					imgs2.add(ImageUtil.getImage("image/particle/bigExplode/" + i + ".png"));
				}
			}
		}else if(type==0){
			if (imgs0.size() == 0) {
				for (int i = 1; i <= 6; i++) {
					imgs0.add(ImageUtil.getImage("image/particle/microExplode/" + i + ".png"));
				}
			}
		}

	}

	public void draw(Graphics g) {
		if (type == 1) {
			if (count1 < imgs1.size()) {
				g.drawImage(imgs1.get((int) count1), (int) x, (int) y, null);
				count1 += Constants.ExplodeSpeed;
			} else {
				isOver = true;
			}
		} else if(type==2){
			if (count2 < imgs2.size()) {
				g.drawImage(imgs2.get((int) count2), (int) x, (int) y, null);
				count2 += Constants.ExplodeSpeed;
			} else {
				isOver = true;
			}
		}else if(type==0){
			if (count0 < imgs0.size()) {
				g.drawImage(imgs0.get((int) count0), (int) x, (int) y, null);
				count0 += Constants.ExplodeSpeed;
			} else {
				isOver = true;
			}
		}

	}

	public Explode(double x, double y, int type) {
		this.type = type;
		init();
		if (type == 1) {
			this.x = x - imgs1.get(0).getWidth(null) / 2;
			this.y = y - imgs1.get(0).getHeight(null) / 2;
		} else if(type==2){
			this.x = x - imgs2.get(0).getWidth(null) / 2;
			this.y = y - imgs2.get(0).getHeight(null) / 2;
		}else if(type==0){
			this.x = x - imgs0.get(0).getWidth(null) / 2;
			this.y = y - imgs0.get(0).getHeight(null) / 2;
		}

		isOver = false;
	}

	@Override
	public void move() {

	}

}
