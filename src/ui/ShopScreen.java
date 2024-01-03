package ui;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import constants.MyColors;
import constants.PlayerParameter;
import constants.WindowSize;
import shooting.Player;
import shooting.PlayerLevel;

public class ShopScreen {
	public static void render(Graphics gra, JPanel p, long time, Player player, PlayerLevel playerLevel, int selectingLevel) {
		displayHeader(gra, p);
		displayCoins(gra, p, player);
		displayLevels(gra, p, player, playerLevel, selectingLevel);
		displayKeyMessage(gra, p, time);
	}
	
	public static void displayHeader(Graphics gra, JPanel p) {
		// タイトルの表示
		String title = "メニュー";
		gra.setColor(MyColors.TextBlack);
		Font font = new Font("SansSerif", Font.PLAIN, 30);
		gra.setFont(font);
	    int titleX = 40;
	    int titleY =  p.getHeight() / 10 ;
	    gra.drawString(title, titleX, titleY);
	}
	
	public static void displayCoins(Graphics gra, JPanel p, Player player) {
		
		String str = "コイン " + String.valueOf(player.getCoins()) + "G";
		gra.setColor(MyColors.TextBlack);
		Font font = new Font("SansSerif", Font.PLAIN, 30);
		gra.setFont(font);
		int strW = gra.getFontMetrics().stringWidth(str);
		int titleX = WindowSize.x - strW - 20;
	    int titleY =  p.getHeight() / 10 ;
	    gra.drawString(str, titleX, titleY);
		
	}
	
