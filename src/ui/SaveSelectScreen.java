package ui;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import constants.MyColors;

public class SaveSelectScreen {
	public static void render(Graphics gra, JPanel p, int selectingData, long time) {
		displayKeyMessage(gra, p, time);
		displayDatas(gra, p, selectingData);
	}
	
	public static void displayKeyMessage(Graphics gra, JPanel p, long time) {
		if (time % 40 <= 35) {
			// 指示文の表示
		    String str = "選択： ";
		    String keyString = "ENTER";
		    
			Font font = new Font("SansSerif", Font.PLAIN, 30);
			gra.setFont(font);
			int strW = gra.getFontMetrics().stringWidth(str);
			int keyStringW = gra.getFontMetrics().stringWidth(keyString);
			
		    int strX = (p.getWidth() - strW - keyStringW) / 2;
		    int strY =  p.getHeight() / 5 ;
		    int keyStringX = strX + strW;
		    
		    // textの背景
		    gra.setColor(MyColors.ButtonBG);
		    gra.fillRect(keyStringX-4, strY-32, keyStringW+8, 40);
		    
		    // textの表示
		    gra.setColor(MyColors.TextBlack);
		    gra.drawString(str, strX, strY);
		    gra.drawString(keyString, keyStringX, strY);
		}
	}
	
	public static void displayDatas(Graphics gra, JPanel p, int selectingData) {
		for (int i = 1; i < 4; i++) {
			String str = "セーブデータ" + String.valueOf(i);
			int strX = 80;
			int strY = (i+1) * p.getHeight() / 5;
			Font font = new Font("SansSerif", Font.PLAIN, 50);
			gra.setFont(font);
			gra.setColor(MyColors.TextBlack);
			gra.drawString(str, strX, strY);
			
			if (i == selectingData) {
				gra.setColor(MyColors.TextBlack);
				gra.fillPolygon(new int[] {strX-25, strX-5, strX-25}, new int[] {strY-24, strY-12, strY}, 3);
			}
			
		}
	}
}
