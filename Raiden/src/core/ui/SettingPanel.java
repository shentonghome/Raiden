package core.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import core.config.Constants;
import core.config.GameConfig;
import core.factory.ButtonFactory;
import core.factory.SoundFactory;
import core.util.ImageUtil;
import core.util.UIPanel;

public class SettingPanel extends UIPanel {
	JButton voice;
	JButton difficulty;
	JButton back;
	int count;
	JLabel voiceText;
	JLabel difficultyText;
	static String info;
	static String voice_str = "开";

	public SettingPanel(ActionListener Listener) {
		super(Listener);
	}

	public void launch() {
		count = GameConfig.currentDifficulty;
		ImageIcon img = new ImageIcon(LoadingPanel.class.getResource("/image/yxsz.png"));
		title = new JLabel(img);
		difficulty = ButtonFactory.getButton("difficulty");
		voice = ButtonFactory.getButton("sound"); // 声音开关
		difficultyText = new JLabel(info);
		judge();// 判断
		voiceText = new JLabel(voice_str);
		voice.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SoundFactory.play("Btn_sound1");
				boolean isSound = SoundFactory.isSound;
				if (isSound) {
					voice_str = "关";
					voiceText.setText(voice_str);
					SoundFactory.stopAll();
				} else {
					voice_str = "开";
					voiceText.setText(voice_str);
					SoundFactory.isSound = true;
					SoundFactory.play("Btn_sound1");
					SoundFactory.loop("BGM_menu");
				}
			}
		});

		difficulty.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				SoundFactory.play("Btn_sound1");
				count++;
				if (count == Constants.targetScore.length) {
					count = 0;
				}
				GameConfig.currentDifficulty = count;
				GameConfig.EnemyBulletEmmiterFrequence = Constants.EnemyBulletEmmiterFrequence[count];
				GameConfig.minEnemyEmmitBulletLv = Constants.minEnemyEmmitBulletLv[count];
				GameConfig.targetScore = Constants.targetScore[count];
				GameConfig.EnemiesSpeed = Constants.EnemiesSpeed[count];
				GameConfig.BulletLv = Constants.BulletLv[count];
				GameConfig.BulletLv = Constants.BulletLv[count];
				GameConfig.max_minEEnemyEmmiterFrequence = Constants.max_minEEnemyEmmiterFrequence[count];
				judge();
			}
		});

		back = ButtonFactory.getButton("back");

		back.setActionCommand("menu");
		back.addActionListener(Listener);

		super.launch();

		title.setFont(new Font("宋体", Font.BOLD, 50));
		title.setForeground(new Color(250, 230, 50));
		voiceText.setFont(new Font("宋体", Font.BOLD, 30));
		voiceText.setForeground(new Color(250, 158, 8));
		difficultyText.setForeground(new Color(250, 158, 8));
		difficultyText.setFont(new Font("宋体", Font.BOLD, 30));

		add(title, constraints, 0, 0, 1, 1);
		add(voice, constraints, 1, 0, 1, 1);
		add(voiceText, constraints, 1, 1, 1, 1);
		add(difficulty, constraints, 2, 0, 1, 1);
		add(difficultyText, constraints, 2, 1, 1, 1);
		add(back, constraints, 3, 2, 1, 1);
	}

	public void judge() {
		switch (String.valueOf(count)) {
		case "0":
			info = "简单";
			break;
		case "1":
			info = "中等";
			break;
		case "2":
			info = "难";
			break;
		case "3":
			info = "变态";
			break;
		default:
			return;
		}
		difficultyText.setText(info);
	}
}
