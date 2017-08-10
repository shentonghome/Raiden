package core.util;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import core.config.Constants;

public class UIPanel extends JPanel {
	
	protected JLabel title;
	protected ActionListener Listener;
	GridBagLayout grid;
	protected GridBagConstraints constraints;
	
	public UIPanel(){}
	
	public UIPanel(ActionListener Listener) {
		this.Listener = Listener;
		setSize(Constants.FrameWidth, Constants.FrameHeight);
		launch();
	}

	public void launch() {
		grid = new GridBagLayout();
		setLayout(grid);
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.ipadx = 60;	
		constraints.ipady = 40;
	}

	protected void add(Component component, GridBagConstraints constraints, int x, int y, int width, int height) {
		constraints.gridx = y;
		constraints.gridy = x;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		add(component, constraints);
	}

	@Override
	protected void paintComponent(Graphics g) {
		drawBg(g);
	}

	public void drawBg(Graphics g) {
		Image bg = ImageUtil.getImage("image/bg_blue.jpg");
		g.drawImage(bg, 0, 0, Constants.FrameWidth, Constants.FrameHeight, null);
	}
}
