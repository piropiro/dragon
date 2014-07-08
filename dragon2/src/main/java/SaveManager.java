// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SaveManager.java

import java.util.Vector;
import mine.DataStream;
import mine.io.BeanIO;
import mine.io.MatrixIO;

class SaveManager {

	SaveManager(UnitWorks unitworks) {
		uw = unitworks;
		sd = null;
		stage = (int[][]) MatrixIO.read("data/stages.txt");
		leftFlag = false;
	}

	public void saveApplet(Equip equip) {
		countSave();
		countTime(getTime());
		SaveData savedata = sd.copy();
		Vector vector = new Vector();
		Vector vector1 = equip.getEquips();
		for (int i = 0; i < vector1.size(); i++) {
			Body body = (Body) vector1.elementAt(i);
			vector.add(body.copy());
		}

		apl_list = new Vector();
		apl_list.add(vector);
		apl_list.add(savedata);
		timerReset();
	}

	public Equip loadApplet() {
		if (apl_list == null)
			apl_list = initData();
		sd = ((SaveData) apl_list.elementAt(1)).copy();
		Vector vector = (Vector) apl_list.elementAt(0);
		Vector vector1 = new Vector();
		for (int i = 0; i < vector.size(); i++) {
			Body body = (Body) vector.elementAt(i);
			vector1.add(body.copy());
		}

		timerReset();
		return new Equip(vector1, uw);
	}

	private Vector initData() {
		Vector vector = new Vector();
		//vector.add((Vector) DataStream.read("data/E0.txt"));
                vector.add((Vector) BeanIO.read("data/body/E00.xml"));
		vector.add(new SaveData());
		return vector;
	}

	public Equip loadData(String s) {
		Vector vector = (Vector) DataStream.read(s);
		if (vector == null)
			vector = initData();
		sd = (SaveData) vector.elementAt(1);
		timerReset();
		return new Equip((Vector) vector.elementAt(0), uw);
	}

	public void saveData(String s, Equip equip) {
		countSave();
		countTime(getTime());
		Vector vector = new Vector();
		vector.add(equip.getEquips());
		vector.add(sd);
		DataStream.write(s, vector);
		timerReset();
	}

	public int[][] getMapData() {
            String file = String.format("D%02d.txt", getMapNum());
            int[][] ai = (int[][]) MatrixIO.readX("data/map/" + file);
            return ai;
	}

	public int[][] getCampMap() {
		return (int[][]) MatrixIO.readX("data/map/D00.txt");
	}

	public int[][] getCollectionMap() {
		return (int[][]) MatrixIO.readX("data/map/D90.txt");
	}

	public int[][] getWazalistMap() {
		return (int[][]) MatrixIO.readX("data/map/D92.txt");
	}

	public Vector getEnemyData() {
		// Vector vector = (Vector) DataStream.read("data/E" + getMapNum() + ".txt");
            String file = String.format("E%02d.xml", getMapNum());
            Vector vector = (Vector) BeanIO.read("data/body/" + file);
		if (vector == null)
			vector = new Vector();
		return vector;
	}

	public int getMapNum() {
		if (leftFlag)
			return stage[sd.mapNum][0];
		else
			return stage[sd.mapNum][1];
	}

	public boolean isDivided() {
		return stage[sd.mapNum][0] != stage[sd.mapNum][1];
	}

	public void selectLR(boolean flag) {
		leftFlag = flag;
	}

	private void timerReset() {
		startTime = System.currentTimeMillis();
	}

	private long getTime() {
		long l = System.currentTimeMillis() - startTime;
		return l;
	}

	public void setPlayerName(String s) {
		sd.name = s;
	}

	public void countStage() {
		sd.stage++;
	}

	public void countTurn() {
		sd.turn++;
	}

	public void countDead() {
		sd.dead++;
	}

	public void countKill() {
		sd.kill++;
	}

	public void countItem() {
		sd.item++;
	}

	public void countEscape() {
		sd.escape++;
	}

	public void countSave() {
		sd.save++;
	}

	public void countTime(long l) {
		sd.play_time += l;
	}

	public int getTurn() {
		return sd.turn;
	}

	public int getDead() {
		return sd.dead;
	}

	public int getKill() {
		return sd.kill;
	}

	public int getItem() {
		return sd.item;
	}

	public int getEscape() {
		return sd.escape;
	}

	public int getSave() {
		return sd.save;
	}

	public long getPlayTime() {
		return sd.play_time + getTime();
	}

	public int getStage() {
		return sd.stage;
	}

	public String getPlayerName() {
		return sd.name;
	}

	public int getScore() {
		return sd.score;
	}

	public boolean isFirst() {
		return sd.mapNum == 0;
	}

	public boolean isReverse() {
		return sd.reverse;
	}

	public boolean isAllClear() {
		return sd.allClear || Statics.isDebug();
	}

	public boolean isFinalStage() {
		return getMapNum() == stage[stage.length - 1][0];
	}

	public int getEnemyLevel() {
		if (sd.enemyLevel < 16) {
			return sd.enemyLevel;
		} else {
			sd.enemyLevel = sd.enemyLevel / 8 - 2;
			return sd.enemyLevel;
		}
	}

	public void setMapNum(int i) {
		sd.mapNum = i;
	}

	public void stageClear() {
		countStage();
		if (isFinalStage()) {
			if (!sd.allClear) {
				Rank.setup(this, null);
				sd.score = Rank.getScore1();
				sd.allClear = true;
			}
			sd.enemyLevel++;
			sd.reverse = !sd.reverse;
		}
		sd.mapNum = getMapNum();
	}

	private int[][] stage;
	private UnitWorks uw;
	private SaveData sd;
	private long startTime;
	private boolean leftFlag;
	static final boolean LEFT = true;
	static final boolean RIGHT = false;
	private Vector apl_list;
}
