package dragon3.save;

import javax.inject.Inject;
import javax.inject.Singleton;

import dragon3.camp.Equip;
import dragon3.controller.UnitWorks;
import lombok.Setter;
import mine.MineException;
import mine.io.JsonIO;

@Singleton
public class SaveManagerImpl implements SaveManager {

	@Setter UnitWorks uw;
    private SaveData sd;
    private long startTime;

    @Inject
    public SaveManagerImpl() {
    }

    /**
     * * SaveData ******************************************************
     */
    private SaveData initData() {
    	SaveData newData = new SaveData();
    	newData.setOpened("D01", true);
    	newData.setBodyList(uw.loadEnemyData("camp", 0));
        return newData;
    }

    @Override
    public Equip loadData(String filename) {
        try {
        	sd = JsonIO.read(filename, SaveData.class);
        } catch (MineException e) {
            sd = initData();
        }
        timerReset();
        return new Equip(sd.getBodyList());
    }

    @Override
    public void saveData(String filename, Equip equip) {
        sd.countSave();
        sd.addTime(getTime());
        sd.setBodyList(equip.getEquips());
        try {
            JsonIO.write(filename, sd);
        } catch (MineException e) {
            e.printStackTrace();
        }
        timerReset();
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
}
