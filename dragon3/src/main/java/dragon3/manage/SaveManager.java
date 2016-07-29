package dragon3.manage;

import dragon3.bean.SaveData;
import dragon3.common.util.Equip;

public interface SaveManager {

	public Equip loadData(String filename);

	public void saveData(String filename, Equip equip);

	/*** DataLoad ******************************/
	public int[][] getCampMap();

	public int[][] getCollectionMap();

	public int[][] getWazalistMap();

	public long getPlayTime();

	public SaveData getSaveData();

	/*** Stage Clear ********************************************/
	public void stageClear(String stageId);
}