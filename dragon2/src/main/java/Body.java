// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Body.java

import java.io.PrintStream;
import java.io.Serializable;

public class Body implements Serializable, Cloneable {

	public Body() {
		atk = new int[6];
		type = new int[5];
		name = "none";
	}

	public Body copy() {
		try {
			return (Body) clone();
		} catch (CloneNotSupportedException clonenotsupportedexception) {
			System.out.println(clonenotsupportedexception);
		}
		return null;
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
		TYPE = new boolean[i];
		setType(type);
	}

	public void setType(int ai[]) {
		for (int i = 0; i < ai.length; i++)
			TYPE[ai[i]] = true;

	}

	public void setType(int i, boolean flag) {
		TYPE[i] = flag;
	}

	public boolean isType(int i) {
		return TYPE[i];
	}

	public boolean isAlive() {
		return hp > 0;
	}

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
	protected boolean TYPE[];
}
