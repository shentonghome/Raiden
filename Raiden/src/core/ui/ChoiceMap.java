package core.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import core.config.Constants;
import core.factory.ButtonFactory;
import core.util.ImageUtil;
import core.util.UIPanel;

public class ChoiceMap extends UIPanel{
	JButton firstMap;
	JButton secondMap;
	JButton thirdMap;
	JButton forthMap;
	JButton fifthMap;
	JButton back;

	public ChoiceMap(ActionListener Listener) {
		super(Listener);
	}

	public void launch() {
		ImageIcon img = new ImageIcon(LoadingPanel.class.getResource("/image/gkxz.png"));
		title = new JLabel(img);
		
		firstMap = ButtonFactory.getButton("first");
		secondMap = ButtonFactory.getButton("second");
		thirdMap =ButtonFactory.getButton("third");
		forthMap = ButtonFactory.getButton("forth");
		fifthMap = ButtonFactory.getButton("fifth");
		back = ButtonFactory.getButton("back");
		
		firstMap.setActionCommand("first");
		firstMap.addActionListener(Listener);
		secondMap.setActionCommand("second");
		secondMap.addActionListener(Listener);
		thirdMap.setActionCommand("third");
		thirdMap.addActionListener(Listener);
		forthMap.setActionCommand("forth");
		forthMap.addActionListener(Listener);
		fifthMap.setActionCommand("fifth");
		fifthMap.addActionListener(Listener);
		back.setActionCommand("menu");
		back.addActionListener(Listener);
		
		super.launch();
		title.setFont(new Font("宋体", Font.BOLD, 50));
		title.setForeground(new Color(250, 230, 50));
		add(title, constraints, 0, 0, 1, 1);
		add(firstMap, constraints, 1, 0, 1, 1);
		add(secondMap, constraints, 1, 1, 1, 1);
		add(thirdMap, constraints, 2, 0, 1, 1);
		add(forthMap, constraints, 2, 1, 1, 1);
		add(fifthMap, constraints, 3, 0, 1, 1);
		add(back, constraints, 3, 1, 1, 1);
	}
}
