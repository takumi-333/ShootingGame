package shooting;

import java.util.Random;

import constants.WindowSize;

public class BossMove {
	
	public static int getDelta(int x, int deltaX, int moveSpeed, long time, int movePattern) {
		switch(movePattern) {
		case 0:
			return 0;
		case 1:
			return pattern1(x, deltaX, moveSpeed);
		case 2:
			return pattern2(x, deltaX, moveSpeed, time);
		case 3:
			return pattern3(x, deltaX, moveSpeed, time);
		default:
			return 0;
		}
	}
	
	// -350 ~ 350の間をランダムに動く(-3, 0, 3)
	public static int pattern2(int x, int deltaX, int moveSpeed, long time) {
		int delta;
		int moveRange = 350;
		// -350 ~ 350の間を動く（画面外にはいかない)
		if (time % 100 > 0) {
			delta = moveSpeed;
		} else {
			Random rnd = new Random();
			int r = rnd.nextInt(3);
			if (r == 0) {
				delta = -3;
			} else if(r == 1) {
				delta = 0;
			} else {
				delta = 3;
			}
		}
		if (x + delta <= 10 || deltaX + delta <= -moveRange) {
			delta = -delta;
		} else if (x + delta >= WindowSize.x - 20 || deltaX + delta >= moveRange) {
			delta = -delta;
		}
		return delta;
	}
	
	// -300 ~ 300を与えられた速度で往復する動き
	public static int pattern1(int x, int deltaX, int moveSpeed) {
		int delta;
		if (moveSpeed != 0) delta = moveSpeed;
		else delta = 3;
		int moveRange = 300;
		if (x + delta <= 10 || deltaX + delta <= -moveRange) {
			delta = -delta;
		} else if (x + delta >= WindowSize.x - 20 || deltaX + delta >= moveRange) {
			delta = -delta;
		}
		return delta;
	}
	
	// -350 ~ 350の間をランダムに動く(-3, 0, 3)
		public static int pattern3(int x, int deltaX, int moveSpeed, long time) {
			int delta;
			int moveRange = 250;
			// -350 ~ 350の間を動く（画面外にはいかない)
			if (time % 80 > 0) {
				delta = moveSpeed;
			} else {
				Random rnd = new Random();
				int r = rnd.nextInt(10);
				if (r == 0) {
					delta = 0;
				} else if (r <= 2) {
					delta = -2;
				} else if (r <= 4) {
					delta = 2;
				} else if (r <= 7) {
					delta = 4;
				} else {
					delta = -4;
				}
			}
			if (x + delta <= 10 || deltaX + delta <= -moveRange) {
				delta = -delta;
			} else if (x + delta >= WindowSize.x - 20 || deltaX + delta >= moveRange) {
				delta = -delta;
			}
			return delta;
		}
	
	
}
