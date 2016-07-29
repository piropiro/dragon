package dragon3.save;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@lombok.Data
public class SaveData implements Serializable, Cloneable {

	private static final long serialVersionUID = 761813354588427943L;

	private boolean reverse;
	private boolean allClear;
	private int score;
	private int turn;
	private int dead;
	private int kill;
	private int item;
	private int escape;
	private int save;
	private int stage;
	private long playTime;
	private String playerName = "Player";
	private Map<String, Integer> starList = new HashMap<>();


	public SaveData copy() {
		try {
			return (SaveData) this.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public void countStage() {
		stage++;
	}
	public void countTurn() {
		turn++;
	}
	public void countDead() {
		dead++;
	}
	public void countKill() {
		kill++;
	}
	public void countItem() {
		item++;
	}
	public void countEscape() {
		escape++;
	}
	public void countSave() {
		save++;
	}
	public void addTime(long n) {
		playTime += n;
	}
	
	public int getStarNum(String stageId) {
		return starList.containsKey(stageId)? starList.get(stageId) : 0;
	}
	public void countStarNum(String stageId) {
		int starNum = getStarNum(stageId);
		starList.put(stageId, starNum + 1);
	}
}
