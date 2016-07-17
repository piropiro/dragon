/*
 * 作成日: 2004/03/07
 */
package dragon3.bean;

/**
 * @author k-saito
 */
public class StageData implements Data {

	private String id;
	private String name;
	private int level;

	public StageData() {
		id = "none";
		name = "none";
	}

	public String toString(){
		return id + " - " + name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