	public static void displayKeyMessage(Graphics gra, JPanel p, long time) {
		if (time % 40 <= 35) {
			// 指示文の表示
		    String str = "ホームヘ： ";
		    String keyString = "BACK";
		    String str2 = " or ";
		    String keyString2 = "ESC";
		    
			Font font = new Font("SansSerif", Font.PLAIN, 20);
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
		    
		    str = "| レベルアップ： ";
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
	
	public static void displayLevels(Graphics gra, JPanel p, Player player, PlayerLevel playerLevel, int selectingLevel) {
		
		int maxStrW = 0;
		int paramStrX = 65;
		Font font = new Font("SansSerif", Font.PLAIN, 30);
		gra.setFont(font);
		
		// 一番長い文字列の計算
		for (int i = 0; i < PlayerParameter.parameters.size(); i++) {
			maxStrW = Math.max(maxStrW, gra.getFontMetrics().stringWidth(PlayerParameter.parameters.get(i)));
		}
		
		// 各パラメータの描画
		for (int i = 0; i < PlayerParameter.parameters.size(); i++) {
			// バリアとビーム以外の描画
			if (i < 6) {
				String paramString = PlayerParameter.parameters.get(i);
				int level = playerLevel.getLevelByIndex(i);
				String levelString = "Lv. " + String.valueOf(level);
				
				int paramStrY = p.getHeight() / 10 + 60 + 45 * (i + 1);
				int levelStrW = gra.getFontMetrics().stringWidth(levelString);
				gra.setColor(MyColors.TextBlack);
				gra.drawString(paramString, paramStrX, paramStrY);
				gra.drawString(levelString, paramStrX + maxStrW + 10, paramStrY);
				
				
				if (level < PlayerParameter.maxLevel) {
					int needCoin = PlayerParameter.needCoin[level-1];
					String needCoinString = String.valueOf(needCoin) + "G 必要";
					// coinが足りるか足りないか
					if (player.getCoins() < needCoin) {
						gra.setColor(MyColors.ShortCoinText);
					} else {
						gra.setColor(MyColors.TextBlack);
					}
					gra.drawString(needCoinString, paramStrX + maxStrW + levelStrW + 35, paramStrY);
				} else {
					String needCoinString = "MAX";
					gra.setColor(MyColors.TextBlack);
					gra.drawString(needCoinString, paramStrX + maxStrW + levelStrW + 35, paramStrY);
				}
				if (i == selectingLevel) {
					gra.setColor(MyColors.TextBlack);
					gra.fillPolygon(new int[] {paramStrX-25, paramStrX-5, paramStrX-25}, new int[] {paramStrY-24, paramStrY-12, paramStrY}, 3);
				}
			} else if (i == 6) {
				
				// バリア持っているとき
				if (playerLevel.hasBarrier) {
					String paramString = PlayerParameter.parameters.get(i);
					int level = playerLevel.getLevelByIndex(i);
					String levelString = "Lv. " + String.valueOf(level);
					
					int paramStrY = p.getHeight() / 10 + 60 + 45 * (i + 1);
					int levelStrW = gra.getFontMetrics().stringWidth(levelString);
					gra.setColor(MyColors.TextBlack);
					gra.drawString(paramString, paramStrX, paramStrY);
					gra.drawString(levelString, paramStrX + maxStrW + 10, paramStrY);
					
					
					if (level < PlayerParameter.maxLevel) {
						int needCoin = PlayerParameter.needCoin[level-1];
						String needCoinString = String.valueOf(needCoin) + "G 必要";
						// coinが足りるか足りないか
						if (player.getCoins() < needCoin) {
							gra.setColor(MyColors.ShortCoinText);
						} else {
							gra.setColor(MyColors.TextBlack);
						}
						gra.drawString(needCoinString, paramStrX + maxStrW + levelStrW + 35, paramStrY);
					} else {
						String needCoinString = "MAX";
						gra.setColor(MyColors.TextBlack);
						gra.drawString(needCoinString, paramStrX + maxStrW + levelStrW + 35, paramStrY);
					}
					if (i == selectingLevel) {
						gra.setColor(MyColors.TextBlack);
						gra.fillPolygon(new int[] {paramStrX-25, paramStrX-5, paramStrX-25}, new int[] {paramStrY-24, paramStrY-12, paramStrY}, 3);
					}
					// バリア持っていないとき
				} else {
					String limitString = "未解放";
					int paramStrY = p.getHeight() / 10 + 60 + 45 * (i + 1);
					gra.setColor(MyColors.ShortCoinText);
					gra.drawString(limitString, paramStrX, paramStrY);
				}
			} else if (i == 7) {
				//ビーム持っているとき
				if (playerLevel.hasBeam) {
					String paramString = PlayerParameter.parameters.get(i);
					int level = playerLevel.getLevelByIndex(i);
					String levelString = "Lv. " + String.valueOf(level);
					
					int paramStrY = p.getHeight() / 10 + 60 + 45 * (i + 1);
					int levelStrW = gra.getFontMetrics().stringWidth(levelString);
					gra.setColor(MyColors.TextBlack);
					gra.drawString(paramString, paramStrX, paramStrY);
					gra.drawString(levelString, paramStrX + maxStrW + 10, paramStrY);
					
					
					if (level < PlayerParameter.maxLevel) {
						int needCoin = PlayerParameter.needCoin[level-1];
						String needCoinString = String.valueOf(needCoin) + "G 必要";
						// coinが足りるか足りないか
						if (player.getCoins() < needCoin) {
							gra.setColor(MyColors.ShortCoinText);
						} else {
							gra.setColor(MyColors.TextBlack);
						}
						gra.drawString(needCoinString, paramStrX + maxStrW + levelStrW + 35, paramStrY);
					} else {
						String needCoinString = "MAX";
						gra.setColor(MyColors.TextBlack);
						gra.drawString(needCoinString, paramStrX + maxStrW + levelStrW + 35, paramStrY);
					}
					if (i == selectingLevel) {
						gra.setColor(MyColors.TextBlack);
						gra.fillPolygon(new int[] {paramStrX-25, paramStrX-5, paramStrX-25}, new int[] {paramStrY-24, paramStrY-12, paramStrY}, 3);
					}
					// ビーム持っていないとき
				} else {
					String limitString = "未解放";
					int paramStrY = p.getHeight() / 10 + 60 + 45 * (i + 1);
					gra.setColor(MyColors.ShortCoinText);
					gra.drawString(limitString, paramStrX, paramStrY);
					
				}
				
			}
		}
	}
}
