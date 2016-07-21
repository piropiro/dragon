package dragon3.impl;

import java.util.ArrayList;
import java.util.List;

import dragon3.Statics;
import dragon3.UnitWorks;
import dragon3.bean.SaveData;
import dragon3.common.Body;
import dragon3.common.util.Equip;
import dragon3.manage.SaveManager;
import mine.MineException;
import mine.io.MatrixIO;
import mine.io.ObjectIO;

public class SaveManagerImpl implements SaveManager {

    private UnitWorks uw;
    private SaveData sd;
    private long startTime;
    private boolean leftFlag;
    private int[][] stage;

    public SaveManagerImpl(UnitWorks uw) {
        this.uw = uw;
        sd = null;

        try {
            stage = (int[][]) MatrixIO.read(Statics.STAGE_DIR + "stages.txt");

        } catch (MineException e) {
            throw new RuntimeException(e);
        }
        leftFlag = false;
    }

    /**
     * * SaveData ******************************************************
     */
    private List<Object> initData() {
        List<Object> list = new ArrayList<>();
        list.add(uw.loadEnemyData("init"));
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

    @Override
    public int getMapNum() {
        if (leftFlag) {
            return stage[sd.getMapNum()][0];
        } else {
            return stage[sd.getMapNum()][1];
        }
    }

    @Override
    public boolean isDivided() {
        return stage[sd.getMapNum()][0] != stage[sd.getMapNum()][1];
    }

    @Override
    public void selectLR(boolean flag) {
        leftFlag = flag;
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
    public boolean isFirst() {
        return (sd.getMapNum() == 0);
    }

    @Override
    public boolean isFinalStage() {
        return (getMapNum() == stage[stage.length - 1][0]);
    }

    @Override
    public int getEnemyLevel() {
        if (sd.getEnemyLevel() < 16) {
            return sd.getEnemyLevel();
        } else {
            sd.setEnemyLevel(sd.getEnemyLevel() / 8 - 2);
            return sd.getEnemyLevel();
        }
    }

    @Override
    public SaveData getSaveData() {
        return sd;
    }

    /*
     * * Stage Clear *******************************************
     */
    @Override
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
