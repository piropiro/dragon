package dragon2;





import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dragon2.common.Body;

public class SaveData implements Serializable, Cloneable {

	public SaveData() {
		reverse = false;
		allClear = false;
		name = null;
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
		play_time = 0L;
		bodys = new ArrayList<>();
	}

	public SaveData copy() {
		try {
			return (SaveData) clone();
		} catch (CloneNotSupportedException clonenotsupportedexception) {
			System.out.println(clonenotsupportedexception);
		}
		return null;
	}

	boolean reverse;
	boolean allClear;
	int score;
	int mapNum;
	int enemyLevel;
	int turn;
	int dead;
	int kill;
	int item;
	int escape;
	int save;
	int stage;
	long play_time;
	String name;
	List<Body> bodys;
}
