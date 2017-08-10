package core.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import core.config.Constants;
import core.util.ImageUtil;

public class Score {
	static Image zero = ImageUtil.getImage("image/number/0.png");
	static Image one = ImageUtil.getImage("image/number/1.png");
	static Image two = ImageUtil.getImage("image/number/2.png");
	static Image three = ImageUtil.getImage("image/number/3.png");
	static Image four = ImageUtil.getImage("image/number/4.png");
	static Image five = ImageUtil.getImage("image/number/5.png");
	static Image six = ImageUtil.getImage("image/number/6.png");
	static Image seven = ImageUtil.getImage("image/number/7.png");
	static Image eight = ImageUtil.getImage("image/number/8.png");
	static Image nine = ImageUtil.getImage("image/number/9.png");
	public static int score; // 分数
	int x, y;
	static Image[]  numbers = { zero, one, two, three, four, five, six, seven, eight, nine };
	List<Integer> position = new CopyOnWriteArrayList<Integer>();
	int leng = 1;;
	
	public Score(int x, int y) {
		this.x = x;
		this.y = y;
		score = 0;
		position.add(x);
	}

	public void draw(Graphics g) {
		String scoreStr = String.valueOf(score);
		int newleng = scoreStr.length();
		for (int i = 0; i < newleng; i++) {
			if(leng!=newleng){
				x -= Constants.ScoreOffSet;
				position.add(x);
			}
			g.drawImage(numbers[(int) ((score / (Math.pow(10, i))) % 10)], position.get(i), y,30,42, null);
		}
		leng = newleng;
	}

	public void addScore(int num) {
		score += num;
	}
}
