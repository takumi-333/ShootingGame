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
	public Color weakEnemyColor;
	public Color bgColor;
	public List<BossEnemyStatus> enemyStatus;
	
	
	public StageInfo() {
		enemyStatus = new ArrayList<BossEnemyStatus>();
	}
	
	
}
