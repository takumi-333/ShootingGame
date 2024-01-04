package shooting;

import java.awt.Color;

import constants.EnumEnemyType;

public class BossEnemyStatus {
	public int x; public int y;
	public int coin;
	public int hp;
	public int power;
	public int speed;
	public int bulletRate;
	public EnumEnemyType type;
	public long time;
	public int partId;
	public Color entityColor;
	
	// 後から設定
	public int movePattern = 1;
	public boolean hasBeam = false;
	public int bulletSize = 6;
	public int barrierPartId = 0;
	public int bulletSpeed = 5;
	public int posY = 100;
	public boolean isSpecial = false;
	
	public BossEnemyStatus(int x, int y, int hp, int coin, int power, int speed, int bulletRate, EnumEnemyType type, long time, int partId, Color color) {
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.coin = coin;
		this.power = power;
		this.speed = speed;
		this.bulletRate = bulletRate;
		this.type = type;
		this.time = time;
		this.partId = partId;
		this.entityColor = color;
	}
}
