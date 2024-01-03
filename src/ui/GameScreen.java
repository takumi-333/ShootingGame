package ui;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import shooting.Bullet;
import shooting.EnemyEntity;
import shooting.Player;

public class GameScreen {
	
	public static void render(Graphics gra, JPanel p, long time, Player player, List<EnemyEntity> enemyEntitys, List<Bullet> playerBullets, List<Bullet> enemyBullets) {
		player.render(gra);
		enemyEntitys.forEach(enemy -> {
			enemy.render(gra);
		});
		playerBullets.forEach(bullet -> {
			bullet.render(gra);
		});
		enemyBullets.forEach(bullet -> {
			bullet.render(gra);
		});
		
	}
	
	
}
