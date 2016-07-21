package dragon3.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import dragon3.common.constant.GameColors;
import dragon3.common.constant.ArmorType;
import dragon3.common.constant.BodyAttribute;
import dragon3.common.constant.BodyKind;
import dragon3.common.constant.DeployType;
import dragon3.common.constant.MoveType;
import dragon3.common.constant.SoulType;
import dragon3.common.constant.WeponType;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Body implements Serializable, Cloneable {

	private String id = "none";
	private String name = "none";
	private String image = "none.png";
	private BodyKind kind = BodyKind.CHARA;
	private GameColors color = GameColors.BLACK;

	private DeployType deployType;
	
	private int hp, hpMax;
	private int str, baseStr;
	private int def, baseDef;
	private int mst, baseMst;
	private int mdf, baseMdf;
	private int hit, baseHit;
	private int mis, baseMis;

	private int moveStep;
	private MoveType moveType = MoveType.NONE;
	private SoulType soulType = SoulType.NONE;

	private WeponType weponType = WeponType.NONE;
	private ArmorType armorType = ArmorType.NONE;

	private List<String> wazaList = new ArrayList<>();
	private Set<BodyAttribute> attrSet = new LinkedHashSet<BodyAttribute>();

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
		str = baseStr / 10;
		def = baseDef / 10;
		mst = baseMst / 10;
		mdf = baseMdf / 10;
		hit = baseHit / 10;
		mis = baseMis / 10;
		store = 8;
	}

	public boolean isAlive(){
		return (hp > 0);
	}

	public void clearAttr() {
		attrSet.clear();
	}
	public void addAttr(BodyAttribute attr){
		attrSet.add(attr);
	}
	public void removeAttr(BodyAttribute attr){
		attrSet.remove(attr);
	}
	public boolean hasAttr(BodyAttribute attr){
		return attrSet.contains(attr);
	}
	
	public void clearWaza() {
		wazaList.clear();
	}
	

	@Override
	public String toString() {
		return "Body [id=" + id + ", name=" + name + ", image=" + image + ", kind=" + kind + ", color=" + color
				+ ", deployType=" + deployType + ", hp=" + hp + ", hpMax=" + hpMax + ", str=" + str + ", def=" + def
				+ ", mst=" + mst + ", mdf=" + mdf + ", hit=" + hit + ", mis=" + mis + ", moveStep=" + moveStep
				+ ", moveType=" + moveType + ", soulType=" + soulType + ", weponType=" + weponType + ", armorType="
				+ armorType + ", wazaList=" + wazaList + ", attrSet=" + attrSet + ", x=" + x + ", y=" + y + ", scope="
				+ scope + ", range=" + range + ", limitTurn=" + limitTurn + ", goalX=" + goalX + ", goalY=" + goalY
				+ ", level=" + level + ", exp=" + exp + ", store=" + store + ", imageNum=" + imageNum + "]";
	}
	
	
}
