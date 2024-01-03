package shooting;

import java.awt.Graphics;

import constants.EnumBulletType;

public class PlayerBullet extends Bullet{
	public PlayerBullet(int x, int y) {
		super(x, y);
	}
	
	public PlayerBullet(int x, int y, EnumBulletType type) {
		super(x, y, type);
	}
	
	public PlayerBullet(int x, int y, int power) {
		super(x, y, power);
	}
	
	public PlayerBullet(int x, int y, int power, EnumBulletType type) {
		super(x, y, power, type);
	}
	
	@Override
	public void render(Graphics gra) {
		y-= speed;
		super.render(gra);
		if (y < 0) super.isUnnecessary = true;
	}
}
