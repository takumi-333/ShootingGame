package shooting;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import constants.MyColors;
import constants.PartsCollection;
import constants.StageInfos;
import constants.WindowSize;

public class Stage {
	private StageInfo stageInfo;
	public boolean isFinished;
	private int time;
	private int weakEnemyInterval;
	private Random rnd;
	private int numEnemys;
	public Color bgColor;
	
	public Stage(int stageId) {
		StageInfos stageInfos = new StageInfos();
		stageInfo = stageInfos.stageInfos.get(stageId);
		isFinished = false;
		time = 0;
		weakEnemyInterval = 0;
		rnd = new Random();
		numEnemys = stageInfo.numEnemys;
		bgColor = stageInfo.bgColor;
	}
	
	public void update(List<EnemyEntity> enemyEntitys) {
		
		// 時間の経過
		time = (time + 1) % 100000;
		weakEnemyInterval = Math.max(weakEnemyInterval-1, 0);
		
		// weak enemyの生成
		if (weakEnemyInterval <= 0) {
			EnemyEntity weakEnemy = new EnemyEntity(rnd.nextInt(WindowSize.x) + 20, 0, stageInfo.weakEnemyCoin, stageInfo.weakEnemyPower);

			weakEnemy.setHp(stageInfo.weakEnemyHp);
			weakEnemy.setBulletRate(stageInfo.weakEnemyBulletRate);
			weakEnemy.setEntityColor(stageInfo.weakEnemyColor);
			int p = rnd.nextInt(100);
			if (p == 99) {
				weakEnemy.setEntityColor(MyColors.CoinEnemyColor);
				weakEnemy.coin = weakEnemy.coin * 10;
			}
			enemyEntitys.add(weakEnemy);
			weakEnemyInterval = stageInfo.weakEnemyRate;
		}
		
		// boss enemyの生成
		stageInfo.enemyStatus.forEach(enemy -> {
			if (time == enemy.time) {
				EnemyEntity bossEnemy = new EnemyEntity(enemy.x, enemy.y, enemy.coin, enemy.power);
				bossEnemy.setHp(enemy.hp);
				bossEnemy.setType(enemy.type);
				bossEnemy.setMoveSpeed(enemy.speed);
				bossEnemy.setParts(PartsCollection.getParts(enemy.partId));
				bossEnemy.setEntityColor(enemy.entityColor);
				bossEnemy.setBulletRate(enemy.bulletRate);
				bossEnemy.setBulletSpeed(enemy.bulletSpeed);
				bossEnemy.hasBeam = enemy.hasBeam;
				bossEnemy.setBulletSize(enemy.bulletSize);
				bossEnemy.movePattern = enemy.movePattern;
				if (enemy.barrierPartId != 0 ) {
					bossEnemy.setBarrierParts(PartsCollection.getBarrierParts(enemy.barrierPartId));
				}
				enemyEntitys.add(bossEnemy);
			}
		});
	}
	
	public void defeatEnemy() {
		numEnemys--;
		if (numEnemys <= 0) isFinished = true;
	}
}
