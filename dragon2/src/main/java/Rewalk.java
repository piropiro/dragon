// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Rewalk.java

import mine.UnitMap;

class Rewalk {

	Rewalk() {
	}

	public static void setup(UnitWorks unitworks, UnitMap unitmap, Map map1) {
		uw = unitworks;
		V = unitmap;
		map = map1;
	}

	public static void set(Body body) {
		x = body.x;
		y = body.y;
	}

	public static void rewalk(Body body) {
		V.clear(4, 0, 0);
		V.clear(1, 0, 0);
		V.S(2, 0, body.x, body.y, 0);
		V.S(3, 0, body.x, body.y, 0);
		int i = V.G(5, 0, body.x, body.y);
		V.S(5, 0, body.x, body.y, 0);
		body.x = x;
		body.y = y;
		V.S(2, 0, body.x, body.y, body.img);
		V.S(5, 0, body.x, body.y, i);
		uw.closeHPanel();
		uw.closeTPanel();
		uw.closeAPanel();
		map.setWalkPaint(body);
		map.repaint();
	}

	public static boolean isWalked(Body body) {
		if (body.x != x)
			return true;
		return body.y != y;
	}

	static UnitWorks uw;
	static UnitMap V;
	static Map map;
	static int x;
	static int y;
}
