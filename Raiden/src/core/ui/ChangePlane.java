package core.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import core.bean.MyPlane;
import core.config.Constants;
import core.config.GameConfig;
import core.factory.ButtonFactory;
import core.factory.SoundFactory;
import core.util.UIPanel;

public class ChangePlane extends UIPanel {

	JButton back;
	JButton left; // 左右按钮
	JButton right;
	JLabel plane;
	static ArrayList<ImageIcon> players;
	static HashMap<ImageIcon, String[]> map;
	int currentPlaneCount = 0;

	static {
		players = new ArrayList<ImageIcon>();
		players.add(new ImageIcon(ChangePlane.class.getResource("/image/1.jpg")));
		players.add(new ImageIcon(ChangePlane.class.getResource("/image/2.jpg")));
		players.add(new ImageIcon(ChangePlane.class.getResource("/image/3.jpg")));
		players.add(new ImageIcon(ChangePlane.class.getResource("/image/4.jpg")));

		String[] p1 = { "image/img_group/img_plane_main/img_plane_main5.png", Constants.MyPlaneHP1 + "",
				Constants.MyPlaneSpeed1 + "" };
		String[] p2 = { "image/img_group/img_plane_main/img_plane_main14.png", Constants.MyPlaneHP2 + "",
				Constants.MyPlaneSpeed2 + "" };
		String[] p3 = { "image/img_group/img_plane_main/img_plane_main13.png", Constants.MyPlaneHP3 + "",
				Constants.MyPlaneSpeed3 + "" };
		String[] p4 = { "image/img_group/img_plane_main/img_plane_main6.png", Constants.MyPlaneHP4 + "",
				Constants.MyPlaneSpeed4 + "" };

		map = new HashMap<ImageIcon, String[]>();
		map.put(players.get(0), p1);
		map.put(players.get(1), p2);
		map.put(players.get(2), p3);
		map.put(players.get(3), p4);

	}

	public ChangePlane(ActionListener Listener) {
		super(Listener);
	}

	@Override
	public void launch() {
		requestFocus();
		setSize(Constants.FrameWidth, Constants.FrameHeight);
		ImageIcon img = new ImageIcon(LoadingPanel.class.getResource("/image/zjxz.png"));
		title = new JLabel(img);

		left = ButtonFactory.getButton("left");
		right = ButtonFactory.getButton("right");

		plane = new JLabel(players.get(GameConfig.currentPlane));
		SoundFactory.play("Player"+(GameConfig.currentPlane+1));

		back = ButtonFactory.getButton("back");

		back.setActionCommand("menu");
		back.addActionListener(Listener);

		GridBagLayout grid = new GridBagLayout();
		setLayout(grid);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 50;
		constraints.weighty = 50;

		title.setFont(new Font("宋体", Font.BOLD, 50));
		title.setForeground(new Color(250, 230, 50));
		add(title, constraints, 0, 1, 1, 1);
		add(left, constraints, 1, 0, 1, 1);
		add(right, constraints, 1, 6, 1, 1);
		add(plane, constraints, 1, 1, 4, 1);
		add(back, constraints, 4, 2, 1, 1);

		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lastPlane();
			}
		});

		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nextPlane();
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					lastPlane();
					break;
				case KeyEvent.VK_RIGHT:
					nextPlane();
					break;
				case KeyEvent.VK_ENTER:
					ActionEvent e1 = new ActionEvent(new Object(), 1, "back");
					Listener.actionPerformed(e1);
				}
			}
		});
	}

	public void lastPlane() {
		SoundFactory.play("ChangePage");
		currentPlaneCount -= 1;
		if (currentPlaneCount < 0) {
			currentPlaneCount = players.size() - 1;
		}
		SoundFactory.play("Player"+(currentPlaneCount+1));
		setPlane();
	}

	public void nextPlane() {
		SoundFactory.play("ChangePage");
		currentPlaneCount += 1;
		if (currentPlaneCount > players.size() - 1) {
			currentPlaneCount = 0;
		}
		SoundFactory.play("Player"+(currentPlaneCount+1));
		setPlane();
	}

	public void setPlane() {
		plane.setIcon(players.get(currentPlaneCount));
		GameConfig.myPlaneConfig = map.get(players.get(currentPlaneCount)); // 设置当前飞机
		GameConfig.currentPlane = currentPlaneCount; // 设置当前飞机序号
		this.requestFocus();
	}
}
