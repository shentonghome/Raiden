package core.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static Image getImage(String path){
		URL u = ImageUtil.class.getClassLoader().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static URL getUrl(String path){
		URL url = ImageUtil.class.getClassLoader().getResource(path);
		return url;
	}
	
	
}
