package shooting;

import java.awt.Graphics;

import constants.WindowSize;

public class EnemyBeam extends Beam{
	public EnemyBeam(int x, int y, int power) {
		super(x, y, power);
	}
	
	@Override
	public void render(Graphics gra) {
		super.render(gra);
		gra.setColor(bulletColor);
		this.x = entity.x;
		this.y = entity.y;
		gra.fillRect(x-(size/2), y, size, WindowSize.y);
	}
}