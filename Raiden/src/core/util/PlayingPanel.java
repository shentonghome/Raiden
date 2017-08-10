package core.util;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import core.config.Constants;
import core.config.GameConfig;

/**
 * 重写JPanel
 */
public class PlayingPanel extends JPanel implements KeyListener {

	public PlayingPanel() {
		setSize(Constants.FrameWidth, Constants.FrameHeight);
		setDoubleBuffered(true); // 设置双缓冲
		setOpaque(false); // 设置组件透明：避免重影
		FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
		setLayout(layout);

	}

	public void launch() {
		new PaintThread().start();
	}

	class PaintThread extends Thread {
		@Override
		public void run() {
			while (GameConfig.isExit) {
				repaint();
				ThreadUtil.sleep(Constants.RepaintFrequence);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
