// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Treasure.java

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;
import mine.UnitMap;

class Treasure {

	Treasure(Vector vector, UnitWorks unitworks, UnitMap unitmap) {
		uw = unitworks;
		V = unitmap;
		item = null;
		treasure = new Body[30];
		holder = new Body[30];
		status = new int[30];
		Sources = new Vector();
		Comment = new Vector();
		Point point = unitworks.getCrystal(1);
		int i = 0;
		for (Iterator iterator = vector.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (unitworks.have(body)) {
				body.setTypeState(35, true);
				body.hp = 0;
			} else if (body.x == point.x && body.y == point.y) {
				item = body;
				body.hp = 0;
				status[i] = 4;
				i++;
			} else {
				switch (body.type[0]) {
				case 1: // '\001'
				case 2: // '\002'
				case 3: // '\003'
				case 4: // '\004'
				case 39: // '\''
					treasure[i] = body;
					body.hp = 0;
					i++;
					break;
				}
			}
		}

		for (int j = 0; j < i; j++) {
			Body body1 = treasure[j];
			if (body1 != null && (body1.gx != 0 || body1.gy != 0)) {
				status[j] = 2;
				for (Iterator iterator1 = vector.iterator(); iterator1
						.hasNext();) {
					Body body3 = (Body) iterator1.next();
					if (body3 != body1 && body3.isAlive()
							&& body3.x == body1.gx && body3.y == body1.gy) {
						holder[j] = body3;
						status[j] = 1;
					}
				}

			}
		}

		for (int k = 0; k < i; k++)
			if (holder[k] == null) {
				Body body2 = treasure[k];
				if (body2 != null && body2.moveturn != 0) {
					unitmap.S(0, 0, body2.x, body2.y, 21);
					status[k] = 3;
				}
			}

	}

	public int getLimitTurn(Point point) {
		for (int i = 0; i < treasure.length; i++) {
			Body body = treasure[i];
			if (body != null && holder[i] == null && point.x == body.x
					&& point.y == body.y)
				return body.moveturn;
		}

		return 0;
	}

	public int getLimitTurn() {
		if (item == null)
			return 0;
		else
			return item.moveturn;
	}

	public String getCount() {
		String s = "";
		for (int i = 0; i < treasure.length; i++)
			switch (status[i]) {
			case 0: // '\0'
			default:
				break;

			case 1: // '\001'
				if (holder[i].isAlive() && isAlive(treasure[i]))
					s = s + Texts.kigo[0];
				else
					s = s + Texts.kigo[1];
				break;

			case 2: // '\002'
				if (isAlive(treasure[i]))
					s = s + Texts.kigo[2];
				else
					s = s + Texts.kigo[3];
				break;

			case 3: // '\003'
				if (isAlive(treasure[i]))
					s = s + Texts.kigo[4];
				else
					s = s + Texts.kigo[5];
				break;

			case 4: // '\004'
				if (isAlive(item))
					s = s + Texts.kigo[6];
				else
					s = s + Texts.kigo[7];
				break;

			case 5: // '\005'
				s = s + Texts.kigo[8];
				break;
			}

		return s;
	}

	public Vector getSources() {
		return Sources;
	}

	public void add(Body body) {
		Sources.add(body);
		uw.countItem();
	}

	public void limitOver() {
		for (int i = 0; i < treasure.length; i++) {
			Body body = treasure[i];
			if (body != null && body.moveturn == uw.getTurn()
					&& V.G(0, 0, body.x, body.y) == 21) {
				V.S(0, 0, body.x, body.y, 23);
				Point point = new Point(body.x, body.y);
				uw.setAnime(-7, 11, point, point);
				uw.sleep(300L);
				V.S(5, 0, body.x, body.y, 0);
			}
		}

	}

	public void addMember(Body body) {
		add(body);
		getTreasure(body, true);
		message();
	}

	public void getTreasure(Body body, boolean flag) {
		for (int i = 0; i < holder.length; i++)
			if (body == holder[i]) {
				Body body1 = treasure[i];
				if (isAlive(body1) && (body1.moveturn != 0 || flag)) {
					add(body1);
					Comment.add(new Integer(i));
					status[i] = 5;
				}
			}

	}

	public void searchTreasure(Body body) {
		for (int i = 0; i < treasure.length; i++) {
			Body body1 = treasure[i];
			if (isAlive(body1) && holder[i] == null && body1.x == body.x
					&& body1.y == body.y) {
				treasure[i] = null;
				status[i] = 5;
				message(body, body1);
				add(body1);
				if (V.G(0, 0, body.x, body.y) == 21)
					V.S(0, 0, body.x, body.y, 22);
			}
		}

	}

	public void getClearItem() {
		if (!isAlive(item)) {
			return;
		} else {
			message(null, item);
			add(item);
			return;
		}
	}

	private boolean isAlive(Body body) {
		if (body == null)
			return false;
		if (body.moveturn == 0)
			return true;
		return body.moveturn >= uw.getTurn();
	}

	public void message() {
		Body body1;
		for (Iterator iterator = Comment.iterator(); iterator.hasNext(); uw
				.startMPanel(body1)) {
			Integer integer = (Integer) iterator.next();
			Body body = holder[integer.intValue()];
			body1 = treasure[integer.intValue()];
			uw.setMPanel(body.name + Texts.ha);
			uw.setMPanel(Texts.treasure1);
			uw.setMPanel(body1.name + Texts.wo);
			uw.setMPanel(Texts.treasure2);
		}

		Comment.removeAllElements();
	}

	public void message(Body body, Body body1) {
		if (body != null) {
			if (body.name.length() <= 2) {
				uw.setMPanel(body.name + Texts.ha + Texts.treasure3);
			} else {
				uw.setMPanel(body.name + Texts.ha);
				uw.setMPanel(Texts.treasure3);
			}
		} else {
			uw.setMPanel(Texts.treasure3);
		}
		uw.setMPanel(body1.name + Texts.wo);
		uw.setMPanel(Texts.treasure2);
		uw.startMPanel(body1);
	}

	public void down() {
		for (Iterator iterator = Sources.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			switch (body.type[0]) {
			case 2: // '\002'
			case 3: // '\003'
			case 4: // '\004'
				down(body);
				break;
			}
		}

	}

	public static void down(Body body) {
		body.hpMax = Math.max(1, body.hpMax - 1);
		body.strMax = Math.max(0, body.strMax - 6);
		body.defMax = Math.max(0, body.defMax - 6);
		body.mstMax = Math.max(0, body.mstMax - 6);
		body.mdfMax = Math.max(0, body.mdfMax - 6);
		body.hitMax = Math.max(0, body.hitMax - 6);
		body.misMax = Math.max(0, body.misMax - 6);
		body.type[3] = 44;
		body.setTypeState(44, true);
	}

	private Body item;
	private Body treasure[];
	private Body holder[];
	private int status[];
	static final int MAX = 30;
	static final int S_NONE = 0;
	static final int S_ENEMY = 1;
	static final int S_GROUND = 2;
	static final int S_BOX = 3;
	static final int S_CLEAR = 4;
	static final int S_HAVE = 5;
	private Vector Sources;
	private Vector Comment;
	private UnitWorks uw;
	private UnitMap V;
}
