package dragon3.impl;

import java.util.ArrayList;
import java.util.List;

import mine.MineException;
import mine.io.ObjectIO;
import dragon3.Statics;
import dragon3.UnitWorks;
import dragon3.bean.SaveData;
import dragon3.common.Body;
import dragon3.common.util.Equip;
import dragon3.manage.SaveManager;

public class SaveManagerImpl implements SaveManager {

	private int[] stage;

	@SuppressWarnings("unused")
	private UnitWorks uw;
	private SaveData sd;
	private long startTime;
	private boolean leftFlag;

	public SaveManagerImpl(UnitWorks uw) {
		this.uw = uw;
		sd = null;
		try {
			stage = (int[]) ObjectIO.read(Statics.STAGE_DIR + "stage.txt");
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
		leftFlag = false;
	}


	/*** SaveData *******************************************************/

	private List<Object> initData() {
		List<Object> list = new ArrayList<>();
		list.add(Statics.getEnemyData(0));
		list.add(new SaveData());
		return list;
	}

	@SuppressWarnings("unchecked")
	public Equip loadData(String filename) {
		List<Object> list = null;
		try {
			list = (List<Object>) ObjectIO.read(filename);
		} catch (MineException e) {
			list = initData();
		}
		sd = (SaveData) list.get(1);
		timerReset();
		return new Equip((List<Body>) list.get(0));
	}

	public void saveData(String filename, Equip equip) {
		sd.countSave();
		sd.addTime(getTime());
		List<Object> list = new ArrayList<>();
		list.add(equip.getEquips());
		list.add(sd);
		try {
			ObjectIO.write(filename, list);
		} catch (MineException e) {
			e.printStackTrace();
		}
		timerReset();
	}

	/*** DataLoad ******************************/

	public int[][] getCampMap() {
		return Statics.getMapData(0);
	}
	public int[][] getCollectionMap() {
		return Statics.getMapData(90);
	}
	public int[][] getWazalistMap() {
		return Statics.getMapData(92);
	}

	public int getMapNum() {
		if (leftFlag)
			return stage[sd.getMapNum() * 2];
		else
			return stage[sd.getMapNum() * 2 + 1];
	}
	public boolean isDivided() {
		return (stage[sd.getMapNum() * 2] != stage[sd.getMapNum() * 2 + 1]);
	}

	public void selectLR(boolean flag) {
		leftFlag = flag;
	}

	/*** Score ***************************************************/

	private void timerReset() {
		startTime = System.currentTimeMillis();
	}
	private long getTime() {
		long time = System.currentTimeMillis() - startTime;
		return time;
	}

	public long getPlayTime() {
		return sd.getPlayTime() + getTime();
	}

	/*** Get Data **************************************/

	public boolean isFirst() {
		return (sd.getMapNum() == 0);
	}

	public boolean isFinalStage() {
		return (getMapNum() == stage[stage.length - 1]);
	}
	public int getEnemyLevel() {
		if (sd.getEnemyLevel() < 16) {
			return sd.getEnemyLevel();
		} else {
			sd.setEnemyLevel(sd.getEnemyLevel() / 8 - 2);
			return sd.getEnemyLevel();
		}
	}

	public SaveData getSaveData() {
		return sd;
	}

	/*** Stage Clear ********************************************/

	public void stageClear() {
		sd.countStage();
		if (isFinalStage()) {
			if (!sd.isAllClear()) {
				sd.setAllClear(true);
			}
			sd.setEnemyLevel(sd.getEnemyLevel() + 1);
			sd.setReverse(!sd.isReverse());
		}
		sd.setMapNum(getMapNum());
	}
}
