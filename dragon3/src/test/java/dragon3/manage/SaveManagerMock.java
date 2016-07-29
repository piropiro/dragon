package dragon3.manage;

import dragon3.bean.SaveData;
import dragon3.common.util.Equip;
import dragon3.manage.SaveManager;

public class SaveManagerMock implements SaveManager {

	@Override
	public Equip loadData(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveData(String filename, Equip equip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[][] getCampMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getCollectionMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getWazalistMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPlayTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SaveData getSaveData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stageClear(String stageId) {
		// TODO Auto-generated method stub
		
	}



}
