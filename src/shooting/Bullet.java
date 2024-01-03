package shooting;

import java.awt.Color;
import java.awt.Graphics;

import constants.EnumBulletType;

public class Bullet {
	public int x;
	public int y;
	protected int size= 6;
	protected Color bulletColor = Color.black;
	protected EnumBulletType bulletType = EnumBulletType.Circle;
	public int power = 1;
	protected int speed = 5;
	public boolean isUnnecessary = false;
	public Entity entity;
	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public Bullet(int x, int y, EnumBulletType type) {
		this.x = x;
		this.y = y;
		this.bulletType = type;
	}
	
	public Bullet(int x, int y, int power) {
		this.x = x;
		this.y = y;
		this.power = power;
	}
	
	public Bullet(int x, int y, int power, EnumBulletType type) {
		this.x = x;
		this.y = y;
		this.power = power;
		this.bulletType = type;
	}
	
	public void setColor(Color color) {
		bulletColor = color;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void render(Graphics gra) {
		gra.setColor(bulletColor);
		switch(bulletType) {
		case Circle:
			gra.fillOval(x-size, y-size, size, size);
			break;
		case Rect:
			gra.fillRect(x-size, y-size, size, size);
			break;
		case Beam:
			break;
		}
	}
}
