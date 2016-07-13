package dragon2.common.util;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import dragon2.Statics;
import dragon2.UnitWorks;
import dragon2.attack.AttackData;
import dragon2.common.Body;
import dragon2.common.constant.Colors;
import dragon2.common.constant.Kinds;
import dragon2.common.constant.Texts;

public class Equip {

	public Equip(List<Body> bodys, UnitWorks unitworks) {
		uw = unitworks;
		Equips = bodys;
	}

	public Body search(int i, int j) {
		for (Iterator iterator = Equips.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.x == i && body.y == j)
				return body;
		}

		return null;
	}

	private Body searchItem(int i, int j) {
		for (Iterator iterator = Equips.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.color == 2 && body.x == i && body.y == j)
				return body;
		}

		return null;
	}

	public void addBody(Body body) {
		Equips.add(body);
	}

	public void removeBody(Body body) {
		Equips.remove(body);
	}

	public List<Body> getEquips() {
		return Equips;
	}

	public void getEXP(Body body, Body body1) {
		int i = body1.exp;
		if (body.level != 0)
			if (body1.level == 0)
				i = (int) ((double) i * 1.5D);
			else if (body.level > body1.level)
				i /= Math.min((body.level - body1.level) + 1, 4);
			else
				i += (i * (body1.level - body.level)) / 2;
		body.exp += i;
		body1.exp = 0;
		if (body.kind == Kinds.DOLL) {
			for (int j = 2; j <= 11; j++) {
				Body body2 = searchItem(body.gx + j, body.gy);
				if (body2 != null)
					body2.exp += (double) i * 1.5D;
			}

		} else {
			for (int k = 1; k <= 4; k++) {
				Body body3 = searchItem(body.gx + k, body.gy);
				if (body3 != null)
					body3.exp += i;
			}

		}
	}

	public void levelup(Body body) {
		if (body.level == 0) {
			if (body.exp >= 1000) {
				body.exp -= 1000;
				uw.setMPanel(body.name + Texts.ha);
				uw.setMPanel(Texts.equip1);
				statusup(body);
				uw.startMPanel(body);
			}
			for (int i = 2; i <= 11; i++) {
				Body body2 = searchItem(body.gx + i, body.gy);
				itemup(body2);
			}

			return;
		}
		Body body1 = searchItem(body.gx + 1, body.gy);
		Body body3 = searchItem(body.gx + 2, body.gy);
		Body body4 = searchItem(body.gx + 3, body.gy);
		Body body5 = searchItem(body.gx + 4, body.gy);
		itemup(body1);
		itemup(body3);
		itemup(body4);
		itemup(body5);
		while (body.exp >= 100) {
			body.exp -= 100;
			if (body.name.length() <= 1) {
				uw.setMPanel(body.name + Texts.ha + Texts.equip1);
			} else {
				uw.setMPanel(body.name + Texts.ha);
				uw.setMPanel(Texts.equip1);
			}
			statusup(body);
			body.level++;
		}
		attackup(body);
		uw.startMPanel(body);
	}

	private void itemup(Body body) {
		if (body == null)
			return;
		if (body.exp < 1000) {
			return;
		} else {
			body.exp -= 1000;
			body.level += 10;
			body.hpMax = (body.hpMax * 3) / 2 + 1;
			body.strMax = (body.strMax * 3) / 2 + 10;
			body.defMax = (body.defMax * 3) / 2 + 10;
			body.mstMax = (body.mstMax * 3) / 2 + 10;
			body.mdfMax = (body.mdfMax * 3) / 2 + 10;
			body.hitMax = (body.hitMax * 3) / 2 + 10;
			body.misMax = (body.misMax * 3) / 2 + 10;
			restrict(body);
			return;
		}
	}

	private void attackup(Body body) {
		boolean aflag[] = getAttack(body);
		attacklearn(Texts.buki, aflag[1], body.atk[1]);
		attacklearn(Texts.bougu, aflag[2], body.atk[2]);
		attacklearn(Texts.shokugyo, aflag[3], body.atk[3]);
		attacklearn(Texts.shokugyo, aflag[4], body.atk[4]);
		attacklearn(Texts.komono, aflag[5], body.atk[5]);
	}

	private void attacklearn(String s, boolean flag, int i) {
		if (!flag)
			return;
		AttackData attackdata = Statics.getAttackData(i);
		uw.setMPanel(s + Texts.equip1);
		if (attackdata.name.length() <= 5) {
			uw.setMPanel(attackdata.name + Texts.wo + Texts.equip2);
		} else {
			uw.setMPanel(attackdata.name + Texts.wo);
			uw.setMPanel(Texts.equip2);
		}
	}

	private void statusup(Body body) {
		int i = body.hpMax;
		int j = body.strMax / 10;
		int k = body.defMax / 10;
		int l = body.mstMax / 10;
		int i1 = body.mdfMax / 10;
		int j1 = body.hitMax / 10;
		int k1 = body.misMax / 10;
		Body body1 = searchItem(body.gx + 1, body.gy);
		Body body2 = searchItem(body.gx + 4, body.gy);
		if (body.level == 0) {
			body.hpMax += 10;
			body.strMax *= 2;
			body.defMax *= 2;
			body.mstMax *= 2;
			body.mdfMax *= 2;
			body.hitMax *= 2;
			body.misMax *= 2;
		} else if (body1 == null) {
			body.hpMax += Luck.rnd(2);
			body.strMax += Luck.rnd(12);
			body.defMax += Luck.rnd(12);
			body.mstMax += Luck.rnd(12);
			body.mdfMax += Luck.rnd(12);
			body.hitMax += Luck.rnd(12);
			body.misMax += Luck.rnd(12);
		} else if (body2 == null) {
			body.hpMax += (body1.hpMax + Luck.rnd(9)) / 10;
			body.strMax += body1.strMax / 10;
			body.defMax += body1.defMax / 10;
			body.mstMax += body1.mstMax / 10;
			body.mdfMax += body1.mdfMax / 10;
			body.hitMax += body1.hitMax / 10;
			body.misMax += body1.misMax / 10;
		} else {
			body.hpMax += (body1.hpMax + body2.hpMax + Luck.rnd(9)) / 10;
			body.strMax += (body1.strMax + body2.strMax) / 10;
			body.defMax += (body1.defMax + body2.defMax) / 10;
			body.mstMax += (body1.mstMax + body2.mstMax) / 10;
			body.mdfMax += (body1.mdfMax + body2.mdfMax) / 10;
			body.hitMax += (body1.hitMax + body2.hitMax) / 10;
			body.misMax += (body1.misMax + body2.misMax) / 10;
		}
		restrict(body);
		body.hp += statusup(Texts.hp, body.hpMax * 10, i);
		body.str += statusup(Texts.kougekiryoku, body.strMax, j);
		body.def += statusup(Texts.bougyoryoku, body.defMax, k);
		body.mst += statusup(Texts.mahouryoku, body.mstMax, l);
		body.mdf += statusup(Texts.teikouryoku, body.mdfMax, i1);
		body.hit += statusup(Texts.meichuritu, body.hitMax, j1);
		body.mis += statusup(Texts.kaihiritu, body.misMax, k1);
	}

	private int statusup(String s, int i, int j) {
		int k = i / 10 - j;
		if (k > 0)
			uw.setMPanel(s + Texts.ga + " " + k + Texts.equip3);
		return k;
	}

	public static void restrict(Body body) {
		body.hpMax = Math.min(999, body.hpMax);
		body.strMax = Math.min(9999, body.strMax);
		body.defMax = Math.min(9999, body.defMax);
		body.mstMax = Math.min(9999, body.mstMax);
		body.mdfMax = Math.min(9999, body.mdfMax);
		body.hitMax = Math.min(9999, body.hitMax);
		body.misMax = Math.min(9999, body.misMax);
	}

	public Vector getPlayers() {
		Vector vector = new Vector();
		for (int i = 4; i >= 0; i--) {
			Body body = search(1, 1 + i * 3);
			if (body != null) {
				body.color = Colors.getPC();
				body.setMax();
				equip(body);
				vector.add(body);
			}
		}

		return vector;
	}

	public Body getChangeChara(Body body) {
		Body body1 = null;
		for (Iterator iterator = Equips.iterator(); iterator.hasNext();) {
			Body body2 = (Body) iterator.next();
			if (Colors.isPlayer(body2) && body2.gx == body.gx + 7
					&& body2.gy == body.gy) {
				body1 = body2;
				break;
			}
		}

		if (body1 == null) {
			return null;
		} else {
			body1.setMax();
			equip(body1);
			return body1;
		}
	}

	public void equip(Body body) {
		body.setMax();
		body.newType();
		if (body.kind == Kinds.DOLL) {
			equipDoll(body);
			return;
		}
		Body body1 = searchItem(body.gx + 1, body.gy);
		Body body2 = searchItem(body.gx + 2, body.gy);
		Body body3 = searchItem(body.gx + 3, body.gy);
		Body body4 = searchItem(body.gx + 4, body.gy);
		if (body1 != null)
			body.mergeTypeState(body1.type);
		if (body2 != null)
			body.mergeTypeState(body2.type);
		if (body3 != null)
			body.mergeTypeState(body3.type);
		if (body4 != null)
			body.mergeTypeState(body4.type);
		equip(body, body2);
		equip(body, body3);
		equip(body, body4);
		getAttack(body);
		restrict(body);
	}

	public void equipDoll(Body body) {
		for (int i = 2; i < 12; i++) {
			Body body1 = searchItem(body.gx + i, body.gy);
			if (body1 != null) {
				equip(body, body1);
				body.mergeTypeState(body1.type);
			}
		}

		Body body2 = searchItem(body.gx + 2, body.gy);
		Body body3 = searchItem(body.gx + 5, body.gy);
		Body body4 = searchItem(body.gx + 6, body.gy);
		Body body5 = searchItem(body.gx + 8, body.gy);
		Body body6 = searchItem(body.gx + 9, body.gy);
		Body body7 = searchItem(body.gx + 10, body.gy);
		Body body8 = searchItem(body.gx + 11, body.gy);
		body.atk[0] = body2 == null ? 0 : body2.atk[0];
		body.atk[2] = body3 == null ? 0 : body3.atk[0];
		body.atk[5] = body4 == null ? 0 : body4.atk[0];
		body.atk[1] = 0;
		if (body5 == null)
			return;
		if (body6 == null)
			return;
		if (body7 == null)
			return;
		if (body8 == null) {
			return;
		} else {
			int j = body5.img % 3 + (body5.img / 75) * 3;
			int k = body6.img % 3 + (body6.img / 75) * 3;
			int l = body7.img % 3 + (body7.img / 75) * 3;
			int i1 = body8.img % 3 + (body8.img / 75) * 3;
			body.atk[1] = (j + k * 3 + l * 9 + i1 * 27)
					% Statics.getAttackDataSize();
			return;
		}
	}

	private void equip(Body body, Body body1) {
		if (body1 == null) {
			return;
		} else {
			body.str += body1.str;
			body.def += body1.def;
			body.mst += body1.mst;
			body.mdf += body1.mdf;
			body.hit += body1.hit;
			body.mis += body1.mis;
			return;
		}
	}

	public boolean[] getAttack(Body body) {
		boolean aflag[] = new boolean[6];
		Body body1 = searchItem(body.gx + 1, body.gy);
		Body body2 = searchItem(body.gx + 2, body.gy);
		Body body3 = searchItem(body.gx + 3, body.gy);
		Body body4 = searchItem(body.gx + 4, body.gy);
		if (body1 != null)
			body.atk[0] = body1.atk[0];
		if (body2 != null) {
			body.atk[0] = body2.atk[0];
			if (body.atk[1] != body2.atk[1])
				body.atk[1] = 0;
		} else {
			AttackData attackdata = Statics.getAttackData(body.atk[0]);
			body.atk[0] = attackdata.AttackN1;
			body.atk[1] = 0;
		}
		if (body3 != null) {
			if (body.atk[2] != body3.atk[0])
				body.atk[2] = 0;
		} else {
			body.atk[2] = 0;
		}
		if (body4 != null) {
			if (body.atk[5] != body4.atk[0])
				body.atk[5] = 0;
		} else {
			body.atk[5] = 0;
		}
		aflag[1] = getAttack(body, 1, body2, 1, 100);
		aflag[2] = getAttack(body, 2, body3, 0, 100);
		aflag[5] = getAttack(body, 5, body4, 0, 100);
		if (body1 != null && body1.atk[1] != 0 && body1.atk[1] != body.atk[3]
				&& body1.atk[1] != body.atk[4])
			if (body.atk[3] == 0)
				aflag[3] = getAttack(body, 3, body1, 1, 200);
			else if (body.atk[4] == 0) {
				aflag[4] = getAttack(body, 4, body1, 1, 200);
			} else {
				int i = body.atk[4];
				aflag[4] = getAttack(body, 4, body1, 1, 200);
				if (aflag[4])
					body.atk[3] = i;
			}
		return aflag;
	}

	private boolean getAttack(Body body, int i, Body body1, int j, int k) {
		if (body1 == null)
			return false;
		if (body.atk[i] == body1.atk[j])
			return false;
		if (body1.atk[j] != 0 && body1.exp >= k) {
			body.atk[i] = body1.atk[j];
			return true;
		} else {
			return false;
		}
	}

	public boolean have(Body body) {
		for (Iterator iterator = Equips.iterator(); iterator.hasNext();) {
			Body body1 = (Body) iterator.next();
			if (body.img == body1.img && body.name.equals(body1.name)
					&& !isDust(body1))
				return true;
		}

		return false;
	}

	private boolean isDust(Body body) {
		if (body.y != 14)
			return false;
		return body.x >= 14;
	}

	private List<Body> Equips;
	private UnitWorks uw;
}
