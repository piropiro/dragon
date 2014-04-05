// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TurnManager.java

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;
import mine.UnitMap;

class TurnManager extends ActionBase {

	TurnManager(Vector vector, UnitWorks unitworks) {
		Charas = vector;
		uw = unitworks;
	}

	public void reset() {
		turn = 0;
	}

	public int getTurn() {
		return turn;
	}

	public void change(int i) {
		type = i;
		if (i == 0)
			turn++;
		action();
	}

	public void actionMain() {
		switch (type) {
		case 0: // '\0'
			mensTurnStart();
			break;

		case 1: // '\001'
			enemyTurnStart();
			break;

		case 2: // '\002'
			remoteTurnStart();
			break;
		}
	}

	private void mensTurnStart() {
		turnChange(true);
		PaintBase.map.setBasicPaint();
		PaintBase.map.repaint();
	}

	private void enemyTurnStart() {
		turnChange(false);
		uw.limitOver();
		EnemyTurn enemyturn = new EnemyTurn();
		PaintBase.map.setPaintListener(enemyturn);
	}

	private void remoteTurnStart() {
		SlgClient.setTurnEnd();
		turnChange(false);
		RemoteTurn remoteturn = new RemoteTurn();
		PaintBase.map.setPaintListener(remoteturn);
	}

	private void turnChange(boolean flag) {
		PaintBase.V.clear(1, 0, 0);
		PaintBase.V.clear(3, 0, 0);
		PaintBase.V.clear(4, 0, 0);
		for (Iterator iterator = Charas.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.isAlive()) {
				if (!body.isType(32))
					PaintBase.V.S(5, 0, body.x, body.y, 0);
				body.setTypeState(36, false);
				body.setTypeState(37, false);
				setTikei(body, flag);
				setPoison(body, flag);
				setHeal(body, flag);
				setBersek(body, flag);
				setStatus(body, 30, 4);
				setStatus(body, 31, 5);
				setStatus(body, 25, 3);
				setStatus(body, 26, 10);
				setStatus(body, 27, 6);
				setStatus(body, 21, 1);
				if (body.isType(32)) {
					body.setTypeState(32, false);
					if (body.isType(27) || body.isType(21))
						body.setTypeState(35, true);
				}
			}
		}

	}

	private void setStatus(Body body, int i, int j) {
		if (!body.isType(32))
			body.setTypeState(i, false);
		else if (body.isType(i))
			PaintBase.V.S(5, 0, body.x, body.y, j);
	}

	private void setTikei(Body body, boolean flag) {
		if (Walk.getTikei(body) == 1)
			return;
		Point point = new Point(body.x, body.y);
		switch (PaintBase.V.G(0, 0, body.x, body.y)) {
		case 17: // '\021'
			if (Colors.isPlayer(body) != flag)
				return;
			if (body.hp == body.hpMax && !body.isType(23)) {
				return;
			} else {
				body.setTypeState(23, false);
				uw.setAnime(1, -1, point, point);
				body.hp += Math.max(2, body.hpMax / 4);
				body.hp = Math.min(body.hp, body.hpMax);
				return;
			}

		case 3: // '\003'
		case 4: // '\004'
			if (body.isType(17))
				return;
			if (body.isType(47))
				return;
			if (body.itype == 4)
				return;
			if (body.itype == 5)
				return;
			body.setTypeState(25, true);
			if (body.isType(21))
				return;
			if (body.isType(27)) {
				return;
			} else {
				body.setTypeState(32, true);
				return;
			}

		case 7: // '\007'
			if (body.isType(17))
				return;
			if (body.isType(24)) {
				return;
			} else {
				body.setTypeState(23, true);
				return;
			}

		case 8: // '\b'
			if (body.isType(17))
				return;
			body.setTypeState(26, true);
			if (body.isType(21))
				return;
			if (body.isType(27)) {
				return;
			} else {
				body.setTypeState(32, true);
				return;
			}

		case 9: // '\t'
			if (Colors.isPlayer(body) != flag)
				return;
			if (body.isType(8))
				return;
			int i = 4;
			if (body.isType(6))
				i *= 2;
			if (body.isType(26))
				i *= 2;
			if (body.isType(7))
				i /= 2;
			uw.setAnime(1, -10, point, point);
			body.hp -= Math.max(2, (body.hpMax * i) / 16);
			body.hp = Math.max(1, body.hp);
			// fall through

		case 5: // '\005'
		case 6: // '\006'
		case 10: // '\n'
		case 11: // '\013'
		case 12: // '\f'
		case 13: // '\r'
		case 14: // '\016'
		case 15: // '\017'
		case 16: // '\020'
		default:
			return;
		}
	}

	private void setPoison(Body body, boolean flag) {
		if (!body.isType(23))
			return;
		if (Colors.isPlayer(body) != flag || body.hp == 1) {
			PaintBase.V.S(5, 0, body.x, body.y, 2);
		} else {
			Point point = new Point(body.x, body.y);
			uw.setAnime(-7, 2, point, point);
			body.hp -= Math.max(1, body.hpMax / 8);
			body.hp = Math.max(1, body.hp);
		}
		if (body.hp == 1)
			body.setTypeState(23, false);
	}

	private void setBersek(Body body, boolean flag) {
		if (!body.isType(39))
			return;
		if (!body.isType(58))
			return;
		if (Colors.isPlayer(body) != flag) {
			PaintBase.V.S(5, 0, body.x, body.y, 12);
		} else {
			body.str = Math.max(0, body.str - 1);
			body.def = Math.max(0, body.def - 1);
			body.mst = Math.max(0, body.mst - 1);
			body.mdf = Math.max(0, body.mdf - 1);
			body.hit = Math.max(0, body.hit - 1);
			body.mis = Math.max(0, body.mis - 1);
			Point point = new Point(body.x, body.y);
			uw.setAnime(-7, 12, point, point);
		}
	}

	private void setHeal(Body body, boolean flag) {
		if (!body.isType(29))
			return;
		if (Colors.isPlayer(body) != flag || body.hp == body.hpMax)
			PaintBase.V.S(5, 0, body.x, body.y, 7);
		else if (body.mst > 0) {
			Point point = new Point(body.x, body.y);
			uw.setAnime(-7, 7, point, point);
			body.mst = Math.max(0, body.mst - 2);
			body.hp = Math.min(body.hpMax, body.hp + body.hpMax / 2);
		} else {
			body.setTypeState(29, false);
		}
	}

	public void setSelectBody(Body body) {
	}

	public void mouseMoved(Point point) {
	}

	public void leftPressed() {
	}

	public void rightPressed() {
		uw.waitFast();
	}

	public void rightReleased() {
		uw.waitSlow();
	}

	static final int MENS = 0;
	static final int ENEMY = 1;
	static final int REMOTE = 2;
	private int turn;
	private int type;
	private Vector Charas;
	private UnitWorks uw;
}
