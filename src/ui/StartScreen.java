package ui;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import constants.MyColors;

public class StartScreen {
	public static void render(Graphics gra, JPanel p, long time, int stageId) {
		displayTitle(gra, p);
		displayGameStartMessage(gra, p, time);
		displayShopMessage(gra, p, time);
		displayStageMessage(gra, p, time);
		displayStage(gra, p, stageId);
	}
	
	private static void displayTitle(Graphics gra, JPanel p) {
		// タイトルの表示
	    String title = "シンプル インベーダー";
	    gra.setColor(MyColors.TitleBlack);
		Font font = new Font("SansSerif", Font.PLAIN, 50);
		gra.setFont(font);
	    int titleX = (p.getWidth() - gra.getFontMetrics().stringWidth(title)) / 2;
	    int titleY =  1 * p.getHeight() / 5 ;
	    gra.drawString(title, titleX, titleY);
	}
	
	private static void displayStage(Graphics gra, JPanel p, int stageId) {
		String title = "ステージ" + String.valueOf(stageId + 1);
	    gra.setColor(MyColors.TextBlack);
		Font font = new Font("SansSerif", Font.PLAIN, 30);
		gra.setFont(font);
	    int titleX = (p.getWidth() - gra.getFontMetrics().stringWidth(title)) / 2;
	    int titleY =  2 * p.getHeight() / 5;
	    gra.drawString(title, titleX, titleY);
	}
	
	private static void displayGameStartMessage(Graphics gra, JPanel p, long time) {
		
		if (time % 40 <= 35) {
			// 指示文の表示
		    String str = "ゲームスタート： ";
		    String keyString = "SPACE";
		    
			Font font = new Font("SansSerif", Font.PLAIN, 30);
			gra.setFont(font);
			int strW = gra.getFontMetrics().stringWidth(str);
			int keyStringW = gra.getFontMetrics().stringWidth(keyString);
			
		    int strX = (p.getWidth() - strW - keyStringW) / 2;
		    int strY =  p.getHeight() / 2 ;
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
	
	private static void displayShopMessage(Graphics gra, JPanel p, long time) {
		
		if (time % 40 <= 35) {
			// 指示文の表示
		    String str = "メニュー： ";
		    String keyString = "M";
		    
			Font font = new Font("SansSerif", Font.PLAIN, 30);
			gra.setFont(font);
			int strW = gra.getFontMetrics().stringWidth(str);
			int keyStringW = gra.getFontMetrics().stringWidth(keyString);
			
		    int strX = (p.getWidth() - strW - keyStringW) / 2;
		    int strY =  p.getHeight() / 2 + 50;
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
	
	private static void displayStageMessage(Graphics gra, JPanel p, long time) {
		
		if (time % 40 <= 35) {
			// 指示文の表示
		    String str = "ステージ選択： ";
		    String keyString = "S";
		    
			Font font = new Font("SansSerif", Font.PLAIN, 30);
			gra.setFont(font);
			int strW = gra.getFontMetrics().stringWidth(str);
			int keyStringW = gra.getFontMetrics().stringWidth(keyString);
			
		    int strX = (p.getWidth() - strW - keyStringW) / 2;
		    int strY =  p.getHeight() / 2  + 100;
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
}
