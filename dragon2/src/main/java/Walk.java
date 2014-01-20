// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Walk.java

import java.awt.Point;
import java.util.Vector;
import mine.UnitMap;

class Walk extends ActionBase {

	Walk(Body body) {
		ba = body;
		int i = body.itype;
		ido = body.ido;
		if (body.isType(49))
			ido = ido + 2;
		if (body.isType(48))
			ido = ido + 1;
		if (body.isType(56))
			ido = ido - 1;
		if (body.isType(26))
			ido = (ido + 1) / 2;
		if (body.isType(36))
			i = 1;
		if (body.isType(37))
			i = 2;
		if (body.isType(46))
			i = 1;
		int ai[];
		switch (i) {
		case 1: // '\001'
			int ai1[] = { 1, 1, 1, 1, 1, 99, 1, 1, 1, 1, 1 };
			ai = ai1;
			break;

		case 2: // '\002'
			int ai2[] = { 1, 3, 99, 6, 99, 99, 1, 2, 2, 2, 99 };
			ai = ai2;
			break;

		case 3: // '\003'
			int ai3[] = { 1, 1, 99, 3, 99, 99, 1, 2, 2, 2, 99 };
			ai = ai3;
			break;

		case 4: // '\004'
			int ai4[] = { 99, 99, 99, 1, 1, 99, 1, 1, 1, 1, 99 };
			ai = ai4;
			break;

		case 5: // '\005'
			int ai5[] = { 2, 6, 99, 1, 1, 99, 1, 1, 1, 1, 99 };
			ai = ai5;
			break;

		case 6: // '\006'
			int ai6[] = { 1, 1, 99, 1, 1, 99, 1, 1, 1, 1, 99 };
			ai = ai6;
			break;

		case 7: // '\007'
			int ai7[] = { 1, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99 };
			ai = ai7;
			break;

		default:
			int ai8[] = { 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 };
			ai = ai8;
			break;
		}
		if (body.isType(57)) {
			ai[0] = 1;
			ai[1] = 1;
		}
		if (body.isType(47)) {
			ai[3] = 1;
			ai[4] = 1;
		}
		for (int j = 0; j < ai.length; j++)
			if (ai[j] > ido)
				ai[j] = 255;

		PaintBase.V.clear(0, 2, 0);
		PaintBase.V.change(0, 0, 0, 2, ai);
		PaintBase.V.change(0, 2, 0, 0, 2, 1);
		PaintBase.V.copy(0, 2, 1, 2);
		for (int k = 0; k < PaintBase.Charas.size(); k++) {
			Body body1 = (Body) PaintBase.Charas.elementAt(k);
			if (body1.isAlive())
				PaintBase.V.S(0, 2, body1.x, body1.y, 255);
		}

		PaintBase.V.S(0, 2, body.x, body.y, 1);
		PaintBase.V.J(0, body.x, body.y, ido + 1);
		PaintBase.V.change(0, 3, 65535, 1, 0, 0, 1);
		for (int l = 0; l < PaintBase.Charas.size(); l++) {
			Body body2 = (Body) PaintBase.Charas.elementAt(l);
			if (body2.isAlive())
				PaintBase.V.S(1, 0, body2.x, body2.y, 0);
		}

		PaintBase.V.S(1, 0, body.x, body.y, 1);
		setHelp();
	}

	private void setHelp() {
		if (!Colors.isPlayer(ba)) {
			return;
		} else {
			PaintBase.uw.setHelp(Texts.help[19], 1);
			return;
		}
	}

	public void actionMain() {
		Rewalk.set(ba);
		Point point = PaintBase.map.getWaku();
		walk(point.x, point.y);
		PaintBase.V.clear(1, 0, 0);
		PaintBase.map.setFightManager(ba);
	}

	private void walk(int i, int j) {
		PaintBase.V.J(0, i, j, ido + 1);
		Point point = new Point(ba.x, ba.y);
		PaintBase.uw.setAnime(-4, 0, point, point);
		ba.x = i;
		ba.y = j;
	}

	public void enemy(int i, int j) {
		Rewalk.set(ba);
		walk(i, j);
		PaintBase.V.clear(1, 0, 0);
	}

	public void leftPressed() {
		Point point = PaintBase.map.getWaku();
		if (PaintBase.V.G(1, 0, point.x, point.y) == 0) {
			rightPressed();
			return;
		}
		if (!Statics.isDebug()) {
			if (ba.isType(21))
				return;
			if (ba.isType(39) && ba.isType(58))
				return;
			if (Colors.isPlayer(ba)) {
				if (ba.isType(27))
					return;
			} else if (!ba.isType(27))
				return;
		}
		SlgClient.setActionWalk(ba.x, ba.y, point.x, point.y);
		action();
	}

	public void rightPressed() {
		PaintBase.V.clear(1, 0, 0);
		PaintBase.map.setBasicPaint();
		PaintBase.map.repaint();
	}

	public static int getTikei(Body body) {
		if (body.isType(36))
			return 1;
		if (!body.isType(37) && !body.isType(21)) {
			if (body.itype == 1)
				return 1;
			if (body.itype == 6)
				return 1;
		}
		switch (PaintBase.V.G(0, 0, body.x, body.y)) {
		case 6: // '\006'
			return 5;

		case 3: // '\003'
		case 4: // '\004'
			return 3;

		case 7: // '\007'
		case 8: // '\b'
		case 9: // '\t'
			return 4;

		case 5: // '\005'
		default:
			return 2;
		}
	}

	static final int SKY = 1;
	static final int WALK = 2;
	static final int RUN = 3;
	static final int SWIM = 4;
	static final int TWIN = 5;
	static final int FLY = 6;
	static final int OLD = 7;
	static final int WHITE = 0;
	static final int YELLOW = 1;
	static final int GREEN = 2;
	static final int AQUA = 3;
	static final int BLUE = 4;
	static final int BLACK = 5;
	static final int ICE = 6;
	static final int POISONP = 7;
	static final int OILP = 8;
	static final int FIREP = 9;
	static final int SKYP = 10;
	static final int S_BLUE = 15;
	static final int S_RED = 16;
	static final int C_BLUE = 17;
	static final int C_RED = 18;
	static final int C_BLUEC = 19;
	static final int C_REDC = 20;
	static final int CLOSE_BOX = 21;
	static final int OPEN_BOX = 22;
	static final int BROKEN_BOX = 23;
	static final int OPEN_MAGIC = 24;
	static final int CLOSE_MAGIC = 25;
	static final int T_SKY = 1;
	static final int T_LAND = 2;
	static final int T_SEA = 3;
	static final int T_POOL = 4;
	static final int T_ICE = 5;
	private Body ba;
	private int ido;
}
