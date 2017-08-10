package core.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.config.Constants;
import core.config.GameConfig;
import core.factory.SoundFactory;
import core.util.PlayingPanel;

/**
 * 主框架
 */
public class MainFrame extends JFrame implements ActionListener {
	LogoPanel logo;
	MenuPanel menu;
	PlayingPanel playing;
	ChoiceMap choice;
	OverPanel over;
	LoadingPanel loading;
	SettingPanel set;

	public void launch() {
		setResizable(false); // 设置不可调整大小
		setTitle(Constants.Title);
		setBounds(Constants.FramePosX, Constants.FramePosY, Constants.FrameWidth, Constants.FrameHeight);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 设置关闭模式为只触发windowclose()
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) { // 设置关闭事件
				int o = JOptionPane.showConfirmDialog(null, "退出游戏？", "Raiden", JOptionPane.YES_NO_OPTION);
				if (o == 0) {
					GameConfig.isExit = false;
					System.exit(0);
				} else {
					return;
				}
			}
		});
		initLogo();
		setVisible(true);
	}

	public void initLogo() {
		try {
			logo = new LogoPanel(this);
			setContentPane(logo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "start":
			SoundFactory.play("Btn_sound1");
			SoundFactory.loop("BGM_menu");
			choice = new ChoiceMap(this); // 关卡选择菜单
			loadPanel(choice);
			break;
		case "exit":
			SoundFactory.play("Btn_sound1");
			GameConfig.isExit = false;
			System.exit(0);
		case "first":
			initloading(1);
			break;
		case "second":
			initloading(2);
			break;
		case "third":
			initloading(3);
			break;
		case "forth":
			initloading(4);
			break;
		case "fifth":
			initloading(5);
			break;
		case "menu":
			SoundFactory.play("Btn_sound1");
			SoundFactory.stop("BGM1");
			SoundFactory.stop("BGM2");
			SoundFactory.stop("BGM3");
			SoundFactory.stop("BGM4");
			SoundFactory.stop("BGM5");
			SoundFactory.loop("BGM_menu");
			menu = new MenuPanel(this);
			loadPanel(menu);
			break;
		case "over":
			SoundFactory.stop("BGM1");
			SoundFactory.stop("BGM2");
			SoundFactory.stop("BGM3");
			SoundFactory.stop("BGM4");
			SoundFactory.stop("BGM5");
			over = new OverPanel(this, (HashMap<String, Integer>) e.getSource());
			loadPanel(over);
			break;
		case "changePlane":
			SoundFactory.play("Btn_sound1");
			ChangePlane change = new ChangePlane(this);
			loadPanel(change);
			break;
		case "init":
			SoundFactory.loop("BGM_menu");
			menu = new MenuPanel(this);
			loadPanel(menu);
			break;
		case "play":
			int maplv = e.getID();
			SoundFactory.loop("BGM" + maplv);
			playing = new MyPlayingPanel(this, "image/img_bg_level_" + maplv + ".jpg",
					GameConfig.targetScore[maplv - 1]);
			loadPanel(playing);
			playing.requestFocus(); // 获取焦点
			break;
		case "setting":
			SoundFactory.play("Btn_sound1");
			set = new SettingPanel(this);
			loadPanel(set);
			break;
		}
		

	}

	public void initloading(int mapLv) {	//加载界面
		SoundFactory.play("Btn_sound1");
		SoundFactory.stop("BGM_menu");
		repaint();
		loading = new LoadingPanel(this, mapLv);
		setContentPane(loading); // 加载界面
	}
	
	public void loadPanel(JPanel jp){
		repaint();
		remove(getContentPane());
		setContentPane(jp); // 设置主菜单
	}

}
