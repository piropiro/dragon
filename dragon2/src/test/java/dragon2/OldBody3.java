package dragon2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dragon2.common.constant.BodyAttribute;
import dragon2.common.constant.BodyKind;
import dragon2.common.constant.MoveType;
import mine.MineException;


public class OldBody3 implements Serializable, Cloneable {

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
	public int color;
	public int img;
	public int atk[];
	public BodyKind kind;


	public List<BodyAttribute> type;
	public int maai;
	public int scope;
	public int moveturn;
	public int gx;
	public int gy;
	public int store;
	private Set<BodyAttribute> typeState;
        
	public OldBody3() {
		atk = new int[6];
		type = new ArrayList<>();
		name = "none";
	}

	public OldBody3 copy() {
		try {
			return (OldBody3) clone();
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
		typeState = new HashSet<>();
		OldBody3.this.mergeTypeState(type);
	}

	public void mergeTypeState(List<BodyAttribute> ai) {
		for (BodyAttribute type : ai) {
			typeState.add(type);
		}
	}

	public void setTypeState(BodyAttribute type, boolean flag) {
		if (flag) {
			typeState.add(type);
		} else {
			typeState.remove(type);
		}
	}

	public boolean isType(BodyAttribute type) {
		return typeState.contains(type);
	}

	public boolean isAlive() {
		return hp > 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getStrMax() {
		return strMax;
	}

	public void setStrMax(int strMax) {
		this.strMax = strMax;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getDefMax() {
		return defMax;
	}

	public void setDefMax(int defMax) {
		this.defMax = defMax;
	}

	public int getMst() {
		return mst;
	}

	public void setMst(int mst) {
		this.mst = mst;
	}

	public int getMstMax() {
		return mstMax;
	}

	public void setMstMax(int mstMax) {
		this.mstMax = mstMax;
	}

	public int getMdf() {
		return mdf;
	}

	public void setMdf(int mdf) {
		this.mdf = mdf;
	}

	public int getMdfMax() {
		return mdfMax;
	}

	public void setMdfMax(int mdfMax) {
		this.mdfMax = mdfMax;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getHitMax() {
		return hitMax;
	}

	public void setHitMax(int hitMax) {
		this.hitMax = hitMax;
	}

	public int getMis() {
		return mis;
	}

	public void setMis(int mis) {
		this.mis = mis;
	}

	public int getMisMax() {
		return misMax;
	}

	public void setMisMax(int misMax) {
		this.misMax = misMax;
	}

	public int getMoveStep() {
		return moveStep;
	}

	public void setMoveStep(int moveStep) {
		this.moveStep = moveStep;
	}

	public MoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getImg() {
		return img;
	}

	public void setImg(int img) {
		this.img = img;
	}

	public int[] getAtk() {
		return atk;
	}

	public void setAtk(int[] atk) {
		this.atk = atk;
	}

	public List<BodyAttribute> getType() {
		return type;
	}

	public void setType(List<BodyAttribute> type) {
		this.type = type;
	}

	public int getMaai() {
		return maai;
	}

	public void setMaai(int maai) {
		this.maai = maai;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public int getMoveturn() {
		return moveturn;
	}

	public void setMoveturn(int moveturn) {
		this.moveturn = moveturn;
	}

	public int getGx() {
		return gx;
	}

	public void setGx(int gx) {
		this.gx = gx;
	}

	public int getGy() {
		return gy;
	}

	public void setGy(int gy) {
		this.gy = gy;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}

	public Set<BodyAttribute> getTypeState() {
		return typeState;
	}

	public void setTypeState(Set<BodyAttribute> typeState) {
		this.typeState = typeState;
	}  
	
	public BodyKind getKind() {
		return kind;
	}

	public void setKind(BodyKind kind) {
		this.kind = kind;
	}
	
}
