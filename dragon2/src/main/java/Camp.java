// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Camp.java

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;
import mine.UnitMap;

class Camp extends PaintBase {

	Camp(Treasure treasure, Equip equip1) {
		equip = equip1;
		equips = equip1.getEquips();
		setEquip();
		if (treasure != null)
			setSource(treasure.getSources(), false);
		setColor();
		setHelp();
	}

	public void setHelp() {
		String as[];
		if (ba == null) {
			Point point = PaintBase.map.getWaku();
			if (PaintBase.V.G(3, 0, point.x, point.y) != 0) {
				if (point.x < 14)
					as = Texts.help[2];
				else
					as = Texts.help[3];
			} else {
				as = Texts.help[4];
			}
		} else {
			switch (ba.type[0]) {
			case 1: // '\001'
			case 2: // '\002'
			case 3: // '\003'
			case 4: // '\004'
				as = Texts.help[5];
				break;

			case 39: // '\''
				as = Texts.help[6];
				break;

			default:
				as = Texts.help[7];
				break;
			}
		}
		PaintBase.uw.setHelp(as, 1);
	}

	public void repaint(int ai[][]) {
		PaintBase.V.R(1, 0, ai);
		PaintBase.V.clear(0, 0, 0);
		PaintBase.V.clear(2, 0, 0);
		PaintBase.V.clear(3, 0, 0);
		PaintBase.V.clear(4, 0, 0);
		PaintBase.V.clear(5, 0, 0);
		Body body;
		for (Iterator iterator = equips.iterator(); iterator.hasNext(); PaintBase.V
				.S(2, 0, body.x, body.y, body.img)) {
			body = (Body) iterator.next();
			if (PaintBase.V.G(1, 0, body.x, body.y) == 1)
				PaintBase.V.S(1, 0, body.x, body.y, 2);
		}

	}

	private void setColor() {
		for (Iterator iterator = equips.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (Colors.isPlayer(body))
				body.color = 1;
			else if (Colors.isEnemy(body))
				body.color = 3;
		}

		Colors.setup(1, 3);
	}

	private void setEquip() {
		for (Iterator iterator = equips.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			body.setMax();
			body.newType(100);
			if (Colors.isPlayer(body)) {
				body.x = body.gx;
				body.y = body.gy;
			}
			if (body.x > 13 && body.y == 14)
				iterator.remove();
			else
				PaintBase.V.S(2, 0, body.x, body.y, body.img);
		}

	}

	private void setSource(Vector vector, boolean flag) {
		if (vector == null)
			return;
		for (int i = 1; i < 15; i++) {
			int j = flag ? 14 - i : i;
			for (int k = 14; k < 20; k++) {
				if (vector.size() == 0)
					break;
				if (PaintBase.V.G(2, 0, k, j) == 0) {
					Body body = (Body) vector.firstElement();
					body.x = k;
					body.y = j;
					body.setMax();
					equips.add(body);
					vector.remove(body);
					PaintBase.V.S(2, 0, body.x, body.y, body.img);
				}
			}

		}

	}

	public void removeDust() {
		for (int i = equips.size() - 1; i >= 0; i--) {
			Body body = (Body) equips.elementAt(i);
			if (body.isType(52) || PaintBase.V.G(1, 0, body.x, body.y) == 3) {
				equips.remove(body);
				PaintBase.V.S(2, 0, body.x, body.y, 0);
				PaintBase.map.ppaint(body.x, body.y);
			}
		}

	}

	public void sortItem() {
		Vector vector = new Vector();
		Vector vector1 = new Vector();
		for (int i = equips.size() - 1; i >= 0; i--) {
			Body body = (Body) equips.elementAt(i);
			if (body.y != 0 && body.y != 14 && body.x >= 14) {
				if (body.isType(52))
					vector1.add(body);
				else
					vector.add(body);
				equips.remove(body);
				PaintBase.V.S(2, 0, body.x, body.y, 0);
			}
		}

		Vector vector2 = Sort.sort(vector1);
		Vector vector3 = Sort.sort(vector);
		setSource(vector3, false);
		setSource(vector2, true);
		PaintBase.map.repaint();
	}

