package shooting;

import java.awt.Graphics;

import constants.EnumBulletType;
import constants.WindowSize;

public class EnemyBullet extends Bullet{
	public EnemyBullet(int x, int y) {
		super(x, y);
	}
	
	public EnemyBullet(int x, int y, EnumBulletType type) {
		super(x, y, type);
	}
	
	public EnemyBullet(int x, int y, int power) {
		super(x, y, power);
	}
	
	public EnemyBullet(int x, int y, int power, EnumBulletType type) {
		super(x, y, power, type);
	}
	
	@Override
	public void render(Graphics gra) {
		y += speed;
		super.render(gra);
		if (y > WindowSize.y + 10) super.isUnnecessary = true;
	}
}
