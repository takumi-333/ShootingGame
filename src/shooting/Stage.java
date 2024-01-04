package shooting;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import constants.EnumEnemyType;
import constants.MyColors;
import constants.PartsCollection;
import constants.StageInfos;
import constants.WindowSize;

public class Stage {
	private StageInfo stageInfo;
	public boolean isFinished;
	private int time;
	private int weakEnemyInterval;
	private int attackerInterval;
	private Random rnd;
	private int numEnemys;
	public Color bgColor;
	private int stageId;
	
	public Stage(int stageId) {
		StageInfos stageInfos = new StageInfos();
		stageInfo = stageInfos.stageInfos.get(stageId);
		isFinished = false;
		time = 0;
		weakEnemyInterval = 0;
		attackerInterval = 0;
		rnd = new Random();
		numEnemys = stageInfo.numEnemys;
		bgColor = stageInfo.bgColor;
		this.stageId = stageId;
		
	}
	
	public void update(List<EnemyEntity> enemyEntitys) {
		
		// 時間の経過
		time = (time + 1) % 100000;
		weakEnemyInterval = Math.max(weakEnemyInterval-1, 0);
		
		// 鬼畜処理
		if (stageId == 7) {
			if (time == 4000) {
				stageInfo.attackerHasBeam = true;
			}
			else if (time == 3000) {
				stageInfo.weakEnemyBulletRate -= 10;
			}
			else if (time == 2000) {
				stageInfo.weakEnemyRate -= 15;
				stageInfo.weakEnemyBulletRate -= 5;
			}
			if (time == 1000) {
				stageInfo.weakEnemyRate -= 10;
				stageInfo.attackerRate -= 5;
			}
			
		}
		
		// weak enemyの生成
		if (weakEnemyInterval <= 0) {
			EnemyEntity weakEnemy = new EnemyEntity(rnd.nextInt(WindowSize.x- 40) + 20, 0, stageInfo.weakEnemyCoin, stageInfo.weakEnemyPower);

			weakEnemy.setHp(stageInfo.weakEnemyHp);
			weakEnemy.setBulletRate(stageInfo.weakEnemyBulletRate);
			weakEnemy.setEntityColor(stageInfo.weakEnemyColor);
			weakEnemy.setMoveSpeed(stageInfo.weakEnemyMoveSpeed + rnd.nextInt(2));
			weakEnemy.setBulletSpeed(stageInfo.weakEnemyBulletSpeed + rnd.nextInt(2));
			int p = rnd.nextInt(100);
			if (p == 99) {
				weakEnemy.setEntityColor(MyColors.CoinEnemyColor);
				weakEnemy.coin = weakEnemy.coin * 10;
			}
			enemyEntitys.add(weakEnemy);
			weakEnemyInterval = stageInfo.weakEnemyRate;
		}
		
		if (stageInfo.hasAttacker) {
			attackerInterval = Math.max(attackerInterval-1, 0);
			if (attackerInterval <= 0) {
				EnemyEntity attackerEnemy = new EnemyEntity(rnd.nextInt(WindowSize.x-40) + 20, 0, stageInfo.attackerCoin, stageInfo.attackerPower);
				attackerEnemy.setHp(stageInfo.attackerHp);
				attackerEnemy.setBulletRate(stageInfo.attackerBulletRate);
				attackerEnemy.setEntityColor(stageInfo.attackerColor);
				attackerEnemy.setBarrierParts(PartsCollection.getBarrierParts(stageInfo.attackerBarrierPartId));
				attackerEnemy.setType(EnumEnemyType.ATTACKER);
				attackerEnemy.bulletInterval = stageInfo.attackerBulletRate;
				attackerEnemy.hasBeam = stageInfo.attackerHasBeam;
				if (stageInfo.attackerBarrierPartId != 0) {
					System.out.println("set barrier");
					attackerEnemy.setBarrierParts(PartsCollection.getBarrierParts(stageInfo.attackerBarrierPartId));
				}
				enemyEntitys.add(attackerEnemy);
				attackerInterval = stageInfo.attackerRate;
			}
		}
		
		
		
		// boss enemyの生成
		stageInfo.enemyStatus.forEach(enemy -> {
			if (time == enemy.time) {
				EnemyEntity bossEnemy;
				if (enemy.isSpecial) {
					bossEnemy = new SpecialBossEnemy(enemy.x, enemy.y, enemy.coin, enemy.power);
				}
				else {
					bossEnemy = new EnemyEntity(enemy.x, enemy.y, enemy.coin, enemy.power);
				}
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
				bossEnemy.posY = enemy.posY;
				if (enemy.barrierPartId != 0) {
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
