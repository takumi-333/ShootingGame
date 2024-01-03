package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import constants.MyColors;
import lib.Utils;

public class ClearScreen {
	
	// "Clear"の表示にかかる時間
	public final static long displayTime = 40;
	
	public static void render(Graphics gra, JPanel p, long time, List<String>clearMessage) {
		displayGameOverMessage(gra, p, time);
		displayClearMessage(gra, p, clearMessage);
	}

	private static void displayGameOverMessage(Graphics gra, JPanel p, long time) {
		
		String title = "CLEAR!!";
		Font font = new Font("SansSerif", Font.PLAIN, 50);
		gra.setFont(font);
	    int titleX = (p.getWidth() - gra.getFontMetrics().stringWidth(title)) / 2;
	    int titleY =  2 * p.getHeight() / 5 ;
	    
	    // だんだん透明度を下げていく処理
	    if (time <= displayTime) {
	    	int alpha = Utils.calAlphaByTime(time, displayTime);
	    	Utils.setAlpha(gra, MyColors.TextBlack, alpha);
	    	
	    } else {
	    	gra.setColor(MyColors.TextBlack);
	    }
	    
	    gra.drawString(title, titleX, titleY);
	    
	    if (time > displayTime) {
	    	if (time % 40 <= 35) {
				// 指示文の表示
			    String str = "スタート画面： ";
			    String keyString = "ESC";
			    
				font = new Font("SansSerif", Font.PLAIN, 30);
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
	}
	
	private static void displayClearMessage(Graphics gra, JPanel p, List<String> clearMessage) {
		Font font = new Font("SansSerif", Font.PLAIN, 40);
		gra.setFont(font);
		for (int i = 0; i < clearMessage.size(); i++) {
			String str = clearMessage.get(i);
			int strX = (p.getWidth() - gra.getFontMetrics().stringWidth(str)) / 2;
			int strY =  p.getHeight() / 2 + 55 + i * 55;
			gra.setColor(MyColors.TextBlack);
		    gra.drawString(str, strX, strY);
		}

	}
}
