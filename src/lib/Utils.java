package lib;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import constants.MyColors;
import constants.WindowSize;
import shooting.PlayerLevel;

public class Utils {
	
	public static void setAlpha(Graphics g, Color color, int alpha) {
		int R = color.getRed();
		int G = color.getGreen();
		int B = color.getBlue();
		g.setColor(new Color(R, G, B, alpha));
	}
	
	public static int calAlphaByTime(long time, long maxTime) {
		int alpha = 255;
		alpha = (int)(alpha * ((float)time / (float)maxTime));
		return alpha;
	}
	
	public static void renderBeamCharge(Graphics gra, Color entityColor, int interval, int bulletRate, int x, int y) {
		float ratio = (float)interval / (float) bulletRate;
		gra.setColor(entityColor);
		if (ratio <= 0.1f) {
			gra.fillOval(x-7, y, 14, 14);
		}
		else if (ratio <= 0.3f) {
			gra.fillOval(x-5, y, 10, 10);
		}
		else if (ratio <= 0.5f) {
			gra.fillOval(x-4, y, 8, 8);
		}
		else if (ratio <= 0.7f) {
			gra.fillOval(x-3, y, 6, 6);
		}
		else if (ratio <= 0.9f) {
			gra.fillOval(x-2, y, 4, 4);
		}
	}  
	
	public static void renderPlayerBeamCharge(Graphics gra, Color entityColor, int interval, int x, int y) {
		gra.setColor(entityColor);
		if (interval <= 5) {
			gra.fillOval(x-5, y-10, 10, 10);
		} else if (interval <= 10) {
			gra.fillOval(x-4, y-8,8, 8);
		} else if (interval <= 15) {
			gra.fillOval(x-3, y-6, 6, 6);
		} else if (interval <= 20) {
			gra.fillOval(x-2,  y-4,  4, 4);
		}
	}  
	
	public static Color getPlayerColor(Graphics gra, long interval, long rate) {
		float ratio = (float)interval / (float) rate;
		if (ratio <= 0.0f) {
			return MyColors.PlayerCanBarrierColor;
		} else if (ratio <= 0.2f) {
			return MyColors.PlayerColor3;
		} else if (ratio <= 0.5f) {
			return MyColors.PlayerColor2;
		} else {
			return MyColors.PlayerColor;
		}
	}
	
	public static void displayPlayerHp(Graphics gra, int maxHp, int hp, boolean damaged, long time) {
		float ratio = (float)hp / (float)maxHp;
		int width = (int)Math.ceil(150 * ratio);
		if (ratio <= 0.2f) {
			gra.setColor(MyColors.RedHpColor); 
		} else if (ratio <= 0.4f) {
			gra.setColor(MyColors.OrangeHpColor);
		} else {
			gra.setColor(MyColors.GreenHpColor);
		}
		if (damaged) {
			if (time % 100 >= 10) gra.fillRect(30, WindowSize.y - 70, width, 15);
		} else {
			gra.fillRect(30, WindowSize.y - 70, width, 15);
		}
		
	}
	
	public static void save(PlayerLevel playerLevel, int coins, int selectingData) {
		SaveData data = new SaveData();
		String str = "save" + String.valueOf(selectingData) + ".dat";
		data.setData(playerLevel, coins);
		try {
			data.save(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 左上の座標と、半径
	public static void drawStar(Graphics gra, int x, int y, int r) {
		gra.setColor(MyColors.StarColor);
		int cx = x + r;
		int cy = y + r;
		int n = 10;
		int theta = -90;
		int[] posX = new int[n];
		int[] posY = new int[n];
		for(int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				posX[i] = (int)(r * Math.cos(theta * Math.PI / 180) + cx);
				posY[i] = (int)(r * Math.sin(theta * Math.PI / 180) + cy);
			}
			else {
				posX[i] = (int)((r/2) * Math.cos(theta * Math.PI / 180) + cx);
				posY[i] = (int)((r/2) * Math.sin(theta * Math.PI / 180) + cy);
			}
			theta += 360 / n;
		}
		gra.fillPolygon(posX, posY, n);
		
	}

}
