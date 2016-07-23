package dragon2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dragon2.common.constant.BodyKind;
import dragon2.common.constant.GameColor;
import dragon2.common.constant.MoveType;
import lombok.Data;
import dragon2.common.constant.BodyAttribute;
import mine.MineException;

@Data
public class OldBody5 implements Serializable, Cloneable {

    public static final long serialVersionUID = -2486607915340385590L;
    
    public String name;
	public int x;
	public int y;
	public int level;
	public int exp;
	public int hp;
	public int hpMax;
	public int str;
	public int strMax;
	public int def;
	public int defMax;
	public int mst;
	public int mstMax;
	public int mdf;
	public int mdfMax;
	public int hit;
	public int hitMax;
	public int mis;
	public int misMax;
	public int moveStep;
	public MoveType moveType;
	public GameColor color;
	public int img;
	public int atk[];
	public BodyKind kind;


	public List<BodyAttribute> attrList;
	public int range;
	public int scope;
	public int moveturn;
	public int goalX;
	public int goalY;
	public int store;
	private Set<BodyAttribute> attrSet;
        
	public OldBody5() {
		atk = new int[6];
		attrList = new ArrayList<>();
		name = "none";
	}

	public OldBody5 copy() {
		try {
			return (OldBody5) clone();
		} catch (CloneNotSupportedException clonenotsupportedexception) {
                    throw new MineException(clonenotsupportedexception);
		}
	}

	public void setMax() {
		hp = hpMax;
		str = strMax / 10;
		def = defMax / 10;
		mst = mstMax / 10;
		mdf = mdfMax / 10;
		hit = hitMax / 10;
		mis = misMax / 10;
		store = 8;
	}

	public void newType() {
		attrSet = new HashSet<>();
		OldBody5.this.mergeTypeState(attrList);
	}

	public void mergeTypeState(List<BodyAttribute> ai) {
		for (BodyAttribute type : ai) {
			attrSet.add(type);
		}
	}

	public void setTypeState(BodyAttribute type, boolean flag) {
		if (flag) {
			attrSet.add(type);
		} else {
			attrSet.remove(type);
		}
	}

	public boolean isType(BodyAttribute type) {
		return attrSet.contains(type);
	}

	public boolean isAlive() {
		return hp > 0;
	}
	
}