	public void wazaList() {
		Vector vector = new Vector();
		for (int i = 1; i <= 8; i += 7) {
			for (int j = 1; j <= 13; j += 3) {
				Body body = equip.search(i, j);
				if (body != null) {
					equip.equip(body);
					for (int k = 0; k < body.atk.length; k++)
						if (body.atk[k] != 0) {
							Body body1 = Statics.getWaza(body.atk[k]);
							if (!equip.have(body1))
								vector.add(body1);
						}

				}
			}

		}

		setSource(vector, true);
		PaintBase.map.repaint();
	}

	private void backChara() {
		for (int i = 1; i < 15; i++) {
			int j = ba.isType(52) ? 14 - i : i;
			for (int k = 14; k < 20; k++)
				if (PaintBase.V.G(2, 0, k, j) == 0) {
					moveChara(k, j);
					putChara(k, j, ba);
					PaintBase.map.repaint();
					return;
				}

		}

	}

	private void moveChara(int i, int j) {
		if (end != null) {
			removeCancel(end.x, end.y);
			end = null;
		}
		if (ba == null)
			return;
		if (ps != null)
			PaintBase.V.S(2, 0, ps.x, ps.y, 0);
		if (PaintBase.V.G(2, 0, i, j) == 0) {
			PaintBase.V.S(2, 0, i, j, ba.img);
			ps = new Point(i, j);
		} else {
			ps = null;
		}
	}

	private void putChara(int i, int j, Body body) {
		if (equip.search(i, j) != null) {
			return;
		} else {
			equips.add(body);
			body.x = i;
			body.y = j;
			PaintBase.V.S(2, 0, i, j, body.img);
			ps = null;
			ba = null;
			return;
		}
	}

	private void putChara2(int i, int j) {
		if (equip.search(i, j) != null)
			return;
		switch (ba.type[0]) {
		case 1: // '\001'
			if (i == 2 || i == 9) {
				Body body = charaCheck(i - 1, j);
				if (body == null)
					return;
				if (!equipCheck(body, ba, 1))
					return;
				if (!levelCheck(body, ba))
					return;
				if (equip.search(i + 1, j) != null) {
					PaintBase.uw.setLPanel(Texts.warning1, 3, 1000);
					return;
				} else {
					PaintBase.V.S(1, 0, i, j, 2);
					putChara(i, j, ba);
					equip.equip(body);
					return;
				}
			}
			break;

		case 39: // '\''
		case 52: // '4'
			break;

		case 2: // '\002'
			if (i != 3 && i != 10)
				break;
			Body body1 = charaCheck(i - 2, j);
			if (body1 == null)
				return;
			if (!equipCheck(body1, ba, 2))
				return;
			if (!levelCheck(body1, ba)) {
				return;
			} else {
				putChara(i, j, ba);
				return;
			}

		case 3: // '\003'
			if (i != 4 && i != 11)
				break;
			Body body2 = charaCheck(i - 3, j);
			if (body2 == null)
				return;
			if (!levelCheck(body2, ba)) {
				return;
			} else {
				putChara(i, j, ba);
				return;
			}

		case 4: // '\004'
			if (i != 5 && i != 12)
				break;
			Body body3 = charaCheck(i - 4, j);
			if (body3 == null)
				return;
			if (!levelCheck(body3, ba)) {
				return;
			} else {
				putChara(i, j, ba);
				return;
			}

		default:
			if (i != 1 && i != 8)
				break;
			if (sortf) {
				putSortItems(i, j, items);
			} else {
				ba.gx = i;
				ba.gy = j;
				ba.color = 1;
				PaintBase.V.S(1, 0, i, j, 2);
				putChara(i, j, ba);
			}
			return;
		}
		alarm(ba);
	}

