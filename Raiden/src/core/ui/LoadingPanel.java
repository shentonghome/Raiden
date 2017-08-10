package core.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import core.config.Constants;
import core.factory.SoundFactory;
import core.util.ImageUtil;
import core.util.ThreadUtil;
import core.util.UIPanel;

public class LoadingPanel extends UIPanel {

	JLabel loadImg;
	JLabel loadText;
	JLabel Help;
	int mapLv;
	int max = 300;
	int x, y;
	int jindu;

	public LoadingPanel(ActionListener Listener, int mapLv) {
		super(Listener);
		this.mapLv = mapLv;
	}

	@Override
	public void launch() {
		SoundFactory.play("Loading");
		x = Constants.FrameWidth / 5;
		y = Constants.FrameHeight / 3;
		setLayout(null);
		loadText = new JLabel("Loading...");
		ImageIcon img = new ImageIcon(LoadingPanel.class.getResource("/image/loading4.gif"));
		loadImg = new JLabel(img);
		String info = "<html><b>小提示</b>：1.方向键控制方向；" + "<br>&nbsp;&nbsp;&nbsp;&nbsp;2.空格键发射弹药；"
				+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;3.\"Ctrl\"键触发核弹轰杀。</html>";
		Help = new JLabel(info);
		Help.setBounds(x + 50, 300, 500, 400);
		Help.setForeground(new Color(1,143,209));
		loadText.setBounds(x, y - 70, 100, 100);
		loadText.setForeground(new Color(1,143,209));
		loadImg.setBounds(Constants.FrameWidth - 150, Constants.FrameHeight - 180, 150, 150);
		Help.setFont(new Font("宋体", Font.BOLD, 12));
		add(loadText);
		add(loadImg);
		add(Help);
		new Thread() {
			@Override
			public void run() {
				int count = 150;
				for (int i = 0; i <= count; i++) {
					setjindu((float) i / (float) count);
					ThreadUtil.sleep(30);
					repaint();
				}
				ActionEvent e = new ActionEvent(new Object(), mapLv, "play");
				Listener.actionPerformed(e);
				SoundFactory.stop("Loading");
			}
		}.start();

	}

	@Override
	protected void paintComponent(Graphics g) {
		drawBg(g);
		// Color color = g.getColor();
		// g.setColor(Color.white);
		// g.fillRect(0, 0, Constants.FrameWidth, Constants.FrameHeight);
		// g.setColor(color);
		Color color1 = g.getColor();
		g.setColor(new Color(52, 162, 213));
		g.drawRoundRect(x, y, max + 1, 15, 5, 5);
		g.setColor(new Color(1,143,209));
		g.fillRoundRect(x + 1, y + 1, jindu, 14, 5, 5);
		g.setColor(color1);
	}

	protected void setjindu(float preset) {
		jindu = (int) (preset * max);
	}

	public void drawBg(Graphics g) {
		Image bg = ImageUtil.getImage("image/img_bg_logo.jpg");
		g.drawImage(bg, 0, 0, Constants.FrameWidth, Constants.FrameHeight, null);
	}
}
