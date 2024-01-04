package shooting;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;

import constants.EnumDirection;
import constants.EnumEnemyType;
import constants.EnumScreen;
import constants.MyColors;
import constants.PlayerParameter;
import constants.StageInfos;
import constants.WindowSize;
import lib.Keyboard;
import lib.SaveData;
import lib.Utils;
import ui.ClearScreen;
import ui.GameFrame;
import ui.GameOverScreen;
import ui.GameScreen;
import ui.MainPanel;
import ui.SaveSelectScreen;
import ui.ShopScreen;
import ui.StageSelectScreen;
import ui.StartScreen;

public class Main {
	public static GameFrame gameFrame;
	public static MainPanel panel;
	public static boolean loop;
	public static Graphics gra;
	
	public static EnumScreen screen;
	
	private static int fps;
	private static long startTime;
	private static long time;
	
	// stage関連
	private static int stageId;
	private static Stage stage;
	private static int selectingStage;
	
	// level関連
	private static int selectingLevel;
	private static int menuCursorInterval;
	private static int menuCursorRate = 6;
	
	private static List<EnemyEntity> enemyEntitys;
	private static List<Bullet> enemyBullets;
	private static List<Bullet> playerBullets;
	
	private static List<String>clearMessage;
	
	private static Player player;
	private static PlayerLevel playerLevel;
	private static Clip mainBGM;
	
	private static int selectingData;
	
