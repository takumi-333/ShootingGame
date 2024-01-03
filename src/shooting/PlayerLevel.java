package shooting;

import lib.SaveData;

public class PlayerLevel {
	public int hpLevel;
	public int powerLevel;
	public int speedLevel;
	public int bulletRateLevel;
	public int bulletSizeLevel;
	public int bulletSpeedLevel;
	public int barrierLevel;
	public int beamLevel;
	
	public boolean hasBarrier;
	public boolean hasBeam;
	public int stageLevel;
	
	public PlayerLevel() {
		hpLevel = 1;
		powerLevel = 1;
		speedLevel = 1;
		bulletRateLevel = 1;
		bulletSizeLevel = 1;
		bulletSpeedLevel = 1;
		barrierLevel = 1;
		beamLevel = 1;
		hasBarrier = false;
		hasBeam = false;
		stageLevel = 1;
	}
	
	public int getLevelByIndex(int idx) {
		if (idx == 0) return hpLevel;
		else if (idx == 1) return speedLevel;
		else if (idx == 2) return powerLevel;
		else if (idx == 3) return bulletSpeedLevel;
		else if (idx == 4) return bulletSizeLevel;
		else if (idx == 5) return bulletRateLevel;
		else if (idx == 6) return barrierLevel;
		else if (idx == 7) return beamLevel;
		else return -1;
		
	}
	
	public void setData(SaveData data) {
		
		
		this.hpLevel = data.hpLevel;
		this.powerLevel = data.powerLevel;
		this.speedLevel = data.speedLevel;
		this.bulletRateLevel = data.bulletRateLevel;
		this.bulletSpeedLevel = data.bulletSpeedLevel;
		this.bulletSizeLevel = data.bulletSizeLevel;
		this.barrierLevel = data.barrierLevel;
		this.beamLevel = data.beamLevel;
		this.stageLevel = data.stageLevel;
		this.hasBarrier = (data.hasBarrier == 1);
		this.hasBeam = (data.hasBeam == 1);
	}
	
	public void levelUpByIndex(int idx) {
		if (idx == 0) hpLevel++;
		else if (idx == 1) speedLevel++;
		else if (idx == 2) powerLevel++;
		else if (idx == 3) bulletSpeedLevel++;
		else if (idx == 4) bulletSizeLevel++;
		else if (idx == 5) bulletRateLevel++;	
		else if (idx == 6) barrierLevel++;	
		else if (idx == 7) beamLevel++;
	}
	
	public void levelDownByIndex(int idx) {
		if (idx == 0) hpLevel--;
		else if (idx == 1) speedLevel--;
		else if (idx == 2) powerLevel--;
		else if (idx == 3) bulletSpeedLevel--;
		else if (idx == 4) bulletSizeLevel--;
		else if (idx == 5) bulletRateLevel--;		
		else if (idx == 6) barrierLevel--;	
		else if (idx == 7) beamLevel--;
	}
}
