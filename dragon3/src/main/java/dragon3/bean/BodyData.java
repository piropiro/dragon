/*
 * 作成日: 2004/03/06
 */
package dragon3.bean;

import java.util.ArrayList;
import java.util.List;

import dragon3.common.constant.ArmorType;
import dragon3.common.constant.BodyAttribute;
import dragon3.common.constant.BodyKind;
import dragon3.common.constant.MoveType;
import dragon3.common.constant.SoulType;
import dragon3.common.constant.WeponType;

/**
 * @author k-saito
 */
public class BodyData implements Data {

	private String id;
	private String name;
	private String image;
	private BodyKind kind;

	private int hp;
	private int str;
	private int def;
	private int mst;
	private int mdf;
	private int hit;
	private int mis;
	private int moveStep;
	private MoveType moveType;
	private SoulType soulType;

	private WeponType weponType;
	private ArmorType armorType;

	private List<String> wazaList;
	private List<BodyAttribute> attrList;

	public BodyData() {
		id = "none";
		name = "none";
		image = "0.png";
		kind = BodyKind.CHARA;
		soulType = SoulType.NONE;
		moveType = MoveType.NONE;
		weponType = WeponType.NONE;
		armorType = ArmorType.NONE;
		wazaList = new ArrayList<>();
		attrList = new ArrayList<>();
	}

	public String toString(){
		return id + " - " + name;
	}



	/**
	 * @return
	 */
	public ArmorType getArmorType() {
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
	public BodyKind getKind() {
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
        @Override
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public SoulType getSoulType() {
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

	public List<BodyAttribute> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<BodyAttribute> attrList) {
		this.attrList = attrList;
	}

	/**
	 * @return
	 */
	public WeponType getWeponType() {
		return weponType;
	}

	/**
	 * @param string
	 */
	public void setArmorType(ArmorType string) {
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
	public void setKind(BodyKind string) {
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
	public void setMoveType(MoveType string) {
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
	public void setSoulType(SoulType string) {
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
	public void setWeponType(WeponType string) {
		weponType = string;
	}

}