	private void alarm(Body body) {
		String s = null;
		switch (body.type[0]) {
		case 1: // '\001'
			s = Texts.shokugyo;
			break;

		case 2: // '\002'
			s = Texts.buki;
			break;

		case 3: // '\003'
			s = Texts.bougu;
			break;

		case 4: // '\004'
			s = Texts.komono;
			break;

		case 39: // '\''
			s = Texts.ningyo;
			break;

		case 52: // '4'
			s = Texts.wazasetumei;
			break;

		default:
			s = Texts.nakama;
			break;
		}
		PaintBase.uw.setLPanel(Texts.sokoni + s + Texts.haokemasen, 3, 1000);
	}

	private Body charaCheck(int i, int j) {
		Body body = equip.search(i, j);
		if (body != null) {
			return body;
		} else {
			PaintBase.uw.setLPanel(Texts.warning2, 3, 1000);
			return null;
		}
	}

	private boolean equipCheck(Body body, Body body1, int i) {
		if (body == null)
			return false;
		if (body1 == null)
			return false;
		AttackData attackdata = Statics.getAttackData(body.atk[0]);
		AttackData attackdata1 = Statics.getAttackData(body1.atk[0]);
		if (attackdata.AttackN1 == 0)
			return true;
		if (attackdata1.AttackN1 == 0)
			return true;
		switch (i) {
		case 1: // '\001'
			int j = Statics.getBukiType(attackdata.AttackN1);
			int k = Statics.getBukiType(attackdata1.AttackN1);
			if (j == k) {
				return true;
			} else {
				PaintBase.uw.setLPanel(Texts.warning3, 3, 1000);
				return false;
			}

		case 2: // '\002'
			if (attackdata.AttackN1 == attackdata1.AttackN1) {
				return true;
			} else {
				PaintBase.uw.setLPanel(Texts.warning4, 3, 1000);
				return false;
			}
		}
		return false;
	}

	private boolean levelCheck(Body body, Body body1) {
		if (body.level >= body1.level) {
			return true;
		} else {
			PaintBase.uw.setLPanel(Texts.warning5, 3, 1000);
			return false;
		}
	}

	private void help(int i, int j) {
		if (j == 13)
			switch (i) {
			case 1: // '\001'
				PaintBase.uw.setCampPanel(new Point(i, j), 6, 1);
				return;

			case 3: // '\003'
				PaintBase.uw.setCampPanel(new Point(i, j), 10, 1);
				return;

			case 4: // '\004'
				PaintBase.uw.setCampPanel(new Point(i, j), 11, 1);
				return;

			case 6: // '\006'
			case 7: // '\007'
				PaintBase.uw.setCampPanel(new Point(i, j), 8, 1);
				return;

			case 9: // '\t'
			case 10: // '\n'
			case 11: // '\013'
			case 12: // '\f'
				PaintBase.uw.setCampPanel(new Point(i, j), 9, 1);
				return;
			}
		switch (PaintBase.V.G(1, 0, i, j)) {
		case 2: // '\002'
		default:
			break;

		case 1: // '\001'
		case 4: // '\004'
			if (i == 1) {
				PaintBase.uw.setCampPanel(new Point(i, j), 0, 1);
				break;
			}
			if (i == 8) {
				PaintBase.uw.setCampPanel(new Point(i, j), 1, 1);
				break;
			}
			if (i == 2 || i == 9) {
				PaintBase.uw.setCampPanel(new Point(i, j), 2, 1);
				break;
			}
			if (i == 3 || i == 10) {
				PaintBase.uw.setCampPanel(new Point(i, j), 3, 1);
				break;
			}
			if (i == 4 || i == 11) {
				PaintBase.uw.setCampPanel(new Point(i, j), 4, 1);
				break;
			}
			if (i == 5 || i == 12)
				PaintBase.uw.setCampPanel(new Point(i, j), 5, 1);
			break;

		case 3: // '\003'
			PaintBase.uw.setCampPanel(new Point(i, j), 7, 3);
			break;
		}
	}

