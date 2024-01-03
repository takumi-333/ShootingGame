package lib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import shooting.PlayerLevel;

public class SaveData implements Serializable{
	private static final long serialVersionUID = 9221767827371311543L;
	public int coins;
	public int hpLevel;
	public int powerLevel;
	public int speedLevel;
	public int bulletRateLevel;
	public int bulletSizeLevel;
	public int bulletSpeedLevel;
	public int barrierLevel;
	public int beamLevel;
	public int hasBarrier;
	public int hasBeam;
	public int stageLevel;
	
	
	public void save(String filepath) 
			throws FileNotFoundException, IOException {
		ObjectOutputStream os = new ObjectOutputStream(
				new FileOutputStream(filepath));
		os.writeObject(this);
		os.close();

	}
	public static SaveData load(String filepath) 
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(
				new FileInputStream(filepath));
		SaveData instance = (SaveData) is.readObject();
		is.close();
		return instance;
	}
	
	public void setData(PlayerLevel playerLevel, int coins) {
		this.coins = coins;
		this.barrierLevel = playerLevel.barrierLevel;
		this.beamLevel = playerLevel.beamLevel;
		this.stageLevel = playerLevel.stageLevel;
		this.bulletRateLevel = playerLevel.bulletRateLevel;
		this.bulletSizeLevel = playerLevel.bulletSizeLevel;
		this.bulletSpeedLevel = playerLevel.bulletSpeedLevel;
		this.hpLevel = playerLevel.hpLevel;
		this.powerLevel = playerLevel.powerLevel;
		this.speedLevel = playerLevel.speedLevel;
		if (playerLevel.hasBarrier) this.hasBarrier = 1;
		else this.hasBarrier = 0;
		if (playerLevel.hasBeam) this.hasBeam = 1;
		else this.hasBeam = 0;
		
		System.out.println(this.hasBarrier);
		System.out.println(this.hasBeam);
		
	}
	
	
	// usage
	
//	public static void main(String[] args) {
//		SaveData data;
//		try {
//			data = SaveData.load("save.dat");
//		} catch (Exception e) {
//			data = new SaveData();
//			data.name = "abc";
//			data.number = 0;
//			data.obj = new SaveData.InnerObject();
//			data.obj.a = 10;
//			data.obj.b = 20;
//		}
//		data.number = data.number + 1;
//		System.out.println(data.name);
//		System.out.println(data.number);
//		System.out.println(data.obj.a);
//		System.out.println(data.obj.b);
//		
//		try {
//			data.save("save.dat");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}