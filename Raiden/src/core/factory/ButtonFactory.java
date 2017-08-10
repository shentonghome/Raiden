package core.factory;

import javax.swing.JButton;

import core.ui.GameButton;

public class ButtonFactory {
	
	public static JButton getButton(String name){
		JButton button;
		switch(name){
			case "start":
				button = new GameButton("image/button/start_normal.png","image/button/start_normal.png","image/button/start_press.png");
				return button;
			case "config":
				button = new GameButton("image/button/set_normal.png","image/button/set_normal.png","image/button/set_press.png");
				return button;
			case "exit":
				button = new GameButton("image/button/exit_normal.png","image/button/exit_normal.png","image/button/exit_press.png");
				return button;
			case "pause":
				button = new GameButton("image/img_group/ui_new_btn_png/ui_new_btn_png29.png","image/img_group/ui_new_btn_png/ui_new_btn_png29.png","image/img_group/ui_new_btn_png/ui_new_btn_png29.png");
				return button;
			case "first":
				button = new GameButton("image/button/first.png","image/button/first.png","image/button/first.png");
				return button;
			case "second":
				button = new GameButton("image/button/second.png","image/button/second.png","image/button/second.png");
				return button;
			case "third":
				button = new GameButton("image/button/third.png","image/button/third.png","image/button/third.png");
				return button;
			case "forth":
				button = new GameButton("image/button/forth.png","image/button/forth.png","image/button/forth.png");
				return button;
			case "fifth":
				button = new GameButton("image/button/fifth.png","image/button/fifth.png","image/button/fifth.png");
				return button;
			case "back":
				button = new GameButton("image/button/back.png","image/button/back.png","image/button/back.png");
				return button;
			case "changePlane":
				button = new GameButton("image/button/changePlane.png","image/button/changePlane.png","image/button/changePlane.png");
				return button;
			case "left":
				button = new GameButton("image/button/left.png", "image/button/left.png", "image/button/left.png");
				return button;
			case "right":
				button = new GameButton("image/button/right.png", "image/button/right.png", "image/button/right.png");
				return button;
			case "sound":
				button = new GameButton("image/button/sound.png", "image/button/sound.png", "image/button/sound.png");
				return button;
			case "difficulty":
				button = new GameButton("image/button/difficulty.png", "image/button/difficulty.png", "image/button/difficulty.png");
				return button;
			default:
				button = new JButton(name);
				return button;
		}
	}
}
