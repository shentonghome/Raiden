package core.config;

public class GameConfig {
	public static boolean isPause = true;
	public static boolean isExit = true;
	public static String[] myPlaneConfig = { "image/img_group/img_plane_main/img_plane_main5.png",
			Constants.MyPlaneHP1 + "", Constants.MyPlaneSpeed1 + "" }; // 默认飞机
	public static int currentPlane = 0; // 当前飞机
	public static int currentDifficulty = 1; // 当前难度
	public static int[] targetScore = Constants.targetScore[currentDifficulty]; // 游戏难度
	public static int EnemyBulletEmmiterFrequence = Constants.EnemyBulletEmmiterFrequence[currentDifficulty];// 敌机子弹参考发射频率
	public static int minEnemyEmmitBulletLv = Constants.minEnemyEmmitBulletLv[currentDifficulty];// 敌机最低发射弹药等级
	public static int EnemiesSpeed[] = Constants.EnemiesSpeed[currentDifficulty];// 敌机速度
	public static int BulletLv[] = Constants.BulletLv[currentDifficulty];// 换武器的难度等级
	public static int ScoreLv[] = Constants.ScoreLv[currentDifficulty];// 敌机出现的分数值等级
	public static int max_minEEnemyEmmiterFrequence[] = Constants.max_minEEnemyEmmiterFrequence[currentDifficulty];// 敌机发射频率
}
