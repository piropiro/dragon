// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SlgManager.java

import java.util.Vector;

class SlgManager {

	SlgManager() {
	}

	public int[][] getMapData() {
		return new int[1][1];
	}

	public boolean setMapData(int ai[][]) {
		return true;
	}

	public int getNumber() {
		return 1;
	}

	public Vector getChara(int i) {
		return new Vector();
	}

	public void setChara(int i, Vector vector) {
	}

	public Action getAction() {
		return new Action();
	}

	public void setAction(Action action) {
	}

	public boolean isMyTurn(int i) {
		return true;
	}

	public void reset() {
	}

	public void setTurnEnd(int i) {
	}
}
