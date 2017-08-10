package core.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import core.bean.Background;
import core.bean.Bigbang;
import core.bean.EnemyPlane;
import core.bean.HP;
import core.bean.MyPlane;
import core.bean.Score;
import core.config.Constants;
import core.config.GameConfig;
import core.emmiter.CloudEmmiter;
import core.emmiter.EnemyPlaneEmmiter;
import core.emmiter.ExplodeEmmiter;
import core.emmiter.StarEmmiter;
import core.factory.ButtonFactory;
import core.factory.SoundFactory;
import core.listener.CrashListener;
import core.listener.StarCrashListener;
import core.util.ImageUtil;
import core.util.PlayingPanel;
import core.util.ThreadUtil;

public class MyPlayingPanel extends PlayingPanel {
	public Background bg; // 背景对象
	public MyPlane myPlane; // 玩家飞机
	public JButton pause; // 暂停键
	public CloudEmmiter cloudEmmiter; // 云发生器
	public EnemyPlaneEmmiter enemyEmmiter; // 敌机发生器
	public ExplodeEmmiter explodeEmmiter; // 爆炸发生器
	public StarEmmiter starEmmiter;// 星星发生器
	public CrashListener crashListener; // 碰撞检测器
	public StarCrashListener starCrashListener; // 星星碰撞检测
	public Score score; // 分数显示
	public HP hp; // 生命值显示
	public Bigbang bigbang;// 炸弹显示
	ActionListener Listener;
	public int targetScore;
	boolean isSuccess;
	boolean isOver;

	public MyPlayingPanel(ActionListener Listener, String mapLevel_str, int targetScore) {
		GameConfig.isExit = true;
		GameConfig.isPause = true;
		this.Listener = Listener;
		this.targetScore = targetScore;
		bg = new Background(mapLevel_str);
		cloudEmmiter = new CloudEmmiter();

		myPlane = new MyPlane(GameConfig.myPlaneConfig[0], Constants.FrameWidth / 2 - 50, Constants.FrameHeight,
				Integer.valueOf(GameConfig.myPlaneConfig[1]), Double.valueOf(GameConfig.myPlaneConfig[2]));
		myPlane.launch();
		enemyEmmiter = new EnemyPlaneEmmiter(0, 400);
		enemyEmmiter.launch();
		explodeEmmiter = new ExplodeEmmiter();
		starEmmiter = new StarEmmiter();
		starEmmiter.launch();
		score = new Score(Constants.FrameWidth - 100, 15);
		hp = new HP(15, 15);
		bigbang = new Bigbang(15, (int) (Constants.FrameHeight / 3), this);

		pause = ButtonFactory.getButton("pause"); // 暂停键
		add(pause);
		pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SoundFactory.play("Btn_sound1");
				pause();
			}
		});
		launch();
	}

	@Override
	public void launch() {
		super.launch();
		addKeyListener(this);
		crashListener = new CrashListener(this);
		crashListener.start();
		starCrashListener = new StarCrashListener(this);
		starCrashListener.start();
		SoundFactory.play("ReadGo");
	}

	@Override
	public void paintComponent(Graphics g) {
		bg.draw(g);
		cloudEmmiter.draw(g);
		enemyEmmiter.draw(g);
		myPlane.draw(g);
		explodeEmmiter.draw(g);
		starEmmiter.draw(g);
		score.draw(g);
		hp.draw(g);
		bigbang.draw(g);
		drawMessionComplete(g); // 任务完成
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!isOver) {
			myPlane.press(e);
			bigbang.kill(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		myPlane.relase(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void pause() {
		GameConfig.isPause = false;
		addMenu();
	}

	public void play() {
		GameConfig.isPause = true;
		this.requestFocus();
	}

	public void addMenu() {
		Object[] options = { "继续游戏", "回到目录" };
		int o = JOptionPane.showOptionDialog(null, "暂停中……", "Raiden", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		SoundFactory.play("Btn_sound1");
		if (o != 1) {
			play();
		} else {
			GameConfig.isExit = false;
			ActionEvent e = new ActionEvent(new Object(), 1, "menu");
			Listener.actionPerformed(e);
			EnemyPlaneEmmiter.enemies.clear();// 删除剩下的飞机
		}
	}

	public void over() {
		isOver = true;
		enemyEmmiter.isOver = true;
		if (myPlane.destroyEnemyCount >= targetScore) {
			bigbang.killAll();	//来场大爆炸
			enemyEmmiter.isOver = true;
			SoundFactory.play("StateClear");
			ThreadUtil.sleep(3000);
			isSuccess = true;
			myPlane.endAnimation();// 结束动画
			ThreadUtil.sleep(2000);
		} else {

			ThreadUtil.sleep(2000);
		}
		GameConfig.isExit = false;
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		result.put("score", Score.score);
		result.put("destroyCount", myPlane.destroyEnemyCount);
		result.put("targetScore", targetScore);
		EnemyPlaneEmmiter.enemies.clear(); // 删除剩下的飞机
		ActionEvent e = new ActionEvent(result, 1, "over");
		Listener.actionPerformed(e);
	}

	public void drawMessionComplete(Graphics g) { // 目标完成
		if (isSuccess) {
			Image successImg = ImageUtil.getImage("image/img_group/ui_new_word_png/ui_new_word_png9.png");
			g.drawImage(successImg, Constants.FrameWidth / 7, Constants.FrameHeight / 3, null);
		}
	}

	// public void

}
