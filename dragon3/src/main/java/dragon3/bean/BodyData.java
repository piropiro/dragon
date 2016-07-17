/*
 * 作成日: 2004/03/06
 */
package dragon3.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author k-saito
 */
public class BodyData implements Data {

	private String id;
	private String name;
	private String image;
	private String kind;

	private int hp;
	private int str;
	private int def;
	private int mst;
	private int mdf;
	private int hit;
	private int mis;
	private int moveStep;
	private String moveType;
	private String soulType;

	private String weponType;
	private String armorType;

	private List<String> wazaList;
	private List<String> typeList;

	public BodyData() {
		id = "none";
		name = "none";
		image = "0.png";
		kind = "none";
		soulType = "none";
		moveType = "none";
		weponType = "none";
		armorType = "none";
		wazaList = new ArrayList<>();
		typeList = new ArrayList<>();
	}

	public String toString(){
		return id + " - " + name;
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
	public int getDef() {
		return def;
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
        @Override
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
	public String getMoveType() {
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
        @Override
	public String getName() {
		return name;
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
	public int getStr() {
		return str;
	}



	public List<String> getWazaList() {
		return wazaList;
	}

	public void setWazaList(List<String> wazaList) {
		this.wazaList = wazaList;
	}

	public List<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}

	/**
	 * @return
	 */
	public String getWeponType() {
		return weponType;
	}

	/**
	 * @param string
	 */
	public void setArmorType(String string) {
		armorType = string;
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
	public void setMoveType(String string) {
		moveType = string;
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
	 * @param string
	 */
	public void setSoulType(String string) {
		soulType = string;
	}

	/**
	 * @param i
	 */
	public void setStr(int i) {
		str = i;
	}


	/**
	 * @param string
	 */
	public void setWeponType(String string) {
		weponType = string;
	}

}
