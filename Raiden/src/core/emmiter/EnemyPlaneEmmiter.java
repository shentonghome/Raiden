package core.emmiter;

import java.awt.Graphics;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import core.bean.EnemyPlane;
import core.bean.Plane;
import core.bean.Score;
import core.config.Constants;
import core.config.GameConfig;
import core.factory.EnemyPlaneFactory;
import core.factory.SoundFactory;
import core.util.MoveController;
import core.util.ThreadUtil;

public class EnemyPlaneEmmiter extends Emmiter {
	public static List<EnemyPlane> enemies = new CopyOnWriteArrayList<EnemyPlane>();
	EnemyPlane enemy;
	MoveController mc;
	static ArrayList<String> methodList;
	String[] methods = { "down", "left", "right", "plus360", "minus360", "plus30", "minus30", "plus60", "minus60",
			"plus405", "minus405" };
	public boolean isOver;

	public EnemyPlaneEmmiter(double x, double y) {
		super();
		mc = new MoveController();
		isOver = false;
	}

	@Override
	public void launch() {
		new emmit().start();
		new update().start();
		new remove().start();
	}

	public void draw(Graphics g) {
		if(!isOver){
			for (Plane enemy : enemies) {
				enemy.draw(g);
			}
		}

	}

	class emmit extends Thread {
		public void run() {
			ThreadUtil.sleep(Constants.EnemyEmmiterDelayedTime);
			while (GameConfig.isExit||!isOver) {
				double random = Math.random();
				int Emmitfrequence = (int) (GameConfig.max_minEEnemyEmmiterFrequence[0]
						+ GameConfig.max_minEEnemyEmmiterFrequence[1] * random);
				if (GameConfig.isPause) {
					new addEnemyLine().start();
				}
				ThreadUtil.sleep(Emmitfrequence);
			}
		}

	}

	class update extends Thread {
		public void run() {

			while (GameConfig.isExit) {
				if (GameConfig.isPause) {
					for (EnemyPlane enemy : enemies) {
						enemy.move();
						enemy.degree = call(enemy.moveType, enemy.degree);
					}
				}
				ThreadUtil.sleep(Constants.RepaintFrequence);
			}

		}
	}

	class remove extends Thread {
		public void run() {
			while (GameConfig.isExit) {
				if (GameConfig.isPause) {
					for (EnemyPlane enemy : enemies) {
						if (enemy.getY() >= Constants.FrameHeight+200) {
							enemies.remove(enemy);
						} else if (enemy.getX() <= -200) {
							enemies.remove(enemy);
						} else if (enemy.getX() >= Constants.FrameWidth + 200) {
							enemies.remove(enemy);
						} else if (enemy.getY() <= -200) {
							enemies.remove(enemy);
						}else if(enemy.time_die*Constants.RepaintFrequence>=Constants.EnemyRemoveAlfterDie){
							enemies.remove(enemy);
						}
					}
				}
				ThreadUtil.sleep(Constants.EnemyRemoveFrequence);
			}
		}
	}

	private double call(String methodName, double param) {
		try {
			Method m = MoveController.class.getDeclaredMethod(methodName, double.class);
			double degree = (double) m.invoke(mc, param);
			return degree;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	class addEnemyLine extends Thread {
		public addEnemyLine() {
		}

		public void run() {
			double random = Math.random();
			double random1 = Math.random();
			double random2 = (methods.length) * Math.random();
			double random3 = Math.random();
			if (GameConfig.isPause) {
				int MaxLv = 0;
				int score = Score.score;
				int[] ScoreLv = GameConfig.ScoreLv;
				if (score >= ScoreLv[0] && score < ScoreLv[1]) { // 设置敌机最高等级
					MaxLv = 1;
				} else if (score >= ScoreLv[1] && score < ScoreLv[2]) {
					MaxLv = 2;
				} else if (score >= ScoreLv[2] && score < ScoreLv[3]) {
					MaxLv = 3;
				} else if (score >= ScoreLv[3] && score < ScoreLv[4]) {
					MaxLv = 4;
				} else if (score >= ScoreLv[4] && score < ScoreLv[5]) {
					MaxLv = 5;
				} else if (score >= ScoreLv[5] && score < ScoreLv[6]) {
					MaxLv = 6;
				} else if (score >= ScoreLv[6]) {
					MaxLv = 7;
				}
				int lv = 1 + (int) Math.round(random * MaxLv); // 敌机等级控制
				// System.out.println("等级："+lv+"\n随机数："+random);
				x = -100 + ((Constants.FrameWidth + 100) * random1);// 敌机出现位置X
				double maxY = -20;
				y = maxY;
				if (x <= 0) {
					maxY = Constants.FrameHeight / 3;
					y = Math.random() * maxY; // 敌机出现位置Y
				}

				if (lv > 3) {
					SoundFactory.play("FlySomePlanes");
				}

				int MemberCount = (int) (Constants.EnemyMaxMemberCount * Math.random());
				boolean isEmmit = true;
				if (lv >= 5) {
					MemberCount = 1;
					int moreThan5lvCount = 0;
					for (EnemyPlane enemy : EnemyPlaneEmmiter.enemies) {
						if (enemy.lv >= 5) {
							moreThan5lvCount++;
						}
					}
					if (moreThan5lvCount >= 2) { // 5级以上的飞机如果存在2架以上，则不再发射
						isEmmit = false;
					}
				}

				if (isEmmit) {
					for (int i = 0; i < MemberCount; i++) {
						enemy = EnemyPlaneFactory.getEnemyPlane(lv, x, y); // 获取敌机
						try {
							enemy.degree = Math.PI * random3; // 设置初始角度
							enemy.moveType = methods[(int) Math.round(random2)]; // 设置移动类型
							enemy.speed = GameConfig.EnemiesSpeed[lv - 1]; // 设置敌机初速度
							if (5 <= lv && lv < 7) {
								enemy.setY(-100);
								enemy.moveType = methods[0]; // 设置移动类型
							} else if (lv >= 7) {
								enemy.moveType = methods[0]; // 设置移动类型
							}
							enemies.add(enemy);
						} catch (Exception e) {
							return;
						} finally {
							ThreadUtil.sleep(500);
						}
					}
				}
			}
		}
	}

}
