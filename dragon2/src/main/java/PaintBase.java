// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PaintBase.java

import java.awt.Point;
import java.util.Vector;
import mine.UnitMap;

abstract class PaintBase implements PaintListener {

	PaintBase() {
	}

	public static void setup(UnitWorks unitworks, UnitMap unitmap, Map map1,
			Vector vector) {
		uw = unitworks;
		V = unitmap;
		map = map1;
		Charas = vector;
	}

	public void setSelectPlace(Point point) {
		uw.displayPlace(point);
	}

	public void setSelectBody(Body body) {
		uw.setSPanel(body);
	}

	public boolean isNextPoint(Point point) {
		return false;
	}

	public void mouseMoved(Point point) {
		map.wakuMove(point);
		map.wakuPaint(true);
	}

	public abstract void leftPressed();

	public abstract void rightPressed();

	public void leftReleased() {
	}

	public void rightReleased() {
	}

	static UnitWorks uw;
	static UnitMap V;
	static Map map;
	static Vector Charas;
}
