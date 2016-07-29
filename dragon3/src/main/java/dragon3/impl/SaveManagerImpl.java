package dragon3.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dragon3.Statics;
import dragon3.UnitWorks;
import dragon3.bean.SaveData;
import dragon3.common.Body;
import dragon3.common.util.Equip;
import dragon3.manage.SaveManager;
import mine.MineException;
import mine.io.ObjectIO;

public class SaveManagerImpl implements SaveManager {

    private UnitWorks uw;
    private SaveData sd;
    private long startTime;

    public SaveManagerImpl(UnitWorks uw) {
        this.uw = uw;
        sd = null;

    }

    /**
     * * SaveData ******************************************************
     */
    private List<Object> initData() {
        List<Object> list = new ArrayList<>();
        list.add(uw.loadEnemyData("init", 0));
        list.add(new SaveData());
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Equip loadData(String filename) {
        List<Object> list;
        try {
            list = (List<Object>) ObjectIO.read(filename);
        } catch (MineException e) {
            list = initData();
        }
        sd = (SaveData) list.get(1);
        timerReset();
        return new Equip((List<Body>) list.get(0));
    }

    @Override
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

    /**
     * * DataLoad *****************************
     * @return 
     */
    @Override
    public int[][] getCampMap() {
        return Statics.getMapData("init");
    }

    @Override
    public int[][] getCollectionMap() {
        return Statics.getMapData("collection");
    }

    @Override
    public int[][] getWazalistMap() {
        return Statics.getMapData("wazalist");
    }

    /*
     * * Score **************************************************
     */
    private void timerReset() {
        startTime = System.currentTimeMillis();
    }

    private long getTime() {
        long time = System.currentTimeMillis() - startTime;
        return time;
    }

    @Override
    public long getPlayTime() {
        return sd.getPlayTime() + getTime();
    }

    /*
     * * Get Data *************************************
     */

    @Override
    public SaveData getSaveData() {
        return sd;
    }

    /*
     * * Stage Clear *******************************************
     */
    @Override
    public void stageClear(String stageId) {
        sd.countStage();
        
        Map<String, Integer> starList = sd.getStarList();
        int starNum = starList.containsKey(stageId)? starList.get(stageId) : 0;
        	
        starNum += 1;
        
        starList.put(stageId, starNum);
    }
}
