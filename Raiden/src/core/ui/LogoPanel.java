package core.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import core.config.Constants;
import core.factory.SoundFactory;
import core.util.ImageUtil;
import core.util.ThreadUtil;
import core.util.UIPanel;

public class LogoPanel extends UIPanel {
	
	JLabel copyright;
	
	public LogoPanel(ActionListener Listener) {
		super(Listener);
	}

	@Override
	public void launch() {
		copyright = new JLabel("<html>COPYRIGHT ©2017 Michael ALL RIGHTS RESERVED<br>"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp桐月游戏 版权所有<br>&nbsp<br>&nbsp  </html>");
		GridBagLayout grid = new GridBagLayout();
		setLayout(grid);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.weightx = 50;
		constraints.weighty = 50;
		copyright.setFont(new Font("宋体", 0, 10));
		copyright.setForeground(Color.GRAY);
		add(copyright, constraints, 0, 1, 1, 1);
		new Thread(){
			@Override
			public void run() {
				SoundFactory.play("Logo");
				ThreadUtil.sleep(Constants.LogoTime);
				ActionEvent e = new ActionEvent(new Object(), 1, "init");
				 Listener.actionPerformed(e);
			}
		}.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.white);
		g.fillRect(0, 0, Constants.FrameWidth, Constants.FrameHeight);
		g.setColor(color);
		Image bg = ImageUtil.getImage("image/logo.png");
		g.drawImage(bg, Constants.FrameWidth / 3-10, Constants.FrameHeight/3, null);
	}
}
