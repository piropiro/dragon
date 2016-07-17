/*
 * 作成日: 2004/03/07
 */
package dragon3.bean;

/**
 * @author k-saito
 */
public class DeployData implements Data {

	private String id;
	private String name;

	private String bodyId;
	private String deployType;
	private String color;
	private int level;
	private int scope;
	private int range;
	private int limitTurn;
	private int x;
	private int y;
	private int goalX;
	private int goalY;

	public DeployData() {
		bodyId = "none";
		deployType = "none";
		color = "none";
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


	public String toString(){
		return bodyId;
	}


	/**
	 * @return
	 */
	public String getBodyId() {
		return bodyId;
	}

	/**
	 * @return
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return
	 */
	public int getGoalX() {
		return goalX;
	}

	/**
	 * @return
	 */
	public int getGoalY() {
		return goalY;
	}

	/**
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return
	 */
	public int getLimitTurn() {
		return limitTurn;
	}

	/**
	 * @return
	 */
	public int getRange() {
		return range;
	}

	/**
	 * @return
	 */
	public int getScope() {
		return scope;
	}

	/**
	 * @return
	 */
	public String getDeployType() {
		return deployType;
	}

	/**
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param string
	 */
	public void setBodyId(String string) {
		bodyId = string;
	}

	/**
	 * @param string
	 */
	public void setColor(String string) {
		color = string;
	}

	/**
	 * @param i
	 */
	public void setGoalX(int i) {
		goalX = i;
	}

	/**
	 * @param i
	 */
	public void setGoalY(int i) {
		goalY = i;
	}

	/**
	 * @param i
	 */
	public void setLevel(int i) {
		level = i;
	}

	/**
	 * @param i
	 */
	public void setLimitTurn(int i) {
		limitTurn = i;
	}

	/**
	 * @param i
	 */
	public void setRange(int i) {
		range = i;
	}

	/**
	 * @param i
	 */
	public void setScope(int i) {
		scope = i;
	}

	/**
	 * @param string
	 */
	public void setDeployType(String string) {
		deployType = string;
	}

	/**
	 * @param i
	 */
	public void setX(int i) {
		x = i;
	}

	/**
	 * @param i
	 */
	public void setY(int i) {
		y = i;
	}


}
