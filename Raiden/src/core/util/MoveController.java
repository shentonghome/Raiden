package core.util;

import core.config.Constants;

public class MoveController {

	double targetDegree;
	public boolean isTarget;

	public MoveController() {
		isTarget = false;
	}

	public double down(double degree) {
		degree = Math.PI / 2;
		return degree;
	}

	public double up(double degree) {
		degree = Math.PI * 1.5;
		return degree;
	}

	public double left(double degree) {
		degree = Math.PI;
		return degree;
	}

	public double right(double degree) {
		degree = 0;
		return degree;
	}

	public double remote(double degree, double detal) {
		if (isTarget) {
			if (detal > 0) {
				if (degree <= targetDegree) {
					degree += Constants.EnemyDegreeDetal;
				}
				return degree;
			} else {
				if (degree >= targetDegree) {
					degree -= Constants.EnemyDegreeDetal;
				}
				return degree;
			}
		} else {
			isTarget = true;
			targetDegree = degree + detal;
			return degree;
		}
	}

	public double plus405(double degree) {
		degree = remote(degree, Math.PI * (9d / 4d));
		return degree;
	}

	public double minus405(double degree) {
		degree = remote(degree, -Math.PI * (9d / 4d));
		return degree;
	}

	public double plus30(double degree) {
		degree = remote(degree, Math.PI * (1d / 6d));
		return degree;
	}

	public double minus30(double degree) {
		degree = remote(degree, -Math.PI * (1d / 6d));
		return degree;
	}
	
	public double plus60(double degree) {
		degree = remote(degree, Math.PI * (1d / 3d));
		return degree;
	}
	
	public double minus60(double degree) {
		degree = remote(degree, -Math.PI * (1d / 3d));
		return degree;
	}
	
	public double plus360(double degree) {
		degree = remote(degree, Math.PI * 2);
		return degree;
	}
	
	public double minus360(double degree) {
		degree = remote(degree, -Math.PI * 2);
		return degree;
	}

}
