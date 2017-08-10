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
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import core.config.Constants;
import core.factory.ButtonFactory;
import core.factory.SoundFactory;
import core.util.ImageUtil;
import core.util.UIPanel;

public class OverPanel extends UIPanel {
	JLabel message;
	JButton back;
	HashMap<String, Integer> result;

	public OverPanel(ActionListener Listener, HashMap<String, Integer> result) {
		this.result = result;
		this.Listener = Listener;
		setSize(Constants.FrameWidth, Constants.FrameHeight);
		launch();
	}

	public void launch() {
		title = new JLabel();
		
		String messageStr = "<html>得分: "+result.get("score")+"<br>"+"摧毁敌机： "+result.get("destroyCount")+"</html>";
		message = new JLabel(messageStr);
		back = ButtonFactory.getButton("back");
		judge();

		back.setActionCommand("start");
		back.addActionListener(Listener);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SoundFactory.stop("HappEnding");
				SoundFactory.stop("Fail");
			}
		});

		super.launch();

//		title.setFont(new Font("宋体", Font.BOLD, 50));
//		title.setForeground(Color.white);
		message.setFont(new Font("宋体", Font.BOLD, 30));
		message.setForeground(Color.white);
		add(title, constraints, 0, 1, 1, 1);
		add(message, constraints, 1, 1, 1, 1);
		add(back, constraints, 3, 2, 1, 1);
	}

	@Override
	public void drawBg(Graphics g) {
		Image bg = ImageUtil.getImage("image/fail.jpg");
		g.drawImage(bg, 0, 0, Constants.FrameWidth, Constants.FrameHeight, null);
	}

	public void judge() {
		if (result.get("destroyCount") >= result.get("targetScore")) {
			SoundFactory.play("MessionComplete");
			SoundFactory.play("HappEnding");
			ImageIcon img = new ImageIcon(LoadingPanel.class.getResource("/image/tzcg.png"));
			title.setIcon(img);
		}else{
			ImageIcon img = new ImageIcon(LoadingPanel.class.getResource("/image/tzsb.png"));
			title.setIcon(img);
			SoundFactory.play("GameOver");
			SoundFactory.play("Fail");
		}
	}

}
