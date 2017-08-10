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
import core.factory.ButtonFactory;
import core.factory.SoundFactory;
import core.util.ImageUtil;
import core.util.SoundPlayer;
import core.util.UIPanel;

public class MenuPanel extends UIPanel {
	JButton start_Btn;
	JButton changePlane_Btn;
	JButton config_Btn;
	JButton exit_Btn;

	public MenuPanel(ActionListener Listener) {
		super(Listener);
	}

	public void launch() {
		ImageIcon img = new ImageIcon(LoadingPanel.class.getResource("/image/title.png"));
		title = new JLabel(img);
		start_Btn = ButtonFactory.getButton("start");
		changePlane_Btn = ButtonFactory.getButton("changePlane");
		config_Btn = ButtonFactory.getButton("config");
		exit_Btn = ButtonFactory.getButton("exit");
		
		start_Btn.setActionCommand("start");
		start_Btn.addActionListener(Listener);
		
		config_Btn.setActionCommand("setting");
		config_Btn.addActionListener(Listener);

		changePlane_Btn.setActionCommand("changePlane");
		changePlane_Btn.addActionListener(Listener);

		exit_Btn.setActionCommand("exit");
		exit_Btn.addActionListener(Listener);

		super.launch();

		add(title, constraints, 0, 0, 1, 1);
		add(start_Btn, constraints, 1, 0, 1, 1);
		add(changePlane_Btn, constraints, 2, 0, 1, 1);
		add(config_Btn, constraints, 3, 0, 1, 1);
		add(exit_Btn, constraints, 4, 0, 1, 1);
		SoundFactory.loop("BGM_menu");
	}

}
