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
	
	public static int maxLevel = 9;
	
	public static int[] needCoin = {25, 300, 800, 2500, 12000, 75000, 300000, 1000000};
	
	public static int[] hpList = {1, 2, 4, 6, 9, 12, 15, 20, 30};
	public static int[] speedList = {5, 6, 7, 8, 9, 10, 11, 12, 13};
	public static int[] powerList = {1, 2, 3, 4, 5, 6, 7, 8, 15};
	public static int[] bulletSpeedList = {5, 6, 7, 8, 9, 10, 11, 12, 15};
	public static int[] bulletSizeList = {5, 6, 6, 7, 8, 8, 9, 10, 11};
	public static int[] bulletRateList = {20, 18, 16, 14, 12, 10, 8, 5, 1};
	
	public static long[] barrierDurationList = {15, 25 , 40, 60, 100, 140, 180, 250, 500};
	public static long[] barrierRateList = {350, 340, 330, 320, 310, 300, 290, 280, 270};
	public static int[] barrierTypeList = {1, 2, 2, 3, 3, 4, 4, 5, 5};
	
	public static long[] beamDurationList = {1, 2, 3, 4, 6, 8, 10, 12, 25};
	public static float[] beamPowerRatioList = {1.1f, 1.2f, 1.3f, 1.4f, 1.5f, 1.7f, 1.9f, 2.1f, 3.0f};
	public static int[] beamRateList = {500, 480, 460, 440, 420, 400, 380, 360, 200};
	
}

