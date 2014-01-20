/*
 * Created on 2005/01/10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package dragon3.manage;

import dragon3.bean.SaveData;
import dragon3.common.util.Equip;

/**
 * @author saito
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SaveManager {
	public static final boolean LEFT = true;

	public static final boolean RIGHT = false;

	public abstract Equip loadData(String filename);

	public abstract void saveData(String filename, Equip equip);

	/*** DataLoad ******************************/
	public abstract int[][] getCampMap();

	public abstract int[][] getCollectionMap();

	public abstract int[][] getWazalistMap();

	public abstract int getMapNum();

	public abstract boolean isDivided();

	public abstract void selectLR(boolean flag);

	public abstract long getPlayTime();

	/*** Get Data **************************************/
	public abstract boolean isFirst();

	public abstract boolean isFinalStage();

	public abstract int getEnemyLevel();

	public abstract SaveData getSaveData();

	/*** Stage Clear ********************************************/
	public abstract void stageClear();
}