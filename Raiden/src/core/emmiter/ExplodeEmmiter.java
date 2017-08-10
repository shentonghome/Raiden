package core.emmiter;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import core.bean.Explode;

public class ExplodeEmmiter extends Emmiter {
	List<Explode> explodes;

	public ExplodeEmmiter() {
		explodes = new CopyOnWriteArrayList<Explode>();
	}

	public void addExplode(double x, double y, int type) {
		Explode e = new Explode(x, y, type);
		explodes.add(e);
	}

	@Override
	public void draw(Graphics g) {
		for (Explode e : explodes) {
			e.draw(g);
			if (e.isOver) {
				explodes.remove(e);
			}
		}
	}

}
