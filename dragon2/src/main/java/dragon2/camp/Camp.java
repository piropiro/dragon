package dragon2.camp;





import mine.util.Point;
import java.util.List;
import java.util.Vector;

import dragon2.Sort;
import dragon2.Statics;
import dragon2.Treasure;
import dragon2.attack.AttackData;
import dragon2.common.Body;
import dragon2.common.constant.BodyKind;
import dragon2.common.constant.GameColor;
import dragon2.common.constant.Texts;
import dragon2.common.util.Equip;
import dragon2.paint.PaintBase;

public class Camp extends PaintBase {

	public Camp(Treasure treasure, Equip equip1) {
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
			switch (ba.kind) {
			case CLASS: // '\001'
			case WEPON: // '\002'
			case ARMOR: // '\003'
			case ITEM: // '\004'
				as = Texts.help[5];
				break;

			case DOLL: // '\''
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
		
		for (Body body : equips) {
			if (PaintBase.V.G(1, 0, body.x, body.y) == 1)
				PaintBase.V.S(1, 0, body.x, body.y, 2);
			
			PaintBase.V.S(2, 0, body.x, body.y, body.img);
		}
	}

	private void setColor() {
		for (Body body : equips) {
			if (GameColor.isPlayer(body))
				body.color = GameColor.BLUE;
			else if (GameColor.isEnemy(body))
				body.color = GameColor.RED;
		}

	}

	private void setEquip() {
		for (Body body : equips) {
			body.setMax();
			body.newType();
			if (GameColor.isPlayer(body)) {
				body.x = body.goalX;
				body.y = body.goalY;
			}
			//if (body.x > 13 && body.y == 14)
			//	iterator.remove();
			//else
			PaintBase.V.S(2, 0, body.x, body.y, body.img);
		}

	}

	private void setSource(List<Body> vector, boolean flag) {
		if (vector == null)
			return;
		for (int i = 1; i < 15; i++) {
			int j = flag ? 14 - i : i;
			for (int k = 14; k < 20; k++) {
				if (vector.size() == 0)
					break;
				if (PaintBase.V.G(2, 0, k, j) == 0) {
					Body body = vector.get(0);
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
			Body body = equips.get(i);
			if (body.kind == BodyKind.WAZA || PaintBase.V.G(1, 0, body.x, body.y) == 3) {
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
			Body body = equips.get(i);
			if (body.y != 0 && body.y != 14 && body.x >= 14) {
				if (body.kind == BodyKind.WAZA)
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
			int j = ba.kind == BodyKind.WAZA ? 14 - i : i;
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
		switch (ba.kind) {
		case CLASS: // '\001'
			if (i == 2 || i == 9) {
				Body body = charaCheck(i - 1, j);
				if (body == null)
					return;
				if (!equipCheck(body, ba, 1))
					return;
				if (!levelCheck(body, ba))
					return;
				if (equip.search(i + 1, j) != null) {
					PaintBase.uw.setLPanel(Texts.warning1, GameColor.RED, 1000);
					return;
				} else {
					PaintBase.V.S(1, 0, i, j, 2);
					putChara(i, j, ba);
					equip.equip(body);
					return;
				}
			}
			break;

		case DOLL: // '\''
		case WAZA: // '4'
			break;

		case WEPON: // '\002'
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

		case ARMOR: // '\003'
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

		case ITEM: // '\004'
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
				ba.goalX = i;
				ba.goalY = j;
				ba.color = GameColor.BLUE;
				PaintBase.V.S(1, 0, i, j, 2);
				putChara(i, j, ba);
			}
			return;
		}
		alarm(ba);
	}

	private void alarm(Body body) {
		String s = null;
		switch (body.kind) {
		case CLASS: // '\001'
			s = Texts.shokugyo;
			break;

		case WEPON: // '\002'
			s = Texts.buki;
			break;

		case ARMOR: // '\003'
			s = Texts.bougu;
			break;

		case ITEM: // '\004'
			s = Texts.komono;
			break;

		case DOLL: // '\''
			s = Texts.ningyo;
			break;

		case WAZA: // '4'
			s = Texts.wazasetumei;
			break;

		default:
			s = Texts.nakama;
			break;
		}
		PaintBase.uw.setLPanel(Texts.sokoni + s + Texts.haokemasen, GameColor.RED, 1000);
	}

	private Body charaCheck(int i, int j) {
		Body body = equip.search(i, j);
		if (body != null) {
			return body;
		} else {
			PaintBase.uw.setLPanel(Texts.warning2, GameColor.RED, 1000);
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
		if (attackdata.attackN1 == 0)
			return true;
		if (attackdata1.attackN1 == 0)
			return true;
		switch (i) {
		case 1: // '\001'
			int j = Statics.getBukiType(attackdata.attackN1);
			int k = Statics.getBukiType(attackdata1.attackN1);
			if (j == k) {
				return true;
			} else {
				PaintBase.uw.setLPanel(Texts.warning3, GameColor.RED, 1000);
				return false;
			}

		case 2: // '\002'
			if (attackdata.attackN1 == attackdata1.attackN1) {
				return true;
			} else {
				PaintBase.uw.setLPanel(Texts.warning4, GameColor.RED, 1000);
				return false;
			}
		}
		return false;
	}

	private boolean levelCheck(Body body, Body body1) {
		if (body.level >= body1.level) {
			return true;
		} else {
			PaintBase.uw.setLPanel(Texts.warning5, GameColor.RED, 1000);
			return false;
		}
	}

	private void help(int i, int j) {
		if (j == 13)
			switch (i) {
			case 1: // '\001'
				PaintBase.uw.setCampPanel(new Point(i, j), 6, GameColor.BLUE);
				return;

			case 3: // '\003'
				PaintBase.uw.setCampPanel(new Point(i, j), 10, GameColor.BLUE);
				return;

			case 4: // '\004'
				PaintBase.uw.setCampPanel(new Point(i, j), 11, GameColor.BLUE);
				return;

			case 6: // '\006'
			case 7: // '\007'
				PaintBase.uw.setCampPanel(new Point(i, j), 8, GameColor.BLUE);
				return;

			case 9: // '\t'
			case 10: // '\n'
			case 11: // '\013'
			case 12: // '\f'
				PaintBase.uw.setCampPanel(new Point(i, j), 9, GameColor.BLUE);
				return;
			}
		switch (PaintBase.V.G(1, 0, i, j)) {
		case 2: // '\002'
		default:
			break;

		case 1: // '\001'
		case 4: // '\004'
			if (i == 1) {
				PaintBase.uw.setCampPanel(new Point(i, j), 0, GameColor.BLUE);
				break;
			}
			if (i == 8) {
				PaintBase.uw.setCampPanel(new Point(i, j), 1, GameColor.BLUE);
				break;
			}
			if (i == 2 || i == 9) {
				PaintBase.uw.setCampPanel(new Point(i, j), 2, GameColor.BLUE);
				break;
			}
			if (i == 3 || i == 10) {
				PaintBase.uw.setCampPanel(new Point(i, j), 3, GameColor.BLUE);
				break;
			}
			if (i == 4 || i == 11) {
				PaintBase.uw.setCampPanel(new Point(i, j), 4, GameColor.BLUE);
				break;
			}
			if (i == 5 || i == 12)
				PaintBase.uw.setCampPanel(new Point(i, j), 5, GameColor.BLUE);
			break;

		case 3: // '\003'
			PaintBase.uw.setCampPanel(new Point(i, j), 7, GameColor.RED);
			break;
		}
	}

	private Body pickChara(int i, int j) {
		Body body = equip.search(i, j);
		if (body == null) {
			help(i, j);
			return null;
		} else {
			body.color = GameColor.GREEN;
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
		if (body.color == GameColor.BLUE) {
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
		switch (body.kind) {
		case CLASS: // '\001'
			PaintBase.V.S(1, 0, i, j, 1);
			Vector vector = new Vector();
			body.exp = 0;
			vector.add(body);
			setSource(vector, false);
			PaintBase.map.ppaint(body.x, body.y);
			break;

		case WAZA: // '4'
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
		switch (body.kind) {
		case CLASS: // '\001'
			PaintBase.V.S(1, 0, i, j, 2);
			break;

		case WAZA: // '4'
			PaintBase.V.S(1, 0, i, j, 0);
			break;
		default:
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
				abody[k].color = GameColor.GREEN;
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

		abody[0].goalX = i;
		abody[0].goalY = j;
		abody[0].color = GameColor.BLUE;
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
		switch (ba.kind) {
		case WAZA: 
		default:
			break;

		case WEPON: // '\002'
			if (i == 3) {
				changeChara(i, j);
				return;
			}
			break;

		case ARMOR: // '\003'
			if (i == 4) {
				changeChara(i, j);
				return;
			}
			break;

		case ITEM: // '\004'
			if (i >= 6) {
				changeChara(i, j);
				return;
			}
			break;

		case DOLL: // '\''
			if (i == 1) {
				ba.goalX = i;
				ba.goalY = j;
				ba.color = GameColor.BLUE;
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
			if (body.kind == BodyKind.WAZA)
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
			moveChara(ba.goalX, ba.goalY);
			putChara2(ba.goalX, ba.goalY);
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
				if (body.kind == BodyKind.WAZA)
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
		if (body != null && body.color == GameColor.BLUE)
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
	List<Body> equips;
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
