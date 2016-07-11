// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SaveData.java

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class SaveData implements Serializable, Cloneable {

	SaveData() {
		reverse = false;
		allClear = false;
		name = null;
		score = 0;
		mapNum = 0;
		enemyLevel = 0;
		turn = 0;
		dead = 0;
		kill = 0;
		item = 0;
		escape = 0;
		save = 0;
		stage = 0;
		play_time = 0L;
		bodys = new ArrayList<>();
	}

	public SaveData copy() {
		try {
			return (SaveData) clone();
		} catch (CloneNotSupportedException clonenotsupportedexception) {
			System.out.println(clonenotsupportedexception);
		}
		return null;
	}

	boolean reverse;
	boolean allClear;
	int score;
	int mapNum;
	int enemyLevel;
	int turn;
	int dead;
	int kill;
	int item;
	int escape;
	int save;
	int stage;
	long play_time;
	String name;
	List<Body> bodys;
}
