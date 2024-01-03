package ui;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import constants.MyColors;
import constants.StageInfos;
import shooting.PlayerLevel;

public class StageSelectScreen {
	public static void render(Graphics gra, JPanel p, long time, int stageId, PlayerLevel playerLevel) {
		displayHeader(gra, p);
		displayStages(gra, p, stageId, playerLevel);
		displayKeyMessage(gra, p, time);
	}
	
	public static void displayHeader(Graphics gra, JPanel p) {
		// タイトルの表示
		String title = "ステージ選択";
		gra.setColor(MyColors.TextBlack);
		Font font = new Font("SansSerif", Font.PLAIN, 30);
		gra.setFont(font);
	    int titleX = 40;
	    int titleY =  p.getHeight() / 10 ;
	    gra.drawString(title, titleX, titleY);
	}
	
	public static void displayKeyMessage(Graphics gra, JPanel p, long time) {
		if (time % 40 <= 35) {
			// 指示文の表示
		    String str = "ホームヘ： ";
		    String keyString = "BACK";
		    String str2 = " or ";
		    String keyString2 = "ESC";
		    
			Font font = new Font("SansSerif", Font.PLAIN, 22);
			gra.setFont(font);
			int strW = gra.getFontMetrics().stringWidth(str);
			int keyStringW = gra.getFontMetrics().stringWidth(keyString);
			int str2W = gra.getFontMetrics().stringWidth(str2);
			int keyString2W = gra.getFontMetrics().stringWidth(keyString2);
			
			
		    int strX = 40;
		    int strY =  p.getHeight() / 10 + 50 ;
		    int keyStringX = strX + strW;
		    int str2X = keyStringX + keyStringW;
		    int keyString2X = str2X + str2W;
		    
		    // textの背景
		    gra.setColor(MyColors.ButtonBG);
		    gra.fillRect(keyStringX-4, strY-32, keyStringW+8, 40);
		    gra.fillRect(keyString2X-4, strY-32, keyString2W+8, 40);
		    
		    // textの表示
		    gra.setColor(MyColors.TextBlack);
		    gra.drawString(str, strX, strY);
		    gra.drawString(keyString, keyStringX, strY);
		    gra.drawString(str2, str2X, strY);
		    gra.drawString(keyString2, keyString2X, strY);
		    
		    str = "| 選択： ";
		    keyString = "ENTER";
		    
			strW = gra.getFontMetrics().stringWidth(str);
			keyStringW = gra.getFontMetrics().stringWidth(keyString);
			
		    strX = keyString2X + keyString2W + 20;
		    keyStringX = strX + strW;
		    
		    // textの背景
		    gra.setColor(MyColors.ButtonBG);
		    gra.fillRect(keyStringX-4, strY-32, keyStringW+8, 40);
		    
		    // textの表示
		    gra.setColor(MyColors.TextBlack);
		    gra.drawString(str, strX, strY);
		    gra.drawString(keyString, keyStringX, strY);
		}
	}
	
	public static void displayStages(Graphics gra, JPanel p, int stageId, PlayerLevel playerLevel) {
		for (int i = 0; i < StageInfos.numStages; i++) {
			
			String stageString = "STAGE" + String.valueOf(i+1);
			Font font = new Font("SansSerif", Font.PLAIN, 25);
			gra.setFont(font);
			int strX = 65;
			int strY = p.getHeight() / 10 + 60 + 35 * (i + 1);
			int strW = gra.getFontMetrics().stringWidth(stageString);
			
			// 背景の描画
			if (playerLevel.stageLevel <= i) {
				gra.setColor(MyColors.LimitStageBG);
			} else {
				gra.setColor(MyColors.ButtonBG);
			}
			gra.fillRect(strX-4, strY-28, strW+8, 30);
			
			// ステージテキストの描画
			
			gra.setColor(MyColors.TextBlack);
			gra.drawString(stageString, strX, strY);
			
			
			// 選択カーソルの描画
			if (i == stageId) {
				gra.setColor(MyColors.TextBlack);
				gra.fillPolygon(new int[] {strX-25, strX-5, strX-25}, new int[] {strY-24, strY-12, strY}, 3);
			}
			
			// ステージ制限メッセージの描画
			if (playerLevel.stageLevel <= i) {
				String limitString = "未解放";
				gra.setColor(MyColors.ShortCoinText);
				gra.drawString(limitString, strX + strW + 20, strY);
			} 
			
		}
	    
	}

}
