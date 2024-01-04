package shooting;

import java.awt.Graphics;
import java.util.Random;

import constants.EnumEnemyType;
import constants.MyColors;
import constants.PartsCollection;
import lib.Utils;

public class SpecialBossEnemy extends EnemyEntity {
	
	private int beamInterval;
	private int barrierInterval;
	private int beamRate = 350;
	private int barrierRate = 200;
	private int barrierTime = 0;
	private int barrierDuration = 100;
	private int beamDuration = 15;
	private float beamPowerRatio = 1.5f;
	private int beamSize = 4;
	Random rnd;
	private int actionInterval;
	private int actionRate = 10;
	
	
	public SpecialBossEnemy(int x, int y, int coin, int power) {
		super(x, y, coin, power);
		super.movePattern = 1;
		type = EnumEnemyType.BOSS;
		barrierInterval = 0;
		beamInterval = 100;
		barrierTime = 0;
		rnd = new Random();
		bulletRate = 40;
		actionInterval = 0;
		hasBeam = true;
	}
	
	@Override
	public Bullet shot() {
		
		if (beamInterval <= 0 &&  bulletInterval <= 0) {
			int r = rnd.nextInt(6);
			if (r <= 4) return shotBullet();
			else return beam();
		} else if (beamInterval <= 0) {
			return beam();
		} else if (bulletInterval <= 0) {
			return shotBullet();
		}
		else {
			if (time <= 5) {
				return shotBullet();
			} else {
				return null;
			}
		
		}
	}
	
	private EnemyBullet shotBullet() {
		if (isDead || dying) return null;
		if (bulletInterval <= 0) {
			super.setBulletInterval(bulletRate);
			actionInterval = actionRate;
			EnemyBullet b = new EnemyBullet(x, y, power);
			b.setColor(MyColors.PlayerColor);
			b.setSize(bulletSize);
			b.setSpeed(bulletSpeed);
			b.entity = this;
			return b;
		} else {
			return null;
		}
	}
	
	private EnemyBeam beam() {
		if (isDead || dying) return null;
		if (beamInterval <= 0) {
			beamInterval = beamRate;
			actionInterval = actionRate;
			EnemyBeam b = new EnemyBeam(x, y, power);
			b.setColor(MyColors.PlayerColor);
			b.setSize(beamSize);
			b.setDuration((int)beamDuration);
			b.enhancePower(beamPowerRatio);
			b.entity = this;
			return b;
		} else {
			return null;
		}
	}
	
	private void barrier() {
		if (barrierInterval <= 0) {
			barrierInterval = barrierRate;
			super.setBarrierParts(PartsCollection.getBarrierParts(8));
			barrierTime = 0;
		}
	}
	
	@Override 
	public void render(Graphics gra) {
		if (damaged) {
			if (time % 20 >= 10) {
				barrierParts.forEach(part -> {
					gra.setColor(MyColors.BarrierColor);
					gra.fillRect(this.x + part.x, this.y + part.y, part.width, part.height);
				});
				parts.forEach(part -> {
					gra.setColor(entityColor);
					gra.fillRect(this.x + part.x, this.y + part.y, part.width, part.height);
				});
			}
		}
		else if (dying) {
			if (time <= 5) {
				gra.setColor(MyColors.DyingEffectColor);
				gra.fillOval(this.x -3, this.y-3, 6, 6);
//				gra.setColor(MyColors.particleEffectColor);
//				gra.fillOval(this.x - 6,  this.y, 4, 4);
			} else if (time <= 10) {
				gra.setColor(MyColors.DyingEffectColor);
				gra.fillOval(this.x -6, this.y-6, 12, 12);
//				gra.setColor(MyColors.particleEffectColor);
//				gra.fillOval(this.x +3,  this.y -2, 4, 4);
			} else if (time <= 15) {
				gra.setColor(MyColors.DyingEffectColor);
				gra.fillOval(this.x - 9, this.y - 9, 18, 18);
//				gra.setColor(MyColors.particleEffectColor);
//				gra.fillOval(this.x,  this.y +1, 4, 4);
			} else if (time <= 20) {
				gra.setColor(MyColors.DyingEffectColor);
				gra.fillOval(this.x - 12, this.y - 12, 24, 24);
//				gra.setColor(MyColors.particleEffectColor);
//				gra.fillOval(this.x-2,  this.y, 4, 4);
			}
			else {
				isDead = true;
			}
		}
		else {
			barrierParts.forEach(part -> {
				gra.setColor(MyColors.BarrierColor);
				gra.fillRect(this.x + part.x, this.y + part.y, part.width, part.height);
			});
			parts.forEach(part -> {
				gra.setColor(entityColor);
				gra.fillRect(this.x + part.x, this.y + part.y, part.width, part.height);
			});
		}
		if (hasBeam) {
			Utils.renderBeamCharge(gra, entityColor, beamInterval, beamRate, x, y);
		}
	}
	
	
	
	@Override
	public void update() {
		super.update();
		barrierInterval = Math.max(barrierInterval-1, 0);
		beamInterval = Math.max(beamInterval-1, 0);
		actionInterval =  Math.max(actionInterval-1, 0);
		barrierTime++;
		if (barrierTime > barrierDuration) {
			super.removeBarrierParts();
		}
		if (barrierInterval <= 0) {
			barrier();
		}
	}

}