	private Body pickChara(int i, int j) {
		Body body = equip.search(i, j);
		if (body == null) {
			help(i, j);
			return null;
		} else {
			body.color = 2;
			equips.remove(body);
			ps = new Point(i, j);
			return body;
		}
	}

	private void changeChara(int i, int j) {
		if (equip.search(i, j) == null) {
			putChara(i, j, ba);
			return;
		} else {
			Body body = pickChara(i, j);
			putChara(i, j, ba);
			ba = body;
			return;
		}
	}

	private void removeChara1(int i, int j) {
		Body body = equip.search(i, j);
		if (body == null)
			return;
		if (body.color == 1) {
			items = pickSortItems(i, j);
			return;
		} else {
			end = new Point(i, j);
			PaintBase.V.S(1, 0, i, j, 3);
			PaintBase.V.S(3, 0, i, j, 4);
			PaintBase.V.S(4, 0, i, j, 0);
			PaintBase.map.ppaint(i, j);
			return;
		}
	}

	private void removeChara2(int i, int j) {
		Body body = equip.search(i, j);
		equips.remove(body);
		end = null;
		switch (body.type[0]) {
		case 1: // '\001'
			PaintBase.V.S(1, 0, i, j, 1);
			Vector vector = new Vector();
			body.exp = 0;
			vector.add(body);
			setSource(vector, false);
			PaintBase.map.ppaint(body.x, body.y);
			break;

		case 52: // '4'
			PaintBase.V.S(1, 0, i, j, 0);
			break;
		}
		PaintBase.V.S(2, 0, i, j, 0);
		PaintBase.V.S(3, 0, i, j, 0);
		PaintBase.map.ppaint(i, j);
	}

	private void removeCancel(int i, int j) {
		if (PaintBase.V.G(3, 0, i, j) == 0)
			return;
		Body body = equip.search(i, j);
		switch (body.type[0]) {
		case 1: // '\001'
			PaintBase.V.S(1, 0, i, j, 2);
			break;

		case 52: // '4'
			PaintBase.V.S(1, 0, i, j, 0);
			break;
		}
		PaintBase.V.S(3, 0, i, j, 0);
		PaintBase.map.ppaint(i, j);
		setHelp();
	}

	private Body[] pickSortItems(int i, int j) {
		Body abody[] = new Body[5];
		for (int k = 1; k <= 4; k++) {
			abody[k] = equip.search(i + k, j);
			if (abody[k] != null) {
				PaintBase.V.S(2, 0, i + k, j, 0);
				abody[k].color = 2;
				equips.remove(abody[k]);
				sortf = true;
			}
		}

		PaintBase.V.S(1, 0, i, j, 1);
		PaintBase.V.S(1, 0, i + 1, j, 1);
		abody[0] = pickChara(i, j);
		ba = abody[0];
		PaintBase.map.repaint();
		return abody;
	}

	private void putSortItems(int i, int j, Body abody[]) {
		for (int k = 1; k <= 4; k++)
			if (abody[k] != null) {
				equips.add(abody[k]);
				abody[k].x = i + k;
				abody[k].y = j;
				PaintBase.V.S(2, 0, i + k, j, abody[k].img);
			}

		abody[0].gx = i;
		abody[0].gy = j;
		abody[0].color = 1;
		PaintBase.V.S(1, 0, i, j, 2);
		putChara(i, j, abody[0]);
		if (abody[1] != null)
			PaintBase.V.S(1, 0, i + 1, j, 2);
		sortf = false;
		PaintBase.map.repaint();
	}

	private void changeSortChara(int i, int j) {
		if (i != 1 && i != 8)
			return;
		if (!sortf)
			return;
		Body abody[] = pickSortItems(i, j);
		putSortItems(i, j, items);
		items = abody;
		ba = abody[0];
		for (int k = 1; k <= 4; k++)
			if (abody[k] != null)
				sortf = true;

		PaintBase.map.repaint();
	}

