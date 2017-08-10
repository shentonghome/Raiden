package core.config;

public class Constants {
	public final static String Title = "Raiden";
	public final static int FramePosX = 350; // 窗口位置X
	public final static int FramePosY = 50; // 窗口位置Y
	public final static int FrameWidth = 512;// 窗口宽度
	public final static int FrameHeight = 600;// 窗口高度
	public final static int BulletSpeed = 15; // 默认子弹速度
	public final static int BulletSize = 10;// 子弹大小
	public final static double MyPlaneSpeed1 = 10; // 玩家飞机速度1
	public final static double MyPlaneSpeed2 = 17; // 玩家飞机速度2
	public final static double MyPlaneSpeed3 = 11; // 玩家飞机速度3
	public final static double MyPlaneSpeed4 = 6; // 玩家飞机速度4
	public final static int MyPlaneHP1 = 550; // 玩家默认生命值1
	public final static int MyPlaneHP2 = 300; // 玩家默认生命值2
	public final static int MyPlaneHP3 = 600; // 玩家默认生命值3
	public final static int MyPlaneHP4 = 1000; // 玩家默认生命值4
	public final static int[][] EnemiesSpeed = { { 2, 5, 2, 1, 1, 1, 0 }, { 3, 6, 3, 2, 1, 1, 0 },
			{ 4, 6, 4, 3, 2, 1, 0 }, { 5, 8, 6, 3, 2, 1, 0 } };// 敌机速度
	public final static int EnemyBulletSpeed = 8;// 敌机子弹速度
	public final static double MyBulletDegree = Math.PI * 3 / 2; // 玩家子弹默认角度
	public final static double BackGroundSpeed = 1; // 背景移动速度
	public final static double CloudSpeed = 2; // 云速度
	public final static int RepaintFrequence = 16; // 刷新频率
	public final static int PlanePosX = 250; // 初始玩家飞机位置X
	public final static int PlanePosY = 450; // 初始玩家飞机位置Y
	public final static String deafultParticle = "image/particle/wsparticle_tailinga_0.png"; // 默认粒子图
	public final static int ParticleEmmitFrequence = 30; // 每组粒子之间发射间隔
	public final static float ParticleTimeMax = 300f; // 粒子生存最大时间
	public final static float ParticleTimeMin = 100f; // 粒子生存最小时间
	public final static float ParticleSpeedMax = 3; // 粒子最大速度
	public final static float ParticleSpeedMin = 1; // 粒子最小速度
	public final static double ParticleDegree = Math.PI / 2; // 粒子发射角度
	public final static double ParticleNum = 20; // 一次发射的粒子数目
	public final static int ParticleInterval = 10; // 每组内粒子发射的间隔
	public final static float ParticlePartA = 0.8f; // 粒子消失阶段A
	public final static float ParticlePartB = 0.4f; // 粒子消失阶段B
	public final static int CloudRemoveFrequence = 10000; // 云删除频率
	public final static int BulletRemoveFrequence = 100; // 子弹删除频率
	public final static int EnemyRemoveFrequence = 500; // 敌机删除频率
	public final static int EnemyRemoveAlfterDie = 5000;// 在死亡多久之后删除
	public final static int BulletEmmiterFrequence = 100; // 玩家子弹发射频率
	public final static double EnemyDegreeDetal = 0.02; // 敌机运动的角度变化量
	public final static int ScoreOffSet = 30; // 分数字间隔
	public final static int[][] ScoreLv = { { 100, 500, 1000, 1500, 3000, 4000, 5000 },
			{ 60, 300, 600, 900, 1800, 2400, 3000 }, { 26, 180, 360, 540, 400, 800, 1000 },
			{ 10, 60, 100, 150, 200, 400, 500 } }; // 敌机出现的分数值等级
	public final static float StarRotateSpeed = 0.4f; // 星星旋转速度
	public final static int StarRemoveFrequence = 1000; // 星星删除频率
	public final static int StarScore = 50;// 每个星星的分数;
	public final static int StarFrequence = 150;// 星星组内添加频率
	public final static int Bullet1Hert = 20; // 默认子弹伤害
	public final static int Bullet2Hert = 30; // 默认子弹伤害
	public final static int Bullet3Hert = 80; // 默认子弹伤害
	public final static int Bullet4Hert = 100; // 默认子弹伤害
	public final static int Bullet5Hert = 200; // 默认子弹伤害
	public final static int EnemyBulletHert1 = 10; // 默认子弹伤害(敌机)
	public final static int EnemyBulletHert2 = 20; // 默认子弹伤害(敌机)
	public final static int[][] BulletLv = { { 5, 10, 15, 20, 25 }, { 10, 20, 30, 40, 50 }, { 20, 30, 40, 50, 60 },
			{ 30, 40, 50, 60, 70 } }; // 换武器的难度等级
	public static int[] EnemyBulletEmmiterFrequence = { 1200, 1100, 900, 750 };// 敌机子弹参考发射频率
	public final static int[][] targetScore = { { 20, 40, 60, 80, 100 }, { 40, 80, 120, 160, 200 },
			{ 80, 160, 240, 320, 400 }, { 160, 320, 240, 320, 400 } }; // 游戏难度（每关得分）
	public final static int EnemyMaxMemberCount = 10; // 敌机队伍最大成员数
	public final static int[] EnemiesHP = { 10, 50, 200, 1000, 2000, 4000, 5000 }; // 敌机生命值
	public final static int EnemyEmmiterDelayedTime = 2000; // 敌机延迟发射时间
	public final static int CrashListenerFrequence = 10; // 碰撞检测频率
	public final static float ExplodeSpeed = 0.6f; // 爆炸速度
	public final static int BigBangTime = 1000; // 限制清屏时间
	public final static int LogoTime = 5000;// 开场动画时间
	public final static int[] minEnemyEmmitBulletLv = { 4, 3, 2, 1 }; // 敌机最低发射弹药等级
	public final static int[][] max_minEEnemyEmmiterFrequence = { { 700, 2000 }, { 600, 1500 }, { 500, 1000 },
			{ 400, 800 } };// 敌机发射频率
}
