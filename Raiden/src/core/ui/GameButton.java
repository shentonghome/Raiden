package core.ui;

import java.awt.Graphics;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import core.util.ImageUtil;
/**
 * 自定义按钮
 */
public class GameButton extends JButton{
	
	String normal;
	String hover;
	String press;
	
	public GameButton(String normal,String hover,String press){
		this.normal = normal;
		this.hover = hover;
		this.press =press;
		launch();
	}
	
	public void launch(){
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(false);//不绘制按钮区域
		setBorderPainted(false);//不绘制边框
		setFocusPainted(false);
		URL url1 = ImageUtil.getUrl(normal);
		URL url2 = ImageUtil.getUrl(hover);
		URL url3 = ImageUtil.getUrl(press);
		setIcon(new ImageIcon(url1));//设置默认图片
		setRolloverIcon(new ImageIcon(url2));//设置鼠标经过图片
		setPressedIcon(new ImageIcon(url3));//设置鼠标按下图片
	}
	
	
}
