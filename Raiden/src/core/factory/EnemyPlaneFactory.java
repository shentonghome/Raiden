package core.factory;

import java.awt.Image;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import core.bean.EnemyPlane;
import core.config.Constants;
import core.config.EnemyPath;
import core.util.ImageUtil;

public class EnemyPlaneFactory {

	public static Map<String, Image> imgs;
	public static Map<Integer, ArrayList<String>> names;
	static Image img;

	static {
		imgs = new HashMap<String, Image>();
		names = new HashMap<Integer, ArrayList<String>>();
		EnemyPath ep = new EnemyPath();
		Field fields[] = EnemyPath.class.getFields();
		try {
			String lastLv = "1";
			int count = 1;
			ArrayList<String> n = new ArrayList<String>();
			for (Field field : fields) {
				
				if (field.getName().substring(2, 3).equals(lastLv)) {
					n.add(field.getName());
				}else {
					names.put(count, n);
					count++;
					n = new ArrayList<String>();
					n.add(field.getName());
				}
				lastLv = field.getName().substring(2, 3);
				Object o = field.get(ep);
				img = ImageUtil.getImage(o.toString());
				imgs.put(field.getName(), img);
			}
//			 System.out.println(names);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static EnemyPlane getEnemyPlane(int lv, double x, double y) {
		try {
			ArrayList<String> al = names.get(lv);
			String lvStr = al.get((int)((al.size())*Math.random()));
//			System.out.println("实际等级："+lvStr);
			Image img = imgs.get(lvStr);
			int HP = Constants.EnemiesHP[lv-1];
			EnemyPlane enemy = new EnemyPlane(img, x, y, HP, lv);
			return enemy;
		} catch (Exception e) {
			return null;
		}

	}

}
