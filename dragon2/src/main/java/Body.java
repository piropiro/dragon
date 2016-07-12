// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Body.java

import java.io.Serializable;

import mine.MineException;


public class Body implements Serializable, Cloneable {

    public static final long serialVersionUID = -2486607915340385590L;
    
    	String name;
	int x;
	int y;
	int level;
	int exp;
	int hp;
	int hpMax;
	int str;
	int strMax;
	int def;
	int defMax;
	int mst;
	int mstMax;
	int mdf;
	int mdfMax;
	int hit;
	int hitMax;
	int mis;
	int misMax;
	int ido;
	int itype;
	int color;
	int img;
	int atk[];
	int type[];
	int maai;
	int scope;
	int moveturn;
	int gx;
	int gy;
	int store;
	private boolean[] typeState;
        
	public Body() {
		atk = new int[6];
		type = new int[5];
		name = "none";
	}

	public Body copy() {
		try {
			return (Body) clone();
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

	public void newType(int i) {
		typeState = new boolean[i];
		Body.this.mergeTypeState(type);
	}

	public void mergeTypeState(int ai[]) {
		for (int i = 0; i < ai.length; i++)
			typeState[ai[i]] = true;

	}

	public void setTypeState(int i, boolean flag) {
		typeState[i] = flag;
	}

	public boolean isType(int i) {
		return typeState[i];
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

	public int getIdo() {
		return ido;
	}

	public void setIdo(int ido) {
		this.ido = ido;
	}

	public int getItype() {
		return itype;
	}

	public void setItype(int itype) {
		this.itype = itype;
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

	public int[] getType() {
		return type;
	}

	public void setType(int[] type) {
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

	public boolean[] getTypeState() {
		return typeState;
	}

	public void setTypeState(boolean[] typeState) {
		this.typeState = typeState;
	}  
	
	
}
