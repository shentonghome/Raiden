package core.factory;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;

import core.config.SoundPath;
import core.util.SoundPlayer;

public class SoundFactory {

	static HashMap<String, File> sounds = new HashMap<String, File>();
	static HashMap<String, SoundPlayer> playing = new HashMap<String, SoundPlayer>();
	public static boolean isSound = true;;
	static {
		SoundPath sp = new SoundPath();
		Field fields[] = SoundPath.class.getFields();
		for (Field field : fields) {
			try {
				Object o = field.get(sp);
//				URL url = SoundFactory.class.getClassLoader().getResource(o.toString());
//				String paths[] = url.getPath().split("/");
//				paths = Arrays.copyOf(paths, paths.length - 3);
//				String path = (String.join("/", paths) + "/" + o.toString()).replace("file:", "");
				URL path = SoundFactory.class.getResource(o.toString());
				System.out.println("path: " + path);
				sounds.put(field.getName(),
						new File(path.getPath()));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

	public static void BGMMenu() {

	}

	public static void play(String name) {

		if (isSound) {
			SoundPlayer sp = new SoundPlayer(sounds.get(name));
			playing.put(name, sp);
			// System.out.println("play: "+name);
			sp.play();
		}
	}

	public static void loop(String name) {
		if (isSound) {
			SoundPlayer sp = new SoundPlayer(sounds.get(name));
			if (playing.get(name) == null) {
				playing.put(name, sp);
				sp.loop();
				// System.out.println("loop: "+name);
			}
		}
	}

	public static void stop(String name) {
		SoundPlayer sp = playing.get(name);
		if (sp != null) {
			sp.stop();
			// System.out.println("stop: "+name);
			playing.remove(name);
		}
	}

	public static void stopAll() {
		for (String name : playing.keySet()) {
			playing.get(name).stop();
		}
		playing.clear();
		isSound = false;
	}

}
