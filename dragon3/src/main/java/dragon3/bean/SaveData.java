package dragon3.bean;

import java.io.Serializable;

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
	private String playerName;

	public SaveData() {
		reverse = false;
		allClear = false;
		playerName = null;
		score = 0;
		mapNum = 0;
		enemyLevel = 0;
		turn = 0;
		dead = 0;
		kill = 0;
		item = 0;
		escape = 0;
		save = 0;
		stage = 0;
		playTime = 0;
	}

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

	/**
	 * @return Returns the allClear.
	 */
	public boolean isAllClear() {
		return allClear;
	}
	/**
	 * @param allClear The allClear to set.
	 */
	public void setAllClear(boolean allClear) {
		this.allClear = allClear;
	}
	/**
	 * @return Returns the dead.
	 */
	public int getDead() {
		return dead;
	}
	/**
	 * @param dead The dead to set.
	 */
	public void setDead(int dead) {
		this.dead = dead;
	}
	/**
	 * @return Returns the enemyLevel.
	 */
	public int getEnemyLevel() {
		return enemyLevel;
	}
	/**
	 * @param enemyLevel The enemyLevel to set.
	 */
	public void setEnemyLevel(int enemyLevel) {
		this.enemyLevel = enemyLevel;
	}
	/**
	 * @return Returns the escape.
	 */
	public int getEscape() {
		return escape;
	}
	/**
	 * @param escape The escape to set.
	 */
	public void setEscape(int escape) {
		this.escape = escape;
	}
	/**
	 * @return Returns the item.
	 */
	public int getItem() {
		return item;
	}
	/**
	 * @param item The item to set.
	 */
	public void setItem(int item) {
		this.item = item;
	}
	/**
	 * @return Returns the kill.
	 */
	public int getKill() {
		return kill;
	}
	/**
	 * @param kill The kill to set.
	 */
	public void setKill(int kill) {
		this.kill = kill;
	}
	/**
	 * @return Returns the mapNum.
	 */
	public int getMapNum() {
		return mapNum;
	}
	/**
	 * @param mapNum The mapNum to set.
	 */
	public void setMapNum(int mapNum) {
		this.mapNum = mapNum;
	}
	/**
	 * @return Returns the playerName.
	 */
	public String getPlayerName() {
		return playerName;
	}
	/**
	 * @param playerName The playerName to set.
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	/**
	 * @return Returns the playTyme.
	 */
	public long getPlayTime() {
		return playTime;
	}
	/**
	 * @param playTyme The playTyme to set.
	 */
	public void setPlayTime(long playTyme) {
		this.playTime = playTyme;
	}
	/**
	 * @return Returns the reverse.
	 */
	public boolean isReverse() {
		return reverse;
	}
	/**
	 * @param reverse The reverse to set.
	 */
	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}
	/**
	 * @return Returns the save.
	 */
	public int getSave() {
		return save;
	}
	/**
	 * @param save The save to set.
	 */
	public void setSave(int save) {
		this.save = save;
	}
	/**
	 * @return Returns the score.
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score The score to set.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * @return Returns the stage.
	 */
	public int getStage() {
		return stage;
	}
	/**
	 * @param stage The stage to set.
	 */
	public void setStage(int stage) {
		this.stage = stage;
	}
	/**
	 * @return Returns the turn.
	 */
	public int getTurn() {
		return turn;
	}
	/**
	 * @param turn The turn to set.
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}
}
