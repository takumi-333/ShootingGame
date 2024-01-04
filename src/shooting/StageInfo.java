package shooting;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class StageInfo {
	
	public int numEnemys;
	public int weakEnemyRate;
	public int weakEnemyPower;
	public int weakEnemyCoin;
	public int weakEnemyHp;
	public int weakEnemyBulletRate;
	public int weakEnemyMoveSpeed = 5;
	public int weakEnemyBulletSpeed = 5;
	public Color weakEnemyColor;
	public Color bgColor;
	public List<BossEnemyStatus> enemyStatus;
	
	public boolean hasAttacker = false;
	public int attackerRate;
	public int attackerPower;
	public int attackerCoin;
	public int attackerHp;
	public int attackerBulletRate;
	public int attackerBarrierPartId = 0;
	public Color attackerColor;
	public boolean attackerHasBeam = false;
	
	
	public StageInfo() {
		enemyStatus = new ArrayList<BossEnemyStatus>();
	}
	
	
}