	private void putDoll(int i, int j) {
		if (PaintBase.V.G(1, 0, i, j) == 2)
			return;
		switch (ba.type[0]) {
		case 52: // '4'
		default:
			break;

		case 2: // '\002'
			if (i == 3) {
				changeChara(i, j);
				return;
			}
			break;

		case 3: // '\003'
			if (i == 4) {
				changeChara(i, j);
				return;
			}
			break;

		case 4: // '\004'
			if (i >= 6) {
				changeChara(i, j);
				return;
			}
			break;

		case 39: // '\''
			if (i == 1) {
				ba.gx = i;
				ba.gy = j;
				ba.color = 1;
				changeChara(i, j);
				return;
			}
			break;
		}
		alarm(ba);
	}

	public void leftPressed() {
		Point point = PaintBase.map.getWaku();
		Body body = equip.search(point.x, point.y);
		if (body != null)
			PaintBase.uw.setAtkList(body);
		if (point.y == 13 && 1 <= point.x && point.x <= 12) {
			if (ba != null)
				putDoll(point.x, point.y);
			else
				ba = pickChara(point.x, point.y);
			setHelp();
			return;
		}
		switch (PaintBase.V.G(1, 0, point.x, point.y)) {
		default:
			break;

		case 0: // '\0'
			if (sortf)
				return;
			if (ba != null) {
				changeChara(point.x, point.y);
				break;
			}
			if (body == null)
				break;
			if (body.isType(52))
				removeChara1(point.x, point.y);
			else
				ba = pickChara(point.x, point.y);
			break;

		case 1: // '\001'
		case 4: // '\004'
			if (ba != null)
				putChara2(point.x, point.y);
			else
				ba = pickChara(point.x, point.y);
			break;

		case 2: // '\002'
			if (ba != null)
				changeSortChara(point.x, point.y);
			else
				removeChara1(point.x, point.y);
			break;

		case 3: // '\003'
			if (sortf)
				return;
			if (PaintBase.V.G(3, 0, point.x, point.y) == 0) {
				if (ba != null)
					putChara(point.x, point.y, ba);
				else
					ba = pickChara(point.x, point.y);
				break;
			}
			if (ba == null)
				removeChara2(point.x, point.y);
			break;
		}
		setHelp();
	}

	public void rightPressed() {
		Point point = PaintBase.map.getWaku();
		if (sortf) {
			moveChara(ba.gx, ba.gy);
			putChara2(ba.gx, ba.gy);
			setHelp();
			return;
		}
		switch (PaintBase.V.G(1, 0, point.x, point.y)) {
		default:
			break;

		case 0: // '\0'
		case 1: // '\001'
		case 2: // '\002'
		case 4: // '\004'
			if (ba != null) {
				backChara();
				break;
			}
			Body body = equip.search(point.x, point.y);
			if (body != null) {
				if (body.isType(52))
					removeChara1(point.x, point.y);
				else
					PaintBase.uw.setAnalyze(body);
			} else {
				leftPressed();
			}
			break;

		case 3: // '\003'
			removeCancel(point.x, point.y);
			break;
		}
		setHelp();
	}

	public void mouseMoved(Point point) {
		PaintBase.map.wakuMove(point);
		Body body = equip.search(point.x, point.y);
		if (body != null && body.color == 1)
			equip.equip(body);
		PaintBase.uw.setSPanel(body);
		moveChara(point.x, point.y);
		PaintBase.map.wakuPaint(true);
	}

	public boolean isNextPoint(Point point) {
		if (PaintBase.V.G(1, 0, point.x, point.y) != 0) {
			return false;
		} else {
			Body body = equip.search(point.x, point.y);
			return body != null;
		}
	}

	Equip equip;
	Vector equips;
	Point ps;
	Point end;
	Body ba;
	Body items[];
	boolean sortf;
	static final int T_NONE = 0;
	static final int T_FREE = 1;
	static final int T_PASTE = 2;
	static final int T_ERASE = 3;
	static final int T_STORE = 4;
}
