package dragon2;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Summon.java

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;

import dragon2.common.Body;
import dragon2.common.constant.Types;
import mine.UnitMap;

public class Summon {

	public Summon(Vector vector, UnitWorks unitworks, UnitMap unitmap) {
		uw = unitworks;
		V = unitmap;
		devils = new Body[15];
		int i = 0;
		for (Iterator iterator = vector.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.isType(Types.SUMMON)) {
				unitmap.S(0, 0, body.gx, body.gy, 25);
				body.x = body.gx;
				body.y = body.gy;
				body.gx = 0;
				body.gy = 0;
				body.hp = 0;
				devils[i++] = body;
			}
		}

	}

	public int getLimitTurn(Point point) {
		int i = 64;
		for (int j = 0; j < devils.length; j++) {
			Body body = devils[j];
			if (body != null && point.x == body.x && point.y == body.y
					&& i > body.moveturn)
				i = body.moveturn;
		}

		return i;
	}

	public void summon() {
		int i = uw.getTurn();
		for (int j = 0; j < devils.length; j++) {
			Body body = devils[j];
			if (body != null && body.moveturn <= i
					&& V.G(2, 0, body.x, body.y) == 0) {
				Point point = new Point(body.x, body.y);
				V.S(0, 0, body.x, body.y, 24);
				uw.setAnime(1, -8, point, point);
				uw.setAnime(-8, body.img, point, point);
				body.hp = body.hpMax;
				devils[j] = null;
			}
		}

	}

	private static Body devils[];
	static final int MAX = 15;
	private UnitWorks uw;
	private UnitMap V;
}
