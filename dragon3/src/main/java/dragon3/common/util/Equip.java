package dragon3.common.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

import dragon3.common.Body;
import dragon3.common.constant.BodyAttribute;
import dragon3.common.constant.GameColors;

public class Equip {

	private List<Body> equipList;

	/*** Constructer **************************************/

	public Equip(List<Body> equipList) {
		this.equipList = equipList;
	}

	/*** Search ********************************************/

	public Body search(int x, int y) {
		for (Body b : equipList) {
			if (b != null && b.getX() == x && b.getY() == y) {
				return b;
			}
		}
		return null;
	}
	public Body searchItem(int x, int y) {
		for (Body b : equipList) {
			if (b.getColor() == GameColors.GREEN && b.getX() == x && b.getY() == y) {
				return b;
			}
		}
		return null;
	}

	public void addBody(Body b) {
		equipList.add(b);
	}
	public void removeBody(Body b) {
		equipList.remove(b);
	}
	public List<Body> getEquips() {
		return equipList;
	}

	/*** EXP ****************************************/

	public void getEXP(Body ba, Body bb) {

		int exp = bb.getExp();
		if (ba.getLevel() == 0) {
		} else if (bb.getLevel() == 0) {
			exp *= 1.5;
		} else if (ba.getLevel() > bb.getLevel()) {
			exp = exp / Math.min(ba.getLevel() - bb.getLevel() + 1, 4);
		} else {
			exp = exp + exp * (bb.getLevel() - ba.getLevel()) / 2;
		}
		ba.setExp(ba.getExp() + exp);
		bb.setExp(0);

		// Item Exp

		for (int i = 1; i <= 4; i++) {
			Body item = searchItem(ba.getGoalX() + i, ba.getGoalY());
			if (item != null)
			item.setExp(item.getExp());
		}
	}









	public static void restrict(Body b) {
		b.setHpMax(Math.min(999, b.getHpMax()));
		b.setStr(Math.min(999, b.getStr()));
		b.setDef(Math.min(999, b.getDef()));
		b.setMst(Math.min(999, b.getMst()));
		b.setMdf(Math.min(999, b.getMdf()));
		b.setHit(Math.min(999, b.getHit()));
		b.setMis(Math.min(999, b.getMis()));
	}

	/*** Player ************************************/

	public Vector<Body> getPlayers() {
		Vector<Body> playerList = new Vector<Body>();
		for (int i = 4; i >= 0; i--) {
			Body b = search(1, 1 + i * 3);
			if (b == null)
				continue;
			b.setColor(GameColors.BLUE);
			b.setMax();
			equip(b);
			playerList.add(b);
		}
		return playerList;
	}

	public Body getChangeChara(Body ba) {
		Body bb = null;
		for (Body b : equipList) {
			if (!GameColors.isPlayer(b))
				continue;
			if (b.getGoalX() == ba.getGoalX() + 7 && b.getGoalY() == ba.getGoalY()) {
				bb = b;
				break;
			}
		}
		if (bb == null)
			return null;
		bb.setMax();
		equip(bb);
		return bb;
	}

	/*** Equip **********************************************/

	public void equip(Body ba) {
		ba.setMax();
		ba.setAttrSet(new LinkedHashSet<BodyAttribute>());
		Body wepon = searchItem(ba.getGoalX() + 2, ba.getGoalY());
		Body armor = searchItem(ba.getGoalX() + 3, ba.getGoalY());
		Body item = searchItem(ba.getGoalX() + 4, ba.getGoalY());

		equip(ba, wepon);
		equip(ba, armor);
		equip(ba, item);
		getAttack(ba);
		restrict(ba);
	}


	private void equip(Body ba, Body bb) {
		if (bb == null)
			return;
		ba.setStr(ba.getStr() + bb.getStr());
		ba.setDef(ba.getStr() + bb.getDef());
		ba.setMst(ba.getStr() + bb.getMst());
		ba.setMdf(ba.getStr() + bb.getMdf());
		ba.setHit(ba.getStr() + bb.getHit());
		ba.setMis(ba.getStr() + bb.getMis());
	}

	/***  *****************************************/

	// 0 - Normal Attack
	// 1 - Wepon Attack
	// 2 - Armor Attack
	// 3 - Class Attack 1
	// 4 - Class Attack 2
	// 5 - Item Attack 1

	public boolean[] getAttack(Body ba) {
		boolean flag[] = new boolean[6];

		return flag;
	}



	/*** Have *************************************/

	public boolean have(Body ba) {
		for (Body b : equipList) {
			if (!ba.getId().equals(b.getId())) {
				continue;
			} else if (isDust(b)) {
				continue;
			} else {
				return true;
			}
		}
		return false;
	}

	/*** Dust ***********************************/

	private boolean isDust(Body b) {
		if (b.getY() != 14)
			return false;
		if (b.getX() < 14)
			return false;
		return true;
	}

}
