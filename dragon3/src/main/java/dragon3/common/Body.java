package dragon3.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import dragon3.common.constant.GameColors;
import dragon3.common.constant.MoveType;

@SuppressWarnings("serial")
public class Body implements Serializable, Cloneable {

	private String id = "none";
	private String name = "none";
	private String image = "0.png";
	private String kind = "none";
	private GameColors color = GameColors.BLACK;

	private String deployType;
	
	private int hp;
	private int hpMax;

	private int str;
	private int def;
	private int mst;
	private int mdf;
	private int hit;
	private int mis;

	private int moveStep;
	private MoveType moveType = MoveType.NONE;
	private String soulType = "none";

	private String weponType = "none";
	private String armorType = "none";

	private List<String> wazaList = new ArrayList<>();
	private Set<String> typeSet = new LinkedHashSet<String>();

	private int x;
	private int y;
	private int scope;
	private int range;
	private int limitTurn;
	private int goalX;
	private int goalY;

	private int level;
	private int exp;

	private int store;
	private int imageNum;

	public Body() {
	}

	public void setMax() {
		hp = hpMax;
		store = 8;
	}

	public boolean isAlive(){
		return (hp > 0);
	}

	public void addType(String type){
		typeSet.add(type);
	}
	public void removeType(String type){
		typeSet.remove(type);
	}
	public boolean isType(String type){
		return typeSet.contains(type);
	}
	public boolean isKind(String kind_) {
		return kind_.equals(this.kind);
	}



	/**
	 * @return
	 */
	public String getArmorType() {
		return armorType;
	}

	/**
	 * @return
	 */
	public GameColors getColor() {
		return color;
	}

	/**
	 * @return
	 */
	public int getDef() {
		return def;
	}

	/**
	 * @return
	 */
	public int getExp() {
		return exp;
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
	public int getHit() {
		return hit;
	}

	/**
	 * @return
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @return
	 */
	public int getHpMax() {
		return hpMax;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @return
	 */
	public String getKind() {
		return kind;
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
	public int getMdf() {
		return mdf;
	}

	/**
	 * @return
	 */
	public int getMis() {
		return mis;
	}

	/**
	 * @return
	 */
	public int getMoveStep() {
		return moveStep;
	}

	/**
	 * @return
	 */
	public MoveType getMoveType() {
		return moveType;
	}

	/**
	 * @return
	 */
	public int getMst() {
		return mst;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
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
	public String getSoulType() {
		return soulType;
	}

	/**
	 * @return
	 */
	public int getStore() {
		return store;
	}

	/**
	 * @return
	 */
	public int getStr() {
		return str;
	}

	/**
	 * @return
	 */
	public Set<String> getTypeSet() {
		return typeSet;
	}


	/**
	 * @return
	 */
	public String getWeponType() {
		return weponType;
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
	public void setArmorType(String string) {
		armorType = string;
	}

	/**
	 * @param string
	 */
	public void setColor(GameColors string) {
		color = string;
	}

	
	public String getDeployType() {
		return deployType;
	}

	public void setDeployType(String deployType) {
		this.deployType = deployType;
	}

	/**
	 * @param i
	 */
	public void setDef(int i) {
		def = i;
	}

	/**
	 * @param i
	 */
	public void setExp(int i) {
		exp = i;
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
	public void setHit(int i) {
		hit = i;
	}

	/**
	 * @param i
	 */
	public void setHp(int i) {
		hp = i;
	}

	/**
	 * @param i
	 */
	public void setHpMax(int i) {
		hpMax = i;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

	/**
	 * @param string
	 */
	public void setImage(String string) {
		image = string;
	}

	/**
	 * @param string
	 */
	public void setKind(String string) {
		kind = string;
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
	public void setMdf(int i) {
		mdf = i;
	}

	/**
	 * @param i
	 */
	public void setMis(int i) {
		mis = i;
	}

	/**
	 * @param i
	 */
	public void setMoveStep(int i) {
		moveStep = i;
	}

	/**
	 * @param string
	 */
	public void setMoveType(MoveType m) {
		moveType = m;
	}

	/**
	 * @param i
	 */
	public void setMst(int i) {
		mst = i;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
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
	public void setSoulType(String string) {
		soulType = string;
	}

	/**
	 * @param i
	 */
	public void setStore(int i) {
		store = i;
	}

	/**
	 * @param i
	 */
	public void setStr(int i) {
		str = i;
	}

	/**
	 * @param set
	 */
	public void setTypeSet(LinkedHashSet<String> set) {
		typeSet = set;
	}


	/**
	 * @param string
	 */
	public void setWeponType(String string) {
		weponType = string;
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

	public int getImageNum() {
		return imageNum;
	}

	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}

	public List<String> getWazaList() {
		return wazaList;
	}

	public void setWazaList(List<String> wazaList) {
		this.wazaList = wazaList;
	}

	@Override
	public String toString() {
		return "Body [id=" + id + ", name=" + name + ", image=" + image + ", kind=" + kind + ", color=" + color
				+ ", deployType=" + deployType + ", hp=" + hp + ", hpMax=" + hpMax + ", str=" + str + ", def=" + def
				+ ", mst=" + mst + ", mdf=" + mdf + ", hit=" + hit + ", mis=" + mis + ", moveStep=" + moveStep
				+ ", moveType=" + moveType + ", soulType=" + soulType + ", weponType=" + weponType + ", armorType="
				+ armorType + ", wazaList=" + wazaList + ", typeSet=" + typeSet + ", x=" + x + ", y=" + y + ", scope="
				+ scope + ", range=" + range + ", limitTurn=" + limitTurn + ", goalX=" + goalX + ", goalY=" + goalY
				+ ", level=" + level + ", exp=" + exp + ", store=" + store + ", imageNum=" + imageNum + "]";
	}
	
	
}
