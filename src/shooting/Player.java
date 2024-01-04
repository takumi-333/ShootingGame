package shooting;

import java.awt.Graphics;
import java.util.Random;

import constants.EnumDirection;
import constants.MyColors;
import constants.PartsCollection;
import constants.PlayerParameter;
import constants.WindowSize;
import lib.Utils;

public class Player extends Entity {
	
	private int coins = 0;
	private int speed = 5;
	public static int width = 30;
	private int motionRangeY = 30;
	private int deltaY = 0;
	private int power = 1;
	private int bulletSize;
	
	private long barrierInterval;
	private long barrierDuration;
	private long barrierRate;
	private long barrierTime;
	private int barrierType;
	
	private int maxHp;
	private long beamInterval;
	private long beamDuration;
	private long beamRate;
	private float beamPowerRatio;
	private PlayerLevel playerLevel;
	private Random rnd = new Random();
	
	
	public Player() {
		super();
		entityColor = MyColors.PlayerColor;
		super.setParts(PartsCollection.playerParts());
		super.setBulletInterval(5);
	}
	
	public Player(int x, int y) {
		super(x, y);
		entityColor = MyColors.PlayerColor;
		super.setParts(PartsCollection.playerParts());
	}
	
	public void initStatus(PlayerLevel playerLevel) {
		this.playerLevel = playerLevel;
		super.setHp(PlayerParameter.hpList[playerLevel.hpLevel-1]);
		super.setBulletRate(PlayerParameter.bulletRateList[playerLevel.bulletRateLevel-1]);
		power = PlayerParameter.powerList[playerLevel.powerLevel-1];
		speed = PlayerParameter.speedList[playerLevel.speedLevel-1];
		super.bulletSpeed = PlayerParameter.bulletSpeedList[playerLevel.bulletSpeedLevel-1];
		bulletSize = PlayerParameter.bulletSizeList[playerLevel.bulletSizeLevel-1];
		barrierDuration = PlayerParameter.barrierDurationList[playerLevel.barrierLevel-1];
		barrierRate = PlayerParameter.barrierRateList[playerLevel.barrierLevel-1];
		barrierType = PlayerParameter.barrierTypeList[playerLevel.barrierLevel-1];
		
		beamDuration = PlayerParameter.beamDurationList[playerLevel.beamLevel-1];
		beamRate = PlayerParameter.beamRateList[playerLevel.beamLevel-1];
		beamPowerRatio = PlayerParameter.beamPowerRatioList[playerLevel.beamLevel-1];
		
		
		super.isDead = false;
		super.dying = false;
		super.damaged = false;
		
		maxHp = hp;
		barrierTime = 0;
		barrierInterval = 0;
		beamInterval = 0;
	}
	
	
	
	public void gainCoin(int coin) {
		this.coins += coin;
	}
	
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public int getCoins() {
		return coins;
	}
	
	public void move(EnumDirection dir) {
		if (isDead || dying) return;
		switch(dir) {
		case LEFT:
			if (this.x >= 15) {
				this.x -= speed;
			}
			break;
			
		case RIGHT:
			if (this.x <= WindowSize.x - 20) {
				this.x += speed;
			}
			break;
			
		case UP:
			if (deltaY >= -motionRangeY) {
				this.y -= speed;
				deltaY -= speed;
			}
			
			break;
			
		case DOWN:
			if (deltaY <= motionRangeY && this.y + 40 < WindowSize.y) {
				deltaY += speed;
				this.y += speed;
			}
			
			break;
		}
	}
	
	@Override
	public void update() {
		super.update();
		barrierTime++;
		barrierInterval = Math.max(barrierInterval-1, 0);
		beamInterval--;
		if (barrierTime > barrierDuration) {
			super.removeBarrierParts();
		}
	}
	
	@Override 
	public void render(Graphics gra) {
		super.render(gra);
		if (!dying) {
			if (playerLevel.hasBeam) {
				Utils.renderPlayerBeamCharge(gra, MyColors.PlayerColor, (int)beamInterval, x, y);
			}
			if (playerLevel.hasBarrier) {
				entityColor = Utils.getPlayerColor(gra, barrierInterval, barrierRate);
				// バリアが溜まっている時のエフェクト描画
				if (barrierInterval <= 0) {
					gra.setColor(MyColors.PlayerCanBarrierColor);
					for (int i = 0; i < 2; i++) {
						int rX = rnd.nextInt(40)-20;
						int rY = rnd.nextInt(30)-15;
						gra.fillRect(x + rX, y + 10 + rY, 3, 3);
					}
				}
			}
			Utils.displayPlayerHp(gra, maxHp, hp, damaged, time);
		}
		
	}
	
	public PlayerBullet shot() {
		if (isDead || dying) return null;
		if (bulletInterval <= 0) {
			super.setBulletInterval(bulletRate);
			PlayerBullet b = new PlayerBullet(x, y, power);
			b.setColor(MyColors.PlayerColor);
			b.setSize(bulletSize);
			b.setSpeed(bulletSpeed);
			b.entity = this;
			return b;
		} else {
			return null;
		}
	}
	
	public PlayerBeam beam() {
		if (isDead || dying) return null;
		if (beamInterval <= 0) {
			beamInterval = beamRate;
			PlayerBeam b = new PlayerBeam(x, y, power);
			b.setColor(MyColors.PlayerColor);
			b.setSize(bulletSize);
			b.setSpeed(bulletSpeed);
			b.setDuration((int)beamDuration);
			b.enhancePower(beamPowerRatio);
			System.out.println(b.power);
			b.entity = this;
			return b;
		} else {
			return null;
		}
	}
	
	public void barrier() {
		if (barrierInterval <= 0) {
			barrierInterval = barrierRate;
			super.setBarrierParts(PartsCollection.getPlayerBarrier(barrierType));
			barrierTime = 0;
		}
	}

}