	private static int startScreenY = 0;
	private static boolean isUpMoving = false;
	private static boolean isDownMoving = false;
	private static int movingDuration = 20;
	private static int movingTime = 0;
	
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(() -> {
            gameFrame = new GameFrame();
            // ウィンドウが閉じられたときの処理を追加
            gameFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    loop = false;
                }
            });
            
            try {
	            ClassLoader classLoader = Main.class.getClassLoader();
	            InputStream inputStream = classLoader.getResourceAsStream("Resources/BattleBGM.wav");
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
	
	            // Clipを作成
	            mainBGM = AudioSystem.getClip();
	
	            // Clipにオーディオデータを読み込む
	            mainBGM.open(audioInputStream);
	            
	
            } catch(Exception e) {
            	e.printStackTrace();
            }
    		
            panel = gameFrame.panel;
            loop = true;
            time = 0;
            gra = gameFrame.panel.image.getGraphics();
            screen = EnumScreen.SAVE_SELECT;
            fps = 30;
            stageId = 0;
            player = new Player();
            playerLevel = new PlayerLevel();
            selectingData = 1;
            System.out.println("hello");
            
            
            new Thread(() -> {
                gameLoop();
            }).start();
        });
	}
	
	private static void gameLoop() {
		while(loop) {
			startTime = System.currentTimeMillis();
			
			handleInput();
			update();
			render();
			
			// fpsで指定されたFPSになるようにsleepTimeを計算
			long sleepTime = Math.max((1000 / fps) - (System.currentTimeMillis() - startTime), 0);
			
			// 待機処理
			try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			
			time += 1;
			
			//Debug用
			if (Keyboard.isKeyPressed(KeyEvent.VK_D)) {
				System.out.println(startScreenY);
	        	player.gainCoin(20000);
	        }
			if (Keyboard.isKeyPressed(KeyEvent.VK_P)) {
				playerLevel.hasBarrier = true;
	        }
			if (Keyboard.isKeyPressed(KeyEvent.VK_O)) {
				playerLevel.hasBeam = true;
	        }
			if (Keyboard.isKeyPressed(KeyEvent.VK_I)) {
				playerLevel.stageLevel++;
	        }
		}
	}
	
	// 更新処理
	private static void update() {
		switch(screen) {
		case START:
			if (isUpMoving || isDownMoving) {
				movingTime = Math.min(movingTime+1, movingDuration);
				if (isDownMoving) {
					startScreenY = -(int)(WindowSize.y * ((float)movingTime / (float)movingDuration));
				} else {
					startScreenY = -(int)(WindowSize.y * (1.0f - (float)movingTime / (float)movingDuration));
				}
				if (movingTime == movingDuration) {
					isUpMoving = false;
					isDownMoving = false;
					movingTime = 0;
					break;
				}
				
			}
			break;
		case GAME:
			// playerの状態のupdate
			player.update();
			
			// stageのupdate
			stage.update(enemyEntitys);
			
			// 敵の移動処理とupdate
			enemyEntitys.forEach(enemy -> {
				enemy.update();
				enemy.move();
				Bullet eb = enemy.shot();
				if (eb != null) enemyBullets.add(eb);
			});
			
			player.handleHit(enemyBullets, playerBullets);
			enemyEntitys.forEach(enemy->{
				enemy.handleHit(playerBullets, enemyBullets);
			});
			
			
			// 不要な敵の弾の削除
			for (int i = 0; i < enemyBullets.size(); i++) {
				Bullet bu = enemyBullets.get(i);
				if (bu.isUnnecessary) enemyBullets.remove(bu);
			}
			
			// 不要なplayerの弾の削除
			for (int i = 0; i < playerBullets.size(); i++) {
				Bullet bu = playerBullets.get(i);
				if (bu.isUnnecessary) playerBullets.remove(bu);
			}
			
			// 死んだ敵の削除
			for (int i = 0; i < enemyEntitys.size(); i++) {
				EnemyEntity en = enemyEntitys.get(i);
				if (en.isDead) {
					if (en.getType() == EnumEnemyType.BOSS) {
						stage.defeatEnemy();
					}
					player.gainCoin(en.getCoin());
					enemyEntitys.remove(en);
				}
			}
			
			// CLEAR判定
			if (stage.isFinished) {
				// ステージ初クリア時の処理
				if (playerLevel.stageLevel == stageId+1) {
					playerLevel.stageLevel++;
					if (playerLevel.stageLevel <= StageInfos.numStages) {
						clearMessage.add("ステージ" + String.valueOf(playerLevel.stageLevel) + "解放!");
					} else {
						clearMessage.add("全てクリア！");
						clearMessage.add("遊んでくれてありがとう!");
					}
					if (stageId == 2) {
						playerLevel.hasBarrier = true;
						clearMessage.add("バリアを解放しました!");
					} else if (stageId == 4) {
						playerLevel.hasBeam = true;
						clearMessage.add("ビームを解放しました");
					}
				}
				time = 0;
				mainBGM.stop();
				screen = EnumScreen.CLEAR;
			}
			
			// GAME OVER判定
			if (player.isDead) {
				mainBGM.stop();
				time = 0;
				screen = EnumScreen.GAME_OVER;
			}
			
		case STAGE_SELECT:
			menuCursorInterval = Math.max(menuCursorInterval-1, 0);
			break;
		case SHOP:
			menuCursorInterval = Math.max(menuCursorInterval-1, 0);
			break;
		case SAVE_SELECT:
			menuCursorInterval = Math.max(menuCursorInterval-1, 0);
			break;
			

		default:
			break;
		}
		
	}
	
	private static void render() {
		switch(screen) {
		case START:
			// 描画のリセット
			gra.setColor(MyColors.WhiteGray);
	        gra.fillRect(0, 0, WindowSize.x, WindowSize.y);
			StartScreen.render(gra, panel, time, stageId, startScreenY);
			break;
		case GAME:
			// 描画のリセット
			gra.setColor(stage.bgColor);
	        gra.fillRect(0, 0, WindowSize.x, WindowSize.y);
			GameScreen.render(gra, panel, time, player, enemyEntitys, playerBullets, enemyBullets);
			break;
		case GAME_OVER:
			// 描画のリセット
			gra.setColor(MyColors.WhiteGray);
	        gra.fillRect(0, 0, WindowSize.x, WindowSize.y);
			GameOverScreen.render(gra, panel, time);
			break;
		case SHOP:
			// 描画のリセット
			gra.setColor(MyColors.WhiteGray);
	        gra.fillRect(0, 0, WindowSize.x, WindowSize.y);
			ShopScreen.render(gra, panel, time, player, playerLevel, selectingLevel);
			break;
		case CLEAR:
			// 描画のリセット
			gra.setColor(MyColors.WhiteGray);
	        gra.fillRect(0, 0, WindowSize.x, WindowSize.y);
			ClearScreen.render(gra, panel, time, clearMessage);
			break;
		case STAGE_SELECT:
			gra.setColor(MyColors.WhiteGray);
	        gra.fillRect(0, 0, WindowSize.x, WindowSize.y);
			StageSelectScreen.render(gra, panel, time, selectingStage, playerLevel);
			break;
		case SAVE_SELECT:
			gra.setColor(MyColors.WhiteGray);
	        gra.fillRect(0, 0, WindowSize.x, WindowSize.y);
			SaveSelectScreen.render(gra, panel, selectingData, time);
			break;
		default:
			break;
		}
		
		// 画面の再描画
		panel.draw();
		
	}
	
	private static void handleInput() {
		switch (screen) {
		case START:
			// START -> GAMEに遷移
	        if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
	        	screen = EnumScreen.GAME;
	        	time = 0;
	        	// クリアメッセージの初期化
	        	clearMessage = new ArrayList<String>();
	        	
	        	// 敵、弾リストを生成
	        	enemyEntitys = new ArrayList<EnemyEntity>();
	            enemyBullets = new ArrayList<Bullet>();
	            playerBullets = new ArrayList<Bullet>();
	            
	        	// playerの生成
	        	player.setPos((WindowSize.x - Player.width) / 2, 4 * WindowSize.y /5 - 40);
	        	player.initStatus(playerLevel);
	        	
	        	// ステージの生成
	        	stage = new Stage(stageId);
	        	
	        	mainBGM.setFramePosition(0);
	        	mainBGM.start();
	        } 
	        	
	        // START -> MENUに遷移
	        else if (Keyboard.isKeyPressed(KeyEvent.VK_M)) {
	        	screen = EnumScreen.SHOP;
	        	time = 0;
	        	selectingLevel = 0;
	        }
	        
	        // START -> STAGE_SELECTに遷移
	        else if (Keyboard.isKeyPressed(KeyEvent.VK_S)) {
	        	screen = EnumScreen.STAGE_SELECT;
	        	time = 0;
	        	selectingStage = stageId;
	        }
	        
	        // START画面を上下に遷移
	        else if (Keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
	        	if (!isUpMoving && !isDownMoving && startScreenY == 0) {
	        		isDownMoving = true;
	        	}
	        }
	        
	        else if (Keyboard.isKeyPressed(KeyEvent.VK_UP)) {
	        	if (!isUpMoving && !isDownMoving && startScreenY == -WindowSize.y) {
	        		isUpMoving = true;
	        	}
	        }
	        break;
	        
		case GAME:
			// 弾の発射
			if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
				PlayerBullet bullet = player.shot();
				if (bullet != null) {
					playerBullets.add(bullet);
				}
	        } 
			
			// ビーム
			if (Keyboard.isKeyPressed(KeyEvent.VK_V) && playerLevel.hasBeam) {
				PlayerBeam bullet = player.beam();
				if (bullet != null) {
					playerBullets.add(bullet);
				}
	        } 
			
			if (Keyboard.isKeyPressed(KeyEvent.VK_B) && playerLevel.hasBarrier) {
				player.barrier();
	        } 
			
			// playerの移動処理
			if (Keyboard.isKeyPressed(KeyEvent.VK_LEFT)) player.move(EnumDirection.LEFT);
			if (Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) player.move(EnumDirection.RIGHT);
			if (Keyboard.isKeyPressed(KeyEvent.VK_UP)) player.move(EnumDirection.UP);
			if (Keyboard.isKeyPressed(KeyEvent.VK_DOWN)) player.move(EnumDirection.DOWN);
			
			break;
			
		case GAME_OVER:
			if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
				// saveの処理
				Utils.save(playerLevel, player.getCoins(), selectingData);
				
	        	screen = EnumScreen.START;
	        	time = 0;
	        }
			break;
			
		case SHOP:
			if (Keyboard.isKeyPressed(KeyEvent.VK_BACK_SPACE) || Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
				Utils.save(playerLevel, player.getCoins(), selectingData);
				screen = EnumScreen.START;
				time = 0;
	        }
			
			if (Keyboard.isKeyPressed(KeyEvent.VK_UP)) {
				if (menuCursorInterval <= 0) 
				{
					if (selectingLevel> 0) {
						selectingLevel--;
						menuCursorInterval = menuCursorRate;
					}
				}
			}
			
			if (Keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
				if (menuCursorInterval <= 0) {
					if (selectingLevel < PlayerParameter.parameters.size()-1) {
						// バリア以降に向かわない場合
						if (selectingLevel < 5) {
							selectingLevel++;
							menuCursorInterval = menuCursorRate;
						}
						//バリアに向かう場合
						else if (selectingLevel == 5 && playerLevel.hasBarrier) {
							selectingLevel++;
							menuCursorInterval = menuCursorRate;
						} 
						// ビームに向かう場合
						else if (selectingLevel == 6 && playerLevel.hasBeam) {
							selectingLevel++;
							menuCursorInterval = menuCursorRate;
						}
					}
				}
			}
			
			// レベルアップの実行1
			if (Keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
				if (menuCursorInterval <= 0) {
					int level = playerLevel.getLevelByIndex(selectingLevel);
					if (level < PlayerParameter.maxLevel && player.getCoins() >= PlayerParameter.needCoin[level-1]) {
						playerLevel.levelUpByIndex(selectingLevel);
						player.gainCoin(-PlayerParameter.needCoin[level-1]);
					}
					menuCursorInterval = menuCursorRate;
				}
				
			}
			
			if (Keyboard.isKeyPressed(KeyEvent.VK_D)) {
				if (menuCursorInterval <= 0) {
					int level = playerLevel.getLevelByIndex(selectingLevel);
					if (level > 1) {
						playerLevel.levelDownByIndex(selectingLevel);
					}
					menuCursorInterval = menuCursorRate;
				}
				
			}
			
			break;
			
		case CLEAR:
			if (Keyboard.isKeyPressed(KeyEvent.VK_BACK_SPACE) || Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
				// saveの処理
				Utils.save(playerLevel, player.getCoins(), selectingData);
				screen = EnumScreen.START;
				time = 0;
	        }
			break;
		
		case STAGE_SELECT:
			
			// STARTヘ戻る
			if (Keyboard.isKeyPressed(KeyEvent.VK_BACK_SPACE) || Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
				Utils.save(playerLevel, player.getCoins(), selectingData);
				screen = EnumScreen.START;
				time = 0;
	        }
			
			// ステージを確定してSTARTへ戻る
			if (Keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
				stageId = selectingStage;
				screen = EnumScreen.START;
				time = 0;
			}
			
			if (Keyboard.isKeyPressed(KeyEvent.VK_UP)) {
				if (menuCursorInterval <= 0) {
					if (selectingStage > 0) {
						selectingStage--;
						menuCursorInterval = menuCursorRate;
					}
				}
			}
			
			if (Keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
				if (menuCursorInterval <= 0) {
					if (selectingStage < StageInfos.numStages-1 && selectingStage < playerLevel.stageLevel-1) {
						selectingStage++;
						menuCursorInterval = menuCursorRate;
					}
				}
			}
			
			break;
		case SAVE_SELECT:
			if (Keyboard.isKeyPressed(KeyEvent.VK_UP)) {
				if (menuCursorInterval <= 0) {
					if (selectingData > 1) {
						selectingData--;
						menuCursorInterval = menuCursorRate;
					}
				}
			}
			
			if (Keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
				if (menuCursorInterval <= 0) {
					if (selectingData < 3) {
						selectingData++;
						menuCursorInterval = menuCursorRate;
					}
				}
			}
			
			// ステージを確定してSTARTへ戻る
			if (Keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
				SaveData data;
	    		try {
	    			String str = "save" + String.valueOf(selectingData) + ".dat";
	    			data = SaveData.load(str );
	    			playerLevel.setData(data);
	    			player.setCoins(data.coins);
	    		} catch (Exception e) {
	    			
	    		}
				screen = EnumScreen.START;
				time = 0;
			}
			
			break;
		}
    }
}

