package shooting;

import java.awt.Graphics;

import constants.EnumBulletType;

public class Beam extends Bullet{
	private int duration = 10;
	private int time = 0;
	
	public Beam(int x, int y, int power) {
		super(x,y, power);
		super.setSpeed(0);
		super.bulletType = EnumBulletType.Beam;
		super.size = 20;
	}
	
	public void enhancePower(float ratio) {
		super.power = (int)Math.ceil(power * ratio - 0.49f);
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Override
	public void render(Graphics gra) {
		time++;
		if (time > duration) super.isUnnecessary = true;
	}
}
