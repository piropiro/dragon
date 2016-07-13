package dragon2.paint;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PaintBase.java

import java.awt.Point;
import java.util.Vector;

import dragon2.Map;
import dragon2.UnitWorks;
import dragon2.common.Body;
import mine.UnitMap;

public abstract class PaintBase implements PaintListener {

	public PaintBase() {
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

	public static UnitWorks uw;
	public static UnitMap V;
	public static Map map;
	public static Vector Charas;
}
