package constants;

import java.awt.Rectangle;
import java.util.ArrayList;

public class PartsCollection {
	
	public static ArrayList<Rectangle> getParts(int partId) {
		switch(partId) {
		case 0:
			return defaultEnemyParts();
		case 1:
			return bossPattern1();
		case 2:
			return bossPattern2();
		case 3:
			return bossPattern3();
		case 4:
			return bossPattern4();
		case 5:
			return bossPattern5();
		case 6:
			return bossPattern6();
		case 7:
			return bossPattern7();
		case 8:
			return defaultEnemyParts();
		default:
			return new ArrayList<Rectangle>();
		}
	}
	
	public static ArrayList<Rectangle> getBarrierParts(int barrierPartId) {
		switch(barrierPartId) {
		case 0:
			return new ArrayList<Rectangle>();
		case 1:
			return bossBarrierPattern1();
		case 3:
			return bossBarrierPattern3();
		case 4:
			return bossBarrierPattern4();
		case 5:
			return bossBarrierPattern5();
		case 6:
			return bossBarrierPattern6();
		case 7:
			return bossBarrierPattern7();
		case 8:
			return bossBarrierPattern8();
		case 9:
			return bossBarrierPattern9();
		case 100:
			return bossAllBarrierPattern();
		default:
			return new ArrayList<Rectangle>();
		}
	}

