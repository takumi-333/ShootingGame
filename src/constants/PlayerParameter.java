package constants;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerParameter {
	public static ArrayList<String> parameters = new ArrayList<String>(Arrays.asList(
			"HP",
			"スピード",
			"パワー",
			"弾速",
			"弾の大きさ",
			"発射レート",
			"バリア",
			"ビーム"));
	
	public static int maxLevel = 8;
	
	public static int[] needCoin = {25, 100, 500, 3500, 10000, 50000, 300000};
	
	public static int[] hpList = {1, 3, 15, 30, 50, 100, 200, 1200};
	public static int[] speedList = {5, 6, 7, 8, 9, 10, 11, 12};
	public static int[] powerList = {1, 2, 4, 7, 14, 31, 62, 125};
	public static int[] bulletSpeedList = {5, 6, 7, 8, 9, 10, 11, 12};
	public static int[] bulletSizeList = {5, 6, 6, 7, 8, 8, 9, 10};
	public static int[] bulletRateList = {20,18, 16, 14, 11, 8, 5, 2};
	
	public static long[] barrierDurationList = {3, 10 , 20, 30, 45, 60, 80, 150};
	public static long[] barrierRateList = {300, 295, 290, 280, 270, 260, 250, 180};
	public static int[] barrierTypeList = {1, 2, 2, 3, 3, 4, 4, 5};
	
	public static long[] beamDurationList = {1, 3, 5, 9, 13, 15, 17, 22};
	public static float[] beamPowerRatioList = {1.1f, 1.2f, 1.3f, 1.4f, 1.5f, 1.7f, 2.0f, 3.0f};
	public static int[] beamRateList = {200, 180, 160, 140, 120, 100, 80, 60};
	
}
