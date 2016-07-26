package dragon3.bean;

import java.io.Serializable;

@lombok.Data
public class SaveData implements Serializable, Cloneable {

	private static final long serialVersionUID = 761813354588427943L;

	private boolean reverse;
	private boolean allClear;
	private int score;
	private int mapNum;
	private int enemyLevel;
	private int turn;
	private int dead;
	private int kill;
	private int item;
	private int escape;
	private int save;
	private int stage;
	private long playTime;
	private String playerName = "Player";


	public SaveData copy() {
		try {
			return (SaveData) this.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e);
			return null;
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
}