	public static ArrayList<Rectangle> bossPattern1() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-15, -25, 30, 10);
		Rectangle part2 = new Rectangle(-25, -15, 10, 10);
		Rectangle part3 = new Rectangle(-5, -15, 10, 10);
		Rectangle part4 = new Rectangle(15, -15, 10, 10);
		parts.add(part1);
		parts.add(part2);
		parts.add(part3);
		parts.add(part4);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossPattern2() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-5, -40, 10, 40);
		Rectangle part2 = new Rectangle(-15, -30, 30, 20);
		parts.add(part1);
		parts.add(part2);
		return parts;
	}
	
	// 左側
	public static ArrayList<Rectangle> bossPattern3() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-5, -20, 20, 10);
		Rectangle part2 = new Rectangle(-5, -10, 10, 10);
		parts.add(part1);
		parts.add(part2);
		return parts;
	}
	
	
	// 右側
	public static ArrayList<Rectangle> bossPattern4() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-15, -20, 20, 10);
		Rectangle part2 = new Rectangle(-5, -10, 10, 10);
		parts.add(part1);
		parts.add(part2);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossPattern5() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-5, -50, 10, 50);
		Rectangle part2 = new Rectangle(-25, -40, 50, 10);
		Rectangle part3 = new Rectangle(-25, -30, 10, 10);
		Rectangle part4 = new Rectangle(15, -30, 10, 10);
		Rectangle part5 = new Rectangle(-35, -20, 10, 10);
		Rectangle part6 = new Rectangle(25, -20, 10, 10);
		parts.add(part1);
		parts.add(part2);
		parts.add(part3);
		parts.add(part4);
		parts.add(part5);
		parts.add(part6);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossPattern6() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-2, -4, 4, 4);
		Rectangle part2 = new Rectangle(-4, -12, 8, 8);
		Rectangle part3 = new Rectangle(-5, -22, 10, 10);
		Rectangle part4 = new Rectangle(-5, -25, 15, 3);
		Rectangle part5 = new Rectangle(7, -22, 3, 22);
		parts.add(part1);
		parts.add(part2);
		parts.add(part3);
		parts.add(part4);
		parts.add(part5);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossPattern7() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-2, -4, 4, 4);
		Rectangle part2 = new Rectangle(-26, -14, 52, 10);
		Rectangle part3 = new Rectangle(-26, -4, 10, 10);
		Rectangle part4 = new Rectangle(16, -4, 10, 10);
		Rectangle part5 = new Rectangle(-16, 6, 4, 4);
		Rectangle part6 = new Rectangle(12, 6, 4, 4);
		parts.add(part1);
		parts.add(part2);
		parts.add(part3);
		parts.add(part4);
		parts.add(part5);
		parts.add(part6);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossBarrierPattern8() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-20, 7, 40, 7);
		Rectangle part2 = new Rectangle(-27, -20, 7, 34);
		Rectangle part3 = new Rectangle(20, -20, 7, 34);
		parts.add(part1);
		parts.add(part2);
		parts.add(part3);
		return parts;
	}
	
	
	public static ArrayList<Rectangle> bossBarrierPattern1() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-35, -10, 5, 20);
		Rectangle part2 = new Rectangle(-35, 5, 15, 5);
		Rectangle part3 = new Rectangle(20, 5, 15, 5);
		Rectangle part4 = new Rectangle(30, -10, 5, 20);
		parts.add(part1);
		parts.add(part2);
		parts.add(part3);
		parts.add(part4);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossBarrierPattern9() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-20, 7, 40, 7);
		parts.add(part1);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossBarrierPattern3() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(17, 3, 24, 4);
		parts.add(part1);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossBarrierPattern4() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-43, 7, 24, 4);
		parts.add(part1);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossBarrierPattern5() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-40, -7, 20, 7);
		Rectangle part2 = new Rectangle(20, -7, 20, 7);
		parts.add(part1);
		parts.add(part2);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossBarrierPattern6() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(6, 3, 5, 3);
		parts.add(part1);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossBarrierPattern7() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-26, 12, 9, 7);
		Rectangle part2 = new Rectangle(17, 12, 9, 7);
		parts.add(part1);
		parts.add(part2);
		return parts;
	}
	
	public static ArrayList<Rectangle> bossAllBarrierPattern() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-10, 0, WindowSize.x + 30, 5);
		parts.add(part1);
		return parts;
	}
	
	
	
	public static ArrayList<Rectangle> playerParts() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-5, 0, 10, 10);
		Rectangle part2 = new Rectangle(-15, 10, 30, 10);
		parts.add(part1);
		parts.add(part2);
		return parts;
	}
	
	
	public static ArrayList<Rectangle> defaultEnemyParts() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-5, -10, 10, 10);
		Rectangle part2 = new Rectangle(-10, -20, 20, 10);
		parts.add(part1);
		parts.add(part2);
		return parts;
	}
	
	// playerのバリア
	
	public static ArrayList<Rectangle> getPlayerBarrier(int idx) {
		switch (idx) {
		case 1:
			return playerBarrier1();
		case 2:
			return playerBarrier2();
		case 3:
			return playerBarrier3();
		case 4:
			return playerBarrier4();
		case 5:
			return playerBarrier5();
		default:
			return new ArrayList<Rectangle>();
		}
	}
	
	public static ArrayList<Rectangle> playerBarrier1() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-14, -16, 28, 8);
		parts.add(part1);
		return parts;
	}
	
	public static ArrayList<Rectangle> playerBarrier2() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-16, -16, 32, 8);
		parts.add(part1);
		return parts;
	}
	
	public static ArrayList<Rectangle> playerBarrier3() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-18, -16, 36, 8);
		parts.add(part1);
		return parts;
	}
	
	public static ArrayList<Rectangle> playerBarrier4() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-18, -16, 36, 8);
		Rectangle part2 = new Rectangle(-20, -16, 5, 24);
		Rectangle part3 = new Rectangle(16, -16, 5, 24);
		parts.add(part1);
		parts.add(part2);
		parts.add(part3);
		return parts;
	}
	
	public static ArrayList<Rectangle> playerBarrier5() {
		ArrayList<Rectangle> parts = new ArrayList<Rectangle>();
		Rectangle part1 = new Rectangle(-18, -16, 36, 8);
		Rectangle part2 = new Rectangle(-24, -16, 6, 50);
		Rectangle part3 = new Rectangle(18, -16, 6, 50);
		parts.add(part1);
		parts.add(part2);
		parts.add(part3);
		return parts;
	}
	 
	
	
}
