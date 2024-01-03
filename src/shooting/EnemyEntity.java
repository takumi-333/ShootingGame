package shooting;

import java.awt.Graphics;
import java.util.Random;

import constants.EnumEnemyType;
import constants.MyColors;
import constants.PartsCollection;
import constants.WindowSize;
import lib.Utils;

public class EnemyEntity extends Entity{
	protected boolean canMove;
	protected int moveRange;
	protected int moveSpeed;
	protected EnumEnemyType type;
	protected int bulletSpeed;
	protected boolean hasBeam = false;
	protected int movePattern = 0;
	
	// ステータス関連
	public int power;
	public int coin;
	private int bulletSize;
	private int deltaX;
	
	public EnemyEntity(int x, int y, int coin, int power) {
		super(x,y);
		setParts(PartsCollection.defaultEnemyParts());
		super.setHp(1);
		int maxCoin = (int) (1.1 * coin);
		int minCoin = (int) (0.9 * coin);
		Random random = new Random();
		this.coin = random.nextInt(maxCoin - minCoin) + minCoin;
		this.power = power;
		type = EnumEnemyType.DEFAULT;
		moveSpeed = 5;
		entityColor = MyColors.DefaultEnemyColor;
		deltaX = 0;
		bulletSpeed = 5;
		bulletSize = 6;
		bulletInterval = 20;
	}
	
	public void setMoveSpeed(int speed) {
		this.moveSpeed = speed;
	}
	
	public void setBulletSize(int size) {
		bulletSize = size;
	}
	
	public void setType(EnumEnemyType type) {
		this.type = type;
	}
	
	public void setBulletSpeed(int speed) {
		bulletSpeed = speed;
	}
	
	public int getCoin() {
		return this.coin;
	}
	
	public EnumEnemyType getType() {
		return this.type;
	}
	
	public Bullet shot() {
		if (bulletInterval <= 0) {
			super.setBulletInterval(bulletRate);
			Bullet b;
			if (!hasBeam) {
				b = new EnemyBullet(x, y, power);
			} else {
				b = new EnemyBeam(x, y, power);
			}
			b.setColor(entityColor);
			b.setSpeed(bulletSpeed);
			b.setSize(bulletSize);
			b.entity = this;
			return b;
		} else {
			return null;
		}
	}
	
	@Override 
	public void render(Graphics gra) {
		super.render(gra);
		if (hasBeam) {
			Utils.renderBeamCharge(gra, entityColor, bulletInterval, bulletRate, x, y);
		}
	}
	
	
	@Override
	public void move() {
		if (!dying) {
			switch(type) {
			case DEFAULT:
				// 移動方向の決定
				if (x <= 10) {
					moveSpeed = -moveSpeed;
				}
				else if (x >= WindowSize.x-10) {
					moveSpeed = -moveSpeed;
				}
				// 移動処理
				if (y <= 150) {
					y += moveSpeed;
				} else {
					x += moveSpeed;
				}
				break;
			case ATTACKER:
				break;
			case BOSS:
				if (y <= 100) {
					y += 3;
				}
				else {
					moveSpeed = BossMove.getDelta(x, deltaX, moveSpeed, time, movePattern);
					x += moveSpeed;
					deltaX += moveSpeed;
				}
				break;
			}
		}
		
	}

}
