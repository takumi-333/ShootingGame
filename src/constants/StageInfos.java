package constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import shooting.BossEnemyStatus;
import shooting.StageInfo;

public class StageInfos {
	
	// 総ステージ数
	public static int numStages = 5;
	public List<StageInfo> stageInfos;
	
	// boss enemy
	// x, y, hp, coin, power, speed, bulletRate, type, time, partId, color
	
	public StageInfos() {
		Random rnd = new Random();
		stageInfos = new ArrayList<StageInfo>();
		
		/*
		 * Stage1
		 */
		StageInfo stage1 = new StageInfo();
		stage1.numEnemys = 1;
		stage1.weakEnemyCoin = 5;
		stage1.weakEnemyPower = 1;
		stage1.weakEnemyRate = 110;
		stage1.weakEnemyHp = 2;
		stage1.weakEnemyBulletRate = 70;
		stage1.weakEnemyColor = MyColors.DefaultEnemyColor;
		stage1.bgColor = MyColors.GrayStageBG;
		
		// 真ん中から出現
		BossEnemyStatus enemy1 = new BossEnemyStatus((WindowSize.x - 10) / 2, 0, 6, 200, 1, 3, 150, EnumEnemyType.BOSS, 100, 1, MyColors.BossEnemyColor1);
		enemy1.bulletSpeed = 6;
		enemy1.movePattern = 2;
		stage1.enemyStatus.add(enemy1);
		stageInfos.add(stage1);
		
		
		/*
		 * Stage2
		 */
		StageInfo stage2 = new StageInfo();
		stage2.numEnemys = 5;
		stage2.weakEnemyCoin = 50;
		stage2.weakEnemyPower = 1;
		stage2.weakEnemyRate = 100;
		stage2.weakEnemyHp = 4;
		stage2.weakEnemyBulletRate = 35;
		stage2.weakEnemyColor = MyColors.DarkEnemyColor;
		stage2.bgColor = MyColors.StageBG2;
		
		// 真ん中から出現
		// 3体で一体のやつが出現
		enemy1 = new BossEnemyStatus((WindowSize.x - 10) / 2, 0, 10, 2000, 2, 3, 135, EnumEnemyType.BOSS, 900, 2, MyColors.BossEnemyColor2);
		enemy1.bulletSpeed = 7;
		stage2.enemyStatus.add(enemy1);
		enemy1 = new BossEnemyStatus((WindowSize.x - 10) / 2 - 30, 0, 15, 2500, 3, 3, 120, EnumEnemyType.BOSS, 900, 3, MyColors.BossEnemyColor2);
		enemy1.bulletSpeed = 8;
		stage2.enemyStatus.add(enemy1);
		enemy1 = new BossEnemyStatus((WindowSize.x - 10) / 2 + 30, 0, 10, 2000, 2, 3, 130, EnumEnemyType.BOSS, 900, 4, MyColors.BossEnemyColor2);
		enemy1.bulletSpeed = 6;
		stage2.enemyStatus.add(enemy1);
		
		// 2体の強めのやつ出現
		BossEnemyStatus enemy2 = new BossEnemyStatus(3 * (WindowSize.x - 10) / 4, 0, 8, 800, 2, 4, 125, EnumEnemyType.BOSS, 300, 1, MyColors.BossEnemyColor3);
		enemy2.bulletSpeed = 6;
		stage2.enemyStatus.add(enemy2);
		BossEnemyStatus enemy3 = new BossEnemyStatus((WindowSize.x - 10) / 4, 0, 8, 800, 2, 4, 120, EnumEnemyType.BOSS, 300, 1, MyColors.BossEnemyColor3);
		enemy3.bulletSpeed = 6;
		stage2.enemyStatus.add(enemy3);
		stageInfos.add(stage2);
		
		
		/*
		 * Stage3
		 * バリア持ちが出現
		 */
		StageInfo stage3 = new StageInfo();
		stage3.numEnemys = 1;
		stage3.weakEnemyCoin = 800;
		stage3.weakEnemyPower = 2;
		stage3.weakEnemyRate = 180;
		stage3.weakEnemyHp = 10;
		stage3.weakEnemyBulletRate = 35;
		stage3.weakEnemyColor = MyColors.SalmonEnemyColor;
		stage3.bgColor = MyColors.StageBG3;
		
		enemy1 = new BossEnemyStatus((WindowSize.x - 10) / 2, 0, 40, 20000, 5, 2, 60, EnumEnemyType.BOSS, 10, 5, MyColors.BossEnemyColor4);
		enemy1.bulletSpeed = 3;
		enemy1.barrierPartId = enemy1.partId;
		stage3.enemyStatus.add(enemy1);
		stageInfos.add(stage3);
		
		
		/*
		 * Stage4
		 * バリア持ちが出現
		 */
		StageInfo stage4 = new StageInfo();
		stage4.numEnemys = 10;
		stage4.weakEnemyCoin = 1000;
		stage4.weakEnemyPower = 2;
		stage4.weakEnemyRate = 60;
		stage4.weakEnemyHp = 6;
		stage4.weakEnemyBulletRate = 45;
		stage4.weakEnemyColor = MyColors.SummerGreenEnemyColor;
		stage4.bgColor = MyColors.StageBG4;
		
		for (int i = 0; i < 4; i++) {
			enemy1 = new BossEnemyStatus( 2 * (i+1) * (WindowSize.x - 10) / 10, 0, 15, 15000, 3, 3, rnd.nextInt(5) + 95, EnumEnemyType.BOSS, 400, 0, MyColors.BossEnemyColor5);
			enemy1.bulletSpeed = rnd.nextInt(2) + 6;
			enemy1.movePattern = 1;
			stage4.enemyStatus.add(enemy1);
		}
		for (int i = 0; i < 6; i++) {
			enemy1 = new BossEnemyStatus((WindowSize.x - 10) / 2 , 0, 18, 45000, 3, 3, 100, EnumEnemyType.BOSS, 2500, 6, MyColors.BossEnemyColor6);
			enemy1.bulletSpeed = rnd.nextInt(3) + 6;
			enemy1.movePattern = 3;
			enemy1.barrierPartId = enemy1.partId;
			stage4.enemyStatus.add(enemy1);
		}
		stageInfos.add(stage4);
		
		/*
		 * Stage5
		 * ビーム持ちが出現(バリアも持つ)
		 */
		
		StageInfo stage5 = new StageInfo();
		stage5.numEnemys = 3;
		stage5.weakEnemyCoin = 5000;
		stage5.weakEnemyPower = 4;
		stage5.weakEnemyRate = 120;
		stage5.weakEnemyHp = 16;
		stage5.weakEnemyBulletRate = 35;
		stage5.weakEnemyColor = MyColors.TailGreenEnemyColor;
		stage5.bgColor = MyColors.StageBG5;
		
		
		enemy1 = new BossEnemyStatus((WindowSize.x - 10) / 2, 0, 96, 100000, 1000, 0, 225, EnumEnemyType.BOSS, 100, 7, MyColors.BossEnemyColor7);
		enemy1.hasBeam = true;
		enemy1.bulletSize = 4;
		enemy1.movePattern = 0;
		enemy1.barrierPartId = enemy1.partId;
		stage5.enemyStatus.add(enemy1);
		enemy1 = new BossEnemyStatus(3 * (WindowSize.x - 10) / 4, 0, 72, 50000, 5, 0, 150, EnumEnemyType.BOSS, 850, 7, MyColors.BossEnemyColor7);
		enemy1.hasBeam = true;
		enemy1.bulletSize = 4;
		enemy1.movePattern = 0;
		enemy1.barrierPartId = enemy1.partId;
		stage5.enemyStatus.add(enemy1);
		enemy1 = new BossEnemyStatus(1 * (WindowSize.x - 10) / 4, 0, 72, 50000, 5, 0, 145, EnumEnemyType.BOSS, 850, 7, MyColors.BossEnemyColor7);
		enemy1.hasBeam = true;
		enemy1.bulletSize = 4;
		enemy1.movePattern = 0;
		enemy1.barrierPartId = enemy1.partId;
		stage5.enemyStatus.add(enemy1);
		stageInfos.add(stage5);
		
		/*
		 * Stage6
		 * ビーム持ちが出現(バリアも持つ)
		 */
		
		StageInfo stage6 = new StageInfo();
		stage6.numEnemys = 10;
		stage6.weakEnemyCoin = 10000;
		stage6.weakEnemyPower = 50;
		stage6.weakEnemyRate = 350;
		stage6.weakEnemyHp = 150;
		stage6.weakEnemyBulletRate = 50;
		stage6.weakEnemyColor = MyColors.DarkEnemyColor;
		stage6.bgColor = MyColors.StageBG5;
	}
}

