package ui;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import constants.MyColors;
import constants.WindowSize;

public class StartScreen {
	public static void render(Graphics gra, MainPanel p, long time, int stageId, int startScreenY) {
		
		// 下のページにいないときは、この画面のみを表示
		if (startScreenY > -WindowSize.y) {
			displayTitle(gra, p, startScreenY);
			displayGameStartMessage(gra, p, time, startScreenY);
			displayShopMessage(gra, p, time, startScreenY);
			displayStageMessage(gra, p, time, startScreenY);
			displayStage(gra, p, stageId, startScreenY);
			displayDownMessage(gra, p, time, startScreenY);
		}
		
		// 上のページにいないときは、この画面のみを表示
		if (startScreenY < 0) {
			
			displayDefaultManual(gra, p, startScreenY);
		}
		
	}
	
	private static void displayTitle(Graphics gra, JPanel p, int y) {
		// タイトルの表示
	    String title = "シンプル インベーダー";
	    gra.setColor(MyColors.TitleBlack);
		Font font = new Font("SansSerif", Font.PLAIN, 50);
		gra.setFont(font);
	    int titleX = (p.getWidth() - gra.getFontMetrics().stringWidth(title)) / 2;
	    int titleY =  1 * p.getHeight() / 5  + y;
	    gra.drawString(title, titleX, titleY);
	}
	
	private static void displayStage(Graphics gra, JPanel p, int stageId, int y) {
		String title = "ステージ" + String.valueOf(stageId + 1);
	    gra.setColor(MyColors.TextBlack);
		Font font = new Font("SansSerif", Font.PLAIN, 30);
		gra.setFont(font);
	    int titleX = (p.getWidth() - gra.getFontMetrics().stringWidth(title)) / 2;
	    int titleY =  2 * p.getHeight() / 5 + y;
	    gra.drawString(title, titleX, titleY);
	}
	
	private static void displayGameStartMessage(Graphics gra, JPanel p, long time, int y) {
		
		if (time % 40 <= 35) {
			// 指示文の表示
		    String str = "ゲームスタート： ";
		    String keyString = "SPACE";
		    
			Font font = new Font("SansSerif", Font.PLAIN, 30);
			gra.setFont(font);
			int strW = gra.getFontMetrics().stringWidth(str);
			int keyStringW = gra.getFontMetrics().stringWidth(keyString);
			
		    int strX = (p.getWidth() - strW - keyStringW) / 2;
		    int strY =  p.getHeight() / 2  + y;
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
	
	private static void displayShopMessage(Graphics gra, JPanel p, long time, int y) {
		
		if (time % 40 <= 35) {
			// 指示文の表示
		    String str = "メニュー： ";
		    String keyString = "M";
		    
			Font font = new Font("SansSerif", Font.PLAIN, 30);
			gra.setFont(font);
			int strW = gra.getFontMetrics().stringWidth(str);
			int keyStringW = gra.getFontMetrics().stringWidth(keyString);
			
		    int strX = (p.getWidth() - strW - keyStringW) / 2;
		    int strY =  p.getHeight() / 2 + 50 + y;
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
	
	private static void displayStageMessage(Graphics gra, JPanel p, long time, int y) {
		
		if (time % 40 <= 35) {
			// 指示文の表示
		    String str = "ステージ選択： ";
		    String keyString = "S";
		    
			Font font = new Font("SansSerif", Font.PLAIN, 30);
			gra.setFont(font);
			int strW = gra.getFontMetrics().stringWidth(str);
			int keyStringW = gra.getFontMetrics().stringWidth(keyString);
			
		    int strX = (p.getWidth() - strW - keyStringW) / 2;
		    int strY =  p.getHeight() / 2  + 100 + y;
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
	
	private static void displayDownMessage(Graphics gra, JPanel p, long time, int y) {
		
		if (time % 40 <= 35) {
			// 指示文の表示
		    String str = "操作説明： ";
		    String keyString = "↓";
		    
			Font font = new Font("SansSerif", Font.PLAIN, 30);
			gra.setFont(font);
			int strW = gra.getFontMetrics().stringWidth(str);
			int keyStringW = gra.getFontMetrics().stringWidth(keyString);
			
		    int strX = (p.getWidth() - strW - keyStringW) / 2;
		    int strY =  p.getHeight() / 2  + 150 + y;
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
	 
	private static void displayDefaultManual(Graphics gra, MainPanel p, int y) {
	}
}
