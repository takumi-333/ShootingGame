package shooting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import constants.EnumBulletType;
import constants.MyColors;

public class Entity {
	
	protected List<Rectangle> parts;
	protected List<Rectangle> barrierParts;
	protected Color entityColor; 
	protected int x;
	protected int y;
	protected int hp;
	
	public int bulletRate = 20;
	public int bulletInterval = 0;
	
	//damageを食らう間隔
	protected boolean damaged;
	protected int damageInterval = 0;
	protected int damageRate = 40;
	
	// 死に際の処理のフラグ
	public boolean isDead;
	protected boolean dying;
	 
	protected long time = 0;
	
	//コンストラクタ
	public Entity() {
		parts = new ArrayList<Rectangle>();
		barrierParts = new ArrayList<Rectangle>();
		damaged = false;
		isDead = false;
		dying = false;
	}
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		parts = new ArrayList<Rectangle>();
		barrierParts = new ArrayList<Rectangle>();
		damaged = false;
		isDead = false;
		dying = false;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	
	public void setParts(List<Rectangle> parts) {
		this.parts = parts;
	}
	
	public void setBarrierParts(List<Rectangle> parts) { 
		barrierParts = parts;
	}
	
	public void setBulletRate(int rate) {
		bulletRate = rate;
	}
	
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
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setBulletInterval(int t) {
		bulletInterval = t;
	}
	
	public void setEntityColor(Color color) {
		entityColor = color;
	}
	
	public void removeBarrierParts() {
		barrierParts = new ArrayList<Rectangle>();
	}
	
	public void move() {
		
	}
	
	public void update() {
		time++;
		bulletInterval--;
		if (damageInterval > 0) {
			damageInterval--;
		} else {
			damaged = false;
		}
	}
	
	// 各Entityによって異なる
	private void setParts() {
		
	}
	
	public void handleHit(List<Bullet> bullets, List<Bullet> anotherBullets) {
		if (!damaged && !dying) {
			for (int i = 0; i < bullets.size(); i++) {
				Bullet bullet = bullets.get(i);
				// Beamは跳ね返せない
				if (bullet.bulletType != EnumBulletType.Beam) {
					for (Rectangle part: barrierParts) {
						if ((bullet.x >= this.x + part.x) && (bullet.x <= this.x + part.x + part.width) &&
								(bullet.y >= this.y + part.y - part.height) && (bullet.y <= this.y + part.y + part.height) ||
								(bullet.x+bullet.size >= this.x + part.x) && (bullet.x+bullet.size <= this.x + part.x + part.width) &&
								(bullet.y >= this.y + part.y - part.height) && (bullet.y <= this.y + part.y + part.height)||
								(bullet.x >= this.x + part.x) && (bullet.x <= this.x + part.x + part.width) &&
								(bullet.y +bullet.size >= this.y + part.y - part.height) && (bullet.y + bullet.size <= this.y + part.y + part.height) ||
								(bullet.x+bullet.size >= this.x + part.x) && (bullet.x+bullet.size <= this.x + part.x + part.width) &&
								(bullet.y +bullet.size >= this.y + part.y - part.height) && (bullet.y + bullet.size <= this.y + part.y + part.height)) {
							bullet.setSpeed(-bullet.speed);
							anotherBullets.add(bullet);
							bullets.remove(bullet);
							return;
						}
					}
					
					for (Rectangle part: parts) {
						if ((bullet.x >= this.x + part.x) && (bullet.x <= this.x + part.x + part.width) &&
								(bullet.y >= this.y + part.y - part.height) && (bullet.y <= this.y + part.y + part.height) ||
								(bullet.x+bullet.size >= this.x + part.x) && (bullet.x+bullet.size <= this.x + part.x + part.width) &&
								(bullet.y >= this.y + part.y - part.height) && (bullet.y <= this.y + part.y + part.height)||
								(bullet.x >= this.x + part.x) && (bullet.x <= this.x + part.x + part.width) &&
								(bullet.y +bullet.size >= this.y + part.y - part.height) && (bullet.y + bullet.size <= this.y + part.y + part.height) ||
								(bullet.x+bullet.size >= this.x + part.x) && (bullet.x+bullet.size <= this.x + part.x + part.width) &&
								(bullet.y +bullet.size >= this.y + part.y - part.height) && (bullet.y + bullet.size <= this.y + part.y + part.height)) {
							this.hp = this.hp - bullet.power;
							if (hp <= 0) {
								dying = true;
							}
							else {
								damaged = true;
								damageInterval = damageRate;
							}
							time = 0;
							bullets.remove(bullet);
							return;
						}
					}
				}
				// Beamのとき
				else {
					for (Rectangle part: parts) {
						if ((bullet.x >= this.x + part.x)&& (bullet.x <= this.x + part.x + part.width) || 
								(bullet.x+(bullet.size/2) >= this.x + part.x) && (bullet.x+(bullet.size/2) <= this.x + part.x + part.width)) {
							this.hp = this.hp - bullet.power;
							if (hp <= 0) {
								dying = true;
							}
							else {
								damaged = true;
								damageInterval = damageRate;
							}
							time = 0;
							return;
						}
					}
				}
			}
		}
	}
}
